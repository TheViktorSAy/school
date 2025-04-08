- Удаление таблиц, если они существуют
DROP TABLE IF EXISTS person CASCADE;
DROP TABLE IF EXISTS car CASCADE;
DROP TABLE IF EXISTS broom CASCADE;

-- Создание таблицы "человек"
CREATE TABLE person (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    age INT,
    has_license BOOLEAN,
    car_id INT,
    FOREIGN KEY (car_id) REFERENCES car(id)
);

-- Создание таблицы "машина"
CREATE TABLE car (
    id SERIAL PRIMARY KEY,
    brand VARCHAR(50),
    model VARCHAR(50),
    price DECIMAL(10, 2)
);