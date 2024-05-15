package com.app.HidIt.controllers;

import com.app.HidIt.models.Client;
import com.app.HidIt.models.Trainer;
import com.app.HidIt.models.TrainerDTO;
import com.app.HidIt.services.TrainerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/trainers")
public class TrainerController {

    private final TrainerService trainerService;

    @Autowired
    public TrainerController(TrainerService trainerService) {
        this.trainerService = trainerService;
    }

    @GetMapping
    public List<Trainer> getAllTrainers() {
        return trainerService.getAllTrainers();
    }

    @GetMapping("/{trainerId}")
    public ResponseEntity<TrainerDTO> getTrainerById(@PathVariable Long trainerId) {
        Optional<Trainer> trainerOptional = trainerService.getTrainerById(trainerId);

        if (trainerOptional.isPresent()) {
            Trainer trainer = trainerOptional.get();

            TrainerDTO trainerDTO = convertToDTO(trainer);

            return ResponseEntity.ok(trainerDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    private TrainerDTO convertToDTO(Trainer trainer) {
        return new TrainerDTO(
                trainer.getId(),
                trainer.getUsername(),
                trainer.getEmail(),
                mapClientUsernames(trainer.getClients())
        );
    }

    private Set<String> mapClientUsernames(Set<Client> clients) {
        if (clients != null) {
            return clients.stream()
                    .map(Client::getUsername)
                    .collect(Collectors.toSet());
        } else {
            return Collections.emptySet(); // or return null if you prefer
        }
    }

    @PostMapping
    public ResponseEntity<?> createTrainer(@Valid @RequestBody Trainer trainer, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }

        Trainer createdTrainer = trainerService.createTrainer(trainer);
        return ResponseEntity.ok(createdTrainer);
    }

    @PutMapping("/{trainerId}")
    public ResponseEntity<?> updateTrainer(@PathVariable Long trainerId, @Valid @RequestBody Trainer updatedTrainer, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }

        Trainer updated = trainerService.updateTrainer(trainerId, updatedTrainer);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{trainerId}")
    public void deleteTrainer(@PathVariable Long trainerId) {
        trainerService.deleteTrainer(trainerId);
    }
}
