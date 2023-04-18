CREATE SCHEMA IF NOT EXISTS userservice;

CREATE TABLE IF NOT EXISTS userservice.users
(
    ID                  SERIAL PRIMARY KEY,
    FIRSTNAME           VARCHAR(20)  NOT NULL,
    LASTNAME            VARCHAR(20)  NOT NULL,
);