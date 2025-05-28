package org.ivan.dao;

import org.ivan.model.Student;

import java.util.List;

public interface StudentDAO {
    List<Student> listarEstudiantes();

    boolean insertarEstudiante(Student estudiante);

    boolean eliminarEstudiante(String nif);

    boolean modificarEstudiante(Student estudiante);

    Student obtenerEstudiantePorNif(String nif);
}
