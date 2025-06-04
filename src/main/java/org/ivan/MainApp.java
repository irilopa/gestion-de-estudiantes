package org.ivan;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.ivan.dao.*;
import org.ivan.model.Course;
import org.ivan.model.Student;
import org.ivan.model.StudentGrade;

import java.util.List;

public class MainApp extends Application {
    private final StudentDAO studentDAO = new StudentDAOImpl();
    private final CourseDAO courseDAO = new CourseDAOImpl();
    private final StudentGradeDAO studentGradeDAO = new StudentGradeDAOImpl();

    private TableView<Student> studentTable;
    private TableView<Course> courseTable;
    private TableView<StudentGrade> gradeTable;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        TabPane tabPane = new TabPane();
        tabPane.getTabs().addAll(
                createStudentTab(),
                createCourseTab(),
                createGradeTab()
        );
        Scene scene = new Scene(tabPane, 800, 500);
        primaryStage.setTitle("Gestión Académica - JavaFX");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Tab createStudentTab() {
        Tab tab = new Tab("Estudiantes");
        studentTable = new TableView<>();
        TableColumn<Student, String> nifCol = new TableColumn<>("NIF");
        nifCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getNif()));
        TableColumn<Student, String> nameCol = new TableColumn<>("Nombre");
        nameCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getName()));
        TableColumn<Student, String> surnameCol = new TableColumn<>("Apellidos");
        surnameCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getSurname()));
        TableColumn<Student, Integer> zipCol = new TableColumn<>("CP");
        zipCol.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getZipCode()).asObject());
        studentTable.getColumns().addAll(nifCol, nameCol, surnameCol, zipCol);
        refreshStudentTable();

        Button addBtn = new Button("Añadir");
        addBtn.setOnAction(e -> showStudentDialog(null));
        Button editBtn = new Button("Editar");
        editBtn.setOnAction(e -> {
            Student s = studentTable.getSelectionModel().getSelectedItem();
            if (s != null) showStudentDialog(s);
        });
        Button delBtn = new Button("Eliminar");
        delBtn.setOnAction(e -> {
            Student s = studentTable.getSelectionModel().getSelectedItem();
            if (s != null && studentDAO.eliminarEstudiante(s.getNif())) refreshStudentTable();
        });
        HBox btnBox = new HBox(10, addBtn, editBtn, delBtn);
        VBox vbox = new VBox(10, studentTable, btnBox);
        vbox.setPrefHeight(400);
        tab.setContent(vbox);
        tab.setClosable(false);
        return tab;
    }

    private Tab createCourseTab() {
        Tab tab = new Tab("Cursos");
        courseTable = new TableView<>();
        TableColumn<Course, Integer> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getId()).asObject());
        TableColumn<Course, String> nameCol = new TableColumn<>("Nombre");
        nameCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getName()));
        TableColumn<Course, Integer> yearCol = new TableColumn<>("Año");
        yearCol.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getCourse_year()).asObject());
        courseTable.getColumns().addAll(idCol, nameCol, yearCol);
        refreshCourseTable();

        Button addBtn = new Button("Añadir");
        addBtn.setOnAction(e -> showCourseDialog(null));
        Button delBtn = new Button("Eliminar");
        delBtn.setOnAction(e -> {
            Course c = courseTable.getSelectionModel().getSelectedItem();
            if (c != null && courseDAO.eliminarCurso(c.getId())) refreshCourseTable();
        });
        HBox btnBox = new HBox(10, addBtn, delBtn);
        VBox vbox = new VBox(10, courseTable, btnBox);
        vbox.setPrefHeight(400);
        tab.setContent(vbox);
        tab.setClosable(false);
        return tab;
    }

    private Tab createGradeTab() {
        Tab tab = new Tab("Notas");
        gradeTable = new TableView<>();
        TableColumn<StudentGrade, String> nifCol = new TableColumn<>("NIF");
        nifCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getNif()));
        TableColumn<StudentGrade, Integer> courseCol = new TableColumn<>("ID Curso");
        courseCol.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getCourseId()).asObject());
        TableColumn<StudentGrade, Double> gradeCol = new TableColumn<>("Nota");
        gradeCol.setCellValueFactory(data -> new javafx.beans.property.SimpleDoubleProperty(data.getValue().getGrade()).asObject());
        gradeTable.getColumns().addAll(nifCol, courseCol, gradeCol);
        refreshGradeTable();

        Button addBtn = new Button("Añadir Nota");
        addBtn.setOnAction(e -> showGradeDialog());
        HBox btnBox = new HBox(10, addBtn);
        VBox vbox = new VBox(10, gradeTable, btnBox);
        vbox.setPrefHeight(400);
        tab.setContent(vbox);
        tab.setClosable(false);
        return tab;
    }

    private void refreshStudentTable() {
        List<Student> students = studentDAO.listarEstudiantes();
        ObservableList<Student> data = FXCollections.observableArrayList(students);
        studentTable.setItems(data);
    }

    private void refreshCourseTable() {
        List<Course> courses = courseDAO.listarCursos();
        ObservableList<Course> data = FXCollections.observableArrayList(courses);
        courseTable.setItems(data);
    }

    private void refreshGradeTable() {
        List<StudentGrade> grades = studentGradeDAO.listarNotas();
        ObservableList<StudentGrade> data = FXCollections.observableArrayList(grades);
        gradeTable.setItems(data);
    }

    private void showStudentDialog(Student student) {
        Dialog<Student> dialog = new Dialog<>();
        dialog.setTitle(student == null ? "Añadir Estudiante" : "Editar Estudiante");
        Label nifLabel = new Label("NIF:");
        TextField nifField = new TextField();
        nifField.setText(student != null ? student.getNif() : "");
        nifField.setDisable(student != null);
        Label nameLabel = new Label("Nombre:");
        TextField nameField = new TextField(student != null ? student.getName() : "");
        Label surnameLabel = new Label("Apellidos:");
        TextField surnameField = new TextField(student != null ? student.getSurname() : "");
        Label zipLabel = new Label("CP:");
        TextField zipField = new TextField(student != null ? String.valueOf(student.getZipCode()) : "");
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.add(nifLabel, 0, 0);
        grid.add(nifField, 1, 0);
        grid.add(nameLabel, 0, 1);
        grid.add(nameField, 1, 1);
        grid.add(surnameLabel, 0, 2);
        grid.add(surnameField, 1, 2);
        grid.add(zipLabel, 0, 3);
        grid.add(zipField, 1, 3);
        dialog.getDialogPane().setContent(grid);
        ButtonType okBtn = new ButtonType("Guardar", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(okBtn, ButtonType.CANCEL);
        dialog.setResultConverter(btn -> {
            if (btn == okBtn) {
                try {
                    int zip = Integer.parseInt(zipField.getText());
                    if (student == null) {
                        Student s = new Student(nifField.getText(), nameField.getText(), surnameField.getText(), zip);
                        if (studentDAO.insertarEstudiante(s)) return s;
                    } else {
                        student.setName(nameField.getText());
                        student.setSurname(surnameField.getText());
                        student.setZipCode(zip);
                        if (studentDAO.modificarEstudiante(student)) return student;
                    }
                } catch (Exception ex) {
                }
            }
            return null;
        });
        dialog.showAndWait();
        refreshStudentTable();
    }

    private void showCourseDialog(Course course) {
        Dialog<Course> dialog = new Dialog<>();
        dialog.setTitle(course == null ? "Añadir Curso" : "Editar Curso");
        Label nameLabel = new Label("Nombre:");
        TextField nameField = new TextField(course != null ? course.getName() : "");
        Label yearLabel = new Label("Año:");
        TextField yearField = new TextField(course != null ? String.valueOf(course.getCourse_year()) : "");
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.add(nameLabel, 0, 0);
        grid.add(nameField, 1, 0);
        grid.add(yearLabel, 0, 1);
        grid.add(yearField, 1, 1);
        dialog.getDialogPane().setContent(grid);
        ButtonType okBtn = new ButtonType("Guardar", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(okBtn, ButtonType.CANCEL);
        dialog.setResultConverter(btn -> {
            if (btn == okBtn) {
                try {
                    int year = Integer.parseInt(yearField.getText());
                    if (course == null) {
                        Course c = new Course(0, nameField.getText(), year);
                        if (courseDAO.insertarCurso(c)) return c;
                    }
                } catch (Exception ex) {
                }
            }
            return null;
        });
        dialog.showAndWait();
        refreshCourseTable();
    }

    private void showGradeDialog() {
        Dialog<StudentGrade> dialog = new Dialog<>();
        dialog.setTitle("Añadir Nota");
        Label nifLabel = new Label("NIF:");
        TextField nifField = new TextField();
        Label courseLabel = new Label("ID Curso:");
        TextField courseField = new TextField();
        Label gradeLabel = new Label("Nota (0-10):");
        TextField gradeField = new TextField();
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.add(nifLabel, 0, 0);
        grid.add(nifField, 1, 0);
        grid.add(courseLabel, 0, 1);
        grid.add(courseField, 1, 1);
        grid.add(gradeLabel, 0, 2);
        grid.add(gradeField, 1, 2);
        dialog.getDialogPane().setContent(grid);
        ButtonType okBtn = new ButtonType("Guardar", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(okBtn, ButtonType.CANCEL);
        dialog.setResultConverter(btn -> {
            if (btn == okBtn) {
                try {
                    double grade = Double.parseDouble(gradeField.getText());
                    int courseId = Integer.parseInt(courseField.getText());
                    String nif = nifField.getText();
                    if (grade >= 0 && grade <= 10) {
                        StudentGrade sg = new StudentGrade(nif, courseId, grade);
                        if (studentGradeDAO.insertarNota(sg)) return sg;
                    }
                } catch (Exception ex) {
                }
            }
            return null;
        });
        dialog.showAndWait();
        refreshGradeTable();
    }
}

