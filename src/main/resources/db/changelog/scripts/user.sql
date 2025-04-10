-- liquibase formatted sql
--changeset vkostenko:create_index
--preconditions onFail:MARK_RAN onError:HALT


CREATE INDEX idx_student_name ON student (name);

CREATE INDEX idx_faculty_name_color ON faculty (namefaculty, colorfaculty);



