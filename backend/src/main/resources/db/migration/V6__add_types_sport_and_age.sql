CREATE SEQUENCE IF NOT EXISTS age_id_seq START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE IF NOT EXISTS sport_id_seq START WITH 1 INCREMENT BY 1;

CREATE TABLE age_categories
(
    id              BIGINT NOT NULL,
    name_categories VARCHAR(255),
    CONSTRAINT pk_age_categories PRIMARY KEY (id)
);

CREATE TABLE sport
(
    id         BIGINT NOT NULL,
    name_sport VARCHAR(255),
    CONSTRAINT pk_sport PRIMARY KEY (id)
);

ALTER TABLE instructor
    ADD age_id BIGINT;

ALTER TABLE instructor
    ADD gender VARCHAR(255);

ALTER TABLE instructor
    ALTER COLUMN age_id SET NOT NULL;

ALTER TABLE student
    ADD age_id BIGINT;

ALTER TABLE student
    ADD gender VARCHAR(255);

ALTER TABLE student
    ALTER COLUMN age_id SET NOT NULL;

ALTER TABLE instructor
    ALTER COLUMN gender SET NOT NULL;

ALTER TABLE student
    ALTER COLUMN gender SET NOT NULL;

ALTER TABLE instructor
    ADD CONSTRAINT FK_INSTRUCTOR_ON_AGE FOREIGN KEY (age_id) REFERENCES age_categories (id);

ALTER TABLE student
    ADD CONSTRAINT FK_STUDENT_ON_AGE FOREIGN KEY (age_id) REFERENCES age_categories (id);