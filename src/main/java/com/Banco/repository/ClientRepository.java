package com.Banco.repository;

import com.Banco.model.domain.Person.Client;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class ClientRepository {
    private final List<Client> store = new ArrayList<>();

    public void save(Client client) {
        if (client == null)
            throw new IllegalArgumentException("Client must not be null");
        store.add(client);
    }

    public Optional<Client> findById(int id) {
        return store.stream().filter(c -> c.getId() == id).findFirst();
    }

    public boolean existsById(int id) {
        return findById(id).isPresent();
    }
    public List<Client> findAll() {
        return Collections.unmodifiableList(store);
    }
    public boolean delete(Client client) {
        return store.remove(client);
    }
    public int count() {
        return store.size();
    }
}