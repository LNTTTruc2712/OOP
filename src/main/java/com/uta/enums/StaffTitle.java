package com.uta.enums;

public enum StaffTitle {
    HEAD("Department Head", 2000),
    DEPUTY("Deputy Head", 1000),
    EMPLOYEE("Staff Member", 500);

    private final String label;
    private final int allowance;

    StaffTitle(String label, int allowance) {
        this.label = label;
        this.allowance = allowance;
    }

    public String getLabel() {
        return label;
    }

    public int getAllowance() {
        return allowance;
    }
}
