package com.commercetools.pingpong.newModel;

public class NewGame {

    private NewPlayer playerOne;
    private NewPlayer playerTwo;
    private boolean isPlayerOneBeginning;
    private NewPlayer servingPlayer;

    private int score[] = new int[2];

    public NewGame(NewPlayer playerOne, NewPlayer playerTwo, boolean isPlayerOneBeginning) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        this.isPlayerOneBeginning = isPlayerOneBeginning;

        // Ensure that the game state is initialized
        this.resetGame();
    }

    public int[] getScore() {
        return this.score;
    }

    public void addSetPoint(NewPlayer player) {
        if(player == this.playerOne) {
            this.score[0]++;

        } else if(player == this.playerTwo) {
            this.score[1]++;

        } else {
            throw new Error("Player did not match playerOne or playerTwo");
        }
        switchServingPlayer(true);
    }

    public void subtractSetPoint(NewPlayer player) {
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

    public NewPlayer getServingPlayer() {
        return this.servingPlayer;
    }

    public boolean getIsGameIsOver() {
        if (this.score[0] >= 11 || this.score[1] >= 11) {
            return Math.abs(this.score[0] - this.score[1]) >= 2;
        }
        return false;
    }

    public NewPlayer getWinner() {
        if (!this.getIsGameIsOver()) {
            return null;
        }
        return this.score[0] > this.score[1] ? this.playerOne : this.playerTwo;
    }

    public void __setCustomScore (int p1, int p2) {
        this.score[0] = p1;
        this.score[1] = p2;
    }

    public NewPlayer getBeginningPlayer() {
        return isPlayerOneBeginning ? this.playerOne : this.playerTwo;
    }

}
