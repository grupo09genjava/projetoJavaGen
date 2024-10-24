CREATE TABLE IF NOT EXISTS "tb_employees"(
    employee_id SERIAL PRIMARY KEY,
    name VARCHAR(128) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    job_title VARCHAR(128) NOT NULL
);