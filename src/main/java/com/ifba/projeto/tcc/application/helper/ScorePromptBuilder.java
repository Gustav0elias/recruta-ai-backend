package com.ifba.projeto.tcc.application.helper;

import com.ifba.projeto.tcc.application.dto.response.CurriculoExtraidoResponseDTO;
import com.ifba.projeto.tcc.domain.entity.Candidato;
import com.ifba.projeto.tcc.domain.entity.Vaga;

public class ScorePromptBuilder {
    public static String construirPromptScore(Candidato candidato, Vaga vaga){
        return """
        Score: Analise o candidato com base nos requisitos da vaga e gere um score de 0 a 100, 
        onde 100 significa que o candidato atende perfeitamente a todos os requisitos.
        
        analiseGeral: descreva o candidato no geral, analisando os pontos fortes e fracos do candidato, experiência, habilidades (max 220 caracteres).
        analiseEspecifica:avalie o currículo em relação aos requisitos, esse campo é basicamente a justificativa do score (max 220 caracteres).
                        
        **Retorne somente JSON válido, sem explicações ou markdown.**
        Formato esperado:
        {
            "score": 0-100,
            "analiseEspecifica": "",
            "analiseGeral":""
        }

        Vaga:
        %s

        Candidato:
        %s
        """.formatted(vaga.toString(), candidato.toString());
    }
}
