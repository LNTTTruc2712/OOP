package com.uta.specification.employee;

import com.uta.model.Employee;
import com.uta.specification.Specification;

public class NameContainsSpec implements Specification<Employee> {
    private final String keyword;

    public NameContainsSpec(String keyword) {
        this.keyword = keyword.toLowerCase();
    }

    @Override
    public boolean isSatisfiedBy(Employee staff) {
        return staff.getName().toLowerCase().contains(keyword);
    }
}
