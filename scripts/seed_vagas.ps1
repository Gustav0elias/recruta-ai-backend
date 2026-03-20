# ============================================================
# Script de seed: cria usuario, habilidades e 3 vagas de teste
# ============================================================
$ErrorActionPreference = "Stop"
$base = "http://localhost:8080/api/v01"

function Api-Post($path, $body, $token) {
    $params = @{
        Uri         = "$base$path"
        Method      = "Post"
        Body        = ($body | ConvertTo-Json -Depth 5)
        ContentType = "application/json; charset=utf-8"
    }
    if ($token) { $params.Headers = @{ Authorization = "Bearer $token" } }
    return Invoke-RestMethod @params
}

function Api-Get($path, $token) {
    $params = @{
        Uri    = "$base$path"
        Method = "Get"
    }
    if ($token) { $params.Headers = @{ Authorization = "Bearer $token" } }
    return Invoke-RestMethod @params
}

# ============================================================
# 1. Registrar usuario
# ============================================================
Write-Host "`n=== Registrando usuario ===" -ForegroundColor Cyan
try {
    $reg = Api-Post "/usuario/register" @{ nome = "Recrutador TCC"; email = "recrutador@tcc.com"; senha = "senha123" }
    Write-Host "Usuario criado: $($reg.nome) - $($reg.email)"
} catch {
    Write-Host "Usuario ja existe ou erro no registro, tentando login..." -ForegroundColor Yellow
}

# ============================================================
# 2. Login
# ============================================================
Write-Host "`n=== Fazendo login ===" -ForegroundColor Cyan
$login = Api-Post "/usuario/login" @{ email = "recrutador@tcc.com"; senha = "senha123" }
$token = $login.token
Write-Host "Token obtido com sucesso!"

# ============================================================
# 3. Criar habilidades
# ============================================================
Write-Host "`n=== Criando habilidades ===" -ForegroundColor Cyan

$habilidades = @{}

function Criar-Habilidade($nome, $descricao, $tipo) {
    $resp = Api-Post "/habilidade" @{ nome = $nome; descricao = $descricao; tipo = $tipo } $token
    $habilidades[$nome] = $resp.uuid
    Write-Host "  [OK] $nome (uuid: $($resp.uuid))"
    return $resp.uuid
}

# --- HABILIDADES TECNICAS (Backend Java Junior) ---
Criar-Habilidade "Java 17"           "Linguagem Java versao 17 LTS"            "HABILIDADE_TECNICA"
Criar-Habilidade "Spring Boot"       "Framework Spring Boot"                   "HABILIDADE_TECNICA"
Criar-Habilidade "REST API"          "APIs RESTful com JSON e HTTP"            "HABILIDADE_TECNICA"
Criar-Habilidade "PostgreSQL"        "Banco de dados relacional PostgreSQL"    "HABILIDADE_TECNICA"
Criar-Habilidade "JPA/Hibernate"     "Mapeamento objeto-relacional"            "HABILIDADE_TECNICA"
Criar-Habilidade "Git"               "Controle de versao com Git"              "HABILIDADE_TECNICA"
Criar-Habilidade "Docker"            "Containerizacao com Docker"              "HABILIDADE_TECNICA"
Criar-Habilidade "JUnit 5"           "Testes automatizados com JUnit 5"        "HABILIDADE_TECNICA"
Criar-Habilidade "Maven"             "Gerenciador de build Maven"              "HABILIDADE_TECNICA"
Criar-Habilidade "Spring Security"   "Seguranca com Spring Security"           "HABILIDADE_TECNICA"

# --- HABILIDADES TECNICAS (Analista de Suporte) ---
Criar-Habilidade "Windows 10/11"     "Sistema operacional Windows 10/11"       "HABILIDADE_TECNICA"
Criar-Habilidade "Active Directory"  "Servico de diretorio Microsoft AD"       "HABILIDADE_TECNICA"
Criar-Habilidade "Redes TCP/IP"      "Protocolos e redes TCP/IP"              "HABILIDADE_TECNICA"
Criar-Habilidade "ServiceNow/ITSM"   "Plataforma de gestao de chamados"       "HABILIDADE_TECNICA"
Criar-Habilidade "Microsoft 365"     "Suite Microsoft 365"                     "HABILIDADE_TECNICA"
Criar-Habilidade "Remote Desktop"    "Acesso remoto a desktops"               "HABILIDADE_TECNICA"
Criar-Habilidade "Linux Basico"      "Conhecimentos basicos em Linux"          "HABILIDADE_TECNICA"
Criar-Habilidade "Firewall"          "Configuracao e gestao de firewall"       "HABILIDADE_TECNICA"
Criar-Habilidade "ITIL"              "Boas praticas ITIL para TI"             "HABILIDADE_TECNICA"
Criar-Habilidade "DNS/DHCP"          "Servicos de rede DNS e DHCP"            "HABILIDADE_TECNICA"

