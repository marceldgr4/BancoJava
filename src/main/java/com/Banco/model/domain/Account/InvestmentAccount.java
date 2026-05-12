package com.Banco.model.domain.Account;

import com.Banco.model.type.TransactionType;
import com.Banco.model.Investment.InvestmentCompany;
import com.Banco.model.domain.Person.Client;
import com.Banco.util.Constants;

public class InvestmentAccount extends BankAccount {
    public static final double MINIMUM_INITIAL_DEPOSIT = Constants.INVESTMENT_MINIMUM_INITIAL;
    public static final double MINIMUM_BALANCE = Constants.INVESTMENT_MINIMUM_BALANCE;

    private InvestmentCompany company;
    private boolean cancelled = false;

    public InvestmentAccount(String accountNumber, Client owner, double initialBalance, InvestmentCompany company) {
        super(accountNumber, owner, initialBalance);
        this.company = company;
    }

    @Override
    protected boolean isWithdrawalValid(double amount) {
        if (cancelled) {
            return amount <= getBalance();
        }
        return (getBalance() - amount) >= MINIMUM_BALANCE;
    }

    @Override
    public String getAccountType() {
        return "Investment Account";
    }

    @Override
    protected double getMinimumInitialBalance() {
        return MINIMUM_INITIAL_DEPOSIT;
    }

    public double fullWithdraw() {
        if (cancelled)
            throw new IllegalStateException("Account " + getAccountNumber() + " is already cancelled");
        
        cancelled = true;
        double total = getBalance();
        setBalance(0);
        recordTransaction(TransactionType.FULL_WITHDRAWAL, total);
        return total;
    }

    public InvestmentCompany getCompany() {
        return company;
    }

    public void setCompany(InvestmentCompany company) {
        this.company = company;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    @Override
    public String toString() {
        String companyName = company != null ? company.getName() : "N/A";
        return String.format("InvestmentAccount[number=%s, owner=%s, balance=$%.2f, company=%s, cancelled=%s]",
                getAccountNumber(), getOwner().getFullName(), getBalance(), companyName, cancelled);
    }
}
