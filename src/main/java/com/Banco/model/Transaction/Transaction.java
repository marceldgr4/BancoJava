package com.Banco.model.Transaction;

import com.Banco.model.Emun.TransactionType;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Transaction {
    private final TransactionType type;
    private final double amount;
    private final double balanceAfter;
    private final LocalDateTime timestamp;

    public Transaction(TransactionType type, double amount, double balanceAfter, LocalDateTime timestamp) {
        if (type == null)
            throw new IllegalArgumentException("Type may not be null");
        if (amount <= 0)
            throw new IllegalArgumentException("Amount may not be negative");
        if (balanceAfter < 0)
            throw new IllegalArgumentException("Balance may not be negative");
        
        this.type = type;
        this.amount = amount;
        this.balanceAfter = balanceAfter;
        this.timestamp = timestamp != null ? timestamp : LocalDateTime.now();
    }

    public Transaction(TransactionType transactionType, double amount, double balance) {
        this(transactionType, amount, balance, LocalDateTime.now());
    }

    public TransactionType getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public double getBalanceAfter() {
        return balanceAfter;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return String.format("Transaction[type=%-15s amount=$%10.2f balanceAfter=$%10.2f at=%s]",
                type,amount,balanceAfter,timestamp);
    }
}
