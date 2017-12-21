public class GameMonitor {

    Players firstPlayer;
    Players secondPlayer;
    Game match;

    public GameMonitor() {
        this.firstPlayer = new Players();
        this.secondPlayer = new Players();
        this.match = new Game(firstPlayer, secondPlayer);
    }

    public void buttonPress(Message message) {
        if (message.getButtonId().equals("1") && match.whichPlayerSide(firstPlayer, secondPlayer)) {
            match.updateScoreOfPlayer(firstPlayer, message.getActionType());
            System.out.println(String.format("%s : %s", firstPlayer.getSetScore(), secondPlayer.getSetScore()));

            if (match.hasSomebodyWon()) {
                match.updateMatchScoreOfPlayers();
                System.out.println(String.format("%s:%s", firstPlayer.getMatchScore(), secondPlayer.getMatchScore()));
            }

        } else if (message.getButtonId().equals("2") && match.whichPlayerSide(firstPlayer, secondPlayer)) {
            match.updateScoreOfPlayer(secondPlayer, message.getActionType());
            System.out.println(String.format("%s : %s", firstPlayer.getSetScore(), secondPlayer.getSetScore()));

            if (match.hasSomebodyWon()) {
                match.updateMatchScoreOfPlayers();
                System.out.println(String.format("%s:%s", firstPlayer.getMatchScore(), secondPlayer.getMatchScore()));
            }

        } else if (message.getButtonId().equals("1")) {
            match.updateScoreOfPlayer(secondPlayer, message.getActionType());
            System.out.println(String.format("%s : %s", secondPlayer.getSetScore(), firstPlayer.getSetScore()));

            if (match.hasSomebodyWon()) {
                match.updateMatchScoreOfPlayers();
                System.out.println(String.format("%s:%s", secondPlayer.getMatchScore(), firstPlayer.getMatchScore()));
            }

        } else if (message.getButtonId().equals("2")) {
            match.updateScoreOfPlayer(firstPlayer, message.getActionType());
            System.out.println(String.format("%s : %s", secondPlayer.getSetScore(), firstPlayer.getSetScore()));

            if (match.hasSomebodyWon()) {
                match.updateMatchScoreOfPlayers();
                System.out.println(String.format("%s:%s", secondPlayer.getMatchScore(), firstPlayer.getMatchScore()));
            }

        }

    }

}


