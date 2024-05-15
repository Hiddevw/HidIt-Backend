package com.app.HidIt.models;

import com.app.HidIt.enums.ActivityLevel;
import com.app.HidIt.enums.BiologicalGender;
import jakarta.persistence.*;

@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String email;

    private Double weight;

    private double length;

    private int age;

    private boolean wantToGainWeight;

    private boolean usingSupplements;

    @Enumerated(EnumType.STRING)
    private ActivityLevel activityLevel;

    @ManyToOne
    @JoinColumn(name = "trainer_id")
    private Trainer trainer;

    @Enumerated(EnumType.STRING)
    private BiologicalGender biologicalGender;

    public Client() {
    }

    public Client(String username, String email, Double weight, double length, int age, ActivityLevel activityLevel, Trainer trainer, BiologicalGender biologicalGender, boolean wantToGainWeight, boolean usesSupplements) {
        this.username = username;
        this.email = email;
        this.weight = weight;
        this.length = length;
        this.age = age;
        this.activityLevel = activityLevel;
        this.trainer = trainer;
        this.biologicalGender = biologicalGender;
        this.wantToGainWeight = wantToGainWeight;
        this.usingSupplements = usesSupplements;
    }

    public boolean isUsingSupplements() {
        return usingSupplements;
    }

    public void setUsingSupplements(boolean usingSupplements) {
        this.usingSupplements = usingSupplements;
    }

    public boolean getWantToGainWeight() {
        return wantToGainWeight;
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

    public void setId(Long id) {
        this.id = id;
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

    public Trainer getTrainer() {
        return trainer;
    }

    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }
}
