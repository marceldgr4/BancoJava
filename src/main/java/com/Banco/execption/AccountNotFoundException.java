package com.Banco.execption;
/**
 * Se lanza cuando no se encuentra una cuenta con el número especificado.
 */

public class AccountNotFoundException extends  RuntimeException{
    public AccountNotFoundException(String accountNumber){
        super("Account not found with number:"+ accountNumber);
    }
}
