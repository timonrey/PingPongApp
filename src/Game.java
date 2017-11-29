public class Game {

    Players playerOne;
    Players playerTwo;

    public Game(Players firstPlayerName, Players secondPlayerName) {
        this.playerOne = firstPlayerName;
        this.playerTwo = secondPlayerName;
        
    }

    public void buttonWasPressedWithValue(Message mail) {
        int scoreOne = playerOne.getter();
        int scoreTwo = playerTwo.getter();
        Players valuePlayer = mail.getPlayer();
        int valueActionType = mail.getActionType();

        if (valueActionType == 1 && scoreOne < 11 && scoreTwo < 11) {
            valuePlayer.add();

        } else if (valueActionType == 2) {
            valuePlayer.sub();

        } else if (valueActionType == 3) {
            valuePlayer.reset();


        }
    }
}


