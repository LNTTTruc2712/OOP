package com.uta.specification.employee;

import com.uta.model.Employee;
import com.uta.specification.Specification;

public class MinSalarySpec implements Specification<Employee> {
    private final double minSalary;

    public MinSalarySpec(double minSalary) {
        this.minSalary = minSalary;
    }

    @Override
    public boolean isSatisfiedBy(Employee staff) {
        return staff.calculateSalary() >= minSalary;
    }
}
