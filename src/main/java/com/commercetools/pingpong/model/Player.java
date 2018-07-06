package com.commercetools.pingpong.model;

public class Player {
    private String name;

    private int setScore;
    private int matchScore;
    private boolean serve;
    private boolean beginningServe;

    public Player(String name, int numberOfGamesWon, int totalPoints) {
    }

    public Player(String name) {
        this.name = name;
    }

    public Player() {
    }

    public void addPoint() {
        setScore++;
    }

    public void subPoint() {
        setScore--;
    }

    public void resetSet() {
        setScore = 0;
    }

    public void setBeginningServe() {
        beginningServe = true;
    }

    public void unsetBeginningServe() {
        beginningServe = false;
    }

    public void setServe() {
        serve = true;
    }

    public void unsetServe() {
        serve = false;
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

    public int getSetScore() {
        return setScore;

    }

    public int getMatchScore() {
        return matchScore;
    }

    public boolean amIServing() {
        return serve;
    }

    public boolean didIServeAtBeginning() {
        return beginningServe;
    }
}
