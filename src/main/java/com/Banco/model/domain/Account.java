package com.Banco.model.domain;

import com.Banco.model.Emun.TypeBanc;

public abstract  class Account {
    protected String accountNumber;
    protected Clients owner;
    protected double balance;

    public static TypeBanc getTypeBanc(String accountNumber) {
        switch (accountNumber) {
            case "Banco_Nacional":
        }
        return null;
    }

    public Account(String accountNumber, Clients owner, double Beginningbalance) {
        this.accountNumber = accountNumber;
        this.owner = owner;
        this.balance = Beginningbalance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Clients getOwner() {
        return owner;
    }

    public void setOwner(Clients owner) {
        this.owner = owner;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
