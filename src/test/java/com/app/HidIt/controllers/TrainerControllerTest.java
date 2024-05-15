package com.app.HidIt.controllers;

import com.app.HidIt.models.Trainer;
import com.app.HidIt.models.TrainerDTO;
import com.app.HidIt.services.TrainerService;
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
public class TrainerControllerTest {

    @Mock
    private TrainerService trainerService;

    @InjectMocks
    private TrainerController trainerController;

    @Test
    public void testGetAllTrainers() {
        // Arrange
        when(trainerService.getAllTrainers()).thenReturn(Arrays.asList(new Trainer(), new Trainer()));

        // Act
        List<Trainer> result = trainerController.getAllTrainers();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    public void testGetTrainerByIdSuccess() {
        // Arrange
        Long trainerId = 1L;
        when(trainerService.getTrainerById(trainerId)).thenReturn(Optional.of(new Trainer()));

        // Act
        ResponseEntity<TrainerDTO> response = trainerController.getTrainerById(trainerId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    public void testGetTrainerByIdNotFound() {
        // Arrange
        Long trainerId = 1L;
        when(trainerService.getTrainerById(trainerId)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<TrainerDTO> response = trainerController.getTrainerById(trainerId);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    public void testCreateTrainerSuccess() {
        // Arrange
        Trainer trainer = new Trainer();
        when(trainerService.createTrainer(trainer)).thenReturn(trainer);

        // Act
        ResponseEntity<?> response = trainerController.createTrainer(trainer, mockBindingResult(false));

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    public void testUpdateTrainerSuccess() {
        // Arrange
        Long trainerId = 1L;
        Trainer updatedTrainer = new Trainer();
        when(trainerService.updateTrainer(trainerId, updatedTrainer)).thenReturn(updatedTrainer);

        // Act
        ResponseEntity<?> response = trainerController.updateTrainer(trainerId, updatedTrainer, mockBindingResult(false));

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }


    @Test
    public void testDeleteTrainer() {
        // Arrange
        Long trainerId = 1L;
        doNothing().when(trainerService).deleteTrainer(trainerId);

        // Act
        assertDoesNotThrow(() -> trainerController.deleteTrainer(trainerId));
    }

    private BindingResult mockBindingResult(boolean hasErrors) {
        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(hasErrors);
        return bindingResult;
    }
}
