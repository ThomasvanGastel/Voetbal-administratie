package com.thomas.voetbaladministratie.model;

public class Team {
    private int teamId;
    private String teamName;
    private String ageCategory;
    private User trainer;

    public Team(int teamId, String teamName, String ageCategory, User trainer) {
        this.teamId = teamId;
        this.teamName = teamName;
        this.ageCategory = ageCategory;
        this.trainer = trainer;
    }


    public int getTeamId() {
        return teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public String getAgeCategory() {
        return ageCategory;
    }

    public User getTrainer() {
        return trainer;
    }



    @Override
    public String toString() {
        return teamName + " (" + ageCategory + ")";
    }
}
