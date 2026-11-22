USE transaction_student_db;

DELIMITER $$

CREATE PROCEDURE update_student(
    IN in_id INT,
    IN in_name VARCHAR(100),
    IN in_age INT
)
BEGIN
    UPDATE students
    SET name = in_name,
        age = in_age
    WHERE id = in_id;
END $$

DELIMITER ;
