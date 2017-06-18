INSERT INTO "user" (email, name, password_hash)
VALUES ('test@test.no', 'Test Testesen', '$2a$10$dKdoZg6Lwb9eCFZWDV1JJOHb/0g9uQ/eJEOai/6B0DMtfPF7U32Pa');

INSERT INTO user_role (user_id, role) VALUES (1, 'ROLE_USER');
