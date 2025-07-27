package com.uta.controller;

import com.uta.enums.EmployeeType;
import com.uta.factory.AdministrativeStaffFactory;
import com.uta.factory.EmployeeFactory;
import com.uta.factory.LecturerFactory;
import com.uta.model.Employee;
import com.uta.service.EmployeeService;
import com.uta.utils.InputUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeeController {
    private static final Map<EmployeeType, EmployeeFactory> factories = new HashMap<>();
    private final EmployeeService employeeService;
    private final InputUtils inputUtils;

    static {
        factories.put(EmployeeType.LECTURER, new LecturerFactory());
        factories.put(EmployeeType.ADMINISTRATIVE_STAFF, new AdministrativeStaffFactory());
    }

    public EmployeeController(EmployeeService employeeService, InputUtils inputUtils) {
        this.employeeService = employeeService;
        this.inputUtils = inputUtils;
    }

    public void run() {
        while (true) {
            printMenu();
            int choice = inputUtils.readInt("👉 Choose: ");
            switch (choice) {
                case 1 -> addEmployeeMenu();
                case 2 -> displayAll();
                case 3 -> searchByName();
                case 4 -> deleteById();
                case 5 -> displayTotalSalary();
                case 6 -> updateEmployeeMenu();
                case 7 -> searchEmployeesBySalaryAscending();
                case 8 -> searchEmployeesBySalaryDescending();
                case 9 -> searchById();
                case 10 -> searchByHighestSalary();
                case 11 -> searchByLowestSalary();
                case 0 -> {
                    System.out.println("👋 Exiting... Bye!");
                    return;
                }
                default -> System.out.println("❌ Invalid choice!");
            }
        }
    }

    private void printMenu() {
        System.out.println("""
        \n======== EMPLOYEE MANAGEMENT ========
        1. ➕ Add new employee
        2. 📋 Display all employees
        3. 🔍 Search employee by name
        4. 🗑️ Delete employee by ID
        5. 💰 Show total salary
        6. ✏️ Update employee
        7. 📈 Search employees by salary (ascending)
        8. 📉 Search employees by salary (descending)
        9. 🔍 Search employee by ID
        10. 🏆 Employee(s) with highest salary
        11. 🥇 Employee(s) with lowest salary
        0. ❌ Exit
        """);
    }



    private void addEmployeeMenu() {
        EmployeeType selectedType = inputUtils.chooseEnum(EmployeeType.class, "📌 Choose employee type:");
        if (selectedType == null) return;

        System.out.println("📌 Enter employee info:");
        String id = inputUtils.readString("ID: ");
        String name = inputUtils.readString("Name: ");
        double salaryCoefficient = inputUtils.readDouble("Salary Coefficient: ");

        EmployeeFactory factory = factories.get(selectedType);
        Employee employee = factory.createEmployee(id, name, salaryCoefficient, inputUtils);

        if (employee != null) {
            boolean success = employeeService.add(employee);
            if (success) {
                System.out.println("✅ Employee added!");
            } else {
                System.out.println("❌ Employee with ID '" + id + "' already exists.");
            }
        }
    }

    private void updateEmployeeMenu() {
        System.out.println("📌 Update Employee Information:");
        EmployeeType selectedType = inputUtils.chooseEnum(EmployeeType.class, "📌 Choose employee type:");
        if (selectedType == null) return;

        String id = inputUtils.readString("ID of employee to update: ");

        String name = inputUtils.readString("New Name: ");
        double salaryCoefficient = inputUtils.readDouble("New Salary Coefficient: ");

        EmployeeFactory factory = factories.get(selectedType);
        Employee updatedEmployee = factory.createEmployee(id, name, salaryCoefficient, inputUtils);

        if (updatedEmployee != null) {
            boolean success = employeeService.update(updatedEmployee);
            if (success) {
                System.out.println("✅ Employee updated successfully!");
            } else {
                System.out.println("❌ Failed to update employee.");
            }
        }
    }


    private void displayAll() {
        System.out.println("📋 All employee:");
        employeeService.getAll().forEach(System.out::println);
    }

    private void searchByName() {
        String name = inputUtils.readString("🔍 Enter name to search: ");
        List<Employee> result = employeeService.findByName(name);
        if (!result.isEmpty()) {
            result.forEach(System.out::println);
        } else {
            System.out.println("❌ No employee found with Name: " + name);
        }
    }

    private void searchById() {
        String id = inputUtils.readString("🔍 Enter ID to search: ");
        Employee employee = employeeService.findById(id);

        if (employee != null) {
            System.out.println("✅ Employee found:");
            System.out.println(employee);
        } else {
            System.out.println("❌ No employee found with ID: " + id);
        }
    }

    private void searchEmployeesBySalaryAscending() {
        List<Employee> sorted = employeeService.findAllSortedBySalary(true);
        sorted.forEach(System.out::println);
    }

    private void searchEmployeesBySalaryDescending() {
        List<Employee> sorted = employeeService.findAllSortedBySalary(false);
        sorted.forEach(System.out::println);
    }

    private void searchByHighestSalary() {
        Employee employee = employeeService.findHighestSalaryEmployee();

        if (employee != null) {
            System.out.println("💰 Employee with highest salary:");
            System.out.println(employee);
            System.out.printf("💵 Salary: %.2f\n", employee.calculateSalary());
        } else {
            System.out.println("⚠️ No employees found.");
        }
    }

    private void searchByLowestSalary() {
        Employee employee = employeeService.findLowestSalaryEmployee();

        if (employee != null) {
            System.out.println("💰 Employee with lowest salary:");
            System.out.println(employee);
            System.out.printf("💵 Salary: %.2f\n", employee.calculateSalary());
        } else {
            System.out.println("⚠️ No employees found.");
        }
    }

    private void deleteById() {
        String id = inputUtils.readString("🗑️ Enter ID to delete: ");
        boolean success = employeeService.removeById(id);
        if (success) {
            System.out.println("✅ Deleted!");
        } else {
            System.out.println("❌ Not found!");
        }
    }

    private void displayTotalSalary() {
        double total = employeeService.calculateTotalSalary();
        System.out.println("💰 Total salary: " + total);
    }

}
