# Guía de Contribución

## Cómo Contribuir

1. **Fork del Repositorio**
   - Crear un fork del proyecto en GitHub
   - Clonar localmente tu fork

2. **Configurar el Entorno de Desarrollo**
   - Instalar JDK 21
   - Configurar MySQL y MongoDB
   - Importar el proyecto en tu IDE favorito

3. **Convenciones de Código**
   - Indentación: 4 espacios
   - Nomenclatura:
     - Clases: PascalCase
     - Métodos y variables: camelCase
     - Constantes: UPPER_SNAKE_CASE
   - Documentación: Javadoc en clases y métodos públicos
   - Idioma: Español para nombres de métodos y variables

4. **Proceso de Desarrollo**
   ```
   1. Crear rama feature/nombre-caracteristica
   2. Desarrollar los cambios
   3. Ejecutar pruebas
   4. Commit con mensaje descriptivo
   5. Push a tu fork
   6. Crear Pull Request
   ```

5. **Pruebas**
   - Crear pruebas unitarias para nuevas funcionalidades
   - Verificar integración con bases de datos
   - Probar manejo de errores

## Pull Requests

1. **Antes de Crear PR**
   - [x] Código sigue convenciones
   - [x] Pruebas pasan localmente
   - [x] Documentación actualizada

2. **Información en PR**
   - Descripción clara del cambio
   - Referencias a issues relacionados
   - Capturas de pantalla (si aplica)

## Reportar Problemas

Al crear un issue, incluir:
- Versión del sistema
- Pasos para reproducir
- Comportamiento esperado vs actual
- Logs relevantes

## Contacto

Para dudas o sugerencias:
- Crear un issue en GitHub
- Enviar email a: [correo del mantenedor]
