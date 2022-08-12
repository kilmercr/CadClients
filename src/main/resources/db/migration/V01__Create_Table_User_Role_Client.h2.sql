CREATE TABLE IF NOT EXISTS TB_USER (
    user_id BIGINT NOT NULL AUTO_INCREMENT,
    username VARCHAR(40),
    password VARCHAR(150),
    PRIMARY KEY (user_id))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS TB_ROLE (
    role_id BIGINT NOT NULL AUTO_INCREMENT,
    role_name VARCHAR(40),
    PRIMARY KEY (role_id))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS TB_USERS_ROLES (
    user_id BIGINT,
    role_id BIGINT)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS TB_CLIENT (
    client_id BIGINT NOT NULL AUTO_INCREMENT,
    cpf VARCHAR(11) UNIQUE,
    name VARCHAR(60) NOT NULL,
    sex VARCHAR(1),
    email VARCHAR(100),
    naturality VARCHAR(40),
    nacionality VARCHAR(40),
    dt_birthday DATE NOT NULL,
    dt_create TIMESTAMP NOT NULL,
    dt_update TIMESTAMP,
    PRIMARY KEY (client_id))
ENGINE = InnoDB;

CREATE USER IF NOT EXISTS dbuser PASSWORD dbuser;
GRANT ALL ON TB_USER TO dbuser;
GRANT ALL ON TB_ROLE TO dbuser;
GRANT ALL ON TB_USERS_ROLES TO dbuser;
GRANT ALL ON TB_CLIENT TO dbuser;
