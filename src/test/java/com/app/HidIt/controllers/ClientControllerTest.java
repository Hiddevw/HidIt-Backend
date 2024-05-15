package com.app.HidIt.controllers;

import com.app.HidIt.models.Client;
import com.app.HidIt.models.ClientDTO;
import com.app.HidIt.services.ClientService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ClientControllerTest {

    @Mock
    private ClientService clientService;

    @InjectMocks
    private ClientController clientController;

    @Test
    public void testGetAllClients() {
        // Arrange
        when(clientService.getAllClients()).thenReturn(Arrays.asList(new Client(), new Client()));

        // Act
        List<ClientDTO> result = clientController.getAllClients();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    public void testGetClientByIdSuccess() {
        // Arrange
        Long clientId = 1L;
        when(clientService.getClientById(clientId)).thenReturn(Optional.of(new Client()));

        // Act
        ResponseEntity<ClientDTO> response = clientController.getClientById(clientId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    public void testGetClientByIdNotFound() {
        // Arrange
        Long clientId = 1L;
        when(clientService.getClientById(clientId)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<ClientDTO> response = clientController.getClientById(clientId);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    public void testCreateClientSuccess() {
        // Arrange
        Client client = new Client();
        when(clientService.createClient(client)).thenReturn(client);

        // Act
        ResponseEntity<?> response = clientController.createClient(client, mockBindingResult(false));

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    public void testUpdateClientSuccess() {
        // Arrange
        Long clientId = 1L;
        Client updatedClient = new Client();
        when(clientService.updateClient(clientId, updatedClient)).thenReturn(updatedClient);

        // Act
        ResponseEntity<?> response = clientController.updateClient(clientId, updatedClient, mockBindingResult(false));

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    public void testDeleteClient() {
        // Arrange
        Long clientId = 1L;
        doNothing().when(clientService).deleteClient(clientId);

        // Act
        assertDoesNotThrow(() -> clientController.deleteClient(clientId));
    }

    private BindingResult mockBindingResult(boolean hasErrors) {
        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(hasErrors);
        return bindingResult;
    }
}
