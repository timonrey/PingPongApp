public class GameMonitor {

    Players firstPlayer;
    Players secondPlayer;
    Game game;

    public GameMonitor () {
        this.firstPlayer = new Players();
        this.secondPlayer = new Players();
        this.game = new Game(firstPlayer, secondPlayer);
    }

    public void buttonPress(Message message) {

         if (message.getButtonId().equals("1")) {
             game.updateScoreOfPlayer(firstPlayer, message.getActionType());
             if (game.hasSomebodyWon()) {
                 game.updateMatchScoreOfPlayers();
             }

        } else if (message.getButtonId().equals("2")) {
            game.updateScoreOfPlayer(secondPlayer, message.getActionType());
            if (game.hasSomebodyWon()) {
                game.updateMatchScoreOfPlayers();
            }

        }

        System.out.println(String.format("%s : %s", firstPlayer.getScore(), secondPlayer.getScore()));
        System.out.println(String.format("%s:%s", firstPlayer.getMatchPoints(), secondPlayer.getMatchPoints()));
    }
}
