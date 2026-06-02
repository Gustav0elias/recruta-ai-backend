# Prompts enviados ao ChatGPT

Este documento reúne os prompts usados pela aplicação nas chamadas para a API da OpenAI/ChatGPT.
Os textos abaixo foram extraídos do código-fonte e organizados para publicação no GitHub.

## Chamada padrão para a OpenAI

Arquivo de origem: `src/main/java/com/ifba/projeto/tcc/Infra/api/impl/OpenAiClient.java`

Toda chamada enviada ao ChatGPT usa a mesma estrutura de mensagens:

```json
{
  "model": "${openai.model}",
  "messages": [
    {
      "role": "system",
      "content": "Você é um assistente que extrai dados de currículos e responde **somente** em JSON válido."
    },
    {
      "role": "user",
      "content": "{{prompt_montado_pela_aplicacao}}"
    }
  ],
  "temperature": 0.2
}
```

Observações:

- O modelo é configurado em `openai.model`.
- A URL é configurada em `openai.api.url`.
- A chave da API não deve ser publicada no GitHub.
- O conteúdo de `user` é gerado pelos builders descritos abaixo.

## Prompt 1: Extração de dados do currículo

Arquivo de origem: `src/main/java/com/ifba/projeto/tcc/application/helper/CurriculoPromptBuilder.java`

Usado para transformar o texto extraído de um currículo em um JSON estruturado.

```text
Extraia do texto do currículo abaixo e devolva **somente** um JSON válido.
**Não inclua explicações, markdown ou outro texto.**

**Atenção:** 
- Todos os campos string devem ter **aspas duplas escapadas** e **quebras de linha removidas**.
- Respeite os **tamanhos máximos** de cada campo:
  - nome: até 50 caracteres
  - email: até 50 caracteres
  - telefone: até 12 caracteres
  - habilidades.nome: até 50 caracteres
  - habilidades.tipo: apenas valores válidos (HABILIDADE_TECNICA, SOFT_SKILL)
  - experiencias.empresa: até 50 caracteres
  - experiencias.cargo: até 50 caracteres
  - experiencias.descricao: até 100 caracteres
  - datas: formato AAAA-MM-DD (se o profissional estiver atualmente no emprego, dataFim = null)

**Importante:** 
- Cada habilidade deve ser um **objeto separado** no array `habilidades`.
- Não agrupe várias habilidades em uma única linha ou objeto.
- Se o currículo listar várias habilidades, gere uma entrada para cada uma.
- Retorne **apenas JSON válido**, sem explicações ou texto extra.

**JSON esperado:**
{
  "nome": "",
  "email": "",
  "telefone": "",
  "habilidades": [
    {
      "nome": "",
      "tipo": ""
    },
    {
      "nome": "",
      "tipo": ""
    }
  ],
  "experiencias": [
    {
      "empresa": "",
      "cargo": "",
      "descricao": "",
      "dataInicio": "",
      "dataFim": ""
    }
  ]
}

Texto do currículo:
{{conteudo_curriculo}}
```

Placeholder:

- `{{conteudo_curriculo}}`: texto extraído do arquivo PDF do currículo.

## Prompt 2: Cálculo de score candidato x vaga

Arquivos de origem:

- `src/main/java/com/ifba/projeto/tcc/application/helper/ScorePromptBuilder.java`
- `src/main/java/com/ifba/projeto/tcc/application/helper/prompt/ScorePromptHeader.java`
- `src/main/java/com/ifba/projeto/tcc/application/helper/prompt/CandidatoDataFormatter.java`
- `src/main/java/com/ifba/projeto/tcc/application/helper/prompt/VagaDataFormatter.java`
- `src/main/java/com/ifba/projeto/tcc/application/helper/prompt/ScoreInstrucoesPrompt.java`
- `src/main/java/com/ifba/projeto/tcc/application/helper/prompt/ScoreResponseFormat.java`

Usado para avaliar a compatibilidade entre um candidato e uma vaga, retornando score e análises em JSON.

