package com.app.HidIt.services;

import com.app.HidIt.enums.Role;
import com.app.HidIt.models.Trainer;
import com.app.HidIt.repositories.TrainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrainerServiceImpl implements TrainerService {

    private final TrainerRepository trainerRepository;

    @Autowired
    public TrainerServiceImpl(TrainerRepository trainerRepository) {
        this.trainerRepository = trainerRepository;
    }

    @Override
    public List<Trainer> getAllTrainers() {
        return trainerRepository.findAll();
    }

    @Override
    public Optional<Trainer> getTrainerById(Long trainerId) {
        return trainerRepository.findById(trainerId);
    }

    @Override
    public Optional<Trainer> getTrainerByUsername(String username) {
        return trainerRepository.findByUsername(username);
    }

    @Override
    public Trainer createTrainer(Trainer trainer) {
        return trainerRepository.save(trainer);

    }


    @Override
    public Trainer updateTrainer(Long trainerId, Trainer updatedTrainer) {
        validateAuthorization();

        Optional<Trainer> existingTrainerOptional = trainerRepository.findById(trainerId);
        if (existingTrainerOptional.isPresent()) {
            Trainer existingTrainer = existingTrainerOptional.get();

            return trainerRepository.save(existingTrainer);
        } else {
            throw new RuntimeException("Trainer not found with id: " + trainerId);
        }
    }

    @Override
    public void deleteTrainer(Long trainerId) {
        validateAuthorization();
        trainerRepository.deleteById(trainerId);
    }

    private void validateAuthorization() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (!authentication.getAuthorities().stream().anyMatch(r -> r.getAuthority().equals(Role.ROLE_TRAINER.toString()))) {
            throw new SecurityException("Unauthorized access");
        }
    }
}