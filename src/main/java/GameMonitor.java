public class GameMonitor {

    Player firstPlayer;
    Player secondPlayer;
    Game match;

    public GameMonitor() {
        this.firstPlayer = new Player();
        this.secondPlayer = new Player();
        this.match = new Game(firstPlayer, secondPlayer);

    }

    public Player getPlayer(Message message) {

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
        Player actingPlayer = this.getPlayer(message);
        match.updateScoreOfPlayer(actingPlayer, message.getActionType());

        if (match.isSetScoreEven(firstPlayer, secondPlayer)) {
            this.printScore(firstPlayer, secondPlayer);
        } else {
            this.printScore(secondPlayer, firstPlayer);
        }
    }

    public void printScore(Player playerOnLeftSide, Player playerOnRightSide) {
        System.out.println(String.format("%s : %s", playerOnLeftSide.getSetScore(), playerOnRightSide.getSetScore()));

        if (match.hasSomebodyWon()) {
            match.updateMatchScoreOfPlayers();
            System.out.println(String.format("%s:%s", playerOnLeftSide.getMatchScore(), playerOnRightSide.getMatchScore()));
        }
    }


}




