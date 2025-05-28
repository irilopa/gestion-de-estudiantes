package org.ivan;

import org.ivan.dao.*;
import org.ivan.model.Course;
import org.ivan.model.Student;
import org.ivan.model.StudentGrade;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final StudentDAO studentDAO = new StudentDAOImpl();
    private static final CourseDAO courseDAO = new CourseDAOImpl();
    private static final StudentGradeDAO studentGradeDAO = new StudentGradeDAOImpl();

    public static void main(String[] args) {
        int opcion;
        do {
            mostrarMenu();
            opcion = leerOpcion();

            switch (opcion) {
                case 1:
                    listarEstudiantes();
                    break;
                case 2:
                    insertarEstudiante();
                    break;
                case 3:
                    eliminarEstudiante();
                    break;
                case 4:
                    modificarEstudiante();
                    break;
                case 5:
                    listarCursos();
                    break;
                case 6:
                    insertarCurso();
                    break;
                case 7:
                    eliminarCurso();
                    break;
                case 8:
                    mostrarNotasDeEstudiante();
                    break;
                case 9:
                    añadirNota();
                    break;
                case 0:
                    System.out.println("Saliendo de la aplicación. ¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción no válida. Inténtalo de nuevo.");
            }
            System.out.println("\n--- Presiona Enter para continuar ---");
            scanner.nextLine();
        } while (opcion != 0);

        scanner.close();
    }

    private static void mostrarMenu() {
        System.out.println("\n--- Sistema de Gestión Académica ---");
        System.out.println("1. Listar Estudiantes");
        System.out.println("2. Insertar Estudiante");
        System.out.println("3. Eliminar Estudiante");
        System.out.println("4. Modificar Estudiante");
        System.out.println("------------------------------------");
        System.out.println("5. Listar Cursos");
        System.out.println("6. Insertar Curso");
        System.out.println("7. Eliminar Curso");
        System.out.println("------------------------------------");
        System.out.println("8. Mostrar Notas de Estudiante");
        System.out.println("9. Añadir Nota");
        System.out.println("0. Salir");
        System.out.print("Elige una opción: ");
    }

    private static int leerOpcion() {
        while (!scanner.hasNextInt()) {
            System.out.println("Entrada no válida. Por favor, ingresa un número.");
            scanner.next();
            System.out.print("Elige una opción: ");
        }
        int opcion = scanner.nextInt();
        scanner.nextLine();
        return opcion;
    }

    private static void listarEstudiantes() {
        List<Student> estudiantes = studentDAO.listarEstudiantes();
        if (estudiantes.isEmpty()) {
            System.out.println("No hay estudiantes registrados.");
        } else {
            System.out.println("\n--- Lista de Estudiantes ---");
            estudiantes.forEach(System.out::println);
        }
    }

    private static void insertarEstudiante() {
        System.out.print("NIF del estudiante: ");
        String nif = scanner.nextLine();
        System.out.print("Nombre del estudiante: ");
        String nombre = scanner.nextLine();
        System.out.print("Apellidos del estudiante: ");
        String apellidos = scanner.nextLine();
        System.out.print("Código Postal: ");
        int zipCode = Integer.parseInt(scanner.nextLine());

        Student nuevoEstudiante = new Student(nif, nombre, apellidos, zipCode);
        if (studentDAO.insertarEstudiante(nuevoEstudiante)) {
            System.out.println("Estudiante insertado con éxito.");
        } else {
            System.out.println("Error al insertar el estudiante.");
        }
    }

    private static void eliminarEstudiante() {
        System.out.print("NIF del estudiante a eliminar: ");
        String nif = scanner.nextLine();

        if (studentDAO.eliminarEstudiante(nif)) {
            System.out.println("Estudiante eliminado con éxito.");
        } else {
            System.out.println("No se ha podido encontrar el estudiante.");
        }
    }

    private static void modificarEstudiante() {
        System.out.print("NIF del estudiante a modificar: ");
        String nif = scanner.nextLine();

        Student estudianteExistente = studentDAO.obtenerEstudiantePorNif(nif);
        if (estudianteExistente == null) {
            System.out.println("No se ha podido encontrar el estudiante.");
            return;
        }

        System.out.println("Estudiante actual: " + estudianteExistente);

        System.out.print("Nuevo nombre (dejar en blanco para no modificar): ");
        String nombre = scanner.nextLine();
        if (!nombre.isEmpty()) {
            estudianteExistente.setName(nombre);
        }

        System.out.print("Nuevos apellidos (dejar en blanco para no modificar): ");
        String apellidos = scanner.nextLine();
        if (!apellidos.isEmpty()) {
            estudianteExistente.setSurname(apellidos);
        }

        System.out.print("Nuevo código postal (0 para no modificar): ");
        int zipCode = Integer.parseInt(scanner.nextLine());
        if (zipCode != 0) {
            estudianteExistente.setZipCode(zipCode);
        }

        if (studentDAO.modificarEstudiante(estudianteExistente)) {
            System.out.println("Estudiante modificado con éxito.");
        } else {
            System.out.println("Error al modificar el estudiante.");
        }
    }

    private static void listarCursos() {
        List<Course> cursos = courseDAO.listarCursos();
        if (cursos.isEmpty()) {
            System.out.println("No hay cursos registrados.");
        } else {
            System.out.println("\n--- Lista de Cursos ---");
            cursos.forEach(System.out::println);
        }
    }

    private static void insertarCurso() {
        System.out.print("Nombre del curso: ");
        String nombre = scanner.nextLine();
        System.out.print("Año del curso: ");
        int courseYear = Integer.parseInt(scanner.nextLine());

        Course nuevoCurso = new Course(0, nombre, courseYear);
        if (courseDAO.insertarCurso(nuevoCurso)) {
            System.out.println("Curso insertado con éxito.");
        } else {
            System.out.println("Error al insertar el curso.");
        }
    }

    private static void eliminarCurso() {
        System.out.print("ID del curso a eliminar: ");
        int idCurso = Integer.parseInt(scanner.nextLine());

        if (courseDAO.eliminarCurso(idCurso)) {
            System.out.println("Curso eliminado con éxito.");
        } else {
            System.out.println("No se ha podido encontrar el curso.");
        }
    }

    private static void mostrarNotasDeEstudiante() {
        System.out.print("NIF del estudiante para mostrar notas: ");
        String nif = scanner.nextLine();

        List<StudentGrade> notas = studentGradeDAO.mostrarNotasDeEstudiante(nif);
        if (notas.isEmpty()) {
            System.out.println("El estudiante no tiene notas registradas.");
        } else {
            System.out.println("\n--- Notas del Estudiante " + nif + " ---");
            notas.forEach(System.out::println);
        }
    }

    private static void añadirNota() {
        System.out.print("NIF del estudiante: ");
        String nif = scanner.nextLine();

        System.out.print("ID del curso: ");
        int idCurso = Integer.parseInt(scanner.nextLine());

        System.out.print("Valor de la nota (0.0-10.0): ");
        double valorNota = Double.parseDouble(scanner.nextLine());

        if (valorNota < 0 || valorNota > 10) {
            System.out.println("La nota debe estar entre 0.0 y 10.0.");
            return;
        }

        StudentGrade nuevaNota = new StudentGrade(nif, idCurso, valorNota);
        if (studentGradeDAO.insertarNota(nuevaNota)) {
            System.out.println("Nota añadida con éxito.");
        } else {
            System.out.println("Error al añadir la nota.");
        }
    }
}
