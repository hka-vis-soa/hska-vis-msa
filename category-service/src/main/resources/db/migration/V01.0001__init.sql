CREATE SCHEMA IF NOT EXISTS categoryservice;

CREATE TABLE IF NOT EXISTS categories
(
    ID             SERIAL PRIMARY KEY,
    name           VARCHAR(225) NOT NULL
);