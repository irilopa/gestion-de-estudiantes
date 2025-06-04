package org.ivan.dao;

import org.ivan.model.StudentGrade;

import java.util.List;

public interface StudentGradeDAO {
    boolean insertarNota(StudentGrade nota);

    List<StudentGrade> mostrarNotasDeEstudiante(String nifEstudiante);

    List<StudentGrade> listarNotas();
}
