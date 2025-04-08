-- liquibase formatted sql

-- changeset vkostenko create_student_name_index
CREATE INDEX idx_student_name ON public.student (name);
-- changeset vkostenko create_faculty_name_color_index
CREATE INDEX idx_faculty_name_color ON public.faculty (namefaculty, colorfaculty);

SELECT * FROM information_schema.tables WHERE table_name IN ('student', 'faculty');

DROP INDEX IF EXISTS idx_student_name;
DROP INDEX IF EXISTS idx_faculty_name_color;

SELECT indexname
FROM pg_indexes
WHERE tablename IN ('student', 'faculty');

