package com.uta.specification.employee;

import com.uta.model.Employee;
import com.uta.specification.Specification;

public class MaxSalarySpec implements Specification<Employee> {
    private final double maxSalary;

    public MaxSalarySpec(double maxSalary) {
        this.maxSalary = maxSalary;
    }

    @Override
    public boolean isSatisfiedBy(Employee staff) {
        return staff.calculateSalary() <= maxSalary;
    }
}