```text
Você é um tech Recruiter profissional e rigoroso.
Avalie a compatibilidade entre o candidato e a vaga, calculando um score de 0 a 100.

**CRÍTICO:** A comparação entre a experiência do candidato e o nível exigido pela vaga é o fator mais importante na avaliação.

DADOS DO CANDIDATO
{{dados_candidato}}

DADOS DA VAGA
{{dados_vaga}}

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

FORMATO DA RESPOSTA

Retorne **SOMENTE** um JSON válido, sem markdown, sem explicações adicionais.
O JSON deve começar com { e terminar com }.

**JSON esperado:**
{
  "score": <número inteiro de 0 a 100>,
  "analiseEspecifica": "<análise detalhada de MÍNIMO 2500 caracteres comparando o candidato especificamente com os requisitos da vaga.
   Deve incluir: comparação habilidade por habilidade, análise de experiência, pontos fortes, pontos fracos, correspondência com o nível exigido,
    e justificativa detalhada do score atribuído>",
  "analiseGeral": "<análise geral detalhada de MÍNIMO 2500 caracteres sobre o currículo do candidato. Deve incluir: visão geral do perfil profissional,
   trajetória de carreira, competências técnicas e comportamentais, potencial de crescimento, e avaliação global do candidato>"
}

As análises devem ter MÍNIMO 2500 caracteres cada uma.
Seja detalhado, específico e justifique claramente o score atribuído.
```

### Formato de `{{dados_candidato}}`

Gerado dinamicamente a partir da entidade `Candidato`.

```text
Nome: {{nome_candidato}}
Email: {{email_candidato}}
Telefone: {{telefone_candidato}}
LinkedIn: {{linkedin_candidato}}

Tempo Total de Experiência: {{total_anos_experiencia}} anos

HABILIDADES DO CANDIDATO:
  - {{habilidade_nome}} ({{habilidade_tipo}}): {{habilidade_descricao}}

EXPERIÊNCIAS PROFISSIONAIS:
  Empresa: {{empresa}}
  Cargo: {{cargo}}
  Descrição: {{descricao_experiencia}}
  Período: {{data_inicio}} até {{data_fim}} ({{quantidade_meses}} meses)
```

Variações possíveis:

- Se não houver telefone, o campo `Telefone` não é incluído.
- Se não houver LinkedIn, o campo `LinkedIn` não é incluído.
- Se não houver habilidades, é enviado: `HABILIDADES: Nenhuma habilidade registrada`.
- Se não houver experiências, é enviado: `EXPERIÊNCIAS: Nenhuma experiência profissional registrada`.
- Se a experiência atual não tiver `dataFim`, é enviado: `até o momento (atual - {{quantidade_meses}} meses)`.

### Formato de `{{dados_vaga}}`

Gerado dinamicamente a partir da entidade `Vaga`.

```text
Título: {{titulo_vaga}}

*** NÍVEL DE EXPERIÊNCIA EXIGIDO PELA VAGA (CRÍTICO PARA AVALIAÇÃO): ***
{{nivel_experiencia_formatado}}

Descrição da Vaga: {{descricao_vaga}}

HABILIDADES EXIGIDAS PELA VAGA (com peso de importância):

  ESSENCIAIS (Peso 8-10 - CRÍTICAS):
    - {{habilidade_nome}} [PESO: {{peso}}/10] - {{habilidade_descricao}}

  IMPORTANTES (Peso 5-7):
    - {{habilidade_nome}} [PESO: {{peso}}/10] - {{habilidade_descricao}}

  DESEJÁVEIS (Peso 1-4):
    - {{habilidade_nome}} [PESO: {{peso}}/10] - {{habilidade_descricao}}
```

Mapeamento do nível de experiência:

- `JUNIOR`: `JÚNIOR - Aceita candidatos com 0-2 anos de experiência`
- `PLENO`: `PLENO - Exige MÍNIMO 2-3 anos de experiência comprovada`
- `SENIOR`: `SÊNIOR - Exige MÍNIMO 5 anos de experiência comprovada`
- Nível não informado: `Nível não especificado`

Variação possível:

- Se a vaga não possuir habilidades específicas, é enviado: `HABILIDADES: Nenhuma habilidade específica exigida`.

## Observação para publicação no GitHub

Este arquivo documenta somente os prompts. Antes de publicar o repositório, remova qualquer chave real de API ou credencial dos arquivos versionados e gere uma nova chave caso alguma já tenha sido exposta.
