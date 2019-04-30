package io.priyank.wdcd.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TransactionTypeTest {

    @DisplayName("TransactionType::fromString() get enum")
    @Test
    public void test_TransactionType_fromString() {
        TransactionType result = TransactionType.fromString("credit").get();
        assertNotNull(result);
        assertEquals(TransactionType.CREDIT, result);
    }

    @DisplayName("TransactionType::fromString() get enum for unsupported value")
    @Test
    public void test_TransactionType_UnsupportedValue() {
        assertThrows(NoSuchElementException.class, () -> TransactionType.fromString("test").get());
    }
}
