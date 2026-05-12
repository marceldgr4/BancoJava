package com.Banco.exceptions;

public class AccountOwnershipException extends BankingException{
    private final int attemptedClientId;
    private final String accountNumber;
    private final int actualOwnerId;
    public AccountOwnershipException(int attemptedClientId, String accountNumber, int actualOwnerId) {
        super(String.format(
                "Client %d is not authorized to access account %s (owned by client %d)",
                attemptedClientId, accountNumber, actualOwnerId
        ));
        this.attemptedClientId = attemptedClientId;
        this.accountNumber = accountNumber;
        this.actualOwnerId = actualOwnerId;
    }
    public int getAttemptedClientId() {
        return attemptedClientId;
    }
    public String getAccountNumber() {
        return accountNumber;
    }
    public int getActualOwnerId() {
        return actualOwnerId;
    }
}
