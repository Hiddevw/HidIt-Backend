package com.app.HidIt.services;

import com.app.HidIt.models.Trainer;

import java.util.List;
import java.util.Optional;

public interface TrainerService {

    List<Trainer> getAllTrainers();

    Optional<Trainer> getTrainerById(Long trainerId);

    Optional<Trainer> getTrainerByUsername(String username);

    Trainer createTrainer(Trainer trainer);

    Trainer updateTrainer(Long trainerId, Trainer updatedTrainer);

    void deleteTrainer(Long trainerId);
}

