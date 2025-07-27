package com.uta.factory;

import com.uta.model.Employee;
import com.uta.utils.InputUtils;

import java.util.Scanner;

public interface EmployeeFactory {
    Employee createEmployee(String id, String name, double salaryCoefficient, InputUtils inputUtils);
}
