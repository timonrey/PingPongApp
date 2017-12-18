public class GameMonitor {

    Players firstPlayer;
    Players secondPlayer;
    Game match;

    public GameMonitor () {
        this.firstPlayer = new Players();
        this.secondPlayer = new Players();
        this.match = new Game(firstPlayer, secondPlayer);
    }

    public void buttonPress(Message message) {

         if (message.getButtonId().equals("1")) {
             match.updateScoreOfPlayer(firstPlayer, message.getActionType());
             if (match.hasSomebodyWon()) {
                 match.updateMatchScoreOfPlayers();
             }

        } else if (message.getButtonId().equals("2")) {
            match.updateScoreOfPlayer(secondPlayer, message.getActionType());
            if (match.hasSomebodyWon()) {
                match.updateMatchScoreOfPlayers();
            }

        }

        System.out.println(String.format("%s : %s", firstPlayer.getSetScore(), secondPlayer.getSetScore()));
        System.out.println(String.format("%s:%s", firstPlayer.getMatchPoints(), secondPlayer.getMatchPoints()));
    }
}

