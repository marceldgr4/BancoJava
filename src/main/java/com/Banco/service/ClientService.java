package com.Banco.service;

import com.Banco.execption.ClientNotFoundException;
import com.Banco.model.domain.Person.Client;
import com.Banco.repository.ClientRepository;


import java.util.List;
import java.util.Optional;

public class ClientService {
    private final ClientRepository clientRepository;
    public ClientService(ClientRepository clientRepository) {
        if(clientRepository == null)
            throw new IllegalArgumentException("clientRepository cannot be null");
        this.clientRepository = clientRepository;
    }
    public void addClient(Client client) {
        if (client == null)
            throw new IllegalArgumentException("client cannot be null");
        if (clientRepository.existsById(client.getId()))
            throw new IllegalArgumentException("client with ID'" + client.getId() + "' exists");
        clientRepository.Save(client);
    }

    public Optional<Client> findClientById(int clientId){
        return clientRepository.FindById(clientId);
    }

    public Client getClientById(int clientId){
        return clientRepository.FindById(clientId).orElseThrow(() -> new ClientNotFoundException(clientId));
    }

    public List<Client> getAllClients(){
        return clientRepository.findAll();
    }

    public int getTotalClients(){
        return clientRepository.count();
    }


}
