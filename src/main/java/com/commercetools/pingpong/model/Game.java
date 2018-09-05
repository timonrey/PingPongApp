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
            player.gameData.addPoint();
            decideWhoServes();

        } else if (actionType == 2 && player.gameData.getSetScore() > 0) {
            player.gameData.subPoint();
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
            playerOne.gameData.addMatchScore();
            resetSetScores();
            whoBeginsServing();

        } else if (hasPlayerWonSet(playerTwo, playerOne) && !isMatchOver()) {
            playerTwo.gameData.addMatchScore();
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
        if (playerWhoIsWinning.gameData.getSetScore() == 11 && playerWhoIsLoosing.gameData.getSetScore() < 10) {
            return true;

        } else if (playerWhoIsWinning.gameData.getSetScore() >= 11 && playerWhoIsLoosing.gameData.getSetScore() >= 10) {
            return isTwoPointDifference(playerWhoIsWinning, playerWhoIsLoosing);

        } else {
            return false;
        }
    }
 
    public boolean isMatchOver() {
        return (playerOne.gameData.getMatchScore() == 2 || playerTwo.gameData.getMatchScore() == 2);
    }

    public boolean isTwoPointDifference(Player winningPlayer, Player losingPlayer) {
        return (winningPlayer.gameData.getSetScore() - losingPlayer.gameData.getSetScore() == 2);
    }

    public void resetSetScores() {
        playerOne.gameData.resetSet();
        playerTwo.gameData.resetSet();
    }

    public void resetMatchScores() {
        playerOne.gameData.resetMatchScore();
        playerTwo.gameData.resetMatchScore();
    }

    public void resetServe() {
        playerOne.gameData.unsetServe();
        playerTwo.gameData.unsetServe();
    }

    public void resetBeginningServe() {
        playerOne.gameData.unsetBeginningServe();
        playerTwo.gameData.unsetBeginningServe();
    }

    public boolean areBothSetScoresZero() {
        return (playerOne.gameData.getSetScore() == 0 && playerTwo.gameData.getSetScore() == 0);
    }

    public boolean areBothMatchScoresZero() {
        return (playerOne.gameData.getMatchScore() == 0 && playerTwo.gameData.getMatchScore() == 0);
    }

    public boolean isSomeoneServing() {
        return (playerOne.gameData.amIServing() || playerTwo.gameData.amIServing());
    }

    public boolean isSetScoreEven() {
        return ((playerOne.gameData.getSetScore() + playerTwo.gameData.getSetScore()) % 2 == 0);
    }

    public boolean isMatchScoreEven() {
        return ((playerOne.gameData.getMatchScore() + playerTwo.gameData.getMatchScore()) % 2 == 0);
    }

    public Player getPlayerOne() {
        return playerOne;
    }

    public Player getPlayerTwo() {
        return playerTwo;
    }

    public int setScore(Player desiredPlayer) {
        return desiredPlayer.gameData.getSetScore();
    }

    public int matchScore(Player desiredPlayer) {
        return desiredPlayer.gameData.getMatchScore();
    }

    public boolean getServe(Player desiredPlayer) {
        return desiredPlayer.gameData.amIServing();
    }

    public boolean getPlayerOneServe() {
        return playerOne.gameData.amIServing();
    }

    public boolean getPlayerTwoServe() {
        return playerTwo.gameData.amIServing();
    }

    public void changeServingPlayer(Player nextServingPlayer, Player lastServingPlayer) {
        lastServingPlayer.gameData.unsetServe();
        nextServingPlayer.gameData.setServe();
    }

    public void changeBeginningServingPlayer(Player nextServingPlayer, Player lastServingPlayer) {
        lastServingPlayer.gameData.unsetBeginningServe();
        nextServingPlayer.gameData.setBeginningServe();
        lastServingPlayer.gameData.unsetServe();
        nextServingPlayer.gameData.setServe();
    }

    public boolean isOvertime(Player playerOne, Player playerTwo) {
        return playerOne.gameData.getSetScore() >= 10 && playerTwo.gameData.getSetScore() >= 10;
    }

    public void whoServesAfterDoubleClick() {
        if (!isSetScoreEven()) {
            if (playerOne.gameData.amIServing()) {
                changeServingPlayer(playerTwo, playerOne);

            } else if (playerTwo.gameData.amIServing()) {
                changeServingPlayer(playerOne, playerTwo);
            }
        }
    }

    public void setFirstServe(Player actingPlayer) {
        actingPlayer.gameData.setBeginningServe();
        actingPlayer.gameData.setServe();
    }


    public void whoBeginsServing() {
        if (playerOne.gameData.didIServeAtBeginning()) {
            changeBeginningServingPlayer(playerTwo, playerOne);

        } else if (playerTwo.gameData.didIServeAtBeginning()) {
            changeBeginningServingPlayer(playerOne, playerTwo);
        }
    }

    public void decideWhoServes() {
        if (areBothSetScoresZero() && areBothMatchScoresZero()) {
            return;

        } else if (isOvertime(playerOne, playerTwo) && playerOne.gameData.amIServing()) {
            changeServingPlayer(playerTwo, playerOne);

        } else if (isOvertime(playerOne, playerTwo) && playerTwo.gameData.amIServing()) {
            changeServingPlayer(playerOne, playerTwo);

        } else if (playerOne.gameData.amIServing() && isSetScoreEven()) {
            changeServingPlayer(playerTwo, playerOne);

        } else if (playerTwo.gameData.amIServing() && isSetScoreEven()) {
            changeServingPlayer(playerOne, playerTwo);
        }
    }
}
