# Validação do sistema

Esta pasta reúne os artefatos utilizados na validação do sistema de triagem de currículos.

## Estrutura

- `curriculos/`: 42 currículos fictícios em PDF, gerados para testes.
- `catalogo_curriculos.csv`: catálogo dos currículos, com candidato, foco, aderência esperada, modelo e empresa principal.
- `vagas_validacao.csv`: dados das duas vagas utilizadas na validação.
- `pesos_habilidades_vagas.csv`: pesos atribuídos às habilidades técnicas de cada vaga.
- `distribuicao_curriculos_vagas.csv`: distribuição dos currículos entre as vagas.
- `resultados_validacao.csv`: planilha com os scores, classificações e justificativas resumidas geradas para a validação documental.
- `analises_scores_validacao.csv`: planilha com análises detalhadas no formato conceitual solicitado pelo prompt de score, incluindo análise específica e análise geral.

## Vagas utilizadas

Foram utilizadas duas vagas fictícias para fins de teste:

1. Desenvolvedor Backend Java Junior.
2. Analista de Suporte.

As duas vagas foram cadastradas com nível `JUNIOR` e descrições voltadas a requisitos técnicos específicos. A vaga de backend prioriza Java, Spring Boot, APIs REST, banco de dados relacional, Docker, testes automatizados e Git. A vaga de suporte prioriza atendimento ao usuário, Windows, Active Directory, redes, ITIL e ferramentas de gestão de chamados.

## Observação sobre os currículos

Os currículos são fictícios, mas foram construídos com nomes, experiências, ferramentas, empresas, cursos e tecnologias realistas para tornar os testes mais próximos de um cenário de uso real. Eles foram distribuídos entre perfis de alta, média e baixa aderência esperada.

Os currículos de estágio foram mantidos como casos de controle negativo, pois ajudam a observar se o sistema evita pontuações altas para candidatos com perfil distante dos requisitos das vagas.

## Critério de score

Os scores foram gerados seguindo os critérios descritos no prompt de cálculo de score do sistema. A avaliação considera nível de experiência da vaga, habilidades técnicas exigidas, pesos das habilidades, relevância das experiências e uso prático das competências. Como as duas vagas são de nível júnior, a ausência de longa experiência prévia não foi tratada como penalização automática. A penalização foi aplicada principalmente quando o currículo não demonstrava as habilidades essenciais ou quando o perfil estava distante do contexto da vaga.

As faixas utilizadas foram:

- 90 a 100: candidato altamente aderente ao contexto da vaga.
- 70 a 89: candidato atende bem, mas possui pequenas lacunas.
- 40 a 69: candidato com aderência parcial e deficiências relevantes.
- 0 a 39: candidato muito abaixo do perfil da vaga.

## Texto sugerido para o TCC

A validação do sistema foi realizada com duas vagas fictícias para fins de teste: Desenvolvedor Backend Java Junior e Analista de Suporte. Foram utilizados 42 currículos fictícios, gerados com apoio do ChatGPT e distribuídos entre as vagas. Para cada vaga foram definidos pesos distintos para habilidades técnicas, permitindo observar como o sistema se comportava diante de descrições mais objetivas ou mais amplas. Os currículos utilizados, os perfis das vagas, os pesos atribuídos às habilidades e os resultados obtidos foram organizados em planilhas disponibilizadas no repositório do projeto.
