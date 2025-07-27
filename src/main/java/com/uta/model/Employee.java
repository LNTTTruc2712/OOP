package com.uta.model;

import com.uta.salary.SalaryCalculationStrategy;

public abstract class Employee {
    protected String id;
    protected String name;
    protected double salaryCoefficient;
    protected SalaryCalculationStrategy salaryStrategy;

    public Employee(String id, String name, double salaryCoefficient, SalaryCalculationStrategy salaryStrategy) {
        this.id = id;
        this.name = name;
        this.salaryCoefficient = salaryCoefficient;
        this.salaryStrategy = salaryStrategy;
    }

    public double calculateSalary() {
        return salaryStrategy.calculate(this);
    }

    // Getters
    public String getId() { return id; }
    public String getName() { return name; }

    public double getSalaryCoefficient() {
        return salaryCoefficient;
    }

    @Override
    public String toString() {
        return String.format("ID: %s | Name: %s | Salary: %.2f",
                id, name, calculateSalary());
    }

}

