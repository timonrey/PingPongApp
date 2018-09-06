package com.commercetools.pingpong.newModel;

public class NewMatch {

    private NewPlayer playerOne;
    private NewPlayer playerTwo;

    private int matchPoints;
    private boolean beginningServe;

    public NewMatch(NewPlayer playerOne, NewPlayer playerTwo) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
    }

    public void createNewGame() {
        NewGame game = new NewGame(playerOne, playerTwo);
    }

    public int getNumberOfMatches() {
        int gamesPlayed = playerOne.currentMatch.getMatchPoints() + playerTwo.currentMatch.getMatchPoints();

        return gamesPlayed;
    }

    public boolean getMatchStatus() {
        boolean matchOver;

        if(getGameMod() == 3) {

            if (getNumberOfMatches() == 3) {
                return matchOver = true;

            } else {
                return matchOver = false;
            }

        } else {
            return false;
        }
    }

    public int getGameMod() {
        int bestOfThree = 3;

        return bestOfThree;
    }


    public void addMatchPoint() {
        matchPoints++;
    }

    public void removeMatchPoint() {
        matchPoints--;
    }

    public void setBeginningServe() {
        beginningServe = true;
    }

    public void unsetBeginningServe() {
        beginningServe = false;
    }

    public void resetAllData() {
        matchPoints = 0;
        beginningServe = false;
    }


    public int getMatchPoints() {
        return matchPoints;
    }

    public boolean didIServeAtBeginning() {
        return beginningServe;
    }

}