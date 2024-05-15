package com.app.HidIt.services;

import com.app.HidIt.enums.BiologicalGender;
import com.app.HidIt.models.Client;
import com.app.HidIt.repositories.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    @Override
    public Optional<Client> getClientById(Long clientId) {
        return clientRepository.findById(clientId);
    }

    @Override
    public Optional<Client> getClientByUsername(String username) {
        return clientRepository.findByUsername(username);
    }

    @Override
    public Client createClient(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public Client updateClient(Long clientId, Client updatedClient) {
        Optional<Client> existingClientOptional = clientRepository.findById(clientId);
        if (existingClientOptional.isPresent()) {
            Client existingClient = existingClientOptional.get();

            existingClient.setUsername(updatedClient.getUsername());
            existingClient.setEmail(updatedClient.getEmail());

            return clientRepository.save(existingClient);
        } else {
            throw new RuntimeException("Client not found with id: " + clientId);
        }
    }

    public double calculateBMR(Client client) {
        double bmr;

        if (client.getBiologicalGender() == BiologicalGender.MALE) {
            // Harris-Benedict formula for men
            bmr = 66 + (13.7 * client.getWeight()) + (5 * client.getLength()) - (6.8 * client.getAge());
        } else if (client.getBiologicalGender() == BiologicalGender.FEMALE) {
            // Harris-Benedict formula for women
            bmr = 655 + (9.6 * client.getWeight()) + (1.8 * client.getLength()) - (4.7 * client.getAge());
        } else {
            throw new IllegalArgumentException("Invalid gender specified");
        }

        return bmr * client.getActivityLevel().getFactor();
    }


    public double calculateAdjustedBMR(Client client) {
        double bmr = calculateBMR(client);
        double adjustmentFactor = client.getWantToGainWeight() ? 1.1 : 0.9; // Increase by 10% to gain weight, decrease by 10% to lose weight
        return bmr * adjustmentFactor;
    }

    public double calculateMaxFatIntake(Client client) {
        return client.getWeight() * 0.7;
    }

    public double calculateMaxProteinIntake(Client client) {
        double supplementFactor = client.isUsingSupplements() ? 2.2 : 1.8;
        return client.getWeight() * supplementFactor;
    }

    @Override
    public void deleteClient(Long clientId) {
        clientRepository.deleteById(clientId);
    }
}