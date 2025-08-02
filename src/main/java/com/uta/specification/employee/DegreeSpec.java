package com.uta.specification.employee;

import com.uta.enums.DegreeRank;
import com.uta.model.Employee;
import com.uta.model.Lecturer;
import com.uta.specification.Specification;

public class DegreeSpec implements Specification<Employee> {
    private final DegreeRank degreeRank;

    public DegreeSpec(DegreeRank degreeRank) {
        this.degreeRank = degreeRank;
    }

    @Override
    public boolean isSatisfiedBy(Employee employee) {
        if (!(employee instanceof Lecturer lecturer)) return false;
        return lecturer.getDegreeRank().equals(degreeRank);
    }
}
