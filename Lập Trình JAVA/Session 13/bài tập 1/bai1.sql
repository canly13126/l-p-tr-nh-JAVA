CREATE DATABASE IF NOT EXISTS student_management_db;
USE student_management_db;

CREATE TABLE Students (
    student_id INT PRIMARY KEY AUTO_INCREMENT,
    full_name VARCHAR(100) NOT NULL,
    date_of_birth DATE NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE
);
DELIMITER $$

CREATE PROCEDURE get_all_students()
BEGIN
    SELECT * FROM Students;
END $$

CREATE PROCEDURE add_student(
    IN in_full_name VARCHAR(100),
    IN in_date_of_birth DATE,
    IN in_email VARCHAR(100)
)
BEGIN
    INSERT INTO Students (full_name, date_of_birth, email)
    VALUES (in_full_name, in_date_of_birth, in_email);
END $$

CREATE PROCEDURE update_student(
    IN in_id INT,
    IN in_full_name VARCHAR(100),
    IN in_date_of_birth DATE,
    IN in_email VARCHAR(100)
)
BEGIN
    UPDATE Students
    SET full_name = in_full_name,
        date_of_birth = in_date_of_birth,
        email = in_email
    WHERE student_id = in_id;
END $$

CREATE PROCEDURE find_student_by_id(
    IN in_id INT
)
BEGIN
    SELECT * FROM Students
    WHERE student_id = in_id;
END $$

CREATE PROCEDURE delete_student(
    IN in_id INT
)
BEGIN
    DELETE FROM Students
    WHERE student_id = in_id;
END $$

DELIMITER ;

CALL add_student('Nguyen Van An', '2003-05-15', 'an.nguyen@example.com');
CALL add_student('Tran Thi Bich', '2004-08-20', 'bich.tran@example.com');

CALL get_all_students();

CALL find_student_by_id(1);

CALL update_student(1, 'Nguyen Van An Updated', '2003-05-15', 'an.updated@example.com');
CALL find_student_by_id(1);

CALL delete_student(2);
CALL get_all_students();
