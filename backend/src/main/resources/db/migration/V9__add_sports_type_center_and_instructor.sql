CREATE TABLE center_types_sports
(
    center_id BIGINT NOT NULL,
    sport_id  BIGINT NOT NULL,
    CONSTRAINT pk_center_types_sports PRIMARY KEY (center_id, sport_id)
);

CREATE TABLE instructor_sports
(
    instructor_id BIGINT NOT NULL,
    sport_id      BIGINT NOT NULL,
    CONSTRAINT pk_instructor_sports PRIMARY KEY (instructor_id, sport_id)
);

ALTER TABLE center_types_sports
    ADD CONSTRAINT fk_centypspo_on_sport FOREIGN KEY (sport_id) REFERENCES sport (id);

ALTER TABLE center_types_sports
    ADD CONSTRAINT fk_centypspo_on_sports_center FOREIGN KEY (center_id) REFERENCES sport_center (user_id);

ALTER TABLE instructor_sports
    ADD CONSTRAINT fk_insspo_on_instructor FOREIGN KEY (instructor_id) REFERENCES instructor (user_id);

ALTER TABLE instructor_sports
    ADD CONSTRAINT fk_insspo_on_sport FOREIGN KEY (sport_id) REFERENCES sport (id);