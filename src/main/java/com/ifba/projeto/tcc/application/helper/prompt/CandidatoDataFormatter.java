package com.ifba.projeto.tcc.application.helper.prompt;

import com.ifba.projeto.tcc.domain.entity.Candidato;
import com.ifba.projeto.tcc.domain.entity.Experiencia;
import com.ifba.projeto.tcc.domain.entity.Habilidade;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class CandidatoDataFormatter {
    
    public static String formatar(Candidato candidato) {
        StringBuilder sb = new StringBuilder();
        
        adicionarInformacoesBasicas(sb, candidato);
        adicionarTempoExperiencia(sb, candidato);
        adicionarHabilidades(sb, candidato);
        adicionarExperiencias(sb, candidato);
        
        return sb.toString();
    }
    
    private static void adicionarInformacoesBasicas(StringBuilder sb, Candidato candidato) {
        sb.append("Nome: ").append(candidato.getNome()).append("\n");
        sb.append("Email: ").append(candidato.getEmail()).append("\n");
        if (candidato.getTelefone() != null) {
            sb.append("Telefone: ").append(candidato.getTelefone()).append("\n");
        }
        if (candidato.getLinkedin() != null) {
            sb.append("LinkedIn: ").append(candidato.getLinkedin()).append("\n");
        }
    }
    
    private static void adicionarTempoExperiencia(StringBuilder sb, Candidato candidato) {
        long totalAnosExperiencia = calcularAnosExperiencia(candidato);
        sb.append("\nTempo Total de Experiência: ").append(totalAnosExperiencia).append(" anos\n");
    }
    
    private static void adicionarHabilidades(StringBuilder sb, Candidato candidato) {
        if (candidato.getHabilidades() != null && !candidato.getHabilidades().isEmpty()) {
            sb.append("\nHABILIDADES DO CANDIDATO:\n");
            candidato.getHabilidades().forEach(habilidade -> {
                formatarHabilidade(sb, habilidade);
            });
        } else {
            sb.append("\nHABILIDADES: Nenhuma habilidade registrada\n");
        }
    }
    
    private static void formatarHabilidade(StringBuilder sb, Habilidade habilidade) {
        sb.append("  - ").append(habilidade.getNome())
          .append(" (").append(habilidade.getTipo() != null ? habilidade.getTipo().name() : "N/A").append(")");
        if (habilidade.getDescricao() != null && !habilidade.getDescricao().isEmpty()) {
            sb.append(": ").append(habilidade.getDescricao());
        }
        sb.append("\n");
    }
    
    private static void adicionarExperiencias(StringBuilder sb, Candidato candidato) {
        if (candidato.getExperiencias() != null && !candidato.getExperiencias().isEmpty()) {
            sb.append("\nEXPERIÊNCIAS PROFISSIONAIS:\n");
            candidato.getExperiencias().forEach(experiencia -> {
                formatarExperiencia(sb, experiencia);
            });
        } else {
            sb.append("\nEXPERIÊNCIAS: Nenhuma experiência profissional registrada\n");
        }
    }
    
    private static void formatarExperiencia(StringBuilder sb, Experiencia experiencia) {
        sb.append("  Empresa: ").append(experiencia.getEmpresa()).append("\n");
        sb.append("  Cargo: ").append(experiencia.getCargo()).append("\n");
        
        if (experiencia.getDescricao() != null && !experiencia.getDescricao().isEmpty()) {
            sb.append("  Descrição: ").append(experiencia.getDescricao()).append("\n");
        }
        
        if (experiencia.getDataInicio() != null) {
            formatarPeriodo(sb, experiencia);
        }
        sb.append("\n");
    }
    
    private static void formatarPeriodo(StringBuilder sb, Experiencia experiencia) {
        sb.append("  Período: ").append(experiencia.getDataInicio());
        
        if (experiencia.getDataFim() != null) {
            long meses = ChronoUnit.MONTHS.between(experiencia.getDataInicio(), experiencia.getDataFim());
            sb.append(" até ").append(experiencia.getDataFim())
              .append(" (").append(meses).append(" meses)");
        } else {
            long meses = ChronoUnit.MONTHS.between(experiencia.getDataInicio(), LocalDate.now());
            sb.append(" até o momento (atual - ").append(meses).append(" meses)");
        }
        sb.append("\n");
    }
    
    public static long calcularAnosExperiencia(Candidato candidato) {
        if (candidato.getExperiencias() == null || candidato.getExperiencias().isEmpty()) {
            return 0;
        }
        
        long totalMeses = 0;
        for (Experiencia exp : candidato.getExperiencias()) {
            if (exp.getDataInicio() != null) {
                LocalDate dataFim = exp.getDataFim() != null ? exp.getDataFim() : LocalDate.now();
                long meses = ChronoUnit.MONTHS.between(exp.getDataInicio(), dataFim);
                totalMeses += meses;
            }
        }
        
        return totalMeses / 12;
    }
}

