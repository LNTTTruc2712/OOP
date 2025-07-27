package com.uta.enums;

public enum DegreeRank {
    BACHELOR("Bachelor", 300),
    MASTER("Master", 500),
    DOCTORAL("DOCTORAL", 1000);

    private final String label;
    private final int allowance;

    DegreeRank(String label, int allowance) {
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
