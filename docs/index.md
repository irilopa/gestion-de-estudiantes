# Gestión de Estudiantes

Sistema de gestión académica en Java con MySQL y logs en MongoDB.

## Características

- Gestión de estudiantes y cursos
- Registro de notas
- Persistencia en MySQL
- Logs automáticos en MongoDB
- Uso de Lombok para simplificar modelos

## Arquitectura

- **Backend:** Java 21+
- **Base de datos principal:** MySQL
- **Logs:** MongoDB (recomendado usuario dedicado, no root)
- **ORM y utilidades:** JDBC, Lombok

## Buenas prácticas de seguridad

- No uses el usuario `root` de MongoDB para la aplicación.
- Crea un usuario específico solo con permisos sobre la base de datos de logs.
- Consulta la sección [Configuración MongoDB](mongo.md) para más detalles.

## Requisitos

- Java 21+
- MySQL
- MongoDB
- Maven

