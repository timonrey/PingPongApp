public class Game {

    Players playerOne;
    Players playerTwo;

    public Game(Players firstPlayer, Players secondPlayer) {
        this.playerOne = firstPlayer;
        this.playerTwo = secondPlayer;
    }

    public boolean hasSomebodyWon() {
        return (hasPlayerWonSet(playerOne, playerTwo) || hasPlayerWonSet(playerTwo, playerOne));
    }

    public boolean hasSomeOneOfThePlayersTwoPointsMore() {
        return  (Math.abs(this.playerOne.getSetScore() - this.playerTwo.getSetScore()) == 2);
    }

    public boolean hasPlayerWonSet(Players playerWhoIsWinning, Players playerWhoIsLoosing) {
        if (playerWhoIsWinning.getSetScore() == 11 && playerWhoIsLoosing.getSetScore() < 10) {
            return true;

        } else if (playerWhoIsWinning.getSetScore() >= 11 && playerWhoIsLoosing.getSetScore() >= 10) {
            return hasSomeOneOfThePlayersTwoPointsMore();

        } else {
            return false;
        }
    }

    public boolean isMatchOver() {
        return (playerOne.getMatchPoints() == 2 || playerTwo.getMatchPoints() == 2);
    }


    public void updateScoreOfPlayer(Players player, int actionType) {

        if (actionType == 1 && !hasSomebodyWon()) {
            player.add();

        } else if (actionType == 2 && player.getSetScore() > 0) {
            player.sub();

        } else if (actionType == 3) {
            player.reset();
        }

    }

    public void updateMatchScoreOfPlayers() {



     if (hasPlayerWonSet(playerOne, playerTwo) && !isMatchOver()) {
         this.playerOne.addMatchPoint();
         this.playerOne.reset();
         this.playerTwo.reset();

     } else if (hasPlayerWonSet(playerTwo, playerOne) && !isMatchOver()) {
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

