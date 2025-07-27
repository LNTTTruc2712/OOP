package com.uta.factory;

import com.uta.enums.DegreeRank;
import com.uta.model.Employee;
import com.uta.model.Lecturer;
import com.uta.salary.LecturerSalaryStrategy;
import com.uta.utils.InputUtils;

public class LecturerFactory implements EmployeeFactory {
    @Override
    public Employee createEmployee(String id, String name, double salaryCoefficient, InputUtils inputUtils) {
        System.out.println("📌 Enter Lecturer info:");
        String faculty = inputUtils.readString("Faculty: ");
        int teachingHours = inputUtils.readInt("Teaching hours: ");
        DegreeRank degreeRank = inputUtils.chooseEnum(DegreeRank.class, "Choose Degree Rank:");
        if (degreeRank == null) return null;
        return new Lecturer(id, name, salaryCoefficient, faculty, teachingHours, degreeRank, new LecturerSalaryStrategy());
    }
}
