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
	first_name varchar,
	last_name varchar,
	email varchar,
	CONSTRAINT PK_user PRIMARY KEY (id)
);

INSERT INTO users (username,password_hash,role, first_name, last_name, email) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER', 'User', 'Userrrr', 'contact.beer.recipe@gmail.com');
INSERT INTO users (username,password_hash,role, first_name, last_name, email) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN', 'Admin', 'AAdmin', 'contact.beer.recipe@gmail.com');

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
        imgUrl varchar,
        postDate date,
        user_id int,
        stickied boolean,

        constraint pk_post primary key (id),
        constraint fk_user foreign key (user_id) references users (id)
);

INSERT INTO post (title, message, imgurl, postdate, user_id, stickied) VALUES ('Welcome', 'Welcome to the Beer Recipe Forums.  Please feel free to add any topic you would like to discuss and respond to any as well.  Please also be polite to other members of our community.', 'https://d1csarkz8obe9u.cloudfront.net/posterpreviews/welcome-design-template-9aa14ff9ecf8f172a9ff8dcedcae9657_screen.jpg?ts=1567076070', '10/6/2020', 2, true);
INSERT INTO post (title, message, imgurl, postdate, user_id, stickied) VALUES ('Test', 'Welcome to the Beer Recipe Forums.  Please feel free to add any topic you would like to discuss and respond to any as well.  Please also be polite to other members of our community.', 'https://d1csarkz8obe9u.cloudfront.net/posterpreviews/welcome-design-template-9aa14ff9ecf8f172a9ff8dcedcae9657_screen.jpg?ts=1567076070', '10/6/2020', 1, false);


CREATE TABLE reply_post (
        id serial,
	user_id int NOT NULL,
        post_id int not null,
        title varchar,
        reply varchar,
        replyDate date,

	CONSTRAINT PK_reply_post PRIMARY KEY (id),
        CONSTRAINT fk_users foreign key (user_id) references users (id),
        CONSTRAINT fk_post foreign key (post_id) references post (id)
);

INSERT INTO reply_post (user_id, post_id, title, reply, replyDate) VALUES (1, 1, 'Thanks', 'It is good to be here', '2020-10-15');


CREATE TABLE favorites (
        user_id int NOT NULL, 
        recipe_id int NOT NULL,
        
        CONSTRAINT pk_favorites PRIMARY KEY (user_id, recipe_id),
        constraint fk_user foreign key (user_id) references users (id),
        CONSTRAINT fk_beerRecipe FOREIGN KEY (recipe_id) references beerRecipe (id)
);

INSERT INTO favorites (user_id, recipe_id) VALUES (1, 1);

COMMIT TRANSACTION;

SELECT * FROM users;
SELECT * FROM beerrecipe;
SELECT * FROM reply_post;

SELECT post.*, users.username FROM post
JOIN users ON post.user_id = users.id ORDER BY postdate DESC;

SELECT post.*, users.username FROM post
JOIN users ON post.user_id = users.id WHERE post.id = 1;

SELECT reply_post.*, users.username FROM reply_post
JOIN users ON reply_post.user_id = users.id WHERE post_id = 1 ORDER BY replyDate;

SELECT * FROM post;

SELECT post.*, users.username FROM post
JOIN users ON post.user_id = users.id WHERE post.id = 1;

SELECT * FROM reply_post;