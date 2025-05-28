package org.ivan.dao;

import org.ivan.model.Course;
import org.ivan.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseDAOImpl implements CourseDAO {

    @Override
    public List<Course> listarCursos() {
        List<Course> courses = new ArrayList<>();
        String sql = "SELECT * FROM course";

        try (
                Connection conn = DatabaseConnection.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)
        ) {
            while (rs.next()) {
                Course course = new Course(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("course_year")
                );
                courses.add(course);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courses;
    }

    @Override
    public boolean insertarCurso(Course curso) {
        String sql = "INSERT INTO course (name, course_year) VALUES (?, ?)";

        try (
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {
            pstmt.setString(1, curso.getName());
            pstmt.setInt(2, curso.getCourse_year());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean eliminarCurso(int idCurso) {
        String sql = "DELETE FROM course WHERE id = ?";

        try (
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {
            pstmt.setInt(1, idCurso);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("No se ha podido encontrar el curso");
            return false;
        }
    }

    @Override
    public Course obtenerCursoPorId(int id) {
        String sql = "SELECT * FROM course WHERE id = ?";

        try (
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Course(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getInt("course_year")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
