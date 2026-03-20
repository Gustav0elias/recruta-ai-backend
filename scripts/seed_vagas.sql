-- ============================================================
-- SEED: Habilidades + 3 Vagas para usuario_id = 8
-- ============================================================

CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

-- Limpar vagas anteriores desse usuario
DELETE FROM vaga_habilidade WHERE id_vaga IN (SELECT id FROM vaga WHERE id_usuario = 8);
DELETE FROM vaga WHERE id_usuario = 8;

-- Limpar habilidades orfas
DELETE FROM habilidade WHERE id NOT IN (
    SELECT DISTINCT id_habilidade FROM vaga_habilidade
    UNION
    SELECT DISTINCT h.id FROM habilidade h
    INNER JOIN candidato_habilidade ch ON ch.id_habilidade = h.id
);

-- ============================================================
-- HABILIDADES TECNICAS - Backend Java Junior
-- ============================================================
INSERT INTO habilidade (uuid, nome, descricao, tipo_habilidade)
SELECT uuid_generate_v4(), n, d, 'HABILIDADE_TECNICA'
FROM (VALUES
    ('Java 17',           'Linguagem Java versao 17 LTS'),
    ('Spring Boot',       'Framework Spring Boot'),
    ('REST API',          'APIs RESTful com JSON e HTTP'),
    ('PostgreSQL',        'Banco de dados relacional PostgreSQL'),
    ('JPA/Hibernate',     'Mapeamento objeto-relacional'),
    ('Git',               'Controle de versao com Git'),
    ('Docker',            'Containerizacao com Docker'),
    ('JUnit 5',           'Testes automatizados com JUnit 5'),
    ('Maven',             'Gerenciador de build Maven'),
    ('Spring Security',   'Seguranca com Spring Security')
) AS t(n, d)
WHERE NOT EXISTS (SELECT 1 FROM habilidade h WHERE h.nome = t.n);

-- HABILIDADES TECNICAS - Analista de Suporte
INSERT INTO habilidade (uuid, nome, descricao, tipo_habilidade)
SELECT uuid_generate_v4(), n, d, 'HABILIDADE_TECNICA'
FROM (VALUES
    ('Windows 10/11',     'Sistema operacional Windows 10/11'),
    ('Active Directory',  'Servico de diretorio Microsoft AD'),
    ('Redes TCP/IP',      'Protocolos e redes TCP/IP'),
    ('ServiceNow/ITSM',   'Plataforma de gestao de chamados'),
    ('Microsoft 365',     'Suite Microsoft 365'),
    ('Remote Desktop',    'Acesso remoto a desktops'),
    ('Linux Basico',      'Conhecimentos basicos em Linux'),
    ('Firewall',          'Configuracao e gestao de firewall'),
    ('ITIL',              'Boas praticas ITIL para TI'),
    ('DNS/DHCP',          'Servicos de rede DNS e DHCP')
) AS t(n, d)
WHERE NOT EXISTS (SELECT 1 FROM habilidade h WHERE h.nome = t.n);

-- HABILIDADES TECNICAS - Estagiario em TI
INSERT INTO habilidade (uuid, nome, descricao, tipo_habilidade)
SELECT uuid_generate_v4(), n, d, 'HABILIDADE_TECNICA'
FROM (VALUES
    ('Logica de Programacao',     'Fundamentos de logica computacional'),
    ('Python',                    'Linguagem de programacao Python'),
    ('HTML/CSS',                  'Marcacao e estilizacao web'),
    ('JavaScript',                'Linguagem de programacao JavaScript'),
    ('SQL',                       'Linguagem de consulta SQL'),
    ('Git/GitHub',                'Versionamento com Git e GitHub'),
    ('Windows',                   'Sistema operacional Windows'),
    ('Microsoft Office',          'Suite Microsoft Office'),
    ('Banco de Dados Relacional', 'Conceitos de banco relacional')
) AS t(n, d)
WHERE NOT EXISTS (SELECT 1 FROM habilidade h WHERE h.nome = t.n);

-- SOFT SKILLS
INSERT INTO habilidade (uuid, nome, descricao, tipo_habilidade)
SELECT uuid_generate_v4(), n, d, 'SOFT_SKILL'
FROM (VALUES
    ('Trabalho em equipe',     'Colaboracao em equipe'),
    ('Resolucao de problemas', 'Capacidade analitica e solucoes'),
    ('Comunicacao tecnica',    'Comunicacao tecnica clara'),
    ('Atendimento ao cliente', 'Atendimento ao usuario final'),
    ('Paciencia',              'Paciencia no atendimento'),
    ('Comunicacao clara',      'Expressao clara e objetiva'),
    ('Aprendizado rapido',     'Capacidade de aprender rapido'),
    ('Proatividade',           'Iniciativa e proatividade')
) AS t(n, d)
WHERE NOT EXISTS (SELECT 1 FROM habilidade h WHERE h.nome = t.n);

