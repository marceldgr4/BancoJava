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
    public void deposit(int clientId, String accountNumber, double amount){
        accountService.deposit(clientId, accountNumber, amount);
    }
    public void withdraw(int clientId, String accountNumber, double amount){
        accountService.withdraw(clientId, accountNumber, amount);
    }
    public void openSavingsAccount(int clientId, String accountNumber, double initialBalance, double interestRate) {
        accountService.openSavingsAccount(clientId, accountNumber, initialBalance, interestRate);
    }
    public void openInvestmentAccount(int clientId, String accountNumber, double initialBalance, com.Banco.model.Investment.InvestmentCompany company) {
        accountService.openInvestmentAccount(clientId, accountNumber, initialBalance, company);
    }
    public double fullWithdraw(String accountNumber){
        return accountService.fullWithdraw(accountNumber);
    }
    public void applyMonthlyInterest(){
        accountService.applyMonthlyInterestToAllSavings();
    }
    public List<BankAccount> getClientAccounts(int clientId){
        return accountService.getAccountsByClient(clientId);
    }
}
