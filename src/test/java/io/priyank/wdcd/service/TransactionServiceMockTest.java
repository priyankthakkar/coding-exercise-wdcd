package io.priyank.wdcd.service;

import io.priyank.wdcd.model.Account;
import io.priyank.wdcd.model.AccountType;
import io.priyank.wdcd.model.Transaction;
import io.priyank.wdcd.model.TransactionType;
import io.priyank.wdcd.repository.TransactionRepository;
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
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TransactionServiceMockTest {

    @MockBean
    private TransactionRepository transactionRepository;

    @Autowired
    private TransactionService transactionService;

    @BeforeEach
    public void setMockOutput() {
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
        when(this.transactionRepository
                .findAllTransactionsByAccountId(any(), any(Pageable.class)))
                .thenReturn(transactionPage);
    }

    @DisplayName("Test findAllTransactionsByAccountId(accountId, pageable)")
    @Test
    public void test_findAllTransactionsByAccountId() {
        Page<Transaction> transactionPage = this.transactionService
                .findAllTransactionsByAccountId(259359299, PageRequest.of(0, 2));
        assertNotNull(transactionPage);
        assertFalse(transactionPage.hasNext());
        assertFalse(transactionPage.hasPrevious());
        assertTrue(transactionPage.hasContent());
        assertEquals(2, transactionPage.getContent().size());
        Transaction transaction = transactionPage.getContent().stream().findFirst().get();
        assertEquals(1, transaction.getIdentifier().intValue());
        assertEquals(259359299, transaction.getAccount().getAccountNumber().intValue());
        assertEquals(1367.46, transaction.getDebitAmount().doubleValue());
    }
}
