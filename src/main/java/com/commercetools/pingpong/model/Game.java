package com.commercetools.pingpong.model;

public class Game {

    private Player playerOne;
    private Player playerTwo;
    private boolean isPlayerOneBeginning;
    private Player servingPlayer;

    private int score[] = new int[2];

    public Game(Player playerOne, Player playerTwo, boolean isPlayerOneBeginning) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        this.isPlayerOneBeginning = isPlayerOneBeginning;

        // Ensure that the game state is initialized
        this.resetGame();
    }

    public int[] getScore() {
        return this.score;
    }

    public void addSetPoint(Boolean playerOne) {
        if(playerOne) {
            this.score[0]++;

        } else {
            this.score[1]++;
        }
        switchServingPlayer(true);
    }

    public void subtractSetPoint(Player player) {
        if(player == this.playerOne) {
            if(this.score[0] > 0) {
                this.score[0]--;
            }

        } else if(player == this.playerTwo) {
            if(this.score[1] > 0)

                this.score[1]--;
        } else {
            throw new Error("Player did not match playerOne or playerTwo");
        }
        switchServingPlayer(false);
    }


    // Check if the new score is an odd or even number, in which case we need to switch the serving player.
    public void switchServingPlayer(boolean isEven) {
        if(this.getIsOvertime()) {
            this.servingPlayer = this.servingPlayer == this.playerOne ? this.playerTwo : this.playerOne;
            return;
        }

        int condition = isEven ? 0 : 1;
        int scoreSum = this.score[0] + this.score[1];
        int rest = scoreSum % 2;
        if (rest == condition) {
            this.servingPlayer = this.servingPlayer == this.playerOne ? this.playerTwo : this.playerOne;
        }
    }

    public boolean getIsOvertime() {
        return (this.score[0] >= 10 && this.score[1] >= 10);
    }

    public void resetGame() {
        this.score[0] = 0;
        this.score[1] = 0;
        this.servingPlayer = this.isPlayerOneBeginning ? this.playerOne : this.playerTwo;
    }

    public Player getServingPlayer() {
        return this.servingPlayer;
    }

    public boolean getIfGameIsOver() {
        if (this.score[0] >= 11 || this.score[1] >= 11) {
            return Math.abs(this.score[0] - this.score[1]) >= 2;
        }
        return false;
    }

    public void __setCustomScore (int p1, int p2) {
        this.score[0] = p1;
        this.score[1] = p2;
    }

    public Player getBeginningPlayer() {
        return isPlayerOneBeginning ? this.playerOne : this.playerTwo;
    }

}
