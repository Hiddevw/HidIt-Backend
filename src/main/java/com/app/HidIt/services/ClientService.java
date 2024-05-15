package com.app.HidIt.services;

import com.app.HidIt.models.Client;

import java.util.List;
import java.util.Optional;

public interface ClientService {

    List<Client> getAllClients();

    Optional<Client> getClientById(Long clientId);

    Optional<Client> getClientByUsername(String username);

    Client createClient(Client client);

    Client updateClient(Long clientId, Client updatedClient);

    void deleteClient(Long clientId);
}
