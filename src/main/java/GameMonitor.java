public class GameMonitor {

    Players firstPlayer;
    Players secondPlayer;
    Game match;

    public GameMonitor() {
        this.firstPlayer = new Players();
        this.secondPlayer = new Players();
        this.match = new Game(firstPlayer, secondPlayer);

    }

    public Players playerWhoPressedButton(Message message) {

        if (message.getButtonId().equals("1") && match.isSetScoreEven(firstPlayer, secondPlayer)) {
            return  this.firstPlayer;
        } else if (message.getButtonId().equals("2") && match.isSetScoreEven(firstPlayer, secondPlayer)) {
            return this.secondPlayer;
        } else if (message.getButtonId().equals("1")) {
            return this.secondPlayer;
        } else {
            return this.firstPlayer;
        }

    }

    public void buttonPress(Message message) {
        Players actingPlayer = this.playerWhoPressedButton(message);
        match.updateScoreOfPlayer(actingPlayer, message.getActionType());

        if (match.isSetScoreEven(firstPlayer, secondPlayer)) {
            System.out.println(String.format("%s : %s", firstPlayer.getSetScore(), secondPlayer.getSetScore()));

            if (match.hasSomebodyWon()) {
                match.updateMatchScoreOfPlayers();
                System.out.println(String.format("%s:%s", firstPlayer.getMatchScore(), secondPlayer.getMatchScore()));
            }

        } else {
            System.out.println(String.format("%s : %s", secondPlayer.getSetScore(), firstPlayer.getSetScore()));

            if (match.hasSomebodyWon()) {
                match.updateMatchScoreOfPlayers();
                System.out.println(String.format("%s:%s", secondPlayer.getMatchScore(), firstPlayer.getMatchScore()));
            }

        }


    }


}




