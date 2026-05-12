package com.Banco.model.domain.Account;

import com.Banco.model.type.TransactionType;
import com.Banco.model.domain.Person.Client;
import com.Banco.util.Constants;

public class SavingsAccount extends BankAccount {
    public static final double MINIMUM_INITIAL_DEPOSIT = Constants.SAVINGS_MINIMUM_INITIAL;
    public static final double MINIMUM_BALANCE = Constants.SAVINGS_MINIMUM_BALANCE;

    private double annualInterestRate;

    public SavingsAccount(String accountNumber, Client owner, double initialBalance, double annualInterestRate) {
        super(accountNumber, owner, initialBalance);
        com.Banco.util.validator.AccountValidator.validateSavingsInitialDeposit(initialBalance);
        validateInterestRate(annualInterestRate);
        this.annualInterestRate = annualInterestRate;
    }

    public double getAnnualInterestRate() {
        return annualInterestRate;
    }

    @Override
    public boolean isWithdrawalValid(double amount) {
        return (getBalance() - amount) >= MINIMUM_BALANCE;
    }

    @Override
    public String getAccountType() {
        return "Savings Account";
    }

    @Override
    protected double getMinimumInitialBalance() {
        return MINIMUM_INITIAL_DEPOSIT;
    }

    public void setAnnualInterestRate(double annualInterestRate) {
        validateInterestRate(annualInterestRate);
        this.annualInterestRate = annualInterestRate;
    }

    private static void validateInterestRate(double rate) {
        if (rate < 0 || rate > 1)
            throw new IllegalArgumentException("\"Annual interest rate must be between 0.0 and 1.0, got: " + rate);
    }

    public void applyMonthlyInterest() {
        double monthlyRate = annualInterestRate / 12.0;
        double interest =getBalance()*monthlyRate;
        if (interest> 0){
            setBalance(getBalance()+interest);
            recordTransaction(TransactionType.INTEREST, interest);
        }
    }
}
