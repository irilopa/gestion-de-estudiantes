# API interna (clases y métodos)

Este sistema no expone una API REST. La interacción se realiza mediante un menú en consola y llamadas a métodos Java.

## Clases principales

- **StudentDAO**: Métodos para listar, insertar, eliminar, modificar y consultar estudiantes.
- **CourseDAO**: Métodos para listar, insertar, eliminar y consultar cursos.
- **StudentGradeDAO**: Métodos para insertar y consultar calificaciones.

## Ejemplo de uso de métodos DAO

```java
// Listar estudiantes
List<Student> estudiantes = studentDAO.listarEstudiantes();

// Insertar un curso
Course nuevoCurso = new Course(0, "DAM", 2024);
courseDAO.insertarCurso(nuevoCurso);

// Añadir nota
StudentGrade nota = new StudentGrade("1X", 1, 8.5);
studentGradeDAO.insertarNota(nota);
```

## Menú de consola

El usuario puede:
- Listar, insertar, eliminar y modificar estudiantes
- Listar, insertar y eliminar cursos
- Mostrar y añadir notas

No existen endpoints HTTP ni recursos REST.
