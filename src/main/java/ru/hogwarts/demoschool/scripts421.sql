-- Ограничение: Возраст студента не может быть меньше 16 лет
ALTER TABLE  public.student
ADD CONSTRAINT age_check CHECK (age >= 21);

-- Ограничение: Имена студентов должны быть уникальными и не равны нулю
ALTER TABLE student
ADD CONSTRAINT not_null_name CHECK (name IS NOT NULL);

-- Ограничение: Пара "значение названия" - "цвет факультета" должна быть уникальной
ALTER TABLE faculty
ADD CONSTRAINT unique_faculty_color UNIQUE (namefaculty, colorfaculty);

-- Ограничение: При создании студента без возраста ему автоматически должно присваиваться 20 лет
ALTER TABLE student
ADD CONSTRAINT check_age CHECK (age>=21);
