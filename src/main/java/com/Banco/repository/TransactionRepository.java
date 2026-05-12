package com.Banco.repository;

import com.Banco.model.Transaction.Transaction;

import java.util.Collections;
import java.util.List;

public class TransactionRepository {
    private final AccountRepository accountRepository;
    public TransactionRepository(AccountRepository accountRepository) {
        if(accountRepository == null)
            throw new NullPointerException("accountRepositori must not be null");
                    this.accountRepository = accountRepository;
    }
    public List<Transaction> findAll(){
        return accountRepository.findAll().stream()
                .flatMap(a->a.getTransactions().stream())
                .toList();
    }
    public List<Transaction> findByAccountNumber(String accountNumber){
        return accountRepository.findByAccountNumber(accountNumber)
                .map(a->a.getTransactions())
                .orElse(Collections.emptyList());
    }
}
