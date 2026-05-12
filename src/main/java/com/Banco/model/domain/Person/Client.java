package com.Banco.model.domain.Person;



import com.Banco.model.domain.Account.BankAccount;
import com.Banco.util.validator.BaseValidator;

import java.util.*;

public class Client extends Person {
    private static int clientCount = 0;
    private final List<BankAccount> accounts =new ArrayList<>();

    public Client(int id, String fullName){
        super(id, fullName);
        clientCount++;
    }


    public static int getClientCount() {
        return clientCount;
    }

    static void setClientCount() {
        clientCount = 0;
    }

    public void addAccount(BankAccount account){
        BaseValidator.requireNonNull(account,"Account");
            accounts.add(account);
    }

    public boolean removeAccount(BankAccount account) {
        return accounts.remove(account);
    }

        public List<BankAccount> getAccounts(){
            return Collections.unmodifiableList(accounts);
        }

        public Optional<BankAccount> getAccountByNumber(String accountNumber) {
            return accounts.stream()
                    .filter(a -> a.getAccountNumber().equals(accountNumber))
                    .findFirst();
        }
        public boolean ownsAccount(String accountNumber) {
            return getAccountByNumber(accountNumber).isPresent();
        }
        public int getAccountCount() {
            return accounts.size();
        }

        @Override
        public String toString(){
        return String.format("Client[id=%s, name=%s, accounts=%d]",
                getId(), getFullName(), accounts.size());
        }

}
