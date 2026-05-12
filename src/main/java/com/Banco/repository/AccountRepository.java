package com.Banco.repository;

import com.Banco.model.domain.Account.BankAccount;
import com.Banco.model.domain.Person.Client;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class AccountRepository {
    private  final ClientRepository clientRepository;
    public AccountRepository(ClientRepository clientRepository) {
        if (clientRepository == null)
            throw new IllegalArgumentException("clientRepository cannot be null");
        this.clientRepository = clientRepository;
    }
    public Optional<BankAccount> findByAccountNumber(String accountNumber) {
        return clientRepository.findAll().stream()
                .flatMap(c->c.getAccounts().stream())
                .filter(a -> a.getAccountNumber().equals(accountNumber))
                .findFirst();
    }

    public void save(BankAccount account) {
        if (account == null)
            throw new IllegalArgumentException("Account must not be null");
        // Check if already added to avoid duplicates if needed
        if (!account.getOwner().getAccounts().contains(account)) {
            account.getOwner().addAccount(account);
        }
    }

    public boolean deleteByAccountNumber(String accountNumber) {
        return findByAccountNumber(accountNumber)
                .map(account -> account.getOwner().removeAccount(account))
                .orElse(false);
    }

    public boolean existsByAccountNumber(String accountNumber) {
        return findByAccountNumber(accountNumber).isPresent();
    }
    public List<BankAccount> findAllByClientId(int clientId) {
        return clientRepository.findById(clientId)
                .map(Client::getAccounts)
                .orElse(Collections.emptyList());
    }

    public List<BankAccount> findAll(){
        return clientRepository.findAll().stream()
                .flatMap(c->c.getAccounts().stream())
                .toList();
    }
}
