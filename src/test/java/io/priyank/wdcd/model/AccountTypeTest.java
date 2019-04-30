package io.priyank.wdcd.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AccountTypeTest {

    @DisplayName("AccountType::fromString() get enum")
    @Test
    public void test_AccountType_fromString(){
        AccountType result = AccountType.fromString("savings").get();
        assertNotNull(result);
        assertEquals(AccountType.SAVINGS, result);
    }

    @DisplayName("AccountType::fromString() get enum for unsupported value")
    @Test
    public void test_AccountType_UnsupportedValue() {
        assertThrows(NoSuchElementException.class, () -> AccountType.fromString("test").get());
    }
}
