CREATE TABLE secrets (
  id SERIAL PRIMARY KEY,
  user_id INT NOT NULL REFERENCES users(id),
  secret VARCHAR(255)
);