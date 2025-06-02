# Rendimiento y Requisitos del Sistema

## Requisitos Mínimos del Sistema

### Hardware
- Procesador: Dual-core 2GHz o superior
- Memoria RAM: 2GB mínimo, 4GB recomendado
- Espacio en disco: 500MB para la aplicación y bases de datos

### Software
- Sistema Operativo: Windows/Linux/MacOS
- Java Runtime Environment (JRE) 21 o superior
- MySQL 8.0 o superior
- MongoDB 4.4 o superior

## Métricas de Rendimiento

### Operaciones de Base de Datos
| Operación | Tiempo Promedio | Máximo Aceptable |
|-----------|----------------|------------------|
| Listar estudiantes (100 registros) | <100ms | 500ms |
| Insertar estudiante | <50ms | 200ms |
| Consultar notas | <150ms | 600ms |

### Uso de Recursos
- Memoria heap: 256MB máximo
- Conexiones simultáneas a MySQL: 10 máximo
- Tamaño de logs en MongoDB: ~1MB por día de uso intensivo

## Optimizaciones Implementadas

1. **Pooling de Conexiones**
   - Reutilización de conexiones a bases de datos
   - Límite máximo de conexiones configurado

2. **Consultas Optimizadas**
   - Índices en campos clave (NIF, course_id)
   - Consultas preparadas (PreparedStatement)

3. **Gestión de Memoria**
   - Cierre adecuado de recursos (try-with-resources)
   - Liberación de conexiones después de uso

## Recomendaciones de Uso

1. **Para mejor rendimiento:**
   - Mantener índices de MySQL actualizados
   - Limpiar logs antiguos de MongoDB periódicamente
   - Reiniciar la aplicación cada 24 horas de uso continuo

2. **Mantenimiento:**
   - Backup diario de la base de datos MySQL
   - Monitoreo del espacio de logs en MongoDB
   - Actualización regular de JRE y dependencias

3. **Escalabilidad:**
   - Soporta hasta 1000 estudiantes sin degradación
   - Hasta 50 cursos simultáneos
   - Historial de notas ilimitado
