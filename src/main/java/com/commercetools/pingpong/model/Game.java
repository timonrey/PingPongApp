package com.commercetools.pingpong.model;


public class Game {
    private Player playerOne;
    private Player playerTwo;

    public Game(Player firstPlayer, Player secondPlayer) {
        playerOne = firstPlayer;
        playerTwo = secondPlayer;
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
            resetBeginningServe();

        }

    }

    public void updateMatchScoreOfPlayers() {
        if (hasPlayerWonSet(playerOne, playerTwo) && !isMatchOver()) {
            playerOne.addMatchScore();
            resetSetScores();
            whoBeginsServing();

        } else if (hasPlayerWonSet(playerTwo, playerOne) && !isMatchOver()) {
            playerTwo.addMatchScore();
            resetSetScores();
            whoBeginsServing();
        }
    }

    public void gameOver() {
        resetSetScores();
        resetMatchScores();
        resetServe();
        resetBeginningServe();

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

    public void resetSetScores() {
        playerOne.resetSet();
        playerTwo.resetSet();
    }

    public void resetMatchScores() {
        playerOne.resetMatchScore();
        playerTwo.resetMatchScore();
    }

    public void resetServe() {
        playerOne.unsetServe();
        playerTwo.unsetServe();
    }

    public void resetBeginningServe() {
        playerOne.unsetBeginningServe();
        playerTwo.unsetBeginningServe();
    }

    public boolean areBothSetScoresZero() {
        return (playerOne.getSetScore() == 0 && playerTwo.getSetScore() == 0);
    }

    public boolean areBothMatchScoresZero() {
        return (playerOne.getMatchScore() == 0 && playerTwo.getMatchScore() == 0);
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

    public boolean getPlayerOneServe() {
        return playerOne.amIServing();
    }

    public boolean getPlayerTwoServe() {
        return playerTwo.amIServing();
    }

    public void changeServingPlayer(Player nextServingPlayer, Player lastServingPlayer) {
        lastServingPlayer.unsetServe();
        nextServingPlayer.setServe();
    }

    public void changeBeginningServingPlayer(Player nextServingPlayer, Player lastServingPlayer) {
        lastServingPlayer.unsetBeginningServe();
        nextServingPlayer.setBeginningServe();
        lastServingPlayer.unsetServe();
        nextServingPlayer.setServe();
    }

    public boolean isOvertime(Player playerOne, Player playerTwo) {
        return playerOne.getSetScore() >= 10 && playerTwo.getSetScore() >= 10;
    }

    public void whoServesAfterDoubleClick() {
        if (!isSetScoreEven()) {
            if (playerOne.amIServing()) {
                changeServingPlayer(playerTwo, playerOne);

            } else if (playerTwo.amIServing()) {
                changeServingPlayer(playerOne, playerTwo);
            }
        }
    }

    public void setFirstServe(Player actingPlayer) {
        actingPlayer.setBeginningServe();
        actingPlayer.setServe();
    }


    public void whoBeginsServing() {
        if (playerOne.didIServeAtBeginning()) {
            changeBeginningServingPlayer(playerTwo, playerOne);

        } else if (playerTwo.didIServeAtBeginning()) {
            changeBeginningServingPlayer(playerOne, playerTwo);
        }
    }

    public void decideWhoServes() {
        if (areBothSetScoresZero() && areBothMatchScoresZero()) {
            return;

        } else if (isOvertime(playerOne, playerTwo) && playerOne.amIServing()) {
            changeServingPlayer(playerTwo, playerOne);

        } else if (isOvertime(playerOne, playerTwo) && playerTwo.amIServing()) {
            changeServingPlayer(playerOne, playerTwo);

        } else if (playerOne.amIServing() && isSetScoreEven()) {
            changeServingPlayer(playerTwo, playerOne);

        } else if (playerTwo.amIServing() && isSetScoreEven()) {
            changeServingPlayer(playerOne, playerTwo);
        }
    }
}
