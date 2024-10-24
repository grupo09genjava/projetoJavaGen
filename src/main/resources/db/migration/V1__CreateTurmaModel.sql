CREATE TABLE IF NOT EXISTS "tb_classes" (
    class_id SERIAL PRIMARY KEY,
    name VARCHAR(128) NOT NULL,
    instructor VARCHAR(128) NOT NULL
);