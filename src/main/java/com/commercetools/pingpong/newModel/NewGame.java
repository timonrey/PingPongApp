package com.commercetools.pingpong.newModel;

public class NewGame {

    private NewPlayer playerOne;
    private NewPlayer playerTwo;

    private int setPoints;
    private boolean serve;

    NewPlayer testPlayer;

    public NewGame(NewPlayer playerOne, NewPlayer playerTwo) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
    }

    public void setWinnerOfGame(NewPlayer winner) {
        winner.currentMatch.addMatchPoint();
    }

    public void getWinnerOfGame() {

        if (playerOne.currentGame.getSetPoints() == 11) {
            setWinnerOfGame(playerOne);

        } else if (playerTwo.currentGame.getSetPoints() == 11) {
            setWinnerOfGame(playerTwo);
        }
    }

    public void addSetPoint() {
        setPoints++;
    }

    public void removeSetPoint() {
        setPoints--;
    }

    public void setServe() {
        serve = true;
    }

    public void unsetServe() {
        serve = false;
    }

    public void resetAllData() {
        setPoints = 0;
        serve = false;
    }

    public int getSetPoints() {
        return setPoints;
    }

    public boolean amIServing() {
        return serve;
    }

}
