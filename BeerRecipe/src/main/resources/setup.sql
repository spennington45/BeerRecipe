BEGIN TRANSACTION;

DROP TABLE IF EXISTS beerrecipe;

CREATE TABLE beerrecipe (
  id serial PRIMARY KEY,
  name varchar(256) NOT NULL, 
  ingredients varchar(1000) NOT NULL,
  volume int,
  abv decimal,
  directions varchar (10000) NOT NULL,
  author varchar (100)
);

COMMIT TRANSACTION;
