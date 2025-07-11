ALTER TABLE instructor
    ADD user_id BIGINT;

ALTER TABLE sport_center
    ADD user_id BIGINT;

ALTER TABLE student
    ADD user_id BIGINT;

ALTER TABLE instructor
    ADD CONSTRAINT FK_INSTRUCTOR_ON_USER FOREIGN KEY (user_id) REFERENCES "user" (id);

ALTER TABLE sport_center
    ADD CONSTRAINT FK_SPORT_CENTER_ON_USER FOREIGN KEY (user_id) REFERENCES "user" (id);

ALTER TABLE student
    ADD CONSTRAINT FK_STUDENT_ON_USER FOREIGN KEY (user_id) REFERENCES "user" (id);

ALTER TABLE instructor
DROP
COLUMN id;

ALTER TABLE sport_center
DROP
COLUMN id;

ALTER TABLE student
DROP
COLUMN id;

ALTER TABLE "user"
DROP
COLUMN "id_субъекта";

ALTER TABLE instructor
    ADD CONSTRAINT pk_instructor PRIMARY KEY (user_id);

ALTER TABLE sport_center
    ADD CONSTRAINT pk_sport_center PRIMARY KEY (user_id);

ALTER TABLE student
    ADD CONSTRAINT pk_student PRIMARY KEY (user_id);