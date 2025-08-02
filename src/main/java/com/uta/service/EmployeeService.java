package com.uta.service;

import com.uta.model.Employee;
import com.uta.specification.Specification;

import java.util.List;

public interface EmployeeService {
    boolean add(Employee employee);
    boolean update(Employee employee);
    boolean removeById(String id);
    Employee findById(String id);
    Employee findHighestSalaryEmployee();
    Employee findLowestSalaryEmployee();
    List<Employee> findByName(String name);
    List<Employee> findAllSortedBySalary(boolean ascending);
    List<Employee> getAll();
    List<Employee> search(Specification<Employee> specification);
    double calculateTotalSalary();
}
