CREATE DATABASE IF NOT EXISTS movie_management_db;
USE movie_management_db;

CREATE TABLE movies (
    id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(200) NOT NULL,
    director VARCHAR(100) NOT NULL,
    release_year INT NOT NULL
);

DELIMITER $$

CREATE PROCEDURE add_movie(
    IN in_title VARCHAR(200),
    IN in_director VARCHAR(100),
    IN in_year INT
)
BEGIN
    INSERT INTO movies (title, director, release_year)
    VALUES (in_title, in_director, in_year);
END $$

CREATE PROCEDURE list_movies()
BEGIN
    SELECT * FROM movies;
END $$

CREATE PROCEDURE update_movie(
    IN in_id INT,
    IN in_title VARCHAR(200),
    IN in_director VARCHAR(100),
    IN in_year INT
)
BEGIN
    UPDATE movies
    SET title = in_title,
        director = in_director,
        release_year = in_year
    WHERE id = in_id;
END $$

CREATE PROCEDURE delete_movie(
    IN in_id INT
)
BEGIN
    DELETE FROM movies
    WHERE id = in_id;
END $$

DELIMITER ;
