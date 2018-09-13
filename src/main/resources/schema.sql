DROP TABLE IF EXISTS account;

CREATE TABLE account (
  id INTEGER IDENTITY PRIMARY KEY,
  name VARCHAR (30),
  username VARCHAR (30),
  email VARCHAR (30),
  created TIMESTAMP
);