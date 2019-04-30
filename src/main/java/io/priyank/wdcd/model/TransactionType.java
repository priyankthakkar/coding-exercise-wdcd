package io.priyank.wdcd.model;

import java.util.Arrays;
import java.util.Optional;

public enum TransactionType {

    CREDIT("CREDIT"),
    DEBIT("DEBIT");

    private String value;

    TransactionType(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public static Optional<TransactionType> fromString(String value) {
        return Arrays
                .stream(values())
                .filter(at -> at.value.equalsIgnoreCase(value))
                .findFirst();
    }
}