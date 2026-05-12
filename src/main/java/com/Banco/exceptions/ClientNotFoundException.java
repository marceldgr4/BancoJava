package com.Banco.exceptions;

public class ClientNotFoundException extends BankingException {
    public ClientNotFoundException(int id) {
        super("Client with ID " + id + " not found.");
    }
}
