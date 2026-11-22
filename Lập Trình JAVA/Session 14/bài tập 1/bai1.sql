CREATE DATABASE IF NOT EXISTS transaction_student_db;
USE transaction_student_db;

CREATE TABLE students (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    age INT NOT NULL
);

DELIMITER $$

CREATE PROCEDURE add_students(
    IN in_name VARCHAR(100),
    IN in_age INT
)
BEGIN
    INSERT INTO students (name, age)
    VALUES (in_name, in_age);
END $$

DELIMITER ;
