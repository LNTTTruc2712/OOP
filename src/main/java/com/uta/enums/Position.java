package com.uta.enums;

public enum Position {
    MANAGER(2000),
    VICE_MANAGER(1000),
    EMPLOYEE(500);

    private final int allowance;

    Position(int allowance) {
        this.allowance = allowance;
    }

    public int getAllowance() {
        return allowance;
    }
}
