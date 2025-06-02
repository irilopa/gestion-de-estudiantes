# Instalación

1. **Clona el repositorio**
   ```bash
   git clone <url-del-repo>
   cd gestion-de-estudiantes
   ```

2. **Configura la base de datos MySQL**
   - Crea la base de datos `highschool` y los usuarios necesarios.
   - Ajusta usuario y contraseña en `DatabaseConnection.java` si es necesario.

3. **Configura MongoDB**
   - Asegúrate de tener MongoDB en ejecución.
   - **No uses el usuario `root` para la aplicación.**  
     Crea un usuario específico para logs (ver [Configuración MongoDB](mongo.md)).

4. **Compila el proyecto**
   ```bash
   mvn clean install
   ```

5. **Ejecuta la aplicación**
   ```bash
   mvn exec:java -Dexec.mainClass="org.ivan.Main"
   ```

