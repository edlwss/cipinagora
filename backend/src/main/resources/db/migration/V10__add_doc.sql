CREATE SEQUENCE IF NOT EXISTS doc_id_seq START WITH 1 INCREMENT BY 1;

CREATE TABLE document
(
    id            BIGINT DEFAULT nextval('doc_id_seq') PRIMARY KEY,
    instructor_id BIGINT NOT NULL,
    doc           VARCHAR(255) NOT NULL,
    CONSTRAINT pk_document PRIMARY KEY (id)
);

ALTER TABLE document
    ADD CONSTRAINT FK_DOCUMENT_ON_INSTRUCTOR FOREIGN KEY (instructor_id) REFERENCES instructor (user_id);