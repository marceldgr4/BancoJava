package com.Banco.service;

import com.Banco.execption.AccountNotFoundException;
import com.Banco.execption.ClientNotFoundException;
import com.Banco.model.domain.Account.BankAccount;
import com.Banco.model.domain.Account.InvestmentAccount;
import com.Banco.model.domain.Account.SavingsAccount;
import com.Banco.model.domain.Person.Client;
import com.Banco.repository.AccountRepository;
import com.Banco.repository.ClientRepository;

import java.util.List;
import java.util.Optional;

public class AccountService {

 private final AccountRepository accountRepository;
 private final ClientRepository clientRepository;

 public AccountService(AccountRepository accountRepository, ClientRepository clientRepository){
     if (accountRepository ==null){
         throw new IllegalArgumentException("AccountRepositori cannot be null");
     }
     if (clientRepository==null){
         throw new IllegalArgumentException("ClientRepository cannot be null");

     }
     this.accountRepository = accountRepository;
     this.clientRepository = clientRepository;
 }
 public  void openAccount(int clientId, BankAccount bankAccount) {
     if (bankAccount == null) {
         throw new IllegalArgumentException("BankAccount cannot be null");
         Client client = clientRepository.findById(clientId)
                 .orElseThrow(() -> new ClientNotFoundException(clientId));
         if (accountRepository.existsByAccountNumber(bankAccount.getAccountNumber()))
             throw new IllegalArgumentException("Account number '" + bankAccount.getAccountNumber() + "' already exists.");
         client.addAccount(bankAccount);

     }
 }
     public Optional<BankAccount> findByAccountNumber(String accountNumber) {
         return accountRepository.findByAccountNumber(accountNumber);
     }
     public BankAccount getByAccountNumber(String accountNumber) {
     return accountRepository.findByAccountNumber(accountNumber).orElseThrow(()-> new AccountNotFoundException(accountNumber));
     }
     public void deposit(String accountNumber, double amount) {
     getByAccountNumber(accountNumber).deposit(amount);
     }
     public void withdraw(String accountNumber, double amount) {
     getByAccountNumber(accountNumber).withdraw(amount);
     }

    public double fullWithdraw(String accountNumber) {
        BankAccount account = getByAccountNumber(accountNumber);
        if (!(account instanceof InvestmentAccount investmentAccount))
            throw new IllegalArgumentException(
                    "Account '" + accountNumber + "' is not an InvestmentAccount.");
        return investmentAccount.fullWithdraw();
    }
    public void applyMonthInterestToAllSaving(){
     accountRepository.findAll().stream()
             .filter(a-> a instanceof SavingsAccount)
             .map(a->(SavingsAccount)a)
             .forEach(SavingsAccount::applyMonthlyInterest);

    }
    public List<BankAccount> getAccountsByClient(int clientId) {
     return accountRepository.findAllByClientId(clientId);

    }
    public List<BankAccount> getAllAccounts(){
        return accountRepository.findAll();
    }
}
