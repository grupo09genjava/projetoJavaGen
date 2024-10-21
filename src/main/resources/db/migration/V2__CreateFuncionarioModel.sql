CREATE TABLE IF NOT EXISTS "tab_funcionarios"(
    funcionarios_id INTEGER PRIMARY KEY,
    nome VARCHAR(128) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    senha VARCHAR(255) NOT NULL,
    cargo VARCHAR(128) NOT NULL
);