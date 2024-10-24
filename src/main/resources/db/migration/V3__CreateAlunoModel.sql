CREATE TABLE IF NOT EXISTS "tb_students"(
    student_id SERIAL PRIMARY KEY,
    name VARCHAR(128) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    age INTEGER NOT NULL,
    first_module_score DECIMAL(3, 1),
    second_module_score DECIMAL(3, 1),
    average DECIMAL(3, 1),
    class_id INTEGER,
    FOREIGN KEY (class_id) REFERENCES tb_classes(class_id) ON DELETE CASCADE
);