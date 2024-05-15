package com.app.HidIt.models;

import com.app.HidIt.enums.ActivityLevel;
import com.app.HidIt.enums.BiologicalGender;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class ClientDTO {

    private Long id;
    @NotBlank(message = "Full name is required")
    private String username;
    @Email(message = "Please provide a valid email address")
    @NotBlank(message = "Email is required")
    private String email;

    private Double weight;

    private double length;

    private int age;

    private ActivityLevel activityLevel;

    private Long trainerId;

    private BiologicalGender biologicalGender;

    private boolean wantToGainWeight;

    private boolean usingSupplements;

    public ClientDTO(Long id, String username, String email, Double weight, double length, int age, ActivityLevel activityLevel, Long trainerId, BiologicalGender biologicalGender, boolean wantToGainWeight, boolean usingSupplements) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.weight = weight;
        this.length = length;
        this.age = age;
        this.activityLevel = activityLevel;
        this.trainerId = trainerId;
        this.biologicalGender = biologicalGender;
        this.wantToGainWeight = wantToGainWeight;
        this.usingSupplements = usingSupplements;
    }

    public boolean isUsingSupplements() {
        return usingSupplements;
    }

    public void setUsingSupplements(boolean usingSupplements) {
        this.usingSupplements = usingSupplements;
    }

    public boolean WantToGainWeight() {
        return wantToGainWeight;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setWantToGainWeight(boolean wantToGainWeight) {
        this.wantToGainWeight = wantToGainWeight;
    }

    public BiologicalGender getBiologicalGender() {
        return biologicalGender;
    }

    public void setBiologicalGender(BiologicalGender biologicalGender) {
        this.biologicalGender = biologicalGender;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public ActivityLevel getActivityLevel() {
        return activityLevel;
    }

    public void setActivityLevel(ActivityLevel activityLevel) {
        this.activityLevel = activityLevel;
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

    public Long getTrainerId() {
        return trainerId;
    }

    public void setTrainerId(Long trainerId) {
        this.trainerId = trainerId;
    }
}
