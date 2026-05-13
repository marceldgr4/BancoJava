package com.Banco.controller;

import com.Banco.model.Investment.InvestmentCompany;
import com.Banco.model.domain.Account.BankAccount;
import com.Banco.service.AccountService;

import java.util.List;

public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    // ── Open accounts ─────────────────────────────────────────────────────────

    public void openAccount(int clientId, BankAccount bankAccount) {
        accountService.openAccount(clientId, bankAccount);
    }

    public void openSavingsAccount(int clientId, String accountNumber,
                                   double initialBalance, double interestRate) {
        accountService.openSavingsAccount(clientId, accountNumber, initialBalance, interestRate);
    }

    public void openInvestmentAccount(int clientId, String accountNumber,
                                      double initialBalance, InvestmentCompany company) {
        accountService.openInvestmentAccount(clientId, accountNumber, initialBalance, company);
    }

    // ── Transactions ──────────────────────────────────────────────────────────

    public void deposit(int clientId, String accountNumber, double amount) {
        accountService.deposit(clientId, accountNumber, amount);
    }

    public void withdraw(int clientId, String accountNumber, double amount) {
        accountService.withdraw(clientId, accountNumber, amount);
    }

    public double fullWithdraw(String accountNumber) {
        return accountService.fullWithdraw(accountNumber);
    }

    // ── Interest ──────────────────────────────────────────────────────────────

    /** Aplica interés mensual a TODAS las cuentas de ahorro. */
    public void applyMonthlyInterest() {
        accountService.applyMonthlyInterestToAllSavings();
    }

    /** Aplica interés mensual a UNA cuenta de ahorro específica. */
    public void applyMonthlyInterestToAccount(String accountNumber) {
        accountService.applyMonthlyInterestToAccount(accountNumber);
    }

    // ── Queries ───────────────────────────────────────────────────────────────

    public BankAccount getBankAccount(String accountNumber) {
        return accountService.getByAccountNumber(accountNumber);
    }

    public List<BankAccount> getClientAccounts(int clientId) {
        return accountService.getAccountsByClient(clientId);
    }
}