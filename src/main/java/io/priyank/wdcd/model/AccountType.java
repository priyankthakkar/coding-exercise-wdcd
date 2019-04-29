package io.priyank.wdcd.model;

import java.util.Arrays;
import java.util.Optional;

public enum AccountType {
    SAVINGS("SAVINGS"),
    CURRENT("CURRENT");

    private String value;

    AccountType(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public static Optional<AccountType> fromString(String value) {
        return Arrays
                .stream(values())
                .filter(at -> at.value.equalsIgnoreCase(value))
                .findFirst();
    }
}
