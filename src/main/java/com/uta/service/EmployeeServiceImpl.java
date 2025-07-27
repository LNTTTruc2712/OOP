package com.uta.service;

import com.uta.model.Employee;
import com.uta.specification.Specification;
import com.uta.specification.employee.NameContainsSpec;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EmployeeServiceImpl implements EmployeeService {
    private final Map<String, Employee> employeeMap = new HashMap<>();

    @Override
    public boolean add(Employee employee) {
        if (employeeMap.containsKey(employee.getId())) {
            return false;
        }
        employeeMap.put(employee.getId(), employee);
        return true;
    }

    @Override
    public boolean update(Employee employee) {
        if (employeeMap.containsKey(employee.getId())) {
            employeeMap.put(employee.getId(), employee);
            return true;
        }
        return false;
    }

    @Override
    public boolean removeById(String id) {
        return employeeMap.remove(id) != null;
    }

    @Override
    public Employee findById(String id) {
        return employeeMap.get(id);
    }

    @Override
    public Employee findHighestSalaryEmployee() {
        return employeeMap.values().stream()
                .max(Comparator.comparingDouble(Employee::calculateSalary))
                .orElse(null);
    }

    public Employee findLowestSalaryEmployee() {
        return employeeMap.values().stream()
                .min(Comparator.comparingDouble(Employee::calculateSalary))
                .orElse(null);
    }

    @Override
    public List<Employee> findByName(String name) {
        Specification<Employee> spec = new NameContainsSpec(name);
        return employeeMap.values().stream()
                .filter(spec::isSatisfiedBy)
                .collect(Collectors.toList());
    }

    @Override
    public List<Employee> findAllSortedBySalary(boolean ascending) {
        return employeeMap.values().stream()
                .sorted(ascending
                        ? Comparator.comparing(Employee::calculateSalary)
                        : Comparator.comparing(Employee::calculateSalary).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public List<Employee> getAll() {
        return new ArrayList<>(employeeMap.values());
    }

    @Override
    public List<Employee> search(Specification<Employee> specification) {
        return employeeMap.values().stream()
                .filter(specification::isSatisfiedBy)
                .collect(Collectors.toList());
    }

    @Override
    public double calculateTotalSalary() {
        return employeeMap.values().stream()
                .mapToDouble(Employee::calculateSalary)
                .sum();
    }
}
