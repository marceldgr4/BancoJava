package com.Banco.service;

import com.Banco.model.Transaction.Transaction;
import com.Banco.repository.TransactionRepository;

import java.util.List;

public class TransactionService {
 private final TransactionRepository transactionRepository;

 public TransactionService(TransactionRepository transactionRepository) {
     if (transactionRepository == null)
         throw new IllegalArgumentException("TransactionRepository cannot be null");
     this.transactionRepository = transactionRepository;

 }
 public List<Transaction>  getTransactionsByAccount(String accountNumber) {
    return transactionRepository.findByAccountNumber(accountNumber);
 }
 public List<Transaction> getAllTransactions() {
    return transactionRepository.findAll();
 }

}
