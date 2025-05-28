package org.ivan.dao;

import org.ivan.model.StudentGrade;
import org.ivan.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentGradeDAOImpl implements StudentGradeDAO {
    @Override
    public boolean insertarNota(StudentGrade nota) {
        String sql = "INSERT INTO student_grades (student_nif, course_id, grade) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nota.getNif());
            stmt.setInt(2, nota.getCourseId());
            stmt.setDouble(3, nota.getGrade());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<StudentGrade> mostrarNotasDeEstudiante(String nifEstudiante) {
        String sql = "SELECT * FROM student_grades WHERE student_nif = ?";
        List<StudentGrade> notas = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nifEstudiante);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                StudentGrade nota = new StudentGrade(
                        rs.getString("student_nif"),
                        rs.getInt("course_id"),
                        rs.getDouble("grade")
                );
                notas.add(nota);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return notas;
    }
}