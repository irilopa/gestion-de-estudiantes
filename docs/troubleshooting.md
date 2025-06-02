# Solución de Problemas

!!! tip "Consejo"
    Antes de reportar un problema, asegúrese de revisar esta guía y verificar que está utilizando las versiones correctas de todas las dependencias.

## Problemas Comunes

### 1. Errores de Conexión a MySQL

??? warning "Error: Communications link failure"
    
    **Problema**: No se puede establecer conexión con MySQL.
    
    **Soluciones**:
    
    1. Verificar que MySQL está en ejecución:
       ```powershell
       Get-Service MySQL*
       ```
    
    2. Comprobar credenciales en `DatabaseConnection.java`:
       ```java
       private static final String URL = "jdbc:mysql://localhost:3306/highschool";
       private static final String USER = "root";
       private static final String PASSWORD = "tu_password";
       ```
    
    3. Verificar que el puerto 3306 está disponible:
       ```powershell
       Test-NetConnection -ComputerName localhost -Port 3306
       ```

### 2. Errores de MongoDB

??? warning "Error: MongoTimeoutException"
    
    **Problema**: No se puede conectar a MongoDB para los logs.
    
    **Soluciones**:
    
    1. Verificar que MongoDB está en ejecución:
       ```powershell
       Get-Service MongoDB*
       ```
    
    2. Comprobar credenciales en `MongoLogger.java`
    
    3. Verificar que el usuario tiene permisos:
       ```javascript
       use application_logs
       db.getUser("appLogger")
       ```

### 3. Errores de Java

??? warning "Error: ClassNotFoundException"
    
    **Problema**: No se encuentran las clases necesarias.
    
    **Soluciones**:
    
    1. Limpiar y recompilar el proyecto:
       ```powershell
       mvn clean install
       ```
    
    2. Verificar la versión de Java:
       ```powershell
       java -version
       ```

### 4. Errores de Datos

??? warning "Error: Duplicate entry for key 'PRIMARY'"
    
    **Problema**: Intento de insertar un registro con una clave primaria duplicada.
    
    **Soluciones**:
    
    1. Verificar si el estudiante ya existe
    2. Usar la opción de modificar en lugar de insertar
    3. Verificar la unicidad del NIF antes de insertar

## Mantenimiento Preventivo

1. **Backup Regular**
   ```powershell
   # Backup de MySQL
   mysqldump -u root -p highschool > backup_$(Get-Date -Format "yyyy-MM-dd").sql
   
   # Backup de logs MongoDB
   mongodump --db application_logs --out backup_$(Get-Date -Format "yyyy-MM-dd")
   ```

2. **Limpieza de Logs**
   - Eliminar logs más antiguos de 30 días
   - Verificar espacio en disco regularmente
   - Mantener respaldo de logs importantes

3. **Verificación de Integridad**
   - Ejecutar verificaciones de tablas periódicamente
   - Mantener índices optimizados
   - Monitorear el rendimiento de las consultas

## Contacto de Soporte

Si después de seguir esta guía sigue teniendo problemas:

1. Revise los [problemas conocidos](https://github.com/yourusername/gestion-de-estudiantes/issues)
2. Abra un nuevo issue con toda la información relevante
3. Contacte al equipo de soporte: soporte@example.com
