package com.Banco.controller;

import com.Banco.model.domain.Person.Client;
import com.Banco.service.ClientService;
import java.util.List;
public class ClientController {
    public final ClientService clientService;
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }
    public void registerClient(int id, String fullName){
        clientService.addClient(new Client(id,fullName));
    }
    public Client getClientById(int id){
        return clientService.getClientById(id);
    }
    public List<Client> listAllClients(){
        return clientService.getAllClients();
    }
    public int getTotalClients(){
        return clientService.getTotalClients();
    }

}
