package com.Banco.exceptions;

public class EmployeeNotFoundException extends BankingException {
    public EmployeeNotFoundException(int id) {
        super("Employee with ID " + id + " not found.");
    }
}
