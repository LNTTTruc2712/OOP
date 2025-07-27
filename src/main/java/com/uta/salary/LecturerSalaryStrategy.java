package com.uta.salary;

import com.uta.model.Lecturer;
import com.uta.model.Employee;

public class LecturerSalaryStrategy implements SalaryCalculationStrategy {

    @Override
    public double calculate(Employee employee) {
        if (!(employee instanceof Lecturer lecturer)) {
            throw new IllegalArgumentException("Invalid staff type for LecturerSalaryStrategy");
        }

        return lecturer.getSalaryCoefficient() * 730
                + lecturer.getDegreeRank().getAllowance()
                + lecturer.getTeachingHours() * 45;
    }
}
