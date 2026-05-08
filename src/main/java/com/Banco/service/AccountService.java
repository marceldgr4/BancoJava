package com.Banco.service;

import com.Banco.model.domain.Account.BankAccount;

import javax.security.auth.login.AccountNotFoundException;
import java.util.Optional;

public class AccountService {

    public Optional<BankAccount> findAccountByNumber(String accountNumber) {
        return clients.stream().flatMap(c -> c.getAccounts().stream())
                .filter(a -> a.getAccountNumber().equals(accountNumber))
                .findFirst();
    }
    public BankAccount getAccountByNumber(String accountNumber) {
        return findAccountByNumber(accountNumber).orElseThrow(()-> new AccountNotFoundException(accountNumber));
    }

    public void performDeposit(String accountNumber, double amount) {
        BankAccount account = getAccountByNumber(accountNumber);
        account.deposit(amount);
    }
    public void performWithdraw(String accountNumber, double amount) {
        BankAccount account = getAccountByNumber(accountNumber);
        account.withdraw(amount);
    }

    public double performFullWithdrawal(String accountNumber) {
        BankAccount account = getAccountByNumber(accountNumber);
        if(!(account instanceof InvestmentAccount investmentAccount))
            throw new IllegalArgumentException("account type is not InvestmentAccount");
        return investmentAccount.fullWithdraw();
    }
}
