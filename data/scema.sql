BEGIN TRANSACTION;

DROP TABLE IF EXISTS favorites;
DROP TABLE IF EXISTS reply_post;
DROP TABLE IF EXISTS post;
DROP TABLE IF EXISTS beerRecipe;
DROP TABLE IF EXISTS users;
DROP SEQUENCE IF EXISTS seq_user_id;

CREATE SEQUENCE seq_user_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;

CREATE TABLE users (
	id int DEFAULT nextval('seq_user_id'::regclass) NOT NULL,
	username varchar(50) NOT NULL,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	name varchar,
	CONSTRAINT PK_user PRIMARY KEY (id)
);

INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');

CREATE TABLE beerRecipe (
  id serial,
  name varchar(256) NOT NULL, 
  ingredients varchar(1000) NOT NULL,
  volume int,
  units varchar (24),
  abv decimal,
  directions varchar (10000) NOT NULL,
  author varchar (100),
  user_id int,
  
  CONSTRAINT PK_beerRecipe PRIMARY KEY (id),
  constraint fk_user foreign key (user_id) references users (id)
);

INSERT INTO beerrecipe (name, ingredients, volume, units, abv, directions, author, user_id) VALUES ('BAVARIAN WHEET 1', '1 lb Bavarian Wheet extract dry, 1 lb two row extract dry, 0.5 oz Liberty Hops, SafAle T 58 yeast, 1oz beat suger', 1, 'Gal', 7, 'Add extract and 0.25 oz Liberty hops and boil for 60 min, At 5 min left in the boil add 0.25 oz Liberty hops', 'Steven Pennington', 1);
INSERT INTO beerrecipe (name, ingredients, volume, units, abv, directions, author, user_id) VALUES ('BAVARIAN WHEET 2', '2 lb Bavarian Wheet extract dry, 0.5 oz Liberty Hops, SafAle T 58 yeast, 1oz beat suger', 1, 'Gal', 7, 'Add extract and 0.25 oz Liberty hops and boil for 60 min, At 5 min left in the boil add 0.25 oz Liberty hops', 'Steven Pennington', 1);

CREATE TABLE post (

        id serial, 
        title varchar,
        message varchar,
        reviewImgUrl varchar,
        reviewDate date,
        user_id int,

        constraint pk_post primary key (id),
        constraint fk_user foreign key (user_id) references users (id)
);

CREATE TABLE reply_post (
        id serial,
	user_id int NOT NULL,
        post_id int not null,
        title varchar,
        reply varchar,

	CONSTRAINT PK_reply_post PRIMARY KEY (id),
        CONSTRAINT fk_users foreign key (user_id) references users (id),
        CONSTRAINT fk_post foreign key (post_id) references post (id)
);

CREATE TABLE favorites (
        user_id int NOT NULL, 
        recipe_id int NOT NULL,
        
        CONSTRAINT pk_favorites PRIMARY KEY (user_id, recipe_id),
        constraint fk_user foreign key (user_id) references users (id),
        CONSTRAINT fk_beerRecipe FOREIGN KEY (recipe_id) references beerRecipe (id)
);

COMMIT TRANSACTION;
