package com.ui.ac.shop.ir.shop.model.Enums;

public enum Status {
    ACCEPTED("A") , PENDING("P") , REJECTED("R");

    private final String value;

    public String getValue() {
        return value;
    }

    Status(String value) {
        this.value = value;
    }
}
