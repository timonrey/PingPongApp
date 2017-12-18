public class Players {

    int points = 0;
    int matchPoints;

    public Players() {

    }

    public void add () {
        points = points +1;
    }

    public void sub () {
        points = points - 1;
    }

    public void reset () {
        points = 0;
    }

    public void addMatchPoint() { matchPoints = matchPoints +1; }

    public void resetMatchPoints() { matchPoints = 0; }


    public int getScore() {
        return points;

    }

    public int getMatchPoints() {
        return matchPoints;

    }

}
