package io.priyank.wdcd.controller;

import io.priyank.wdcd.model.Account;
import io.priyank.wdcd.model.AccountType;
import io.priyank.wdcd.service.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.Resources;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class AccountControllerTest {

    @Mock
    private AccountService accountService;

    @InjectMocks
    private AccountController accountController;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
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
        when(this.accountService.getAccounts(any(Pageable.class))).thenReturn(accountPage);
    }

    @DisplayName("Test AccountController::getAccounts(pageNumber, size)")
    @Test
    public void test_getAccounts() {
        Resources<Account> accountResources = this.accountController.getAccounts(0, 2);
        List<Account> accounts = accountResources.getContent().stream().collect(Collectors.toList());
        assertNotNull(accounts);
        assertEquals(2, accounts.size());
        Account account = accounts.stream().findFirst().get();
        assertEquals(259359299, account.getAccountNumber().intValue());
        assertEquals(89993.65, account.getOpeningBalance());
    }
}
