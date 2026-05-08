package com.Banco.service;

import com.Banco.model.domain.Person.Client;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class ClientService {
    private final List<Client>clients = new ArrayList<>();

    public void addClient(Client client) {
        if (client == null)throw new IllegalArgumentException("client must not be null.");
        if(findClientById(client.getId()).isPresent())
            throw new IllegalArgumentException("client with ID:" + client.getId() + " already exists.");
        clients.add(client);
    }
    public Optional<Client> findClientById(String clientId) {
        return clients.stream().filter(c -> c.getId().equals(clientId)).findFirst();
    }
    public Client getClientById(String clientId) {
        return  findClientById(clientId).orElseThrow(() -> new ClientNotFoundException(clientId));
    }
    public List<Client> getAllClients() {
        return Collections.unmodifiableList(clients);
    }
}