-- ============================================================
-- 3 VAGAS
-- ============================================================
INSERT INTO vaga (uuid, titulo, descricao, nivel_experiencia, criado_em, id_usuario)
VALUES
    (uuid_generate_v4(),
     'Desenvolvedor Backend Java Junior',
     'Vaga para desenvolvedor backend Java junior com experiencia em Spring Boot, APIs REST e bancos de dados relacionais. Desejavel conhecimento em Docker, testes automatizados e versionamento com Git.',
     'JUNIOR', NOW(), 8),

    (uuid_generate_v4(),
     'Analista de Suporte',
     'Vaga para analista de suporte tecnico com experiencia em atendimento ao usuario, Windows, Active Directory e redes. Desejavel conhecimento em ITIL e ferramentas de gestao de chamados.',
     'JUNIOR', NOW(), 8),

    (uuid_generate_v4(),
     'Estagiario em TI',
     'Vaga de estagio em TI para estudantes de cursos de tecnologia. Desejavel conhecimento basico em programacao, banco de dados e sistemas operacionais. Boa oportunidade de aprendizado pratico.',
     'JUNIOR', NOW(), 8);

-- ============================================================
-- VINCULAR HABILIDADES AS VAGAS (com pesos)
-- ============================================================

-- VAGA 1: Backend Java Junior
INSERT INTO vaga_habilidade (id_vaga, id_habilidade, peso)
SELECT v.id, h.id, w.peso
FROM vaga v
CROSS JOIN (VALUES
    ('Java 17', 10), ('Spring Boot', 9), ('REST API', 8), ('PostgreSQL', 8),
    ('JPA/Hibernate', 7), ('JUnit 5', 7), ('Git', 6), ('Docker', 6),
    ('Maven', 5), ('Spring Security', 5),
    ('Trabalho em equipe', 7), ('Resolucao de problemas', 8), ('Comunicacao tecnica', 6)
) AS w(nome_hab, peso)
JOIN habilidade h ON h.nome = w.nome_hab
WHERE v.titulo = 'Desenvolvedor Backend Java Junior' AND v.id_usuario = 8;

-- VAGA 2: Analista de Suporte
INSERT INTO vaga_habilidade (id_vaga, id_habilidade, peso)
SELECT v.id, h.id, w.peso
FROM vaga v
CROSS JOIN (VALUES
    ('Windows 10/11', 9), ('Active Directory', 9), ('Redes TCP/IP', 8),
    ('ServiceNow/ITSM', 7), ('Microsoft 365', 7), ('Remote Desktop', 7),
    ('DNS/DHCP', 6), ('ITIL', 6), ('Firewall', 6), ('Linux Basico', 5),
    ('Atendimento ao cliente', 9), ('Comunicacao clara', 8), ('Paciencia', 7)
) AS w(nome_hab, peso)
JOIN habilidade h ON h.nome = w.nome_hab
WHERE v.titulo = 'Analista de Suporte' AND v.id_usuario = 8;

-- VAGA 3: Estagiario em TI
INSERT INTO vaga_habilidade (id_vaga, id_habilidade, peso)
SELECT v.id, h.id, w.peso
FROM vaga v
CROSS JOIN (VALUES
    ('Logica de Programacao', 9), ('SQL', 7), ('Banco de Dados Relacional', 7),
    ('Python', 7), ('HTML/CSS', 6), ('JavaScript', 6), ('Git/GitHub', 6),
    ('Linux Basico', 5), ('Windows', 5), ('Microsoft Office', 5),
    ('Aprendizado rapido', 9), ('Proatividade', 7), ('Trabalho em equipe', 7)
) AS w(nome_hab, peso)
JOIN habilidade h ON h.nome = w.nome_hab
WHERE v.titulo = 'Estagiario em TI' AND v.id_usuario = 8;

-- ============================================================
-- VERIFICACAO
-- ============================================================
SELECT '=== VAGAS CRIADAS ===' AS info;
SELECT v.id, v.titulo, v.nivel_experiencia, COUNT(vh.id) AS total_habilidades
FROM vaga v
LEFT JOIN vaga_habilidade vh ON vh.id_vaga = v.id
WHERE v.id_usuario = 8
GROUP BY v.id, v.titulo, v.nivel_experiencia
ORDER BY v.id;

SELECT '=== HABILIDADES POR VAGA ===' AS info;
SELECT v.titulo AS vaga, h.nome AS habilidade, vh.peso, h.tipo_habilidade AS tipo
FROM vaga_habilidade vh
JOIN vaga v ON v.id = vh.id_vaga
JOIN habilidade h ON h.id = vh.id_habilidade
WHERE v.id_usuario = 8
ORDER BY v.id, vh.peso DESC;
