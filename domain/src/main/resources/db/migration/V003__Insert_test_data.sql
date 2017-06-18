INSERT INTO "user" (email, name, password_hash)
VALUES ('test@test.no', 'Test Testesen', '$2a$12$P3WRGFxhr4.d.xB8xMWguOpLZuU4aaMt7MLhGVw2vvgCesDsCY3jG');

INSERT INTO secret (user_id) VALUES (1);
