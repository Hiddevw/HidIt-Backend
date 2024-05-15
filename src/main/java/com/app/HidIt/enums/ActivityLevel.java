package com.app.HidIt.enums;

public enum ActivityLevel {
    NO_OR_LITTLE_EXERCISE("Little or no exercise, desk job", 1.2),
    LIGHT_EXERCISE("Light exercise/sports, 1-3 days per week", 1.375),
    MODERATE_EXERCISE("Moderate exercise/sports, 3-5 days per week", 1.55),
    INTENSE_EXERCISE("Intense exercise/sports, 6-7 days per week", 1.725),
    INTENSE_DAILY_EXERCISE("Intense daily exercise/sports, physical job, or twice a day training, marathon, tournament, etc.", 1.9);

    private final String description;
    private final double factor;

    ActivityLevel(String description, double factor) {
        this.description = description;
        this.factor = factor;
    }

    public String getDescription() {
        return description;
    }

    public double getFactor() {
        return factor;
    }
}


