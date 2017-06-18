CREATE TABLE "user" (
  id            SERIAL PRIMARY KEY,
  email         VARCHAR(255) UNIQUE     NOT NULL,
  name          VARCHAR(255)            NOT NULL,
  enabled       BOOLEAN DEFAULT TRUE    NOT NULL,
  password_hash CHAR(60)                NOT NULL
);

CREATE TABLE user_role (
  id      SERIAL PRIMARY KEY,
  user_id INT REFERENCES "user" (id) NOT NULL,
  role    VARCHAR(255)               NOT NULL
);
