package io.priyank.wdcd.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "account")
public class Account {

    @Id
    @Column(name = "account_number")
    private Integer accountNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "account_type")
    private AccountType accountType;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "balance_date")
    private LocalDateTime balanceDate;

    @Column(name = "currency")
    private String currency;

    @Column(name = "account_name")
    private String accountName;

    @Column(name = "opening_balance")
    private double openingBalance;

    public Integer getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Integer accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public LocalDateTime getBalanceDate() {
        return balanceDate;
    }

    public void setBalanceDate(LocalDateTime balanceDate) {
        this.balanceDate = balanceDate;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public double getOpeningBalance() {
        return openingBalance;
    }

    public void setOpeningBalance(double openingBalance) {
        this.openingBalance = openingBalance;
    }
}

