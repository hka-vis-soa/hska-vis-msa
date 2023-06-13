CREATE SCHEMA IF NOT EXISTS productservice;

CREATE TABLE IF NOT EXISTS products
(
    ID          SERIAL PRIMARY KEY,
    name        VARCHAR(225)   NOT NULL,
    price       NUMERIC(10, 2) NOT NULL,
    details     VARCHAR(225)   NOT NULL,
    category_id INTEGER        NOT NULL
);