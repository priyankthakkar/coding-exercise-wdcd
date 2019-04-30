package io.priyank.wdcd.controller;

import io.priyank.wdcd.model.Account;
import io.priyank.wdcd.model.AccountType;
import io.priyank.wdcd.model.Transaction;
import io.priyank.wdcd.model.TransactionType;
import io.priyank.wdcd.service.TransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
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
public class TransactionControllerTest {

    @Mock
    private TransactionService transactionService;

    @InjectMocks
    private TransactionController transactionController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        Account account = new Account(259359299,
                AccountType.CURRENT,
                LocalDateTime.now(),
                "SGD",
                "SGCurrent665",
                89993.65);

        List<Transaction> transactions = Stream.of(new Transaction(1,
                        account,
                        LocalDateTime.now(),
                        "SGD",
                        1367.46,
                        0.0,
                        TransactionType.DEBIT,
                        "Test Narrative"),
                new Transaction(2,
                        account,
                        LocalDateTime.now(),
                        "SGD",
                        0.0,
                        150.30,
                        TransactionType.CREDIT,
                        "Test Narrative")).collect(Collectors.toList());

        Page<Transaction> transactionPage = new PageImpl<>(transactions);
        when(this.transactionService
                .findAllTransactionsByAccountId(any(), any(Pageable.class)))
                .thenReturn(transactionPage);
    }

    @DisplayName("Test TransactionController::findAllTransactionsByAccountId(accountNumber, pageNumber, size)")
    @Test
    public void test_findAllTransactionsByAccountId() {
        Resources<Transaction> transactionResources = this.transactionController
                .findAllTransactionsByAccountId(259359299, 0, 2);
        assertNotNull(transactionResources);
        List<Transaction> transactions = transactionResources.getContent().stream().collect(Collectors.toList());
        assertEquals(2, transactions.size());
        Transaction transaction = transactions.stream().findFirst().get();
        assertEquals(1, transaction.getIdentifier().intValue());
        assertEquals(259359299, transaction.getAccount().getAccountNumber().intValue());
        assertEquals(1367.46, transaction.getDebitAmount().doubleValue());
    }
}
