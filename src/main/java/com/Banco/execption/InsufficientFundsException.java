package com.Banco.execption;

public class InsufficientFundsException extends RuntimeException {
    public InsufficientFundsException(String accountNumber, double requested, double available) {
        super(String.format(
                "Insufficient funds in account %s: requested $%.2f but only $%.2f available.",
                accountNumber, requested, available
        ));
    }
}
