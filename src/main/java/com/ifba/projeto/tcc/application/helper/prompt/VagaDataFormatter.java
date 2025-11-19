package com.ifba.projeto.tcc.application.helper.prompt;

import com.ifba.projeto.tcc.domain.entity.Habilidade;
import com.ifba.projeto.tcc.domain.entity.Vaga;
import com.ifba.projeto.tcc.domain.entity.VagaHabilidade;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class VagaDataFormatter {
    
    public static String formatar(Vaga vaga) {
        StringBuilder sb = new StringBuilder();
        
        adicionarInformacoesBasicas(sb, vaga);
        adicionarHabilidadesExigidas(sb, vaga);
        
        return sb.toString();
    }
    
    private static void adicionarInformacoesBasicas(StringBuilder sb, Vaga vaga) {
        sb.append("Título: ").append(vaga.getTitulo()).append("\n");
        sb.append("\n*** NÍVEL DE EXPERIÊNCIA EXIGIDO PELA VAGA (CRÍTICO PARA AVALIAÇÃO): ***\n");
        String nivelExigido = formatarNivelExigido(vaga.getNivelExperiencia());
        sb.append(nivelExigido).append("\n");
        sb.append("\nDescrição da Vaga: ").append(vaga.getDescricao()).append("\n");
    }
    
    private static String formatarNivelExigido(com.ifba.projeto.tcc.domain.entity.NivelExperiencia nivel) {
        if (nivel == null) {
            return "Nível não especificado";
        }
        return switch (nivel) {
            case JUNIOR -> "JÚNIOR - Aceita candidatos com 0-2 anos de experiência";
            case PLENO -> "PLENO - Exige MÍNIMO 2-3 anos de experiência comprovada";
            case SENIOR -> "SÊNIOR - Exige MÍNIMO 5 anos de experiência comprovada";
        };
    }
    
    private static void adicionarHabilidadesExigidas(StringBuilder sb, Vaga vaga) {
        if (vaga.getVagaHabilidades() == null || vaga.getVagaHabilidades().isEmpty()) {
            sb.append("\nHABILIDADES: Nenhuma habilidade específica exigida\n");
            return;
        }
        
        sb.append("\nHABILIDADES EXIGIDAS PELA VAGA (com peso de importância):\n");
        
        List<VagaHabilidade> habilidadesEssenciais = filtrarPorPeso(vaga, 8, 10);
        List<VagaHabilidade> habilidadesImportantes = filtrarPorPeso(vaga, 5, 7);
        List<VagaHabilidade> habilidadesDesejaveis = filtrarPorPeso(vaga, 1, 4);
        
        adicionarCategoriaHabilidades(sb, "ESSENCIAIS (Peso 8-10 - CRÍTICAS)", habilidadesEssenciais);
        adicionarCategoriaHabilidades(sb, "IMPORTANTES (Peso 5-7)", habilidadesImportantes);
        adicionarCategoriaHabilidades(sb, "DESEJÁVEIS (Peso 1-4)", habilidadesDesejaveis);
    }
    
    private static List<VagaHabilidade> filtrarPorPeso(Vaga vaga, int pesoMinimo, int pesoMaximo) {
        return vaga.getVagaHabilidades().stream()
                .filter(vh -> vh.getPeso() >= pesoMinimo && vh.getPeso() <= pesoMaximo)
                .sorted(Comparator.comparing(VagaHabilidade::getPeso).reversed())
                .collect(Collectors.toList());
    }
    
    private static void adicionarCategoriaHabilidades(StringBuilder sb, String categoria, List<VagaHabilidade> habilidades) {
        if (habilidades.isEmpty()) {
            return;
        }
        
        sb.append("\n  ").append(categoria).append(":\n");
        habilidades.forEach(vh -> formatarHabilidadeVaga(sb, vh));
    }
    
    private static void formatarHabilidadeVaga(StringBuilder sb, VagaHabilidade vagaHabilidade) {
        Habilidade habilidade = vagaHabilidade.getHabilidade();
        sb.append("    - ").append(habilidade.getNome())
          .append(" [PESO: ").append(vagaHabilidade.getPeso()).append("/10]");
        
        if (habilidade.getDescricao() != null && !habilidade.getDescricao().isEmpty()) {
            sb.append(" - ").append(habilidade.getDescricao());
        }
        sb.append("\n");
    }
}

