package com.app.HidIt.controllers;

import com.app.HidIt.models.Client;
import com.app.HidIt.models.ClientDTO;
import com.app.HidIt.services.ClientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/clients")
public class ClientController {

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public List<ClientDTO> getAllClients() {
        List<ClientDTO> clientDTOList = clientService.getAllClients()
                .stream()
                .map(this::convertToDTO)
                .toList();

        return clientDTOList;
    }

    @GetMapping("/{clientId}")
    public ResponseEntity<ClientDTO> getClientById(@PathVariable Long clientId) {
        Optional<Client> clientOptional = clientService.getClientById(clientId);

        if (clientOptional.isPresent()) {
            Client client = clientOptional.get();

            ClientDTO clientDTO = convertToDTO(client);

            return ResponseEntity.ok(clientDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> createClient(@Valid @RequestBody Client client, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }

        Client createdClient = clientService.createClient(client);
        return ResponseEntity.ok(createdClient);
    }

    @PutMapping("/{clientId}")
    public ResponseEntity<?> updateClient(@PathVariable Long clientId, @Valid @RequestBody Client updatedClient, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }

        Client updated = clientService.updateClient(clientId, updatedClient);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{clientId}")
    public void deleteClient(@PathVariable Long clientId) {
        clientService.deleteClient(clientId);
    }

    private ClientDTO convertToDTO(Client client) {
        return new ClientDTO(
                client.getId(),
                client.getUsername(),
                client.getEmail(),
                client.getWeight(),
                client.getLength(),
                client.getAge(),
                client.getActivityLevel(),
                client.getTrainer() != null ? client.getTrainer().getId() : null,
                client.getBiologicalGender(),
                client.getWantToGainWeight(),
                client.isUsingSupplements()
        );
    }

}
