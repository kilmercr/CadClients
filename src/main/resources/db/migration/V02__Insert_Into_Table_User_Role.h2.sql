TRUNCATE TABLE `TB_USER`;
TRUNCATE TABLE `TB_ROLE`;
TRUNCATE TABLE `TB_USERS_ROLES`;
TRUNCATE TABLE `TB_CLIENT`;

-- admin, admin123
INSERT INTO `TB_USER` (username, password) VALUES ('admin', '$2a$10$9/Sj7fzfbcfT/2i3UNNdMuHQr01WPBtQyR.bB09WP3ZU6YxIVMRsG');
-- user, user123
INSERT INTO `TB_USER` (username, password) VALUES ('user', '$2a$10$L.AXNeXq5kV1YwrYAQqWKeTTBJCYc5PSfXmVRmzgGHDc1rT6cBq3y');

INSERT INTO `TB_ROLE` (role_name) VALUES ('ROLE_ADMIN');
INSERT INTO `TB_ROLE` (role_name) VALUES ('ROLE_USER');

INSERT INTO `TB_USERS_ROLES` (user_id, role_id) VALUES (1, 1);
INSERT INTO `TB_USERS_ROLES` (user_id, role_id) VALUES (1, 2);
INSERT INTO `TB_USERS_ROLES` (user_id, role_id) VALUES (2, 2);

INSERT INTO `TB_CLIENT` (cpf, name, sex, email, naturality, nacionality, dt_birthday, dt_create) 
    VALUES ('01234567890', 'Client no. 1', 'F', 'client1@tst.com.br', 'Curitiba', 'Brasil', '1980-03-02', CURRENT_TIMESTAMP);
INSERT INTO `TB_CLIENT` (cpf, name, sex, email, naturality, nacionality, dt_birthday, dt_create)  
    VALUES ('98765432109', 'Client no. 2', 'M', 'client2@tst.com.br', 'Joao Pessoa', 'Brasil', '1978-05-18', CURRENT_TIMESTAMP);
