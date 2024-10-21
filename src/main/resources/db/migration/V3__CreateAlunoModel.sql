CREATE TABLE IF NOT EXISTS "tab_alunos"(
    aluno_id INTEGER PRIMARY KEY,
    nome VARCHAR(128) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    idade INTEGER NOT NULL,
    nota_primeiro_modulo DECIMAL(3, 1),
    nota_segundo_modulo DECIMAL(3, 1),
    media DECIMAL(3, 1),
    turma_id INTEGER,
    FOREIGN KEY (turma_id) REFERENCES tab_turmas(turma_id) ON DELETE CASCADE
);