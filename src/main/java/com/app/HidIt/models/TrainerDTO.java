package com.app.HidIt.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import java.util.Set;

public class TrainerDTO {

    private Long id;
    @NotBlank(message = "Full name is required")
    private String username;
    @Email(message = "Please provide a valid email address")
    @NotBlank(message = "Email is required")
    private String email;

    private Set<String> clientUsernames;

    // Constructors, getters, and setters

    public TrainerDTO() {
    }

    public TrainerDTO(Long id, String username, String email, Set<String> clientUsernames) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.clientUsernames = clientUsernames;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<String> getClientUsernames() {
        return clientUsernames;
    }

    public void setClientUsernames(Set<String> clientUsernames) {
        this.clientUsernames = clientUsernames;
    }
}
