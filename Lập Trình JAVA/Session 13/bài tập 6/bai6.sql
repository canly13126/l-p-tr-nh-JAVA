CREATE DATABASE IF NOT EXISTS todo_management_db;
USE todo_management_db;

CREATE TABLE tasks (
    id INT PRIMARY KEY AUTO_INCREMENT,
    task_name VARCHAR(255) NOT NULL,
    status VARCHAR(50) NOT NULL
);

DELIMITER $$

CREATE PROCEDURE add_task(
    IN in_task_name VARCHAR(255),
    IN in_status VARCHAR(50)
)
BEGIN
    INSERT INTO tasks (task_name, status)
    VALUES (in_task_name, in_status);
END $$

CREATE PROCEDURE list_tasks()
BEGIN
    SELECT * FROM tasks;
END $$

CREATE PROCEDURE update_task_status(
    IN in_task_id INT,
    IN in_status VARCHAR(50)
)
BEGIN
    UPDATE tasks
    SET status = in_status
    WHERE id = in_task_id;
END $$

CREATE PROCEDURE delete_task(
    IN in_task_id INT
)
BEGIN
    DELETE FROM tasks
    WHERE id = in_task_id;
END $$

CREATE PROCEDURE search_task_by_name(
    IN in_task_name VARCHAR(255)
)
BEGIN
    SELECT * FROM tasks
    WHERE task_name LIKE CONCAT('%', in_task_name, '%');
END $$

CREATE PROCEDURE task_statistics()
BEGIN
    SELECT
        SUM(CASE WHEN status = 'đã hoàn thành' THEN 1 ELSE 0 END) AS completed_count,
        SUM(CASE WHEN status = 'chưa hoàn thành' THEN 1 ELSE 0 END) AS uncompleted_count
    FROM tasks;
END $$

DELIMITER ;
