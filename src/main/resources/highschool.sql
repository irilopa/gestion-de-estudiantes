DROP DATABASE IF EXISTS highschool;
CREATE DATABASE highschool;
USE highschool;

CREATE TABLE student(
    nif VARCHAR(10) PRIMARY KEY,
    name VARCHAR(30) NOT NULL,
    surname VARCHAR(30) NOT NULL,
    zipCode INT NOT NULL
);

CREATE TABLE course(
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    course_year INT
);

CREATE TABLE student_grade(
    nif VARCHAR(10) NOT NULL,
    course_id INT NOT NULL,
    grade INT NOT NULL,
    PRIMARY KEY(nif, course_id),
    CONSTRAINT fk_student_grade_student
    FOREIGN KEY (nif)
    REFERENCES student(nif),
    CONSTRAINT fk_student_grade_course
    FOREIGN KEY (course_id)
    REFERENCES course(id)
);

INSERT INTO student(nif, name, surname, zipCode)
VALUES ('1X', 'Bob', 'Esponja', 28000);

INSERT INTO student(nif, name, surname, zipCode)
VALUES ('2X', 'Peppa', 'Pig', 28001);

INSERT INTO course(name, course_year) VALUES('DAM', 2024);
INSERT INTO course(name, course_year) VALUES('DAW', 2024);

INSERT INTO student_grade(nif, course_id, grade)
VALUES('1X', 1, 7);

INSERT INTO student_grade(nif, course_id, grade)
VALUES('2X', 2, 5);
