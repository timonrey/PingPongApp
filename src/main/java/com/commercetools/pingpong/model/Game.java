package com.commercetools.pingpong.model;


public class Game {
    private Player leftPlayer;
    private Player rightPlayer;

    public Game(Player firstPlayer, Player secondPlayer) {
        leftPlayer = firstPlayer;
        rightPlayer = secondPlayer;
    }

    public void updateScoreOfPlayer(Player player, int actionType) {

        if (actionType == 1 && !hasSomebodyWonSet()) {
            player.addSetPoint();
            decideWhoServes();

        } else if (actionType == 2 && player.getSetScore() > 0) {
            player.subSetPoint();
            whoServesAfterDoubleClick();

        } else if (actionType == 3) {
            resetSetScores();
            resetMatchScores();
            resetServe();
            resetBeginningServe();

        }

    }

    public void updateMatchScoreOfPlayers() {
        if (hasPlayerWonSet(leftPlayer, rightPlayer) && !isMatchOver()) {
            leftPlayer.addMatchPoint();
            resetSetScores();
            whoBeginsServing();

        } else if (hasPlayerWonSet(rightPlayer, leftPlayer) && !isMatchOver()) {
            rightPlayer.addMatchPoint();
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
        return (hasPlayerWonSet(leftPlayer, rightPlayer) || hasPlayerWonSet(rightPlayer, leftPlayer));
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
        return (leftPlayer.getMatchScore() == 2 || rightPlayer.getMatchScore() == 2);
    }

    public boolean isTwoPointDifference(Player winningPlayer, Player losingPlayer) {
        return (winningPlayer.getSetScore() - losingPlayer.getSetScore() == 2);
    }

    public void resetSetScores() {
        leftPlayer.resetSetScore();
        rightPlayer.resetSetScore();
    }

    public void resetMatchScores() {
        leftPlayer.resetMatchScore();
        rightPlayer.resetMatchScore();
    }

    public void resetServe() {
        leftPlayer.unsetServe();
        rightPlayer.unsetServe();
    }

    public void resetBeginningServe() {
        leftPlayer.unsetBeginningServe();
        rightPlayer.unsetBeginningServe();
    }

    public boolean areBothSetScoresZero() {
        return (leftPlayer.getSetScore() == 0 && rightPlayer.getSetScore() == 0);
    }

    public boolean areBothMatchScoresZero() {
        return (leftPlayer.getMatchScore() == 0 && rightPlayer.getMatchScore() == 0);
    }

    public boolean isSomeoneServing() {
        return (leftPlayer.amIServing() || rightPlayer.amIServing());
    }

    public boolean isSetScoreEven() {
        return ((leftPlayer.getSetScore() + rightPlayer.getSetScore()) % 2 == 0);
    }

    public boolean isMatchScoreEven() {
        return ((leftPlayer.getMatchScore() + rightPlayer.getMatchScore()) % 2 == 0);
    }

    public Player getLeftPlayer() {
        return leftPlayer;
    }

    public Player getRightPlayer() {
        return rightPlayer;
    }

    public boolean getPlayerOneServe() {
        return leftPlayer.amIServing();
    }

    public boolean getPlayerTwoServe() {
        return rightPlayer.amIServing();
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
            if (leftPlayer.amIServing()) {
                changeServingPlayer(rightPlayer, leftPlayer);

            } else if (rightPlayer.amIServing()) {
                changeServingPlayer(leftPlayer, rightPlayer);
            }
        }
    }

    public void setFirstServe(Player actingPlayer) {
        actingPlayer.setBeginningServe();
        actingPlayer.setServe();
    }


    public void whoBeginsServing() {
        if (leftPlayer.didIServeAtBeginning()) {
            changeBeginningServingPlayer(rightPlayer, leftPlayer);

        } else if (rightPlayer.didIServeAtBeginning()) {
            changeBeginningServingPlayer(leftPlayer, rightPlayer);
        }
    }

    public void decideWhoServes() {
        if (areBothSetScoresZero() && areBothMatchScoresZero()) {
            return;

        } else if (isOvertime(leftPlayer, rightPlayer) && leftPlayer.amIServing()) {
            changeServingPlayer(rightPlayer, leftPlayer);

        } else if (isOvertime(leftPlayer, rightPlayer) && rightPlayer.amIServing()) {
            changeServingPlayer(leftPlayer, rightPlayer);

        } else if (leftPlayer.amIServing() && isSetScoreEven()) {
            changeServingPlayer(rightPlayer, leftPlayer);

        } else if (rightPlayer.amIServing() && isSetScoreEven()) {
            changeServingPlayer(leftPlayer, rightPlayer);
        }
    }
}
