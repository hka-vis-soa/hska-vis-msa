CREATE SCHEMA IF NOT EXISTS productservice;

CREATE TABLE IF NOT EXISTS products
(
    ID             SERIAL PRIMARY KEY,
    name           VARCHAR(225) NOT NULL
);