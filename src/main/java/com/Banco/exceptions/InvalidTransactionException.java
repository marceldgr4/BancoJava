package com.Banco.exceptions;

public class InvalidTransactionException extends BankingException{
    private final String accountNumber;
    private final String transactionType;
    private final double amount;

    public InvalidTransactionException(String accountNumber,
                                       String transactionType,
                                       double amount,
                                       String reason) {
        super(String.format(
                "Invalid %s transaction on account %s for amount $%.2f: %s",
                transactionType, accountNumber, amount, reason
        ));
        this.accountNumber = accountNumber;
        this.transactionType = transactionType;
        this.amount = amount;
    }

    public InvalidTransactionException(String message) {
        super(message);
        this.accountNumber = null;
        this.transactionType = null;
        this.amount = 0;
    }

    public String getAccountNumber() { return accountNumber; }
    public String getTransactionType() { return transactionType; }
    public double getAmount() { return amount; }
}
