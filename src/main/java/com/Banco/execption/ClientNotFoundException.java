package com.Banco.execption;

public class ClientNotFoundException extends  RuntimeException{
    public ClientNotFoundException(int clientId){
        super("Client not found with ID:"+ clientId);
    }


}
