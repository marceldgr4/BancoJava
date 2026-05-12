package com.Banco.model.domain.Account;

import com.Banco.model.Emun.TransactionType;
import com.Banco.model.Transaction.Transaction;
import com.Banco.model.domain.Person.Client;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;



public abstract class BankAccount {

    private static int accountCount = 0;

    private final String accountNumber;
    private final Client owner;
    private  double balance;
    private final LocalDate creationDate;
    private final List<Transaction> transactions = new ArrayList<>();

    public BankAccount(String accountNumber, Client owner, double initialBalance){
        if(accountNumber == null|| accountNumber.isBlank())
            throw new IllegalArgumentException("Account number cannot be null or blank");
        if(owner == null)
            throw new IllegalArgumentException("Owner cannot be null");
        if(initialBalance < getMinimumInitialBalance())
            throw new IllegalArgumentException(String.format(
                    "Initial balance $%.2f is below required minimum $%.2f for %s",
        initialBalance,getMinimumInitialBalance(),getClass().getSimpleName()));

        this.accountNumber = accountNumber;
        this.owner = owner;
        this.balance = initialBalance;
        this.creationDate = LocalDate.now();
        accountCount++;
    }
    protected abstract boolean isWithdrawalValid(double amount);

    public  abstract  String getAccountType();

    protected abstract double getMinimumInitialBalance();

    // ── Operations ─

    public void  deposit(double amount){
        if(amount <= 0)
            throw new IllegalArgumentException("Amount cannot be negative, got: "+amount );
        balance += amount;
        transactions.add(new Transaction(TransactionType.DEPOSIT,amount,balance));
    }
    public void  withdraw(double amount){
        if(amount <= 0)
            throw new IllegalArgumentException("Amount cannot be negative, got: "+amount );
        if(!isWithdrawalValid(amount))
            throw new IllegalArgumentException(String.format("Cannot withdraw $%.2f from %s (balance: $%.2f)",amount,getAccountType(),balance));
        balance -= amount;
        transactions.add(new Transaction(TransactionType.WITHDRAWAL, amount, balance));
    }
    //── Protected helpers ────────────────────────────────────
    /* Permite a subclases registrar transacciones especiales*/

    protected void recordTransaction(TransactionType type, double amount){
        transactions.add(new Transaction(type,amount,balance));
    }
    protected void setBalance(double balance){
        this.balance = balance;
    }
    // ── Static ──

    public static int getAccountCount(){
        return accountCount;
    }
    static void resetAccountCount(){
        accountCount = 0;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public Client getOwner() {
        return owner;
    }

    public double getBalance() {
        return balance;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public List<Transaction> getTransactions() {
        return Collections.unmodifiableList(transactions);
    }

    @Override
    public String toString() {
        return String.format("%s[number=%s, owner=%s, balance=$%.2f]",
                getAccountType(),accountNumber,owner.getFullName(), balance);
    }
}
