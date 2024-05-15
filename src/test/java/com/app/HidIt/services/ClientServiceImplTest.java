package com.app.HidIt.services;

import com.app.HidIt.enums.ActivityLevel;
import com.app.HidIt.enums.BiologicalGender;
import com.app.HidIt.models.Client;
import com.app.HidIt.repositories.ClientRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class ClientServiceImplTest {

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientServiceImpl clientService;


    @Test
    public void testCalculateMaxFatIntake() {
        // Arrange
        Client client = mock(Client.class);
        when(client.getWeight()).thenReturn(67.1);

        // Act
        double maxFatIntake = clientService.calculateMaxFatIntake(client);

        // Assert
        assertEquals(46.97, maxFatIntake, 0.01); // Adjust delta as needed
    }

    @Test
    public void testCalculateMaxProteinIntakeWithSupplements() {
        // Arrange
        Client client = mock(Client.class);
        when(client.getWeight()).thenReturn(67.1);
        when(client.isUsingSupplements()).thenReturn(true);

        // Act
        double maxProteinIntake = clientService.calculateMaxProteinIntake(client);

        // Assert
        assertEquals(147.62, maxProteinIntake, 0.01); // Adjust delta as needed
    }

    @Test
    public void testCalculateMaxProteinIntakeWithoutSupplements() {
        // Arrange
        Client client = mock(Client.class);
        when(client.getWeight()).thenReturn(70.0);
        when(client.isUsingSupplements()).thenReturn(false);

        // Act
        double maxProteinIntake = clientService.calculateMaxProteinIntake(client);

        // Assert
        assertEquals(126.0, maxProteinIntake, 0.01); // Adjust delta as needed
    }

    @Test
    void getAllClients() {
        // Arrange
        when(clientRepository.findAll()).thenReturn(Arrays.asList(new Client(), new Client()));

        // Act
        List<Client> clients = clientService.getAllClients();

        // Assert
        assertEquals(2, clients.size());
    }

    @Test
    void getClientById() {
        // Arrange
        long clientId = 1L;
        when(clientRepository.findById(clientId)).thenReturn(Optional.of(new Client()));

        // Act
        Optional<Client> client = clientService.getClientById(clientId);

        // Assert
        assertTrue(client.isPresent());
    }

    @Test
    void getClientByUsername() {
        // Arrange
        String username = "testUser";
        when(clientRepository.findByUsername(username)).thenReturn(Optional.of(new Client()));

        // Act
        Optional<Client> client = clientService.getClientByUsername(username);

        // Assert
        assertTrue(client.isPresent());
    }

    @Test
    void createClient() {
        // Arrange
        Client newClient = new Client();
        when(clientRepository.save(newClient)).thenReturn(newClient);

        // Act
        Client createdClient = clientService.createClient(newClient);

        // Assert
        assertNotNull(createdClient);
    }

    @Test
    void updateClient() {
        // Arrange
        long clientId = 1L;
        Client existingClient = new Client();
        existingClient.setId(clientId);
        when(clientRepository.findById(clientId)).thenReturn(Optional.of(existingClient));
        when(clientRepository.save(existingClient)).thenReturn(existingClient);

        Client updatedClient = new Client();
        updatedClient.setUsername("newUsername");

        // Act
        Client result = clientService.updateClient(clientId, updatedClient);

        // Assert
        assertEquals(updatedClient.getUsername(), result.getUsername());
    }

    @Test
    void calculateBMR() {
        // Arrange
        Client client = new Client();
        client.setWeight(70.0); // Replace with actual weight
        client.setLength(170); // Replace with actual length
        client.setAge(25); // Replace with actual age
        client.setActivityLevel(ActivityLevel.LIGHT_EXERCISE);
        client.setBiologicalGender(BiologicalGender.MALE);

        // Act
        int bmr = (int) clientService.calculateBMR(client);

        // Assert
        int expectedBMR = 2344;
        assertEquals(expectedBMR, bmr);
    }

    @Test
    void calculateAdjustedBMR() {
        // Arrange
        Client client = new Client();
        client.setWeight(92.0); // Replace with actual weight
        client.setLength(181); // Replace with actual length
        client.setAge(35); // Replace with actual age
        client.setActivityLevel(ActivityLevel.NO_OR_LITTLE_EXERCISE);
        client.setBiologicalGender(BiologicalGender.MALE);
        client.setWantToGainWeight(false);
        client.setUsingSupplements(false);

        // Act
        int adjustedBMR = (int) clientService.calculateAdjustedBMR(client);
        double maxProteinIntake = clientService.calculateMaxProteinIntake(client);
        double maxFatIntake = clientService.calculateMaxFatIntake(client);

        // Assert
        int expectedBMR = 2152;
        double expectedMaxProteinIntake= 165.6;
        double expectedMaxFatIntake = 64.39999999999999;
        assertEquals(expectedBMR, adjustedBMR);
        assertEquals(expectedMaxProteinIntake, maxProteinIntake);
        assertEquals(expectedMaxFatIntake, maxFatIntake);
    }

    @Test
    void deleteClient() {
        // Arrange
        long clientId = 1L;

        // Act
        clientService.deleteClient(clientId);

        // Assert
        verify(clientRepository, times(1)).deleteById(clientId);
    }
}
