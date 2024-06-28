CREATE TABLE IF NOT EXISTS customers (
    id VARCHAR(255) PRIMARY KEY,
    age INT,
    cpf VARCHAR(14),
    name VARCHAR(255),
    income DECIMAL(10, 2),
    location VARCHAR(2),
    created_at TIMESTAMP,
    updated_at TIMESTAMP
);