package com.Banco.service;

import com.Banco.exceptions.AccountNotFoundException;
import com.Banco.exceptions.AccountOwnershipException;
import com.Banco.exceptions.ClientNotFoundException;
import com.Banco.exceptions.DuplicateResourceException;
import com.Banco.exceptions.InvalidTransactionException;
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
    private final ClientRepository  clientRepository;

    public AccountService(AccountRepository accountRepository, ClientRepository clientRepository) {
        if (accountRepository == null)
            throw new IllegalArgumentException("AccountRepository cannot be null");
        if (clientRepository == null)
            throw new IllegalArgumentException("ClientRepository cannot be null");
        this.accountRepository = accountRepository;
        this.clientRepository  = clientRepository;
    }

    // ── Open accounts ─────────────────────────────────────────────────────────

    public void openAccount(int clientId, BankAccount bankAccount) {
        if (bankAccount == null)
            throw new IllegalArgumentException("BankAccount cannot be null");
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ClientNotFoundException(clientId));
        if (accountRepository.existsByAccountNumber(bankAccount.getAccountNumber()))
            throw new DuplicateResourceException(
                    "Account number '" + bankAccount.getAccountNumber() + "' already exists.");
        client.addAccount(bankAccount);
        accountRepository.save(bankAccount);
    }

    public void openSavingsAccount(int clientId, String accountNumber,
                                   double initialBalance, double interestRate) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ClientNotFoundException(clientId));
        SavingsAccount acc = new SavingsAccount(accountNumber, client, initialBalance, interestRate);
        openAccount(clientId, acc);
    }

    public void openInvestmentAccount(int clientId, String accountNumber,
                                      double initialBalance,
                                      com.Banco.model.Investment.InvestmentCompany company) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ClientNotFoundException(clientId));
        InvestmentAccount acc = new InvestmentAccount(accountNumber, client, initialBalance, company);
        openAccount(clientId, acc);
    }

    // ── Queries ───────────────────────────────────────────────────────────────

    public Optional<BankAccount> findByAccountNumber(String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber);
    }

    public BankAccount getByAccountNumber(String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new AccountNotFoundException(accountNumber));
    }

    public List<BankAccount> getAccountsByClient(int clientId) {
        return accountRepository.findAllByClientId(clientId);
    }

    public List<BankAccount> getAllAccounts() {
        return accountRepository.findAll();
    }

    // ── Transactions ──────────────────────────────────────────────────────────

    public void withdraw(int clientId, String accountNumber, double amount) {
        BankAccount account = getByAccountNumber(accountNumber);
        if (account.getOwner().getId() != clientId)
            throw new AccountOwnershipException(clientId, accountNumber, account.getOwner().getId());
        account.withdraw(amount);
    }

    public void deposit(int clientId, String accountNumber, double amount) {
        BankAccount account = getByAccountNumber(accountNumber);
        if (account.getOwner().getId() != clientId)
            throw new AccountOwnershipException(clientId, accountNumber, account.getOwner().getId());
        account.deposit(amount);
    }

    public double fullWithdraw(String accountNumber) {
        BankAccount account = getByAccountNumber(accountNumber);
        if (!(account instanceof InvestmentAccount inv))
            throw new IllegalArgumentException(
                    "Account '" + accountNumber + "' is not an InvestmentAccount.");
        return inv.fullWithdraw();
    }

    // ── Interest ──────────────────────────────────────────────────────────────

    /**
     * Aplica interés mensual a TODAS las cuentas de ahorro del sistema.
     */
    public void applyMonthlyInterestToAllSavings() {
        accountRepository.findAll().stream()
                .filter(a -> a instanceof SavingsAccount)
                .map(a -> (SavingsAccount) a)
                .forEach(SavingsAccount::applyMonthlyInterest);
    }

    /**
     * Aplica interés mensual a UNA cuenta de ahorro específica.
     *
     * @param accountNumber número de cuenta
     * @throws AccountNotFoundException    si la cuenta no existe
     * @throws InvalidTransactionException si la cuenta no es de ahorro
     */
    public void applyMonthlyInterestToAccount(String accountNumber) {
        BankAccount account = getByAccountNumber(accountNumber);
        if (!(account instanceof SavingsAccount savings))
            throw new InvalidTransactionException(
                    "Account '" + accountNumber + "' is not a Savings Account. " +
                            "Monthly interest only applies to savings accounts.");
        savings.applyMonthlyInterest();
    }
}