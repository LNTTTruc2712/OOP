package com.uta.specification.employee;

import com.uta.model.Employee;
import com.uta.specification.Specification;

public class IdEqualsSpec implements Specification<Employee> {
    private final String targetId;

    public IdEqualsSpec(String targetId) {
        this.targetId = targetId;
    }

    @Override
    public boolean isSatisfiedBy(Employee staff) {
        return staff.getId().equalsIgnoreCase(targetId);
    }
}
