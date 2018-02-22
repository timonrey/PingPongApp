package com.commercetools.pingpong.model;


public class Game {
    private Player playerOne;
    private Player playerTwo;

    public Game(Player firstPlayer, Player secondPlayer) {
        this.playerOne = firstPlayer;
        this.playerTwo = secondPlayer;
    }

    public void updateScoreOfPlayer(Player player, int actionType) {

        if (actionType == 1 && !hasSomebodyWonSet()) {
            player.addPoint();
            decideWhoServes();

        } else if (actionType == 2 && player.getSetScore() > 0) {
            player.subPoint();
            whoServesAfterDoubleClick();

        } else if (actionType == 3) {
            resetSetScores();
            resetMatchScores();
            resetServe();

        }

    }

    public void updateMatchScoreOfPlayers() {
        if (hasPlayerWonSet(playerOne, playerTwo) && !isMatchOver()) {
            this.playerOne.addMatchScore();
            resetSetScores();

        } else if (hasPlayerWonSet(playerTwo, playerOne) && !isMatchOver()) {
            this.playerTwo.addMatchScore();
            resetSetScores();
        }
    }

    public void isGameOver() {
        if (this.playerOne.getMatchScore() == 2) {
            resetSetScores();
            resetMatchScores();
            resetServe();
        } else if (this.playerTwo.getMatchScore() == 2) {
            resetSetScores();
            resetMatchScores();
            resetServe();
        }
    }

    public boolean hasSomebodyWonSet() {
        return (hasPlayerWonSet(playerOne, playerTwo) || hasPlayerWonSet(playerTwo, playerOne));
    }

    public boolean hasPlayerWonSet(Player playerWhoIsWinning, Player playerWhoIsLoosing) {
        if (playerWhoIsWinning.getSetScore() == 11 && playerWhoIsLoosing.getSetScore() < 10) {
            return true;

        } else if (playerWhoIsWinning.getSetScore() >= 11 && playerWhoIsLoosing.getSetScore() >= 10) {
            return isTwoPointDifference(playerWhoIsWinning, playerWhoIsLoosing);

        } else {
            return false;
        }
    }

    public boolean isMatchOver() {
        return (playerOne.getMatchScore() == 2 || playerTwo.getMatchScore() == 2);
    }

    public boolean isTwoPointDifference(Player winningPlayer, Player losingPlayer) {
        return (winningPlayer.getSetScore() - losingPlayer.getSetScore() == 2);
    }

    public void resetSetScores(){
        playerOne.resetSet();
        playerTwo.resetSet();
    }

    public void resetMatchScores() {
        playerOne.resetMatchScore();
        playerTwo.resetMatchScore();
    }

    public void resetServe() {
        playerOne.changeServe();
        playerTwo.changeServe();
    }

    public boolean areBothScoresZero() {
        return (playerOne.getSetScore() == 0 && playerTwo.getSetScore() == 0);
    }

    public boolean isSomeoneServing() {
        return (playerOne.amIServing() || playerTwo.amIServing());
    }

    public boolean isSetScoreEven() {
        return ((playerOne.getSetScore() + playerTwo.getSetScore()) % 2 == 0);
    }

    public boolean isMatchScoreEven() {
        return ((playerOne.getMatchScore() + playerTwo.getMatchScore()) % 2 == 0);
    }

    public Player getPlayerOne() {
        return playerOne;
    }

    public Player getPlayerTwo() {
        return playerTwo;
    }

    public boolean getPlayerOneServe() {return playerOne.amIServing();}

    public boolean getPlayerTwoServe() {return playerTwo.amIServing();}

    public boolean isOvertime(Player playerOne, Player playerTwo) {
        return playerOne.getSetScore() >= 10 && playerTwo.getSetScore() >= 10;
    }

    public void whoServesAfterDoubleClick() {
        if (!isSetScoreEven()) {
            if (playerOne.amIServing()) {
                playerOne.changeServe();
                playerTwo.setServe();

            } else if (playerTwo.amIServing()) {
                playerTwo.changeServe();
                playerOne.setServe();
            }
        }
    }

    public void setFirstServe(Player actingPlayer) {
        actingPlayer.setServe();
        }

    public void decideWhoServes() {
        if (areBothScoresZero()) {
            return;
        } else if (isOvertime(playerOne, playerTwo) && playerOne.amIServing()) {
            playerOne.changeServe();
            playerTwo.setServe();

        } else if (isOvertime(playerOne, playerTwo) && playerTwo.amIServing()) {
            playerTwo.changeServe();
            playerOne.setServe();

        } else if (playerOne.amIServing() && isSetScoreEven()) {
            playerOne.changeServe();
            playerTwo.setServe();

        } else if (playerTwo.amIServing() && isSetScoreEven()) {
            playerTwo.changeServe();
            playerOne.setServe();
        }
    }
}
