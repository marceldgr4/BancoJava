package com.Banco.exceptions;

public class InsufficientFundsException extends BankingException {
    public InsufficientFundsException(String accountNumber, double balance, double requested) {
        super(String.format("Insufficient funds in account %s. Balance: $%.2f, Requested: $%.2f", 
                accountNumber, balance, requested));
    }
}
