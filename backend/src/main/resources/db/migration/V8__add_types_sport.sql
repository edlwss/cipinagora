ALTER TABLE instructor
    ADD CONSTRAINT chk_instructor_gender CHECK (gender IN ('Male', 'Female'));

ALTER TABLE student
    ADD CONSTRAINT chk_student_gender CHECK (gender IN ('Male', 'Female'));

INSERT INTO age_categories (name_categories) VALUES
                                                 ('3 - 4 года'),
                                                 ('5 - 7 лет'),
                                                 ('8 - 10 лет'),
                                                 ('11 - 14 лет'),
                                                 ('15 - 18 лет'),
                                                 ('19 - 25 лет'),
                                                 ('26 - 35 лет'),
                                                 ('35 - 50 лет'),
                                                 ('Старше 50');