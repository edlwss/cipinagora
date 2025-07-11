CREATE SEQUENCE IF NOT EXISTS role_id_seq START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE IF NOT EXISTS user_id_seq START WITH 1 INCREMENT BY 1;

CREATE TABLE role
(
    id        BIGINT       NOT NULL,
    name_role VARCHAR(255) NOT NULL,
    CONSTRAINT pk_role PRIMARY KEY (id)
);

CREATE TABLE "user"
(
    id               BIGINT       NOT NULL,
    "id_субъекта"    BIGINT       NOT NULL,
    login            VARCHAR(255) NOT NULL,
    password         VARCHAR(255) NOT NULL,
    email            VARCHAR(255),
    number_phone     VARCHAR(255),
    data_regisration TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    role             BIGINT       NOT NULL,
    CONSTRAINT pk_user PRIMARY KEY (id)
);

ALTER TABLE role
    ADD CONSTRAINT uc_role_name_role UNIQUE (name_role);

ALTER TABLE "user"
    ADD CONSTRAINT uc_user_email UNIQUE (email);

ALTER TABLE "user"
    ADD CONSTRAINT uc_user_login UNIQUE (login);

ALTER TABLE "user"
    ADD CONSTRAINT FK_USER_ON_ROLE FOREIGN KEY (role) REFERENCES role (id);
