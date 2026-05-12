package com.Banco.exceptions;

public class InvalidAmountException extends BankingException {
    public InvalidAmountException(String message) {
        super(message);
    }
    public InvalidAmountException(double amount) {
        super("Invalid amount: " + amount + ". Must be positive.");
    }
}
