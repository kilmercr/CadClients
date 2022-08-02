CREATE TABLE IF NOT EXISTS `TB_USER` (
    `user_id` INT NOT NULL AUTO_INCREMENT,
    `username` VARCHAR(40),
    `password` VARCHAR(150),
    PRIMARY KEY (`user_id`))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `TB_ROLE` (
    `role_id` INT NOT NULL AUTO_INCREMENT,
    `role_name` VARCHAR(40),
    PRIMARY KEY (`role_id`))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `TB_USERS_ROLES` (
    `user_id` INT,
    `role_id` INT)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `TB_CLIENT` (
    `client_id` INT NOT NULL AUTO_INCREMENT,
    `cpf` VARCHAR(11) UNIQUE,
    `name` VARCHAR(60) NOT NULL,
    `sex` VARCHAR(1),
    `email` VARCHAR(100),
    `naturality` VARCHAR(40),
    `nacionality` VARCHAR(40),
    `dt_birthday` DATE,
    `dt_create` TIMESTAMP,
    `dt_update` TIMESTAMP,
    PRIMARY KEY (`client_id`))
ENGINE = InnoDB;

CREATE USER IF NOT EXISTS dbuser PASSWORD `dbuser`;
GRANT ALL ON TB_USER TO dbuser;
GRANT ALL ON TB_ROLE TO dbuser;
GRANT ALL ON TB_USERS_ROLES TO dbuser;
GRANT ALL ON TB_CLIENT TO dbuser;

TRUNCATE TABLE `TB_USER`;
TRUNCATE TABLE `TB_ROLE`;
TRUNCATE TABLE `TB_USERS_ROLES`;
TRUNCATE TABLE `TB_CLIENT`;

INSERT INTO `TB_USER` (username, password) VALUES('admin', '$2a$10$9/Sj7fzfbcfT/2i3UNNdMuHQr01WPBtQyR.bB09WP3ZU6YxIVMRsG');
INSERT INTO `TB_USER` (username, password) VALUES('user', '$2a$10$L.AXNeXq5kV1YwrYAQqWKeTTBJCYc5PSfXmVRmzgGHDc1rT6cBq3y');

INSERT INTO `TB_ROLE` (role_name) VALUES('ROLE_ADMIN');
INSERT INTO `TB_ROLE` (role_name) VALUES('ROLE_USER');

INSERT INTO `TB_USERS_ROLES` (user_id, role_id) VALUES(1, 1);
INSERT INTO `TB_USERS_ROLES` (user_id, role_id) VALUES(1, 2);
INSERT INTO `TB_USERS_ROLES` (user_id, role_id) VALUES(2, 2);
