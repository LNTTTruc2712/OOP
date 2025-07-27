package com.uta;

import com.uta.controller.EmployeeController;
import com.uta.service.EmployeeServiceImpl;
import com.uta.utils.InputUtils;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        InputUtils inputUtils = new InputUtils(scanner);
        EmployeeController consoleController = new EmployeeController(new EmployeeServiceImpl(), inputUtils);
        consoleController.run();
    }
}
