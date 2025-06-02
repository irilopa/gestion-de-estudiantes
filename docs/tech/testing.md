# Pruebas del Sistema

## Estructura de Pruebas

El proyecto utiliza pruebas unitarias y de integración para asegurar la calidad del código.

### Pruebas Unitarias Recomendadas

1. **DAOs**
```java
@Test
void testInsertarEstudiante() {
    Student estudiante = new Student("1X", "Test", "User", 28001);
    assertTrue(studentDAO.insertarEstudiante(estudiante));
}
```

2. **Modelos**
```java
@Test
void testStudentModel() {
    Student student = new Student("2X", "Test", "User", 28002);
    assertEquals("2X", student.getNif());
    assertEquals("Test", student.getName());
}
```

## Cobertura de Código Objetivo

| Componente | Cobertura Mínima |
|------------|------------------|
| Models     | 95%             |
| DAOs       | 85%             |
| Utils      | 80%             |

## Configuración de Pruebas

### Base de Datos de Prueba
```sql
CREATE DATABASE highschool_test;
USE highschool_test;
-- Usar el mismo esquema que en producción
```

### MongoDB de Prueba
```javascript
use application_logs_test
db.createUser({
  user: "testLogger",
  pwd: "test_password",
  roles: [{ role: "readWrite", db: "application_logs_test" }]
})
```

## Ejecución de Pruebas

```powershell
# Ejecutar todas las pruebas
mvn test

# Ejecutar pruebas de un paquete específico
mvn test -Dtest="org.ivan.dao.*Test"
```

## Buenas Prácticas

1. Aislar pruebas de base de datos
2. Limpiar datos después de cada prueba
3. Usar datos de prueba predecibles
4. Documentar casos de prueba
