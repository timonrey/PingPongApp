package com.commercetools.pingpong.model;

public class Match {

    private Player playerOne;
    private Player playerTwo;

    private int points[] = new int[2];
    private Game games[] = new Game[3];

    private Player beginningPlayer;

    public Match(Player playerOne, Player playerTwo) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        this.points[0] = 0;
        this.points[1] = 0;
    }

    public Game getCurrentGame() {
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

    public void start(Player beginner) {
        this.beginningPlayer = beginner;
        this.games[0] = this.createNewGame(this.playerOne == this.beginningPlayer);
    }

    public void addGamePoint(Player actingPlayer) {
        Game currentGame = getCurrentGame();
        currentGame.addSetPoint(decideWhoGetsThePoint(actingPlayer));

        if(currentGame.getIfGameIsOver()) {
            Player winner = getGameWinner(actingPlayer);
            this.addMatchPoint(actingPlayer);

            if(this.isMatchOver()) {
                return;
            }

            this.games[getNumberOfGames()] = this.createNewGame(currentGame.getBeginningPlayer() == this.playerOne);

        }
    }

    public boolean decideWhoGetsThePoint(Player actingPlayer) {
        if(actingPlayer == this.playerOne && isPlayerOneOnTheLeftSide()) {
            return true;

        } else if(actingPlayer == this.playerTwo && isPlayerOneOnTheLeftSide()) {
            return false;

        } else {
            return (actingPlayer == this.playerOne);
        }

    }

    public void addMatchPoint(Player winner) {
        if(getRoundSpecificPlayer(winner) == this.playerOne) {
            this.points[0]++;

        } else if(getRoundSpecificPlayer(winner) == this.playerTwo) {
            this.points[1]++;
        }
    }

    public Player getRoundSpecificPlayer(Player player) {
        if (getNumberOfGames() == 2) {
            return player == this.playerOne ? this.playerTwo : this.playerOne;
        } else {
            return player;
        }
    }

    public void subtractGamePoint(Player actingPlayer) {
        getCurrentGame().subtractSetPoint(actingPlayer);
    }

    public void resetGamePoint() {
        getCurrentGame().resetGame();
    }

    public boolean isMatchOver() {
        return this.points[0] == 2 || this.points[1] == 2;
    }

    public Player getWinner() {
        if (this.isMatchOver()) {
            return this.points[0] == 2 ? this.playerOne : this.playerTwo;
        }
        return null;
    }

    public Player getGameWinner(Player actingPlayer) {
        if (!getCurrentGame().getIfGameIsOver()) {
            return null;
        }
        return (actingPlayer == this.playerOne) ? this.playerOne : this.playerTwo;
    }


    public int[] getMatchPoints() {
        return this.points;
    }

    public void __setCustomMatchScore(int p1, int p2) {
        this.points[0] = p1;
        this.points[1] = p2;
    }

    public Game createNewGame(boolean isPlayerOneBeginning) {
        Game game = new Game(this.playerOne, this.playerTwo, isPlayerOneBeginning);
        return game;
    }

    public String findOutWhichPlayerServes() {
        if ((getCurrentGame().getServingPlayer() == playerOne && !isPlayerOneOnTheLeftSide()) || getCurrentGame().getServingPlayer() == playerTwo && isPlayerOneOnTheLeftSide()) {
            return "playerOne";

        } else if ((getCurrentGame().getServingPlayer() == playerOne && isPlayerOneOnTheLeftSide()) || getCurrentGame().getServingPlayer() == playerTwo && !isPlayerOneOnTheLeftSide()) {
            return "playerTwo";

        } else {
            throw new Error("Conditions were not fulfilled");
        }
    }

    public boolean isPlayerOneOnTheLeftSide() {
        return getNumberOfGames() % 2 == 1;
    }

}