package com.uta.service;

import com.uta.model.Employee;
import com.uta.search.SearchEngine;
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
    private SearchEngine<Employee> searchEngine;

    public EmployeeServiceImpl() {
        this.searchEngine = new SearchEngine<>(new ArrayList<>());
    }

    @Override
    public boolean add(Employee employee) {
        if (employee == null || employeeMap.containsKey(employee.getId())) {
            return false;
        }
        employeeMap.put(employee.getId(), employee);
        updateSearchEngine();
        return true;
    }

    @Override
    public boolean update(Employee employee) {
        if (employee == null || !employeeMap.containsKey(employee.getId())) {
            return false;
        }
        employeeMap.put(employee.getId(), employee);
        updateSearchEngine();
        return true;
    }

    @Override
    public boolean removeById(String id) {
        if (employeeMap.remove(id) != null) {
            updateSearchEngine();
            return true;
        }
        return false;
    }

    @Override
    public Employee findById(String id) {
        return employeeMap.get(id);
    }

    @Override
    public Employee findHighestSalaryEmployee() {
        return searchEngine.findMax(Comparator.comparingDouble(Employee::calculateSalary));
    }

    @Override
    public Employee findLowestSalaryEmployee() {
        return searchEngine.findMin(Comparator.comparingDouble(Employee::calculateSalary));
    }

    @Override
    public List<Employee> findByName(String name) {
        return searchEngine.search(new NameContainsSpec(name));
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
        return searchEngine.searchAll(null, 0, Integer.MAX_VALUE);
    }

    @Override
    public List<Employee> search(Specification<Employee> specification) {
        return searchEngine.search(specification);
    }

    @Override
    public double calculateTotalSalary() {
        return employeeMap.values().stream()
                .mapToDouble(Employee::calculateSalary)
                .sum();
    }

    private void updateSearchEngine() {
        this.searchEngine = new SearchEngine<>(new ArrayList<>(employeeMap.values()));
    }
}
