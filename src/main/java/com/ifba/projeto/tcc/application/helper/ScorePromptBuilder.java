package com.ifba.projeto.tcc.application.helper;

import com.ifba.projeto.tcc.application.helper.prompt.*;
import com.ifba.projeto.tcc.domain.entity.Candidato;
import com.ifba.projeto.tcc.domain.entity.Vaga;

public class ScorePromptBuilder {
    
    public static String construirPromptScore(Candidato candidato, Vaga vaga) {
        String header = ScorePromptHeader.construir();
        String dadosCandidato = CandidatoDataFormatter.formatar(candidato);
        String dadosVaga = VagaDataFormatter.formatar(vaga);
        String instrucoes = ScoreInstrucoesPrompt.construir();
        String formatoResposta = ScoreResponseFormat.construir();
        
        return String.format("""
            %s
            DADOS DO CANDIDATO
            %s
            DADOS DA VAGA
            %s
            
            %s
            
            %s
            """, header, dadosCandidato, dadosVaga, instrucoes, formatoResposta);
    }
}