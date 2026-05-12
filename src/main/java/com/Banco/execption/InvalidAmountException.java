package com.Banco.execption;

public class InvalidAmountException  extends  RuntimeException{
    public InvalidAmountException(double amount) {
        super(String.format("Invalid amount: $%.2f. Amount must be positive.", amount));
    }
    public InvalidAmountException(String message) {
        super(message);
    }
}
