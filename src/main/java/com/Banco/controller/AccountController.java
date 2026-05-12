package com.Banco.controller;

import com.Banco.model.domain.Account.BankAccount;
import com.Banco.service.AccountService;

import java.util.List;

public class AccountController {
    public final AccountService accountService;
    public AccountController(AccountService accountService){
        this.accountService = accountService;
    }
    public void openAccount(int clientId, BankAccount bankAccount){
        accountService.openAccount(clientId, bankAccount);
    }
    public BankAccount getBankAccount(String accountNumber){
        return accountService.getByAccountNumber(accountNumber);
    }
    public void deposit(String accountNumber, double amount){
        accountService.deposit(accountNumber, amount);
    }
    public void withdraw(String accountNumber, double amount){
        accountService.withdraw(accountNumber, amount);
    }
    public double fullWithdraw(String accountNumber){
        return accountService.fullWithdraw(accountNumber);
    }
    public void applyMonthlyInterest(){
        accountService.applyMonthInterestToAllSaving();
    }
    public List<BankAccount> getClientAccounts(int clientId){
        return accountService.getAccountsByClient(clientId);
    }
}
