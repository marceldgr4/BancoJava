package com.Banco.exceptions;

public class DuplicateResourceException extends BankingException {
    public DuplicateResourceException(String message) {
        super(message);
    }
}
