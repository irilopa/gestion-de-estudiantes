package org.ivan.model;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Course {
    private int id;
    private String name;
    private int course_year;
}
