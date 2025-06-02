# Guía de Instalación

## Requisitos Previos

### Software Necesario
- Java JDK 21 o superior
- MySQL 8.0 o superior
- MongoDB (para logging)
- Maven 3.8 o superior

### Bases de Datos
1. **MySQL**
   ```sql
   CREATE DATABASE highschool;
   CREATE USER 'gestionapp'@'localhost' IDENTIFIED BY 'tu_contraseña';
   GRANT ALL PRIVILEGES ON highschool.* TO 'gestionapp'@'localhost';
   FLUSH PRIVILEGES;
   ```

2. **MongoDB**
   ```js
   use application_logs
   db.createUser({
     user: "appLogger",
     pwd: "tu_contraseña_segura",
     roles: [{ role: "readWrite", db: "application_logs" }]
   })
   ```

## Pasos de Instalación

1. **Clonar el Repositorio**
   ```bash
   git clone <url-del-repo>
   cd gestion-de-estudiantes
   ```

2. **Configurar la Base de Datos**
   - Ejecutar el script de inicialización:
     ```bash
     mysql -u root -p highschool < src/main/resources/highschool.sql
     ```
   - Modificar credenciales en `src/main/java/org/ivan/util/DatabaseConnection.java`
   - Modificar credenciales en `src/main/java/org/ivan/util/MongoLogger.java`

3. **Compilar el Proyecto**
   ```bash
   mvn clean install
   ```

4. **Ejecutar la Aplicación**
   ```bash
   mvn exec:java -Dexec.mainClass="org.ivan.Main"
   ```

## Verificación de la Instalación

1. La aplicación debería mostrar el menú principal
2. Verificar conexión a MySQL intentando listar estudiantes
3. Verificar logs en MongoDB:
   ```js
   use application_logs
   db.logs.find()
   ```

## Solución de Problemas

### Errores Comunes

1. **Error de conexión a MySQL**
   - Verificar que el servicio MySQL esté corriendo
   - Comprobar credenciales en DatabaseConnection.java
   - Verificar que la base de datos existe

2. **Error de conexión a MongoDB**
   - Verificar que el servicio MongoDB esté corriendo
   - Comprobar credenciales en MongoLogger.java
   - Verificar que el usuario tiene permisos correctos

