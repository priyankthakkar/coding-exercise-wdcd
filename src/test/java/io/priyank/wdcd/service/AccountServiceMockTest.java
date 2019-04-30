package io.priyank.wdcd.service;

import io.priyank.wdcd.model.Account;
import io.priyank.wdcd.model.AccountType;
import io.priyank.wdcd.repository.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class AccountServiceMockTest {

    @MockBean
    private AccountRepository accountRepository;

    @Autowired
    private AccountService accountService;

    @BeforeEach
    public void setMockOutput() {
        List<Account> accounts = Stream.of(new Account(259359299,
                        AccountType.CURRENT,
                        LocalDateTime.now(),
                        "SGD",
                        "SGCurrent665",
                        89993.65),
                new Account(968786565,
                        AccountType.SAVINGS,
                        LocalDateTime.now(),
                        "AUD",
                        "AUSavings610",
                        52259.24))
                .collect(Collectors.toList());

        Page<Account> accountPage = new PageImpl<>(accounts);
        when(this.accountRepository.findAll(any(Pageable.class))).thenReturn(accountPage);
    }

    @DisplayName("Test getAccounts(Pageable pageable)")
    @Test
    public void test_getAccounts() {
        Page<Account> accountPage = this.accountService.getAccounts(PageRequest.of(0, 1));
        assertNotNull(accountPage);
        assertFalse(accountPage.hasNext());
        assertFalse(accountPage.hasPrevious());
        assertTrue(accountPage.hasContent());
        assertEquals(2, accountPage.getContent().size());
        Account account = accountPage.getContent().stream().findFirst().get();
        assertEquals(259359299, account.getAccountNumber().intValue());
        assertEquals(89993.65, account.getOpeningBalance());
        System.out.println();
    }
}