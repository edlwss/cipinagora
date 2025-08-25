ALTER TABLE instructor
    ALTER COLUMN user_id SET DEFAULT nextval('instructor_id_seq');

ALTER TABLE sport_center
    ALTER COLUMN user_id SET DEFAULT nextval('sport_center_id_seq');

ALTER TABLE student
    ALTER COLUMN user_id SET DEFAULT nextval('student_id_seq');

ALTER TABLE age_categories
    ALTER COLUMN id SET DEFAULT nextval('age_id_seq');

ALTER TABLE sport
    ALTER COLUMN id SET DEFAULT nextval('sport_id_seq');

ALTER TABLE role
    ALTER COLUMN id SET DEFAULT nextval('role_id_seq');

ALTER TABLE users
    ALTER COLUMN id SET DEFAULT nextval('user_id_seq');
