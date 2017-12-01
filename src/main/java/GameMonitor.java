public class GameMonitor {

    Players firstPlayer;
    Players secondPlayer;
    Game spiel;

    public GameMonitor () {
        this.firstPlayer = new Players();
        this.secondPlayer = new Players();
        this.spiel = new Game(firstPlayer, secondPlayer);
    }

    public void buttonPress(Message message) {

        if (message.getButtonId().equals("1")) {
            spiel.updateScoreOfPlayer(firstPlayer, message.getActionType());
        } else if (message.getButtonId().equals("2")) {
            spiel.updateScoreOfPlayer(secondPlayer, message.getActionType());
        }

        System.out.println(String.format("%s vs %s", firstPlayer.getScore(), secondPlayer.getScore()));
    }
}
