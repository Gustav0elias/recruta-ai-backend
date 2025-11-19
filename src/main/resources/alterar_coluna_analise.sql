-- Script SQL para alterar a coluna descricao da tabela analise de VARCHAR(1000) para TEXT
-- Execute este script no seu banco de dados PostgreSQL

ALTER TABLE analise 
ALTER COLUMN descricao TYPE TEXT;

