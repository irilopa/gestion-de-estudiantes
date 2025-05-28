package org.ivan.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StudentGrade {
    private String nif;
    private int courseId;
    private double grade;
}
