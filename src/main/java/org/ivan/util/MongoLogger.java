package org.ivan.util;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.time.LocalDateTime;

public class MongoLogger {
    // ¡No es buena práctica usar el usuario root!
    // Crea un usuario específico para la aplicación con permisos solo sobre la base de datos de logs.
    // Por ejemplo:
    // db.createUser({user: "appLogger", pwd: "tu_password", roles: [{role: "readWrite", db: "application_logs"}]})
    // Luego usa ese usuario en la URI:
    // private static final String URI = "mongodb://appLogger:tu_password@localhost:27017/?authSource=application_logs";
    private static final String URI = "mongodb://root:Sandia4you@localhost:27017/?authSource=admin";
    private static final String DB_NAME = "application_logs";
    private static final String COLLECTION = "logs";
    private static MongoCollection<Document> collection;

    static {
        MongoClient mongoClient = new MongoClient(new MongoClientURI(URI));
        MongoDatabase database = mongoClient.getDatabase(DB_NAME);
        collection = database.getCollection(COLLECTION);
    }

    public static void log(String level, String message) {
        Document doc = new Document()
                .append("timestamp", LocalDateTime.now().toString())
                .append("level", level)
                .append("message", message);
        collection.insertOne(doc);
    }

    public static void info(String message) {
        log("INFO", message);
    }

    public static void error(String message) {
        log("ERROR", message);
    }
}
