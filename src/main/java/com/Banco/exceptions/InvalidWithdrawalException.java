package com.Banco.exceptions;

public class InvalidWithdrawalException  extends BankingException {
    private final String accountNumber;
    private final double requestedAmount;
    private final double currentBalance;
    private final double minimumBalance;

    public InvalidWithdrawalException(String accountNumber, double requestedAmount, double currentBalance, double minimumBalance) {
        super(String.format(
                "Invalid withdrawal: Account %s cannot withdraw $%.2f (Current: $%.2f, Required minimum: $%.2f)",
                accountNumber, requestedAmount, currentBalance, minimumBalance
        ));
        this.accountNumber = accountNumber;
        this.requestedAmount = requestedAmount;
        this.currentBalance = currentBalance;
        this.minimumBalance = minimumBalance;
    }
    public InvalidWithdrawalException(String accountNumber, String reason){
        super(String.format("Invalid withdrawal: Account %s: %s", accountNumber, reason));
        this.accountNumber = accountNumber;
        this.requestedAmount = 0.0;
        this.currentBalance = 0.0;
        this.minimumBalance = 0.0;
    }
    public String getAccountNumber() {
        return accountNumber;
    }
    public double getRequestedAmount() {
        return requestedAmount;
    }
    public double getCurrentBalance() {
        return currentBalance;
    }
    public double getMinimumBalance() {
        return minimumBalance;
    }
}
