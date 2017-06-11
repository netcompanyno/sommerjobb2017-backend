CREATE TABLE secret (
  id SERIAL PRIMARY KEY,
  user_id INT NOT NULL REFERENCES "user"(id),
  secret VARCHAR(255)
);