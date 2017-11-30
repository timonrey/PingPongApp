public class Players {

    int points = 0;

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


    public int getter () {
        return points;

    }
}
