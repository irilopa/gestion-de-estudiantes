package org.ivan.dao;

import org.ivan.model.Course;

import java.util.List;

public interface CourseDAO {
    List<Course> listarCursos();

    boolean insertarCurso(Course curso);

    boolean eliminarCurso(int idCurso);

    Course obtenerCursoPorId(int id);
}
