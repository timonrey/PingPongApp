public class Game {

    Players playerOne;
    Players playerTwo;

    public Game(Players firstPlayer, Players secondPlayer) {
        this.playerOne = firstPlayer;
        this.playerTwo = secondPlayer;
    }

    public boolean hasSomeoneOfThePlayersElevenPointsYet() {
        if (this.playerOne.getScore() == 11 || this.playerTwo.getScore() == 11) {
            return true;

        } else {
            return false;
        }
    }


    // We already selected the player...he knows already which player had pushed the button
    public void updateScoreOfPlayer(Players player, int actionType) {

        if (actionType == 1 && !!hasSomeoneOfThePlayersElevenPointsYet()) {
            player.add();

        } else if (actionType == 2) {
            player.sub();

        } else if (actionType == 3) {
            player.reset();


        }

    }
}
