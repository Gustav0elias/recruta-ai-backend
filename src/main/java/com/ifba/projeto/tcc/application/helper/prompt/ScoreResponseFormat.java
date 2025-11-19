package com.ifba.projeto.tcc.application.helper.prompt;

public class ScoreResponseFormat {
    
    public static String construir() {
        return """
            ============================================
            FORMATO DA RESPOSTA
            ============================================
            
            Retorne **SOMENTE** um JSON válido, sem markdown, sem explicações adicionais.
            O JSON deve começar com { e terminar com }.
            
            **JSON esperado:**
            {
              "score": <número inteiro de 0 a 100>,
              "analiseEspecifica": "<análise detalhada de MÍNIMO 2500 caracteres comparando o candidato especificamente com os requisitos da vaga. Deve incluir: comparação habilidade por habilidade, análise de experiência, pontos fortes, pontos fracos, correspondência com o nível exigido, e justificativa detalhada do score atribuído>",
              "analiseGeral": "<análise geral detalhada de MÍNIMO 2500 caracteres sobre o currículo do candidato. Deve incluir: visão geral do perfil profissional, trajetória de carreira, competências técnicas e comportamentais, potencial de crescimento, e avaliação global do candidato>"
            }
            
            As análises devem ter MÍNIMO 2500 caracteres cada uma.
            Seja detalhado, específico e justifique claramente o score atribuído.
            """;
    }
}

