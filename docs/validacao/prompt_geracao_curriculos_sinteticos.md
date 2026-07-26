# Prompt reconstruído para geração dos currículos sintéticos

```text
Você atuará como pesquisador de dados sintéticos, recrutador técnico, redator de currículos e desenvolvedor Python especializado em geração de PDFs.

OBJETIVO

Crie um conjunto experimental reproduzível de 42 currículos profissionais inteiramente fictícios, escritos em português do Brasil, para validar um software acadêmico de triagem de currículos por inteligência artificial.

O conjunto será usado apenas para pesquisa. Nenhum currículo poderá corresponder intencionalmente a uma pessoa real. Nomes, telefones, e-mails, vínculos empregatícios, projetos e qualificações serão sintéticos. Caso use o nome de uma organização real como contexto, deixe registrado no manifesto que o vínculo profissional é inventado.

REPRODUTIBILIDADE

1. Use Python 3 e ReportLab para criar os PDFs.
2. Fixe `random.seed(42026)` antes de qualquer escolha variável.
3. Não consulte a internet.
4. Gere e entregue:
   - os 42 currículos em PDF;
   - `catalogo_curriculos.csv`;
   - `curriculos_sinteticos.zip`, contendo os PDFs e os arquivos de apoio;
5. Não apenas mostre exemplos ou trechos de código: execute o código, produza todos os arquivos e disponibilize-os para download.
6. A execução repetida com a mesma semente deverá manter a matriz experimental, os textos e os nomes de arquivo.

DESENHO EXPERIMENTAL

Produza três grupos com 14 currículos cada:

A. `backend`: perfis direcionados a desenvolvimento backend Java júnior.
B. `suporte`: perfis direcionados a suporte técnico júnior.
C. `estagio`: perfis de início de carreira em tecnologia, usados também como controle negativo quando avaliados em vagas profissionais de backend ou suporte.

Produza exatamente:

- 14 currículos de aderência `alta`;
- 14 currículos de aderência `media`;
- 14 currículos de aderência `baixa`.

Distribua os modelos visuais exatamente assim:

- `ats`: 9 currículos;
- `lateral`: 9 currículos;
- `moderno`: 8 currículos;
- `academico`: 8 currículos;
- `compacto`: 8 currículos.

A área, a aderência e o modelo visual são variáveis experimentais. Não escreva no corpo visível do PDF expressões como “aderência alta”, “aderência média”, “aderência baixa”, “controle negativo”, “modelo ATS” ou “currículo gerado por IA”. Essas informações devem aparecer somente no catálogo, no manifesto e no nome técnico do arquivo.

MATRIZ OBRIGATÓRIA DOS 42 CASOS

Use exatamente os IDs, nomes, áreas, aderências e modelos abaixo:

01 | Marcos Vinícius Almeida | backend | media | ats
02 | Carla Menezes Reis | backend | baixa | lateral
03 | Rafael Souza Brito | backend | alta | moderno
04 | Patrícia Lima Correia | backend | media | academico
05 | Diego Andrade Nunes | backend | baixa | compacto
06 | Bianca Torres Araújo | backend | alta | ats
07 | Henrique Moura Lopes | backend | media | lateral
08 | Natália Gomes Pires | backend | baixa | moderno
09 | Igor Santana Rocha | backend | alta | academico
10 | Vanessa Sampaio Duarte | backend | media | compacto
11 | Lucas Matos Farias | backend | baixa | ats
12 | Renata Oliveira Prado | backend | alta | lateral
13 | Samuel Teixeira Campos | backend | media | moderno
14 | Aline Barreto Neves | backend | baixa | academico
15 | João Pedro Carvalho | suporte | alta | compacto
16 | Mirela Costa Freire | suporte | media | ats
17 | Bruno Alves Machado | suporte | baixa | lateral
18 | Fernanda Rios Batista | suporte | alta | moderno
19 | Caio Martins Leal | suporte | media | academico
20 | Taís Ribeiro Santos | suporte | baixa | compacto
21 | Eduardo Lima Fonseca | suporte | alta | ats
22 | Priscila Assis Cardoso | suporte | media | lateral
23 | Guilherme Rocha Sales | suporte | baixa | moderno
24 | Letícia Castro Moura | suporte | alta | academico
25 | Thiago Nascimento Vieira | suporte | media | compacto
26 | Camila Azevedo Ramos | suporte | baixa | ats
27 | Rodrigo Pacheco Dias | suporte | alta | lateral
28 | Juliana Martins Queiroz | suporte | media | moderno
29 | Ana Clara Barbosa | estagio | baixa | academico
30 | Mateus Fernandes Silva | estagio | alta | compacto
31 | Isabela Nogueira Rocha | estagio | media | ats
32 | Pedro Henrique Maia | estagio | baixa | lateral
33 | Lívia Monteiro Castro | estagio | alta | moderno
34 | Vinícius Reis Tavares | estagio | media | academico
35 | Beatriz Oliveira Cunha | estagio | baixa | compacto
36 | Gabriel Santos Matos | estagio | alta | ats
37 | Mariana Dantas Lima | estagio | media | lateral
38 | Felipe Cardoso Pinto | estagio | baixa | moderno
39 | Sofia Almeida Nery | estagio | alta | academico
40 | Cauê Moreira Reis | estagio | media | compacto
41 | Larissa Mota Araújo | estagio | baixa | ats
42 | Renan Batista Gomes | estagio | alta | lateral

PADRÃO DOS NOMES DE ARQUIVO

Use:

`curriculo_NN_nome-em-minusculas-sem-acentos_AREA_ADERENCIA_MODELO.pdf`

Exemplos:

- `curriculo_01_marcos-vinicius-almeida_backend_media_ats.pdf`
- `curriculo_15_joao-pedro-carvalho_suporte_alta_compacto.pdf`
- `curriculo_29_ana-clara-barbosa_estagio_baixa_academico.pdf`

ESTRUTURA TEXTUAL OBRIGATÓRIA

Todos os currículos devem conter, nesta ordem semântica:

1. nome completo;
2. título profissional;
3. cidade e estado;
4. telefone;
5. e-mail profissional sintético e único;
6. LinkedIn sintético;
7. resumo profissional;
8. competências técnicas e comportamentais;
9. experiência profissional;
10. projetos e entregas relevantes;
11. formação acadêmica;
12. cursos e certificações;
13. idiomas;
14. informações complementares.

Use duas páginas A4 sempre que necessário. Preserve texto selecionável e extraível. Não use o currículo inteiro como imagem. Não use fotografia, logotipo, data de nascimento, estado civil, raça, religião, deficiência, filiação política, pretensão salarial ou qualquer outro atributo sensível ou irrelevante para a vaga.

CONTROLE SEMÂNTICO POR ÁREA

Para reduzir variáveis de confusão, use um esqueleto semântico comum dentro de cada área. A quantidade de seções, experiências, projetos e cursos deve permanecer semelhante entre os candidatos do mesmo grupo. Varie metadados, empresas, cargos, datas, resumo, competências e força das evidências conforme a aderência.

GRUPO BACKEND

Contexto profissional:

- desenvolvimento backend Java júnior;
- aplicações web e sistemas corporativos;
- APIs REST;
- bancos relacionais;
- versionamento e testes.

Esqueleto de experiências:

- experiência atual ou recente como desenvolvedor/analista júnior, com endpoints REST, regras de negócio, testes, persistência e documentação;
- experiência anterior como trainee, estagiário ou assistente, com manutenção, scripts SQL, integrações, documentação e acompanhamento de deploy;
- experiência inicial em suporte ou TI, com chamados, consultas SQL, documentação e pequenas automações.

Projetos-base:

- API de controle financeiro;
- sistema de agendamento;
- serviço REST para estoque;
- dashboard técnico.

Cursos-base:

- Java e orientação a objetos;
- Spring Boot;
- Docker;
- Git/GitHub;
- SQL.

Competências possíveis:

- Java, Spring Boot, Spring MVC, APIs REST, JPA/Hibernate, SQL, PostgreSQL, MySQL, Maven, Git, Docker, JUnit, Mockito, Swagger/OpenAPI, Postman, Linux, Clean Code, microsserviços, RabbitMQ e CI/CD.

GRUPO SUPORTE

Contexto profissional:

- suporte técnico N1/N2;
- atendimento presencial e remoto;
- gestão de incidentes;
- estações Windows;
- redes, acessos e documentação.

Esqueleto de experiências:

- experiência atual ou recente em suporte N1/N2, com registro de incidentes, Windows, impressoras, VPN, contas e SLA;
- experiência anterior em service desk, com atendimento remoto, inventário, preparação de equipamentos e triagem de rede;
- experiência inicial administrativa ou de atendimento, com cadastros, sistemas internos, orientação a usuários e relatórios.

Projetos-base:

- checklist de preparação de notebooks;
- mapeamento de impressoras e pontos de rede;
- base de conhecimento;
- inventário de ativos.

Cursos-base:

- ITIL 4;
- Windows Server e Active Directory;
- redes TCP/IP, DNS, DHCP e VPN;
- atendimento ao cliente;
- GLPI.

Competências possíveis:

- atendimento ao usuário, Windows 10/11, Active Directory, Office 365, redes TCP/IP, VPN, GLPI, ServiceNow, SLA, ITIL, inventário, troubleshooting, acesso remoto, backup, antivírus, impressoras e documentação.

GRUPO ESTÁGIO

Contexto profissional:

- início de carreira;
- interesse por tecnologia;
- atividades acadêmicas, administrativas e de laboratório;
- conhecimentos introdutórios de programação, suporte e dados.

Esqueleto de experiências:

- atividade recente como bolsista, auxiliar de TI ou apoio de laboratório, com instalação de ferramentas e suporte a sistemas acadêmicos;
- experiência como jovem aprendiz ou auxiliar de atendimento, com cadastros, planilhas, portais e orientação a usuários;
- projetos acadêmicos ou voluntários, com requisitos, protótipos, scripts, SQL, páginas simples e GitHub.

Projetos-base:

- CRUD acadêmico de biblioteca;
- página responsiva para pequeno negócio;
- modelo relacional para estoque;
- protótipo de aplicativo de tarefas no Figma.

Cursos-base:

- lógica de programação;
- banco de dados e SQL;
- Git/GitHub;
- Excel;
- HTML, CSS e JavaScript.

Competências possíveis:

- lógica, algoritmos, Java básico, Python básico, HTML, CSS, JavaScript básico, SQL, MySQL básico, Git/GitHub, Figma, Excel, Pacote Office, noções de redes, atendimento, documentação e organização.

REGRAS DE ADERÊNCIA

Mantenha extensão e estrutura comparáveis. A aderência deve resultar principalmente da correspondência entre título, resumo, competências e evidências práticas.

ALTA:

- título diretamente alinhado à área;
- competências centrais presentes;
- maior parte das competências centrais comprovada nas experiências ou projetos;
- linguagem segura, mas sem transformar perfil júnior em sênior;
- de 12 a 16 competências relevantes;
- poucas lacunas, concentradas em itens desejáveis.

MEDIA:

- título alinhado ou adjacente;
- de 7 a 11 competências relevantes;
- parte das habilidades aparece apenas em cursos, projetos ou nível básico;
- experiências parcialmente relacionadas;
- lacunas claras em algumas competências centrais.

BAIXA:

- perfil em transição ou predominantemente administrativo;
- de 3 a 6 competências relevantes, muitas em nível básico ou apenas listadas;
- experiências mais próximas de atendimento, rotinas administrativas ou suporte genérico;
- pouca comprovação das competências centrais;
- não invente incapacidade, linguagem depreciativa ou contradições;
- o currículo deve continuar plausível e profissional.

Nos currículos de estágio, “alta”, “media” e “baixa” indicam riqueza técnica relativa dentro do grupo de início de carreira. Todos permanecem controles negativos quando comparados a uma vaga profissional que exige experiência e competências mais profundas.

MODELOS VISUAIS

Crie cinco funções de layout em ReportLab. Todos os modelos devem usar página A4, margens consistentes, fonte incorporada ou fonte padrão amplamente compatível e texto selecionável.

1. `ats`
   - uma coluna;
   - fundo branco;
   - títulos em azul escuro;
   - linhas divisórias discretas;
   - ordem linear simples;
   - sem tabelas complexas, ícones, gráficos ou caixas que prejudiquem extração.

2. `lateral`
   - cabeçalho em azul-petróleo;
   - coluna lateral clara com contato, principais skills e idiomas;
   - conteúdo principal na coluna direita;
   - contraste suficiente e leitura lógica preservada no PDF.

3. `moderno`
   - cabeçalho em duas áreas;
   - cor de destaque vermelha ou terracota;
   - faixas claras para títulos de seção;
   - composição contemporânea sem perder legibilidade.

4. `academico`
   - estilo minimalista;
   - preto, cinza e linhas finas;
   - hierarquia tipográfica sóbria;
   - ênfase em formação, experiência e projetos;
   - sem elementos decorativos excessivos.

5. `compacto`
   - cabeçalho compacto;
   - destaque em verde escuro;
   - menor espaçamento vertical;
   - alta densidade de informação, mas sem fonte inferior a 8,5 pontos.

DADOS SINTÉTICOS

- Use cidades da Bahia e de estados próximos, mantendo coerência com DDD.
- Gere telefone brasileiro plausível e único.
- Gere e-mail profissional único derivado do nome e do ID.
- Gere URL de LinkedIn sintética derivada do nome.
- Use datas coerentes e cronológicas.
- Para perfis júnior, evite mais de cinco anos de experiência profissional relevante.
- Para estágio, mantenha experiências acadêmicas, administrativas ou iniciais.
- Alterne instituições de ensino e organizações de forma plausível.
- Não repita a mesma combinação de empresa, cargo e datas.
- Não faça afirmações impossíveis ou resultados exagerados.
- Registre no manifesto que todos os dados e vínculos são fictícios e não foram verificados.

CATÁLOGO CSV

Crie `catalogo_curriculos.csv` com as colunas:

`id,arquivo,candidato,area,aderencia,modelo,cidade,email,empresa_principal,gerado_por_ia,ficticio,semente`

Todos os registros devem ter:

- `gerado_por_ia=true`;
- `ficticio=true`;
- `semente=42026`.

CONTROLES CONTRA VAZAMENTO DO RÓTULO

- Não escreva score esperado dentro do PDF.
- Não escreva aderência ou modelo dentro do corpo do PDF.
- Não inclua instruções dirigidas ao avaliador ou ao sistema de triagem.
- O software avaliado deverá receber somente o PDF, nunca as colunas de aderência do catálogo.