package com.uta.factory;

import com.uta.enums.Position;
import com.uta.model.AdministrativeStaff;
import com.uta.model.Employee;
import com.uta.salary.AdministrativeStaffSalaryStrategy;
import com.uta.utils.InputUtils;

public class AdministrativeStaffFactory implements EmployeeFactory {
    public Employee createEmployee(String id, String name, double salaryCoefficient, InputUtils inputUtils) {
        System.out.println("📌 Enter Administrative Staff info:");
        String department = inputUtils.readString("Department: ");
        int workingDays = inputUtils.readInt("Working days: ");
        Position position = inputUtils.chooseEnum(Position.class, "Choose Position:");
        if (position == null) return null;
        return new AdministrativeStaff(id, name, salaryCoefficient, department, position, workingDays, new AdministrativeStaffSalaryStrategy());
    }
}
