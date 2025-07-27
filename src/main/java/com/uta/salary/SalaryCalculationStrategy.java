package com.uta.salary;

import com.uta.model.Employee;

public interface SalaryCalculationStrategy {
    double calculate(Employee staff);
}
