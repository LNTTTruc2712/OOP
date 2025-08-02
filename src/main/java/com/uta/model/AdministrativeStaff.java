package com.uta.model;

import com.uta.enums.Position;
import com.uta.salary.SalaryCalculationStrategy;

public class AdministrativeStaff extends Employee {
    private String department;
    private Position position;
    private int workingDays;

    public AdministrativeStaff(String id, String name, double salaryCoefficient, String department,
                               Position position, int workingDays, SalaryCalculationStrategy salaryStrategy) {
        super(id, name, salaryCoefficient, salaryStrategy);
        this.department = department;
        this.position = position;
        this.workingDays = workingDays;
    }


    public String getDepartment() {
        return department;
    }

    public Position getPosition() {
        return position;
    }

    public int getWorkingDays() {
        return workingDays;
    }
}
