package org.ivan.dao;

import org.ivan.model.StudentGrade;
import org.ivan.util.DatabaseConnection;
import org.ivan.util.MongoLogger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentGradeDAOImpl implements StudentGradeDAO {
    @Override
    public boolean insertarNota(StudentGrade nota) {
        String sql = "INSERT INTO student_grade (nif, course_id, grade) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nota.getNif());
            stmt.setInt(2, nota.getCourseId());
            stmt.setDouble(3, nota.getGrade());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                MongoLogger.info("Nota insertada para estudiante " + nota.getNif() + " en curso " + nota.getCourseId());
            }
            return rowsAffected > 0;

        } catch (SQLException e) {
            MongoLogger.error("Error al insertar nota: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<StudentGrade> mostrarNotasDeEstudiante(String nifEstudiante) {
        String sql = "SELECT * FROM student_grade WHERE nif = ?";
        List<StudentGrade> notas = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nifEstudiante);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                StudentGrade nota = new StudentGrade(
                        rs.getString("nif"),
                        rs.getInt("course_id"),
                        rs.getDouble("grade")
                );
                notas.add(nota);
            }
            MongoLogger.info("Notas consultadas para estudiante: " + nifEstudiante);

        } catch (SQLException e) {
            MongoLogger.error("Error al consultar notas: " + e.getMessage());
            e.printStackTrace();
        }
        return notas;
    }
}
