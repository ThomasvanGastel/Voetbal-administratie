package com.thomas.voetbaladministratie.model;

import java.time.LocalDateTime;

public class Game {
    private int gameId;
    private Team homeTeam;
    private Team awayTeam;
    private LocalDateTime date;
    private String location;

    public Game(int gameId, Team homeTeam, Team awayTeam, LocalDateTime date, String location) {
        this.gameId = gameId;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.date = date;
        this.location = location;
    }

    public int getGameId() {
        return gameId;
    }

    public Team getHomeTeam() {
        return homeTeam;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public String getLocation() {
        return location;
    }

    // === SETTERS VOOR BEWERKEN ===

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public void setHomeTeam(Team homeTeam) {
        this.homeTeam = homeTeam;
    }

    public void setAwayTeam(Team awayTeam) {
        this.awayTeam = awayTeam;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return homeTeam.getTeamName() + " - " + awayTeam.getTeamName() + " Locatie: " + location;
    }
}
