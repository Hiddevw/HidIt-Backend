package com.app.HidIt.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.app.HidIt.models.Trainer;
import com.app.HidIt.repositories.TrainerRepository;

class TrainerServiceImplTest {

    @Mock
    private TrainerRepository trainerRepository;

    @InjectMocks
    private TrainerServiceImpl trainerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getAllTrainers() {
        // Arrange
        List<Trainer> trainers = Arrays.asList(new Trainer(), new Trainer());
        when(trainerRepository.findAll()).thenReturn(trainers);

        // Act
        List<Trainer> result = trainerService.getAllTrainers();

        // Assert
        assertEquals(trainers, result);
    }

    @Test
    void getTrainerById() {
        // Arrange
        Long trainerId = 1L;
        Trainer trainer = new Trainer();
        when(trainerRepository.findById(trainerId)).thenReturn(Optional.of(trainer));

        // Act
        Optional<Trainer> result = trainerService.getTrainerById(trainerId);

        // Assert
        assertEquals(Optional.of(trainer), result);
    }

    // Similar tests for other methods (getTrainerByUsername, createTrainer, updateTrainer, deleteTrainer)

    @Test
    void testCreateTrainer() {
        // Arrange
        Trainer trainer = new Trainer();
        when(trainerRepository.save(trainer)).thenReturn(trainer);

        // Act
        Trainer result = trainerService.createTrainer(trainer);

        // Assert
        assertNotNull(result);
        assertEquals(trainer, result);
    }

}
