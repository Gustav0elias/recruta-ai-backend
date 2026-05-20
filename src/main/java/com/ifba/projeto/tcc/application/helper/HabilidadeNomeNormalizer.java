package com.ifba.projeto.tcc.application.helper;

import org.springframework.stereotype.Component;

import java.text.Normalizer;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@Component
public class HabilidadeNomeNormalizer {

    public String canonicalizar(String nome) {
        String base = sanitizeBase(nome);
        if (base.isEmpty()) {
            return base;
        }
        return toTitleCase(base);
    }

    public String chave(String nome) {
        return chaveInterna(nome);
    }

    public boolean saoEquivalentes(String nomeA, String nomeB) {
        String chaveA = chaveInterna(nomeA);
        String chaveB = chaveInterna(nomeB);

        if (chaveA.isEmpty() || chaveB.isEmpty()) {
            return false;
        }

        if (chaveA.equals(chaveB)) {
            return true;
        }

        String siglaA = sigla(nomeA);
        String siglaB = sigla(nomeB);
        if (!siglaA.isEmpty() && siglaA.equals(siglaB)) {
            return true;
        }

        String menor = chaveA.length() <= chaveB.length() ? chaveA : chaveB;
        String maior = chaveA.length() <= chaveB.length() ? chaveB : chaveA;
        if (abreviacaoCompativel(menor, maior)) {
            return true;
        }

        if (chaveA.length() >= 4 && chaveB.length() >= 4) {
            double similaridade = similaridadeLevenshtein(chaveA, chaveB);
            if (similaridade >= 0.90d) {
                return true;
            }
        }

        return false;
    }

    private static String sanitizeBase(String texto) {
        if (texto == null) {
            return "";
        }
        return texto.trim().replaceAll("\\s+", " ");
    }

    private static String toTitleCase(String texto) {
        String[] partes = texto.toLowerCase(Locale.ROOT).split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < partes.length; i++) {
            if (partes[i].isEmpty()) {
                continue;
            }
            if (i > 0) {
                sb.append(' ');
            }
            sb.append(Character.toUpperCase(partes[i].charAt(0)))
                    .append(partes[i].substring(1));
        }
        return sb.toString();
    }

    private static String chaveInterna(String nome) {
        String base = sanitizeBase(nome);
        if (base.isEmpty()) {
            return base;
        }

        String semAcento = Normalizer.normalize(base, Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "");

        return semAcento
                .toLowerCase(Locale.ROOT)
                .replaceAll("[^a-z0-9]+", "");
    }

    private static String sigla(String nome) {
        String base = sanitizeBase(nome);
        if (base.isEmpty()) {
            return "";
        }

        String comSeparacaoCamelCase = base.replaceAll("(?<=[a-z])(?=[A-Z])", " ");
        String limpo = Normalizer.normalize(comSeparacaoCamelCase, Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "")
                .replaceAll("[^A-Za-z0-9 ]+", " ")
                .toLowerCase(Locale.ROOT)
                .replaceAll("\\s+", " ")
                .trim();

        if (limpo.isEmpty()) {
            return "";
        }

        List<String> tokens = Arrays.stream(limpo.split(" "))
                .filter(token -> !token.isBlank())
                .toList();

        if (tokens.size() == 1) {
            String token = tokens.get(0);
            if (token.length() <= 4) {
                return token;
            }
            return "" + token.charAt(0) + token.charAt(Math.min(1, token.length() - 1));
        }

        StringBuilder sb = new StringBuilder();
        for (String token : tokens) {
            sb.append(token.charAt(0));
        }
        return sb.toString();
    }

    private static double similaridadeLevenshtein(String a, String b) {
        int distancia = distanciaLevenshtein(a, b);
        int max = Math.max(a.length(), b.length());
        if (max == 0) {
            return 1.0d;
        }
        return 1.0d - ((double) distancia / max);
    }

    private static boolean abreviacaoCompativel(String curto, String longo) {
        if (curto.length() < 2 || curto.length() > 4 || longo.length() < 6) {
            return false;
        }

        int[] posicoes = new int[curto.length()];
        int idxLongo = 0;
        for (int i = 0; i < curto.length(); i++) {
            char alvo = curto.charAt(i);
            boolean encontrou = false;
            while (idxLongo < longo.length()) {
                if (longo.charAt(idxLongo) == alvo) {
                    posicoes[i] = idxLongo;
                    idxLongo++;
                    encontrou = true;
                    break;
                }
                idxLongo++;
            }
            if (!encontrou) {
                return false;
            }
        }

        if (posicoes[0] != 0) {
            return false;
        }

        return posicoes[posicoes.length - 1] >= 2;
    }

    private static int distanciaLevenshtein(String a, String b) {
        int[] custos = new int[b.length() + 1];
        for (int j = 0; j <= b.length(); j++) {
            custos[j] = j;
        }

        for (int i = 1; i <= a.length(); i++) {
            int diagonalAnterior = custos[0];
            custos[0] = i;
            for (int j = 1; j <= b.length(); j++) {
                int antigoCustoJ = custos[j];
                int custoSubstituicao = a.charAt(i - 1) == b.charAt(j - 1) ? 0 : 1;
                custos[j] = Math.min(
                        Math.min(custos[j] + 1, custos[j - 1] + 1),
                        diagonalAnterior + custoSubstituicao
                );
                diagonalAnterior = antigoCustoJ;
            }
        }
        return custos[b.length()];
    }
}
