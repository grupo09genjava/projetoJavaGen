CREATE TABLE IF NOT EXISTS "tab_turmas" (
    turma_id SERIAL PRIMARY KEY,
    nome VARCHAR(128) NOT NULL,
    instrutor VARCHAR(128) NOT NULL
);