public class Main {


    public static void main(String[] args) {


        Players nicola = new Players();
        Players philipp = new Players();
        Game match = new Game(nicola, philipp);
        Message nicolaScores = new Message(nicola, 1);
        Message philippScores = new Message(philipp, 1);
        Message nicolaReset = new Message(nicola, 3);
        Message philippReset = new Message(philipp,3);

       // match.playerTwo = new Players()

        match.buttonWasPressedWithValue(nicolaScores);
        match.buttonWasPressedWithValue(philippScores);
        match.buttonWasPressedWithValue(philippScores);
        match.buttonWasPressedWithValue(philippScores);
        match.buttonWasPressedWithValue(philippScores);
        match.buttonWasPressedWithValue(philippScores);
        match.buttonWasPressedWithValue(philippScores);
        match.buttonWasPressedWithValue(nicolaScores);
        match.buttonWasPressedWithValue(nicolaScores);
        match.buttonWasPressedWithValue(nicolaScores);
        match.buttonWasPressedWithValue(nicolaScores);
        match.buttonWasPressedWithValue(nicolaScores);
        match.buttonWasPressedWithValue(nicolaScores);
        match.buttonWasPressedWithValue(nicolaScores);
        match.buttonWasPressedWithValue(nicolaScores);
        match.buttonWasPressedWithValue(nicolaScores);
        match.buttonWasPressedWithValue(nicolaScores);
        match.buttonWasPressedWithValue(nicolaScores);
        match.buttonWasPressedWithValue(philippScores);
        match.buttonWasPressedWithValue(philippScores);
        match.buttonWasPressedWithValue(philippScores);
        match.buttonWasPressedWithValue(philippScores);
        match.buttonWasPressedWithValue(philippScores);
        match.buttonWasPressedWithValue(philippScores);
        match.buttonWasPressedWithValue(philippScores);
        match.buttonWasPressedWithValue(philippScores);
        match.buttonWasPressedWithValue(philippScores);
        match.buttonWasPressedWithValue(philippScores);
        match.buttonWasPressedWithValue(nicolaReset);
        match.buttonWasPressedWithValue(philippReset);

        match.buttonWasPressedWithValue(nicolaScores);
        match.buttonWasPressedWithValue(nicolaScores);
        match.buttonWasPressedWithValue(nicolaScores);
        match.buttonWasPressedWithValue(nicolaScores);
        match.buttonWasPressedWithValue(nicolaScores);
        match.buttonWasPressedWithValue(nicolaScores);
        match.buttonWasPressedWithValue(philippScores);
        match.buttonWasPressedWithValue(philippScores);
        match.buttonWasPressedWithValue(philippScores);
        match.buttonWasPressedWithValue(philippScores);
        match.buttonWasPressedWithValue(philippScores);
        match.buttonWasPressedWithValue(philippScores);
        match.buttonWasPressedWithValue(philippScores);
        match.buttonWasPressedWithValue(nicolaScores);
        match.buttonWasPressedWithValue(nicolaScores);
        match.buttonWasPressedWithValue(nicolaScores);
        match.buttonWasPressedWithValue(nicolaScores);
        match.buttonWasPressedWithValue(nicolaScores);
        match.buttonWasPressedWithValue(nicolaScores);

        int finalCountNicola = nicola.getter();
        System.out.println(finalCountNicola);

        int finalCountPhilipp = philipp.getter();
        System.out.println(finalCountPhilipp);


        }
    }



