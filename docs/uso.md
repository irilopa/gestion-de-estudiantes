# Guía de Uso

!!! note "Nota"
    Las capturas de pantalla son ejemplos y pueden variar ligeramente según la versión del sistema.

## Menú Principal

Al iniciar la aplicación, se muestra el menú principal:

```
--- Sistema de Gestión Académica ---
1. Listar Estudiantes
2. Insertar Estudiante
3. Eliminar Estudiante
4. Modificar Estudiante
------------------------------------
5. Listar Cursos
6. Insertar Curso
7. Eliminar Curso
------------------------------------
8. Mostrar Notas de Estudiante
9. Añadir Nota
0. Salir
```

## Ejemplos de Uso

### 1. Gestión de Estudiantes

=== "Listar Estudiantes"
    ```
    --- Lista de Estudiantes ---
    Student(nif=1X, name=Bob, surname=Esponja, zipCode=28000)
    Student(nif=2X, name=Peppa, surname=Pig, zipCode=28001)
    ```

=== "Insertar Estudiante"
    ```
    NIF del estudiante: 3X
    Nombre del estudiante: Juan
    Apellidos del estudiante: Pérez
    Código Postal: 28002
    Estudiante insertado con éxito.
    ```

=== "Modificar Estudiante"
    ```
    NIF del estudiante a modificar: 3X
    Estudiante actual: Student(nif=3X, name=Juan, surname=Pérez, zipCode=28002)
    Nuevo nombre (dejar en blanco para no modificar): Juan José
    Nuevos apellidos (dejar en blanco para no modificar): 
    Nuevo código postal (0 para no modificar): 0
    Estudiante modificado con éxito.
    ```

### 2. Gestión de Cursos

=== "Listar Cursos"
    ```
    --- Lista de Cursos ---
    Course(id=1, name=DAM, course_year=2024)
    Course(id=2, name=DAW, course_year=2024)
    ```

=== "Insertar Curso"
    ```
    Nombre del curso: ASIR
    Año del curso: 2024
    Curso insertado con éxito.
    ```

### 3. Gestión de Calificaciones

=== "Mostrar Notas"
    ```
    NIF del estudiante para mostrar notas: 1X
    --- Notas del Estudiante 1X ---
    StudentGrade(nif=1X, courseId=1, grade=7.0)
    StudentGrade(nif=1X, courseId=2, grade=8.5)
    ```

=== "Añadir Nota"
    ```
    NIF del estudiante: 1X
    ID del curso: 1
    Valor de la nota (0.0-10.0): 9.5
    Nota añadida con éxito.
    ```

!!! tip "Consejo"
    Siempre verifica los IDs de estudiantes y cursos antes de añadir notas.

## Mensajes de Error Comunes

!!! warning "Error: Estudiante no encontrado"
    ```
    No se ha podido encontrar el estudiante.
    ```

!!! warning "Error: Nota inválida"
    ```
    La nota debe estar entre 0.0 y 10.0.
    ```

!!! warning "Error: Curso no encontrado"
    ```
    No se ha podido encontrar el curso.
    ```

## Operaciones con Estudiantes

### Insertar Estudiante
1. Seleccionar opción 2
2. Ingresar datos:
   - NIF (formato válido, ej: "12345678A")
   - Nombre
   - Apellidos
   - Código Postal (número de 5 dígitos)

Ejemplo:
```
NIF del estudiante: 12345678A
Nombre del estudiante: Ana
Apellidos del estudiante: García López
Código Postal: 28001
```

### Modificar Estudiante
1. Seleccionar opción 4
2. Ingresar NIF del estudiante
3. Modificar los campos deseados (dejar en blanco para mantener valor actual)

## Operaciones con Cursos

### Insertar Curso
1. Seleccionar opción 6
2. Ingresar:
   - Nombre del curso
   - Año del curso

Ejemplo:
```
Nombre del curso: DAM
Año del curso: 2024
```

## Gestión de Calificaciones

### Añadir Nota
1. Seleccionar opción 9
2. Ingresar:
   - NIF del estudiante
   - ID del curso
   - Calificación (0-10)

Ejemplo:
```
NIF del estudiante: 12345678A
ID del curso: 1
Valor de la nota (0.0-10.0): 8.5
```

## Consultas

### Mostrar Notas de Estudiante
1. Seleccionar opción 8
2. Ingresar NIF del estudiante
3. Se mostrarán todas las notas del estudiante en sus diferentes cursos

## Tips y Consejos

1. **Antes de añadir una nota:**
   - Verificar que el estudiante existe (opción 1)
   - Verificar que el curso existe (opción 5)

2. **Modificación de datos:**
   - Al modificar estudiantes, puedes dejar campos en blanco para mantener los valores actuales

3. **Eliminación de registros:**
   - Asegúrate de no tener dependencias antes de eliminar (por ejemplo, notas asociadas)
