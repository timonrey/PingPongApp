public class Players {

    int setScore = 0;
    int matchScore;

    public Players() {

    }

    public void add () {
        setScore = setScore +1;
    }

    public void sub () {
        setScore = setScore - 1;
    }

    public void reset () {
        setScore = 0;
    }

    public void addMatchScore() { matchScore = matchScore +1; }

    public void resetMatchScore() { matchScore = 0; }


    public int getSetScore() {
        return setScore;

    }

    public int getMatchScore() {
        return matchScore;

    }

}
