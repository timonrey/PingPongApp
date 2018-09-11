package com.commercetools.pingpong.newModel;


public class NewMatch {

    private NewPlayer playerOne;
    private NewPlayer playerTwo;

    private int points[] = new int[2];
    private NewGame games[] = new NewGame[3];

    private NewPlayer beginningPlayer;

    public NewMatch(NewPlayer playerOne, NewPlayer playerTwo) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        this.points[0] = 0;
        this.points[1] = 0;
    }

    public NewGame getCurrentGame() {
        return this.games[getNumberOfGames() - 1];
    }

    public int getNumberOfGames() {
        int gamesCount = 0;
        for (int i = 0; i < this.games.length; i++) {
            if (this.games[i] != null) {
                gamesCount++;
            }
        }
        return gamesCount;
    }

    public void start(NewPlayer beginner) {
        this.beginningPlayer = beginner;
        this.games[0] = this.createNewGame(this.playerOne == this.beginningPlayer);
    }

    public void addGamePoint(NewPlayer actingPlayer) {
        NewGame currentGame = getCurrentGame();
        currentGame.addSetPoint(actingPlayer);

        if(currentGame.getIsGameIsOver()) {
            NewPlayer winner = currentGame.getWinner();
            this.addMatchPoint(winner);

            if(this.isMatchOver()) {
                return;
            }

            this.games[getNumberOfGames()] = this.createNewGame(currentGame.getBeginningPlayer() == this.playerOne);

        }
    }

    public void addMatchPoint(NewPlayer winner) {
        if(winner == this.playerOne) {
            this.points[0]++;

        } else {
            this.points[1]++;
        }
    }

    public void subtractGamePoint(NewPlayer actingPlayer) {
        getCurrentGame().subtractSetPoint(actingPlayer);
    }

    public void resetGamePoint() {
        getCurrentGame().resetGame();
    }

    public boolean isMatchOver() {
        return this.points[0] == 2 || this.points[1] == 2;
    }

    public NewPlayer getWinner() {
        if (this.isMatchOver()) {
            return this.points[0] == 2 ? this.playerOne : this.playerTwo;
        }
        return null;
    }



    public int[] getMatchPoints() {
        return this.points;
    }

    public void __setCustomMatchScore(int p1, int p2) {
        this.points[0] = p1;
        this.points[1] = p2;
    }

    private NewGame createNewGame(boolean isPlayerOneBeginning) {
        NewGame game = new NewGame(this.playerOne, this.playerTwo, isPlayerOneBeginning);
        return game;
    }

    public boolean isPlayerOneOnTheLeftSide() {
        return getNumberOfGames() % 2 == 1;
    }

}