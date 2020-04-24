DROP TABLE IF EXISTS account;

CREATE TABLE account (
  id SERIAL PRIMARY KEY,
  name VARCHAR (30),
  username VARCHAR (30) UNIQUE,
  email VARCHAR (30),
  created TIMESTAMP
);