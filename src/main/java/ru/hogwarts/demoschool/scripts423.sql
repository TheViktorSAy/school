-- JOIN-запрос для получения имен и возрастов студентов вместе с названиями факультетов
SELECT s.name, s.age, f.namefaculty
FROM student s
INNER JOIN faculty f ON s.faculty_id = f.idfaculty;

-- JOIN-запрос для получения имен студентов, у которых есть аватарки
SELECT s.name
FROM student s
INNER JOIN avatar a ON s.avatar_id = a.id_avatar;