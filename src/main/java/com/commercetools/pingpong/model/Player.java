package com.commercetools.pingpong.model;

public class Player {
    private String name;
    private int numberOfGamesWon;
    private int totalPoints;

    private int setScore;
    private int matchScore;

    public Player(final String name, final int numberOfGamesWon, final int totalPoints) {
        this.name = name;
        this.numberOfGamesWon = numberOfGamesWon;
        this.totalPoints = totalPoints;
    }

    public Player(final String name) {
        this.name = name;
    }

    public Player() {
    }

    public void add() {
        setScore++;
    }

    public void sub() {
        setScore--;
    }

    public void reset() {
        setScore = 0;
    }

    public void addMatchScore() {
        matchScore++;
    }

    public void resetMatchScore() {
        matchScore = 0;
    }


    public String getName() {
        return name;
    }

    public int getNumberOfGamesWon() {
        return numberOfGamesWon;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public int getSetScore() {
        return setScore;

    }

    public int getMatchScore() {
        return matchScore;
    }

}
