public class Game {

    Players playerOne;
    Players playerTwo;

    public Game(Players firstPlayer, Players secondPlayer) {
        this.playerOne = firstPlayer;
        this.playerTwo = secondPlayer;
    }

    public boolean hasSomebodyWon() {
        return (hasThePlayerOneWon() || hasThePlayerTwoWon());
    }

    public boolean hasSomeOneOfThePlayersTwoPointsMore() {
        return  (Math.abs(this.playerOne.getScore() - this.playerTwo.getScore()) == 2);
    }

    public boolean hasThePlayerOneWon() {
        if (this.playerOne.getScore() == 11 && this.playerTwo.getScore() < 10) {
            return true;

        } else if (this.playerOne.getScore() >= 11 && this.playerTwo.getScore() >= 10) {
            return hasSomeOneOfThePlayersTwoPointsMore();

        } else {
            return false;
        }
    }

    public boolean hasThePlayerTwoWon() {
        if (this.playerTwo.getScore() == 11 && this.playerOne.getScore() < 10) {
            return true;

        } else if (this.playerTwo.getScore() >= 11 && this.playerOne.getScore() >= 10) {
            return hasSomeOneOfThePlayersTwoPointsMore();

        } else {
            return false;
        }
    }

    public boolean isTheGameOver() {
        return (playerOne.getMatchPoints() == 2 || playerTwo.getMatchPoints() == 2);
    }


    public void updateScoreOfPlayer(Players player, int actionType) {

        if (actionType == 1 && !hasSomebodyWon()) {
            player.add();

        } else if (actionType == 2 && player.getScore() > 0) {
            player.sub();

        } else if (actionType == 3) {
            player.reset();
        }

    }

    public void updateMatchScoreOfPlayers() {



     if (hasThePlayerOneWon() && !isTheGameOver()) {
         this.playerOne.addMatchPoint();
         this.playerOne.reset();
         this.playerTwo.reset();

     } else if (hasThePlayerTwoWon() && !isTheGameOver()) {
         this.playerTwo.addMatchPoint();
         this.playerOne.reset();
         this.playerTwo.reset();

     }
     if (this.playerOne.getMatchPoints() == 2) {
            System.out.println("The match is Over! Player1 has won!!!");
            this.playerOne.reset();
            this.playerTwo.reset();
            this.playerOne.resetMatchPoints();
            this.playerTwo.resetMatchPoints();


        } else if (this.playerTwo.getMatchPoints() == 2) {
            System.out.println("The match is Over! Player2 has won!!!");
            this.playerOne.reset();
            this.playerTwo.reset();
            this.playerOne.resetMatchPoints();
            this.playerTwo.resetMatchPoints();


        }

    }

}

