# Base de Datos

El sistema utiliza MySQL para almacenar la información académica y MongoDB para logs.

## Estructura de Base de Datos MySQL

### Tablas principales
- `student` (nif, name, surname, zipCode)
- `course` (id, name, course_year)
- `student_grade` (nif, course_id, grade)

### Script de creación

```sql
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
    FOREIGN KEY (nif) REFERENCES student(nif),
    FOREIGN KEY (course_id) REFERENCES course(id)
);
```

## Ejemplo de consulta SQL

```sql
SELECT name, surname FROM student WHERE zipCode = 28000;
```

## Logs en MongoDB

Cada operación relevante genera un documento en la colección `logs` de la base `application_logs`.
