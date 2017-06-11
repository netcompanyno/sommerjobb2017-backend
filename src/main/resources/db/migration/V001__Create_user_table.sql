 CREATE TABLE "user" (
   id SERIAL PRIMARY KEY,
   email VARCHAR(255) NOT NULL,
   name VARCHAR(255) NOT NULL,
   password_hash CHAR(60) NOT NULL
 );

