CREATE TABLE endereco (
    id SERIAL PRIMARY KEY,
    cep VARCHAR(9) NOT NULL,
    logradouro VARCHAR(100),
    numero VARCHAR(10) NOT NULL,
    bairro VARCHAR(50),
    cidade VARCHAR(50),
    uf VARCHAR(2)
);