package com.thomas.voetbaladministratie.model;

public class Result {
    private int resultId;
    private Game game;
    private int scoreHome;
    private int scoreAway;

    public Result(int resultId, Game game, int scoreHome, int scoreAway) {
        this.resultId = resultId;
        this.game = game;
        this.scoreHome = scoreHome;
        this.scoreAway = scoreAway;
    }

    public int getResultId() {
        return resultId;
    }

    public Game getGame() {
        return game;
    }

    public int getScoreHome() {
        return scoreHome;
    }

    public int getScoreAway() {
        return scoreAway;
    }

    @Override
    public String toString() {
        return game.toString() + " → " + scoreHome + " - " + scoreAway;
    }
}
