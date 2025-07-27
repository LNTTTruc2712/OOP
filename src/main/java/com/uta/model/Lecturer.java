package com.uta.model;

import com.uta.enums.DegreeRank;
import com.uta.salary.SalaryCalculationStrategy;

public class Lecturer extends Employee {
    private String faculty;
    private DegreeRank degreeRank;
    private int teachingHours;
    public Lecturer(String id, String name, double salaryCoefficient, String faculty,
                    int teachingHours, DegreeRank degreeRank, SalaryCalculationStrategy strategy) {
        super(id, name, salaryCoefficient, strategy);
        this.faculty = faculty;
        this.teachingHours = teachingHours;
        this.degreeRank = degreeRank;
    }

    public int getTeachingHours() {
        return teachingHours;
    }

    public String getFaculty() {
        return faculty;
    }

    public DegreeRank getDegreeRank() {
        return degreeRank;
    }
}
