public class Player {

    int setScore;
    int matchScore;

    public void add () {
        setScore++;
    }

    public void sub () {
        setScore--;
    }

    public void reset () {
        setScore = 0;
    }

    public void addMatchScore() { matchScore++; }

    public void resetMatchScore() { matchScore = 0; }


    public int getSetScore() {
        return setScore;

    }

    public int getMatchScore() {
        return matchScore;

    }


}
