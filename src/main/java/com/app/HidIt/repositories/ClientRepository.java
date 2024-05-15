package com.app.HidIt.repositories;

import com.app.HidIt.models.Client;
import com.app.HidIt.models.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {

    Optional<Client> findByUsername(String username);

    List<Client> findByEmail(String email);

    Optional<Client> findFirstByEmail(String email);

    Optional<Client> findByUsernameAndEmail(String username, String email);

    List<Client> findByTrainer(Trainer trainer);

}
