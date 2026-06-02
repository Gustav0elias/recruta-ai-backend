# Sistema Inteligente de Triagem de Candidatos

Sistema web para gerenciamento de vagas de emprego e triagem automatizada de candidatos utilizando Inteligência Artificial (GPT). O sistema analisa currículos, compara com os requisitos das vagas e gera scores de compatibilidade com justificativas detalhadas.

## Tecnologias

- **Backend:** Java 17 / Spring Boot 3.5
- **Banco de Dados:** PostgreSQL 16
- **IA:** OpenAI GPT-3.5 Turbo
- **Autenticação:** JWT (JSON Web Tokens)
- **Documentação:** Swagger / OpenAPI
- **Containerização:** Docker

---

## Como Executar

### Pré-requisitos

- [Docker Desktop](https://www.docker.com/products/docker-desktop/) instalado e rodando

### Passo a passo

1. Clone o repositório:
```bash
git clone <url-do-repositorio>
cd projeto-tcc
```

2. Configure as variáveis de ambiente:
```bash
cp .env.example .env
```

Edite o arquivo `.env` e preencha `OPENAI_API_KEY` com a sua chave da OpenAI.

3. Suba o sistema:
```bash
docker compose up --build
```

4. Aguarde até aparecer no log:
```
Started ProjetoTccApplication in X seconds
```

5. Acesse:
   - **API:** http://localhost:8080
   - **Swagger (documentação interativa):** http://localhost:8080/swagger-ui.html

### Parar o sistema

```bash
docker compose down
```

Para parar e **apagar todos os dados** do banco:
```bash
docker compose down -v
```

### Observações

- O banco PostgreSQL é criado automaticamente pelo Docker.
- As tabelas são criadas/atualizadas automaticamente pelo Hibernate.
- A chave da API OpenAI deve ficar no arquivo `.env`, que não é versionado.

---

## Funcionalidades

### 1. Autenticação e Cadastro de Usuários

O sistema utiliza autenticação via JWT. Para acessar qualquer funcionalidade, é necessário primeiro se registrar e fazer login.

- **Registrar:** `POST /api/v01/usuario/register`
- **Login:** `POST /api/v01/usuario/login`

Após o login, o token JWT retornado deve ser enviado no header `Authorization: Bearer <token>` em todas as requisições.

### 2. Gerenciamento de Vagas

Criação e gerenciamento de vagas de emprego com título, descrição e nível de experiência (Júnior, Pleno, Sênior).

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| POST | `/api/v01/vaga` | Criar nova vaga |
| GET | `/api/v01/vaga` | Listar todas as vagas (paginado) |
| GET | `/api/v01/vaga/detalhar/{vagaId}` | Detalhes de uma vaga |
| PUT | `/api/v01/vaga/{id}` | Editar vaga |
| DELETE | `/api/v01/vaga/{id}` | Remover vaga |
| POST | `/api/v01/vaga/habilidades/{vagaId}` | Adicionar habilidades à vaga com peso |

### 3. Gerenciamento de Habilidades

Cadastro de habilidades técnicas e soft skills que podem ser associadas a vagas e candidatos.

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| POST | `/api/v01/habilidade` | Criar habilidade |
| GET | `/api/v01/habilidade` | Listar habilidades (paginado) |
| PUT | `/api/v01/habilidade/{id}` | Editar habilidade |
| DELETE | `/api/v01/habilidade/{id}` | Remover habilidade |

Tipos de habilidade: `HABILIDADE_TECNICA` e `SOFT_SKILL`.

### 4. Gerenciamento de Candidatos e Currículos

Upload de currículos em PDF e gerenciamento dos dados dos candidatos (nome, email, telefone, LinkedIn, experiências e habilidades).

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| POST | `/api/v01/candidato/curriculo/atualizar` | Enviar/atualizar currículo (PDF) |
| GET | `/api/v01/candidato/{vagaId}` | Listar candidatos de uma vaga (paginado) |
| GET | `/api/v01/candidato/geral` | Listar todos os candidatos (paginado) |
| DELETE | `/api/v01/candidato/{idCandidato}` | Remover candidato |

### 5. Triagem com Inteligência Artificial (Score)

O principal diferencial do sistema. Ao enviar um currículo, a IA automaticamente:

1. **Extrai** os dados do PDF do currículo
2. **Compara** o perfil do candidato com os requisitos de cada vaga
3. **Gera um score de 0 a 100** indicando a compatibilidade
4. **Produz justificativas detalhadas** com dois tipos de análise:
   - **Análise Específica:** comparação detalhada do candidato com os requisitos da vaga
   - **Análise Geral:** avaliação geral do perfil profissional do candidato

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| GET | `/api/v01/candidatura/{vagaId}` | Listar candidaturas de uma vaga |
| GET | `/api/v01/candidatura/score/{vagaId}` | Listar candidaturas ordenadas por score |
| GET | `/api/v01/candidatura/detalhar/{id}` | Detalhes da candidatura com análise da IA |

### 6. Relatórios e Estatísticas

Dashboard com métricas e indicadores do sistema.

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| GET | `/api/v01/relatorio/estatisticas-gerais` | Estatísticas gerais da plataforma |
| GET | `/api/v01/relatorio/candidaturas-por-periodo` | Candidaturas por período |
| GET | `/api/v01/relatorio/habilidades-mais-requisitadas` | Habilidades mais demandadas |
| GET | `/api/v01/relatorio/distribuicao-score` | Distribuição dos scores |
| GET | `/api/v01/relatorio/vagas-mais-procuradas` | Vagas mais procuradas |
| GET | `/api/v01/relatorio/vaga/{vagaId}/estatisticas` | Estatísticas de uma vaga |
| GET | `/api/v01/relatorio/vaga/{vagaId}/top-candidatos` | Top candidatos de uma vaga |

---

## Arquitetura

O sistema segue uma arquitetura em camadas:

```
Web (Controllers) → Facades → Use Cases → Services → Repositories
```

- **Controllers:** recebem as requisições HTTP
- **Facades:** orquestram os casos de uso
- **Use Cases:** contêm a lógica de negócio
- **Services:** integrações externas (OpenAI, processamento de PDF)
- **Repositories:** acesso ao banco de dados via Spring Data JPA

---

## Estrutura do Projeto

```
src/main/java/com/ifba/projeto/tcc/
├── application/
│   ├── dto/          # Objetos de transferência (request/response)
│   ├── facede/       # Orquestradores de casos de uso
│   ├── mapper/       # Mapeamento entre entidades e DTOs
│   └── usecase/      # Lógica de negócio
├── config/           # Configurações (Security, JWT, CORS)
├── domain/
│   ├── entity/       # Entidades JPA
│   ├── enum/         # Enumerações
│   ├── exception/    # Exceções customizadas
│   └── repository/   # Interfaces de acesso a dados
├── Infra/
│   ├── api/          # Interfaces de serviço
│   └── service/      # Implementações (OpenAI, PDF, etc.)
└── web/
    ├── api/          # Controllers REST
    └── exception/    # Tratamento global de erros
```
