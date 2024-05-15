package com.app.HidIt.repositories;

import com.app.HidIt.models.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TrainerRepository extends JpaRepository<Trainer, Long> {

    Optional<Trainer> findByUsername(String username);

    List<Trainer> findByEmail(String email);

    Optional<Trainer> findFirstByEmail(String email);

    Optional<Trainer> findByUsernameAndEmail(String username, String email);

    List<Trainer> findByClientsId(Long clientId);
}
