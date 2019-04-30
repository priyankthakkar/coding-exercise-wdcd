package io.priyank.wdcd.service;

import io.priyank.wdcd.model.Transaction;
import io.priyank.wdcd.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    private TransactionRepository transactionRepository;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public Page<Transaction> findAllTransactionsByAccountId(Integer accountId, Pageable pageable){
        return this.transactionRepository.findAllTransactionsByAccountId(accountId, pageable);
    }
}
