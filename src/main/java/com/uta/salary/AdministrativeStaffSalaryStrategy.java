package com.uta.salary;

import com.uta.model.AdministrativeStaff;
import com.uta.model.Employee;
import com.uta.model.Lecturer;

public class AdministrativeStaffSalaryStrategy implements SalaryCalculationStrategy{
    @Override
    public double calculate(Employee employee) {
        if (!(employee instanceof AdministrativeStaff administrativeStaff)) {
            throw new IllegalArgumentException("Invalid staff type for AdministrativeStaff");
        }
        return administrativeStaff.getSalaryCoefficient() * 730
                + administrativeStaff.getPosition().getAllowance()
                + administrativeStaff.getWorkingDays() * 45;
    }
}
