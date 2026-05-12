package com.Banco.exceptions;

public class ValidationException extends BankingException {
    public ValidationException(String message) {
        super(message);
    }
}
