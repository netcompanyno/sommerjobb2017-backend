CREATE TABLE users (
  id            SERIAL PRIMARY KEY,
  email         VARCHAR(255) UNIQUE     NOT NULL,
  name          VARCHAR(255)            NOT NULL,
  enabled       BOOLEAN DEFAULT TRUE    NOT NULL,
  password_hash CHAR(60)                NOT NULL
);

CREATE TABLE user_roles (
  id      SERIAL PRIMARY KEY,
  user_id INT REFERENCES users (id) NOT NULL,
  role    VARCHAR(255)              NOT NULL
);
