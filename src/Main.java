public class Main {



    public static void main(String[] args) {


        Players playerOne = new Players();
        Players playerTwo = new Players();
        Game match = new Game();
        Message playerOneScores = new Message(playerOne, 1);
        Message playerTwoScores = new Message(playerTwo, 1);


        Players test = playerOneScores.getPlayer();


        match.buttonWasPressedWithValue(playerOneScores);
        match.buttonWasPressedWithValue(playerTwoScores);
        match.buttonWasPressedWithValue(playerTwoScores);
        match.buttonWasPressedWithValue(playerTwoScores);
        match.buttonWasPressedWithValue(playerTwoScores);
        match.buttonWasPressedWithValue(playerTwoScores);
        match.buttonWasPressedWithValue(playerTwoScores);
        match.buttonWasPressedWithValue(playerOneScores);
        match.buttonWasPressedWithValue(playerOneScores);
        match.buttonWasPressedWithValue(playerOneScores);
        match.buttonWasPressedWithValue(playerOneScores);
        match.buttonWasPressedWithValue(playerOneScores);
        match.buttonWasPressedWithValue(playerOneScores);
        match.buttonWasPressedWithValue(playerOneScores);
        match.buttonWasPressedWithValue(playerOneScores);
        match.buttonWasPressedWithValue(playerOneScores);
        match.buttonWasPressedWithValue(playerOneScores);
        match.buttonWasPressedWithValue(playerOneScores);
        match.buttonWasPressedWithValue(playerTwoScores);
        match.buttonWasPressedWithValue(playerTwoScores);
        match.buttonWasPressedWithValue(playerTwoScores);
        match.buttonWasPressedWithValue(playerTwoScores);
        match.buttonWasPressedWithValue(playerTwoScores);
        match.buttonWasPressedWithValue(playerTwoScores);
        match.buttonWasPressedWithValue(playerTwoScores);
        match.buttonWasPressedWithValue(playerTwoScores);
        match.buttonWasPressedWithValue(playerTwoScores);
        match.buttonWasPressedWithValue(playerTwoScores);


        int finalCountOne = playerOne.getter();
            System.out.println(finalCountOne);

        int finalCountTwo = playerTwo.getter();
            System.out.println(finalCountTwo);
        }


    }

