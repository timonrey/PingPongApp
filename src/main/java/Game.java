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

    public boolean hasSomeoneOfPlayersTwoPointsMore(Players playerWhoIsWinning, Players playerWhoIsLoosing) {
        return (playerWhoIsWinning.getSetScore() - playerWhoIsLoosing.getSetScore() == 2);
    }

    public boolean hasPlayerWonSet(Players playerWhoIsWinning, Players playerWhoIsLoosing) {
        if (playerWhoIsWinning.getSetScore() == 11 && playerWhoIsLoosing.getSetScore() < 10) {
            return true;

        } else if (playerWhoIsWinning.getSetScore() >= 11 && playerWhoIsLoosing.getSetScore() >= 10) {
            return hasSomeoneOfPlayersTwoPointsMore(playerWhoIsWinning, playerWhoIsLoosing);

        } else {
            return false;
        }
    }

    public boolean isSetScoreEven(Players playerOne, Players playerTwo) {
        return ((playerOne.getMatchScore() + playerTwo.getMatchScore()) % 2 == 0);

    }



    public boolean isMatchOver() {
        return (playerOne.getMatchScore() == 2 || playerTwo.getMatchScore() == 2);
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
         this.playerOne.addMatchScore();
         this.playerOne.reset();
         this.playerTwo.reset();

     } else if (hasPlayerWonSet(playerTwo, playerOne) && !isMatchOver()) {
         this.playerTwo.addMatchScore();
         this.playerOne.reset();
         this.playerTwo.reset();

     }
     if (this.playerOne.getMatchScore() == 2) {
            System.out.println("The match is Over! Player1 has won!!!");
            this.playerOne.reset();
            this.playerTwo.reset();
            this.playerOne.resetMatchScore();
            this.playerTwo.resetMatchScore();


        } else if (this.playerTwo.getMatchScore() == 2) {
            System.out.println("The match is Over! Player2 has won!!!");
            this.playerOne.reset();
            this.playerTwo.reset();
            this.playerOne.resetMatchScore();
            this.playerTwo.resetMatchScore();


        }

    }

}

