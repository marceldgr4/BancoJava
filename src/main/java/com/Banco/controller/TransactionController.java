package com.Banco.controller;

import com.Banco.model.Transaction.Transaction;
import com.Banco.service.TransactionService;

import java.util.List;

public class TransactionController {
    private final TransactionService transactionService;
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }
    public List<Transaction> getAccountTransactions(String  accountNumber){
        return transactionService.getTransactionsByAccount(accountNumber);
    }
    public List<Transaction> getAllTransactions(){
        return transactionService.getAllTransactions();
    }
}
