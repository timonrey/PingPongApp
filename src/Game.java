public class Game {

    Players playerOne;
    Players playerTwo;
    Players scorePlayerOne = new Players();
    Players scorePlayerTwo = new Players();

    public Game() {


    }

    public void buttonWasPressedWithValue(Message mail) {
        int scoreOne = scorePlayerOne.getter();
        int scoreTwo = scorePlayerTwo.getter();
        Players valuePlayer = mail.getPlayer();
        int valueActionType = mail.getActionType();
        //System.out.println(scoreTwo);

        if (valueActionType == 1 && scoreOne <= 11 && scoreTwo <= 11) {
            valuePlayer.add();

        } else if (valueActionType == 2 && scoreOne <= 11 && scoreTwo <= 11) {
            valuePlayer.sub();


        } else if (valueActionType == 3) {
            valuePlayer.reset();


        }
    }
}


