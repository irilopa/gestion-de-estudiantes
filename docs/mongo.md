# Configuración MongoDB

## Usuario recomendado

No uses el usuario `root` para la aplicación.  
Crea un usuario específico solo con permisos sobre la base de datos de logs:

```js
use application_logs
db.createUser({
  user: "appLogger",
  pwd: "una_contraseña_segura",
  roles: [{ role: "readWrite", db: "application_logs" }]
})
```

## Configuración en el código

En `MongoLogger.java`:

```java
private static final String URI = "mongodb://appLogger:una_contraseña_segura@localhost:27017/?authSource=application_logs";
```

## Logs

Cada operación relevante genera un documento en la colección `logs` de la base `application_logs`.

## MongoDB Atlas

Si usas Atlas, usa la URI proporcionada por la plataforma y asegúrate de que el usuario tenga permisos sobre la base de datos de logs.
