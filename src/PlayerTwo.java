public class PlayerTwo {

    int points;

    public PlayerTwo(int score){
        this.points = score;

    }

    public int addP1 (int score) {
        int counterTwo = score + 1;
        return counterTwo;
    }

    public int subP1 (int score) {
        int counterTwo = score - 1;
        return counterTwo;
    }

    public int resetP1 (int score) {
        int counterTwo = score * 0;
        return counterTwo;
    }


}
