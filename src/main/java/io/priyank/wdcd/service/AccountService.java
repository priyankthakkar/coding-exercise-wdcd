package io.priyank.wdcd.service;

import io.priyank.wdcd.model.Account;
import io.priyank.wdcd.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    private AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Page<Account> getAccounts(Pageable pageable) {
        return this.accountRepository.findAll(pageable);
    }
}