# --- HABILIDADES TECNICAS (Estagiario em TI) ---
Criar-Habilidade "Logica de Programacao" "Fundamentos de logica computacional" "HABILIDADE_TECNICA"
Criar-Habilidade "Python"            "Linguagem de programacao Python"         "HABILIDADE_TECNICA"
Criar-Habilidade "HTML/CSS"          "Marcacao e estilizacao web"              "HABILIDADE_TECNICA"
Criar-Habilidade "JavaScript"        "Linguagem de programacao JavaScript"     "HABILIDADE_TECNICA"
Criar-Habilidade "SQL"               "Linguagem de consulta SQL"              "HABILIDADE_TECNICA"
Criar-Habilidade "Git/GitHub"        "Versionamento com Git e GitHub"          "HABILIDADE_TECNICA"
Criar-Habilidade "Windows"           "Sistema operacional Windows"             "HABILIDADE_TECNICA"
Criar-Habilidade "Microsoft Office"  "Suite Microsoft Office"                  "HABILIDADE_TECNICA"
Criar-Habilidade "Banco de Dados Relacional" "Conceitos de banco relacional"   "HABILIDADE_TECNICA"

# --- SOFT SKILLS (compartilhadas entre vagas) ---
Criar-Habilidade "Trabalho em equipe"    "Colaboracao em equipe"               "SOFT_SKILL"
Criar-Habilidade "Resolucao de problemas" "Capacidade analitica e solucoes"    "SOFT_SKILL"
Criar-Habilidade "Comunicacao tecnica"   "Comunicacao tecnica clara"           "SOFT_SKILL"
Criar-Habilidade "Atendimento ao cliente" "Atendimento ao usuario final"       "SOFT_SKILL"
Criar-Habilidade "Paciencia"             "Paciencia no atendimento"            "SOFT_SKILL"
Criar-Habilidade "Comunicacao clara"     "Expressao clara e objetiva"          "SOFT_SKILL"
Criar-Habilidade "Aprendizado rapido"    "Capacidade de aprender rapido"       "SOFT_SKILL"
Criar-Habilidade "Proatividade"          "Iniciativa e proatividade"           "SOFT_SKILL"

Write-Host "`nTotal de habilidades criadas: $($habilidades.Count)" -ForegroundColor Green

# ============================================================
# 4. Criar as 3 Vagas
# ============================================================
Write-Host "`n=== Criando vagas ===" -ForegroundColor Cyan

# --- VAGA 1: Desenvolvedor Backend Java Junior ---
$vaga1 = Api-Post "/vaga" @{
    titulo            = "Desenvolvedor Backend Java Junior"
    descricao         = "Vaga para desenvolvedor backend Java junior com experiencia em Spring Boot, APIs REST e bancos de dados relacionais. Desejavel conhecimento em Docker, testes automatizados e versionamento com Git."
    nivelExperiencia  = "JUNIOR"
    habilidades       = @(
        @{ habilidadeUuid = $habilidades["Java 17"];           peso = 10 }
        @{ habilidadeUuid = $habilidades["Spring Boot"];       peso = 9  }
        @{ habilidadeUuid = $habilidades["REST API"];          peso = 8  }
        @{ habilidadeUuid = $habilidades["PostgreSQL"];        peso = 8  }
        @{ habilidadeUuid = $habilidades["JPA/Hibernate"];     peso = 7  }
        @{ habilidadeUuid = $habilidades["JUnit 5"];           peso = 7  }
        @{ habilidadeUuid = $habilidades["Git"];               peso = 6  }
        @{ habilidadeUuid = $habilidades["Docker"];            peso = 6  }
        @{ habilidadeUuid = $habilidades["Maven"];             peso = 5  }
        @{ habilidadeUuid = $habilidades["Spring Security"];   peso = 5  }
        @{ habilidadeUuid = $habilidades["Trabalho em equipe"];    peso = 7 }
        @{ habilidadeUuid = $habilidades["Resolucao de problemas"]; peso = 8 }
        @{ habilidadeUuid = $habilidades["Comunicacao tecnica"];   peso = 6 }
    )
} $token

Write-Host "`n[VAGA 1] $($vaga1.titulo)" -ForegroundColor Green
Write-Host "  ID: $($vaga1.id) | UUID: $($vaga1.uuid)"
Write-Host "  Nivel: $($vaga1.nivelExperiencia)"
Write-Host "  Habilidades vinculadas: $($vaga1.habilidades.Count)"

