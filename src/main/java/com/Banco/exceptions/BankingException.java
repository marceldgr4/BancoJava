package com.Banco.exceptions;

public class BankingException extends RuntimeException {
    public BankingException(String message) {
        super(message);
    }
}
