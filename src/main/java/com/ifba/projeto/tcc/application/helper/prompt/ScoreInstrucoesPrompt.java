package com.ifba.projeto.tcc.application.helper.prompt;

public class ScoreInstrucoesPrompt {
    
    public static String construir() {
          return """
                ============================================
                INSTRUÇÕES PARA AVALIAÇÃO
                ============================================
            
                Leia e interprete com atenção a descrição da vaga e as habilidades exigidas. O contexto da vaga é fundamental para a análise!
            
                1. **NÍVEL DE EXPERIÊNCIA (FATOR MAIS IMPORTANTE, MAS SENSÍVEL AO CONTEXTO):**
                    - Se a vaga for de estágio, trainee ou júnior, NÃO exija experiência prévia. Não penalize candidatos por falta de experiência nesse caso.
                    - Para vagas pleno/sênior, avalie o tempo total de experiência do candidato e compare com o nível exigido.
                    - Só penalize fortemente se a vaga realmente exigir experiência e o candidato não tiver.
                    - Use seu conhecimento sobre níveis de experiência (Júnior, Pleno, Sênior) para avaliar a adequação.
            
                2. **HABILIDADES TÉCNICAS:**
                    - Compare cada habilidade exigida (com seus pesos) com as habilidades do candidato.
                    - Priorize habilidades comprovadas por experiência profissional sobre apenas listadas.
                    - Dê mais peso para habilidades marcadas como essenciais na vaga.
            
                3. **RELEVÂNCIA DAS EXPERIÊNCIAS:**
                    - Avalie se as experiências são relevantes para a vaga e para o contexto descrito.
                    - Considere progressão de carreira e uso prático das habilidades exigidas.
            
                **CÁLCULO DO SCORE (0-100):**
                - Comece pela adequação ao contexto da vaga (nível, perfil, habilidades e descrição).
                - Ajuste baseado em habilidades e relevância das experiências.
                - Use toda a escala 0-100, variando entre candidatos diferentes.
                - Penalize fortemente apenas quando a vaga exigir e o candidato não atender.
                - NÃO penalize candidatos de estágio/trainee/júnior por falta de experiência.
                - Seja consistente: candidatos similares devem ter scores similares.
                - Experiência prática é mais valiosa que habilidades apenas listadas.
                - NÃO atribua notas medianas (ex: 60-70) para candidatos que não atendem bem aos requisitos.
                - Exemplo de score:
                     - Score 90-100: candidato perfeitamente aderente ao contexto da vaga, experiência e habilidades acima do esperado.
                     - Score 70-89: candidato atende bem, mas com pequenas lacunas.
                     - Score 40-69: candidato com várias deficiências relevantes.
                     - Score 0-39: candidato muito abaixo do perfil da vaga.
                """;
    }
}

