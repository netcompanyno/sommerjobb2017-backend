INSERT INTO users (email, name, password_hash)
VALUES ('test@test.no', 'Test Testesen', '$2a$10$dKdoZg6Lwb9eCFZWDV1JJOHb/0g9uQ/eJEOai/6B0DMtfPF7U32Pa');
INSERT INTO users (email, name, password_hash)
VALUES ('admin', 'Admin', '$2a$10$dKdoZg6Lwb9eCFZWDV1JJOHb/0g9uQ/eJEOai/6B0DMtfPF7U32Pa');


INSERT INTO user_roles (user_id, role) VALUES (1, 'ROLE_USER');

INSERT INTO user_roles (user_id, role) VALUES (2, 'ROLE_USER');
INSERT INTO user_roles (user_id, role) VALUES (2, 'ROLE_ADMIN');
