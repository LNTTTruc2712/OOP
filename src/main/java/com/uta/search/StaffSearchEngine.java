package com.uta.search;

import com.uta.model.Employee;
import com.uta.specification.Specification;

import java.util.List;
import java.util.stream.Collectors;

public class StaffSearchEngine {
    private final List<Employee> staffList;

    public StaffSearchEngine(List<Employee> staffList) {
        this.staffList = staffList;
    }

    public List<Employee> search(Specification<Employee> spec) {
        return staffList.stream()
                .filter(spec::isSatisfiedBy)
                .collect(Collectors.toList());
    }
}
