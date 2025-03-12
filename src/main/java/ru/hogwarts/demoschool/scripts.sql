-- Получить всех студентов, возраст которых находится между 10 и 20 (можно подставить любые числа, главное, чтобы нижняя граница была меньше верхней).
SELECT * FROM hogwarts WHERE age BETWEEN 10 AND 20;

-- Получить всех студентов, но отобразить только список их имен.
SELECT name FROM hogwarts;

-- Получить всех студентов, у которых в имени присутствует буква О (или любая другая).
SELECT * FROM hogwarts WHERE name ILIKE '%г%';

-- Получить всех студентов, у которых возраст меньше идентификатора.
SELECT * FROM hogwarts WHERE age < id;

-- Получить всех студентов упорядоченных по возрасту
SELECT * FROM hogwarts ORDER BY age;

INSERT INTO public.hogwarts (name, age, id) VALUES ('Гарри Поттер', 11, 1);

DELETE FROM public.hogwarts WHERE name = 'Гарри Поттер';

INSERT INTO public.hogwarts (ID, name, age, "namefaculty", "colorfaculty", "idfaculty")
VALUES (10, 'Гарри Поттер', 11, 'Гриффендор', 'Красный', 1);

INSERT INTO public.hogwarts (ID, name, age, "namefaculty", "colorfaculty", "idfaculty")
VALUES (10, 'Гермиона', 12, 'Гриффендор', 'Красный', 1);

INSERT INTO public.hogwarts (ID, name, age, "namefaculty", "colorfaculty", "idfaculty")
VALUES (10, 'Василий', 50, 'Гриффендор', 'Красный', 1);