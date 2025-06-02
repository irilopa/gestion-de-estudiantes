# Arquitectura

El sistema sigue una arquitectura basada en el patrón DAO (Data Access Object) para separar la lógica de acceso a datos de la lógica de negocio. No utiliza frameworks como Spring Boot ni expone endpoints REST.

## Componentes principales

- **Main.java**: Punto de entrada. Presenta un menú interactivo en consola para gestionar estudiantes, cursos y notas.
- **DAO**: Interfaces y clases de acceso a datos para estudiantes, cursos y calificaciones, implementadas con JDBC.
- **Modelos**: Clases Java simples (`Student`, `Course`, `StudentGrade`) que representan las entidades del sistema.
- **Utilidades**: Clases para la conexión a MySQL (`DatabaseConnection`) y para el registro de logs en MongoDB (`MongoLogger`).

## Diagrama de paquetes

```
org.ivan
├── Main.java
├── dao
│   ├── StudentDAO.java / StudentDAOImpl.java
│   ├── CourseDAO.java / CourseDAOImpl.java
│   └── StudentGradeDAO.java / StudentGradeDAOImpl.java
├── model
│   ├── Student.java
│   ├── Course.java
│   └── StudentGrade.java
└── util
    ├── DatabaseConnection.java
    └── MongoLogger.java
```

## Flujo de ejecución

1. El usuario interactúa con el menú en consola.
2. Se invocan métodos DAO para realizar operaciones sobre la base de datos MySQL.
3. Las operaciones relevantes generan logs en MongoDB.

## Ejemplo de uso

```java
Student nuevo = new Student("1X", "Bob", "Esponja", 28000);
studentDAO.insertarEstudiante(nuevo);
```
