CREATE SEQUENCE IF NOT EXISTS instructor_id_seq START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE IF NOT EXISTS sport_center_id_seq START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE IF NOT EXISTS student_id_seq START WITH 1 INCREMENT BY 1;

CREATE TABLE instructor
(
    id                  BIGINT       NOT NULL,
    photo               VARCHAR(255) NOT NULL,
    skill_description   VARCHAR(255) NOT NULL,
    certificate_number  VARCHAR(255) NOT NULL,
    data_verified       BOOLEAN      NOT NULL,
    official_employment BOOLEAN      NOT NULL,
    last_name           VARCHAR(255) NOT NULL,
    first_name          VARCHAR(255) NOT NULL,
    middle_name         VARCHAR(255),
    CONSTRAINT pk_instructor PRIMARY KEY (id)
);

CREATE TABLE sport_center
(
    id          BIGINT       NOT NULL,
    name        VARCHAR(255) NOT NULL,
    description VARCHAR(255) NOT NULL,
    coordinates VARCHAR(255) NOT NULL,
    region      VARCHAR(255) NOT NULL,
    city        VARCHAR(255) NOT NULL,
    street      VARCHAR(255) NOT NULL,
    building    VARCHAR(255) NOT NULL,
    CONSTRAINT pk_sport_center PRIMARY KEY (id)
);

CREATE TABLE student
(
    id          BIGINT       NOT NULL,
    photo       VARCHAR(255),
    last_name   VARCHAR(255) NOT NULL,
    first_name  VARCHAR(255) NOT NULL,
    middle_name VARCHAR(255),
    CONSTRAINT pk_student PRIMARY KEY (id)
);