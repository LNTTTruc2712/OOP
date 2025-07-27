package com.uta.utils;

import java.util.Scanner;

public class InputUtils {
    private final Scanner scanner;

    public InputUtils(Scanner scanner) {
        this.scanner = scanner;
    }

    public <T extends Enum<T>> T chooseEnum(Class<T> enumClass, String prompt) {
        System.out.println(prompt);
        for (T e : enumClass.getEnumConstants()) {
            System.out.println((e.ordinal() + 1) + ". " + e.name());
        }
        int choice = readInt("Your choice: ") - 1;
        if (choice < 0 || choice >= enumClass.getEnumConstants().length) {
            System.out.println("❌ Invalid choice!");
            return null;
        }
        return enumClass.getEnumConstants()[choice];
    }

    public String readString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public int readInt(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("❌ Please enter a valid number!");
            }
        }
    }

    public double readDouble(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("❌ Please enter a valid number!");
            }
        }
    }
}