# --- VAGA 2: Analista de Suporte ---
$vaga2 = Api-Post "/vaga" @{
    titulo            = "Analista de Suporte"
    descricao         = "Vaga para analista de suporte tecnico com experiencia em atendimento ao usuario, Windows, Active Directory e redes. Desejavel conhecimento em ITIL e ferramentas de gestao de chamados."
    nivelExperiencia  = "JUNIOR"
    habilidades       = @(
        @{ habilidadeUuid = $habilidades["Windows 10/11"];     peso = 9  }
        @{ habilidadeUuid = $habilidades["Active Directory"];  peso = 9  }
        @{ habilidadeUuid = $habilidades["Redes TCP/IP"];      peso = 8  }
        @{ habilidadeUuid = $habilidades["ServiceNow/ITSM"];   peso = 7  }
        @{ habilidadeUuid = $habilidades["Microsoft 365"];     peso = 7  }
        @{ habilidadeUuid = $habilidades["Remote Desktop"];    peso = 7  }
        @{ habilidadeUuid = $habilidades["DNS/DHCP"];          peso = 6  }
        @{ habilidadeUuid = $habilidades["ITIL"];              peso = 6  }
        @{ habilidadeUuid = $habilidades["Firewall"];          peso = 6  }
        @{ habilidadeUuid = $habilidades["Linux Basico"];      peso = 5  }
        @{ habilidadeUuid = $habilidades["Atendimento ao cliente"]; peso = 9 }
        @{ habilidadeUuid = $habilidades["Comunicacao clara"];     peso = 8 }
        @{ habilidadeUuid = $habilidades["Paciencia"];             peso = 7 }
    )
} $token

Write-Host "`n[VAGA 2] $($vaga2.titulo)" -ForegroundColor Green
Write-Host "  ID: $($vaga2.id) | UUID: $($vaga2.uuid)"
Write-Host "  Nivel: $($vaga2.nivelExperiencia)"
Write-Host "  Habilidades vinculadas: $($vaga2.habilidades.Count)"

# --- VAGA 3: Estagiario em TI ---
$vaga3 = Api-Post "/vaga" @{
    titulo            = "Estagiario em TI"
    descricao         = "Vaga de estagio em TI para estudantes de cursos de tecnologia. Desejavel conhecimento basico em programacao, banco de dados e sistemas operacionais. Boa oportunidade de aprendizado pratico."
    nivelExperiencia  = "JUNIOR"
    habilidades       = @(
        @{ habilidadeUuid = $habilidades["Logica de Programacao"];      peso = 9 }
        @{ habilidadeUuid = $habilidades["SQL"];                        peso = 7 }
        @{ habilidadeUuid = $habilidades["Banco de Dados Relacional"];  peso = 7 }
        @{ habilidadeUuid = $habilidades["Python"];                     peso = 7 }
        @{ habilidadeUuid = $habilidades["HTML/CSS"];                   peso = 6 }
        @{ habilidadeUuid = $habilidades["JavaScript"];                 peso = 6 }
        @{ habilidadeUuid = $habilidades["Git/GitHub"];                 peso = 6 }
        @{ habilidadeUuid = $habilidades["Linux Basico"];               peso = 5 }
        @{ habilidadeUuid = $habilidades["Windows"];                    peso = 5 }
        @{ habilidadeUuid = $habilidades["Microsoft Office"];           peso = 5 }
        @{ habilidadeUuid = $habilidades["Aprendizado rapido"];  peso = 9 }
        @{ habilidadeUuid = $habilidades["Proatividade"];        peso = 7 }
        @{ habilidadeUuid = $habilidades["Trabalho em equipe"];  peso = 7 }
    )
} $token

Write-Host "`n[VAGA 3] $($vaga3.titulo)" -ForegroundColor Green
Write-Host "  ID: $($vaga3.id) | UUID: $($vaga3.uuid)"
Write-Host "  Nivel: $($vaga3.nivelExperiencia)"
Write-Host "  Habilidades vinculadas: $($vaga3.habilidades.Count)"

# ============================================================
# Resumo final
# ============================================================
Write-Host "`n============================================" -ForegroundColor Cyan
Write-Host "  SEED COMPLETO!" -ForegroundColor Green
Write-Host "  Habilidades criadas: $($habilidades.Count)"
Write-Host "  Vagas criadas: 3"
Write-Host "    1. $($vaga1.titulo) (ID: $($vaga1.id))"
Write-Host "    2. $($vaga2.titulo) (ID: $($vaga2.id))"
Write-Host "    3. $($vaga3.titulo) (ID: $($vaga3.id))"
Write-Host "============================================`n" -ForegroundColor Cyan
