package com.Banco.exceptions;

public class MinimumBalanceException extends BankingException{

    private final String accountNumber;
    private final String accountType;
    private final double currentBalance;
    private final double minimumRequired;
    private final double attemptedWithdrawal;

    public MinimumBalanceException(String accountNumber, String accountType, double currentBalance, double minimumRequired, double attemptedWithdrawal) {
        super(String.format(
                "%s %s: Cannot withdraw $%.2f. Current balance $%.2f would fall below required minimum $%.2f",
                accountNumber, accountType, currentBalance, minimumRequired
        ));
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.currentBalance = currentBalance;
        this.minimumRequired = minimumRequired;
        this.attemptedWithdrawal = attemptedWithdrawal;
    }
    public String getAccountNumber() {
        return accountNumber;
    }
    public String getAccountType() {
        return accountType;
    }
    public double getCurrentBalance() {
        return currentBalance;
    }
    public double getMinimumRequired() {
        return minimumRequired;
    }
    public double getAttemptedWithdrawal() {
        return attemptedWithdrawal;
    }
}
