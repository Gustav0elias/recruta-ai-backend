package com.ifba.projeto.tcc.application.helper.prompt;

public class ScoreEvaluationInstructions {
    
    public static String construir() {
        return """
            ============================================
            INSTRUÇÕES PARA AVALIAÇÃO
            ============================================
            
            Avalie o candidato considerando os seguintes fatores, em ordem de prioridade:
            
            1. **NÍVEL DE EXPERIÊNCIA (FATOR MAIS IMPORTANTE):**
               Analise o tempo total de experiência do candidato e compare com o nível exigido pela vaga.
               Use seu conhecimento sobre níveis de experiência (Júnior, Pleno, Sênior) para avaliar a adequação.
               Candidatos com experiência insuficiente para o nível exigido devem receber penalização severa.
               Candidatos com experiência muito abaixo do exigido não devem ter score alto, mesmo com todas as habilidades.
            
            2. **HABILIDADES TÉCNICAS:**
               Compare cada habilidade exigida (com seus pesos) com as habilidades do candidato.
               Peso 8-10 (essenciais): impacto alto na ausência.
               Peso 5-7 (importantes): impacto médio.
               Peso 1-4 (desejáveis): impacto baixo.
               Priorize habilidades comprovadas por experiência profissional sobre apenas listadas.
            
            3. **RELEVÂNCIA DAS EXPERIÊNCIAS:**
               Avalie se as experiências são relevantes para a vaga.
               Considere progressão de carreira e uso prático das habilidades exigidas.
            
            **CÁLCULO DO SCORE (0-100):**
            - Comece pela adequação de nível (fator predominante).
            - Ajuste baseado em habilidades e relevância das experiências.
            - Use toda a escala 0-100, variando entre candidatos diferentes.
            - Seja consistente: candidatos similares devem ter scores similares.
            - Experiência prática é mais valiosa que habilidades apenas listadas.
            """;
    }
}

