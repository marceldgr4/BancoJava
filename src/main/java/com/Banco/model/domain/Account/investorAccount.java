package com.Banco.model.domain.Account;

import com.Banco.model.Emun.TransactionType;
import com.Banco.model.Investment.InvestmentCompany;
import com.Banco.model.domain.Person.Client;

public class investorAccount extends BankAccount{
    public static final double MINIMUN_INITIAL_DEPOSIT = 25000.00;
    public static final double MINIMUN_BALANCE = 10000.00;

    private InvestmentCompany company;

    private boolean cancelled = false;

    public investorAccount(String accountNumber, Client owner,
                           double initiaBalance, InvestmentCompany company){
        super(accountNumber, owner, initiaBalance);
        this.company = company;
    }

    @Override
    protected boolean isWithdrawalValid(double amount) {
        if(cancelled){
            return  amount <= getBalance();
        }
        return (getBalance() - amount) >= MINIMUN_BALANCE;
    }

    @Override
    public String getAccountType() {
        return "Investment Account";
    }

    @Override
    protected double getMinimumInitialBalance() {
        return MINIMUN_INITIAL_DEPOSIT;
    }
    public double fullWithdraw(){
        if(cancelled)
            throw new IllegalStateException("Account"+ getAccountNumber()+ "is Already cancelled");
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
    public boolean isCancelled(){
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
