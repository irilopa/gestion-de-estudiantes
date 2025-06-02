package org.ivan.dao;

import org.ivan.model.Student;
import org.ivan.util.DatabaseConnection;
import org.ivan.util.MongoLogger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StudentDAOImpl implements StudentDAO {

    @Override
    public List<Student> listarEstudiantes() {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM student";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Student student = new Student(
                        rs.getString("nif"),
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getInt("zipCode")
                );
                students.add(student);
            }
            MongoLogger.info("Listado de estudiantes consultado.");
        } catch (Exception e) {
            MongoLogger.error("Error al listar estudiantes: " + e.getMessage());
            e.printStackTrace();
        }
        return students;
    }

    @Override
    public boolean insertarEstudiante(Student estudiante) {
        String sql = "INSERT INTO student (nif, name, surname, zipCode) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, estudiante.getNif());
            pstmt.setString(2, estudiante.getName());
            pstmt.setString(3, estudiante.getSurname());
            pstmt.setInt(4, estudiante.getZipCode());

            boolean result = pstmt.executeUpdate() > 0;
            if (result) {
                MongoLogger.info("Estudiante insertado: " + estudiante.getNif());
            }
            return result;
        } catch (Exception e) {
            MongoLogger.error("Error al insertar estudiante: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean eliminarEstudiante(String nif) {
        String sql = "DELETE FROM student WHERE nif = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, nif);
            boolean result = pstmt.executeUpdate() > 0;
            if (result) {
                MongoLogger.info("Estudiante eliminado: " + nif);
            }
            return result;
        } catch (Exception e) {
            MongoLogger.error("Error al eliminar estudiante: " + e.getMessage());
            System.out.println("No se ha podido encontrar el estudiante");
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean modificarEstudiante(Student estudiante) {
        String sql = "UPDATE student SET name = ?, surname = ?, zipcode = ? WHERE nif = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, estudiante.getName());
            pstmt.setString(2, estudiante.getSurname());
            pstmt.setInt(3, estudiante.getZipCode());
            pstmt.setString(4, estudiante.getNif());

            boolean result = pstmt.executeUpdate() > 0;
            if (result) {
                MongoLogger.info("Estudiante modificado: " + estudiante.getNif());
            }
            return result;
        } catch (Exception e) {
            MongoLogger.error("Error al modificar estudiante: " + e.getMessage());
            e.printStackTrace();
            System.out.println("No se ha podido encontrar el estudiante");
            return false;
        }
    }

    @Override
    public Student obtenerEstudiantePorNif(String nif) {
        String sql = "SELECT * FROM student WHERE nif = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, nif);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    MongoLogger.info("Estudiante consultado: " + nif);
                    return new Student(
                            rs.getString("nif"),
                            rs.getString("name"),
                            rs.getString("surname"),
                            rs.getInt("zipcode")
                    );
                }
            }
        } catch (Exception e) {
            MongoLogger.error("Error al consultar estudiante: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
}
