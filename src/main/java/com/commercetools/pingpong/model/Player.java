package com.commercetools.pingpong.model;

public class Player {
    private String name;

    private int setScore;
    private int matchScore;
    private boolean serve;
    private boolean beginningServe;

    public Player(String name) {
        this.name = name;
    }

    public Player() {
    }

    public void customizeSetScore(int newPoints) {
        setScore = setScore + newPoints;
    }

    public void customizeMatchScore(int newPoints) {
        matchScore = matchScore + newPoints;
    }


    public void addSetPoint() {
        setScore++;
    }

    public void subSetPoint() {
        setScore--;
    }

    public void addMatchPoint() {
        matchScore++;
    }

    public void resetSetScore() {
        setScore = 0;
    }

    public void resetMatchScore() {
        matchScore = 0;
    }

    public void setServe() {
        serve = true;
    }

    public void unsetServe() {
        serve = false;
    }

    public void setBeginningServe() {
        beginningServe = true;
    }

    public void unsetBeginningServe() {
        beginningServe = false;
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