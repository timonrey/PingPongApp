package com.commercetools.pingpong.model;

import org.junit.Before;
import org.junit.Test;
import org.relaxng.datatype.DatatypeException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;


public class GameTest {



    public Player getDummyOne(int setScore, int matchScore, boolean serve, boolean beginningServe) {
        Player dummyPlayerOne = new Player();

        for (dummyPlayerOne.getSetScore(); dummyPlayerOne.getSetScore() < setScore;) {
            dummyPlayerOne.addPoint();
        }

        for (dummyPlayerOne.getMatchScore(); dummyPlayerOne.getMatchScore() < matchScore;) {
            dummyPlayerOne.addMatchScore();
        }

        if (serve) {
            dummyPlayerOne.setServe();
        } else {
            dummyPlayerOne.unsetServe();
        }

        if (beginningServe) {
            dummyPlayerOne.setBeginningServe();
        } else {
            dummyPlayerOne.unsetBeginningServe();
        }

        return dummyPlayerOne;
    }


    public Player getDummyTwo(int setScore, int matchScore, boolean serve, boolean beginningServe) {
        Player dummyPlayerTwo = new Player();

        for (dummyPlayerTwo.getSetScore(); dummyPlayerTwo.getSetScore() < setScore;) {
            dummyPlayerTwo.addPoint();
        }

        for (dummyPlayerTwo.getMatchScore(); dummyPlayerTwo.getMatchScore() < matchScore;) {
            dummyPlayerTwo.addMatchScore();
        }

        if (serve) {
            dummyPlayerTwo.setServe();
        } else {
            dummyPlayerTwo.unsetServe();
        }

        if (beginningServe) {
            dummyPlayerTwo.setBeginningServe();
        } else {
            dummyPlayerTwo.unsetBeginningServe();
        }

        return dummyPlayerTwo;
    }




    @Test
    public void shouldReturnNewGame() {
        Player testPlayerOne = new Player("One");
        Player testPlayerTwo = new Player("Two");

        Game testGame = new Game(testPlayerOne, testPlayerTwo);

        assertThat("It should return the name of the first player", testGame.getPlayerOne().getName(), is("One"));
        assertThat("It should return the name of the second player", testGame.getPlayerTwo().getName(), is("Two"));

    }

    @Test
    public void shouldAddSetScoreOfPlayer() {
        Message testMessage = new Message("1", 1);
        Player testplayer = new Player();
        Game testMatch = new Game(testplayer, testplayer);

        testMatch.updateScoreOfPlayer(testplayer, testMessage.getActionType());

        assertThat("", testplayer.getSetScore(), is(1));

    }


    /** WARNING: If any of the following five tests fail, the above dummyPlayers methods do not work properly **/
    @Test
    public void shouldSubSetScoreOfPlayer() {
        Message testMessageOne = new Message("1", 1);
        Message testMesssageTwo = new Message("1", 2);
        Player testplayer = new Player();
        Game testMatch = new Game(testplayer, testplayer);

        testMatch.updateScoreOfPlayer(testplayer, testMessageOne.getActionType());
        testMatch.updateScoreOfPlayer(testplayer, testMessageOne.getActionType());
        testMatch.updateScoreOfPlayer(testplayer, testMesssageTwo.getActionType());

        assertThat("", testplayer.getSetScore(), is(1));

    }

    @Test
    public void shouldAddMatchPointOfFirstPlayer() {
        Game testMatch = new Game(getDummyOne(11, 0, true, false), getDummyTwo(7, 0, false, true));
        testMatch.updateMatchScoreOfPlayers();
        assertThat("", testMatch.getPlayerOne().getMatchScore(), is(1));

    }

    @Test
    public void shouldAddMatchPointOfSecondPlayer() {
        Game testMatch = new Game(getDummyOne(7, 0, false, true), getDummyTwo(11, 0, true, false));
        testMatch.updateMatchScoreOfPlayers();
        assertThat("", testMatch.getPlayerTwo().getMatchScore(), is(1));

    }

    @Test
    public void shouldChangeTheServingPlayer() {
        Game testMatch = new Game(getDummyOne(8,0,true,true), getDummyTwo(6,0,false,false));
        testMatch.changeServingPlayer(testMatch.getPlayerTwo(),testMatch.getPlayerOne());

        assertThat("", testMatch.getPlayerOneServe(), is(false));
        assertThat("", testMatch.getPlayerTwoServe(), is(true));
    }

    @Test
    public void shouldSetTheBeginningServingPlayer() {
        Game testMatch = new Game(getDummyOne(0,0,false,false), getDummyTwo(0,0,false,false));
        testMatch.changeBeginningServingPlayer(testMatch.getPlayerOne(),testMatch.getPlayerTwo());

        assertThat("", testMatch.getPlayerOne().didIServeAtBeginning(), is(true));
        assertThat("", testMatch.getPlayerTwo().didIServeAtBeginning(), is(false));

        assertThat("", testMatch.getPlayerOne().amIServing(), is(true));
        assertThat("", testMatch.getPlayerTwo().amIServing(), is(false));
    }

    @Test
    public void shouldResetGameFirstPlayer() {
        Message testMessage = new Message("1", 3);
        Game testMatch = new Game(getDummyOne(5,1,true,false), getDummyTwo(10,1,false,true));

        testMatch.updateScoreOfPlayer(testMatch.getPlayerOne(), testMessage.getActionType());

        assertThat("", testMatch.getPlayerOne().getSetScore(), is(0));
        assertThat("", testMatch.getPlayerOne().getMatchScore(), is(0));
        assertThat("", testMatch.getPlayerOne().amIServing(), is(false));
        assertThat("", testMatch.getPlayerOne().didIServeAtBeginning(), is(false));
        // After the reset, variables of playerOne should be on default

    }

    @Test
    public void shouldResetGameSecondPlayer() {
        Message testMessage = new Message("1", 3);
        Game testMatch = new Game(getDummyOne(5,1,true,false), getDummyTwo(10,1,false,true));

        testMatch.updateScoreOfPlayer(testMatch.getPlayerTwo(), testMessage.getActionType());

        assertThat("", testMatch.getPlayerTwo().getSetScore(), is(0));
        assertThat("", testMatch.getPlayerTwo().getMatchScore(), is(0));
        assertThat("", testMatch.getPlayerTwo().amIServing(), is(false));
        assertThat("", testMatch.getPlayerTwo().didIServeAtBeginning(), is(false));
        // After the reset, variables of playerTwo should be on default

    }

    @Test
    public void shouldResetEveryValue() {
        Game testMatch = new Game(getDummyOne(11,1,true,true), getDummyTwo(6,1,false,false));
        testMatch.gameOver();

        assertThat("", testMatch.getPlayerOne().getSetScore(), is(0));
        assertThat("", testMatch.getPlayerOne().getMatchScore(), is(0));
        assertThat("", testMatch.getPlayerOne().amIServing(), is(false));
        assertThat("", testMatch.getPlayerOne().didIServeAtBeginning(), is(false));

        assertThat("", testMatch.getPlayerTwo().getSetScore(), is(0));
        assertThat("", testMatch.getPlayerTwo().getMatchScore(), is(0));
        assertThat("", testMatch.getPlayerTwo().amIServing(), is(false));
        assertThat("", testMatch.getPlayerTwo().didIServeAtBeginning(), is(false));
    }

    @Test
    public void shouldCheckIfTheSetScoreDifferenceIsTwo() {
        Game testMatch = new Game(getDummyOne(14,1,true,false), getDummyTwo(12,1,false,true));

        assertThat("", testMatch.isTwoPointDifference(testMatch.getPlayerOne(), testMatch.getPlayerTwo()), is(true));
        assertThat("", testMatch.isTwoPointDifference(testMatch.getPlayerTwo(), testMatch.getPlayerOne()), is(false));
    }

    @Test
    public void shouldFindOutWhoServesAfterDoubleClickCaseOne() {
        Game scenarioOne = new Game(getDummyOne(8,0,true,true), getDummyTwo(6,0,false,false));
        scenarioOne.whoServesAfterDoubleClick();

        assertThat("", scenarioOne.getPlayerOne().amIServing(), is(true));
        assertThat("", scenarioOne.getPlayerTwo().amIServing(), is(false));
    }

    @Test
    public void shouldFindOutWhoServesAfterDoubleClickCaseTwo() {
        Game scenarioTwo = new Game(getDummyOne(7,0,true,true), getDummyTwo(6,0,false,false));
        scenarioTwo.whoServesAfterDoubleClick();

        assertThat("", scenarioTwo.getPlayerOne().amIServing(), is(false));
        assertThat("", scenarioTwo.getPlayerTwo().amIServing(), is(true));
    }

    @Test
    public void shouldFindOutWhoServesAfterDoubleClickCaseThree() {
        Game scenarioThree = new Game(getDummyOne(7,0,false,true), getDummyTwo(6,0,true,false));
        scenarioThree.whoServesAfterDoubleClick();

        assertThat("", scenarioThree.getPlayerOne().amIServing(), is(true));
        assertThat("", scenarioThree.getPlayerTwo().amIServing(), is(false));
    }

    @Test
    public void shouldSetTheFirstServe() {
        Game testMatch = new Game(getDummyOne(0,0,false,false), getDummyTwo(0,0,false,false));
        testMatch.setFirstServe(testMatch.getPlayerOne());

        assertThat("", testMatch.getPlayerOne().didIServeAtBeginning(), is(true));
        assertThat("", testMatch.getPlayerOne().amIServing(), is(true));
    }

    /** Etwas stimmit hier nicht :D Nur der beginningServe wird verändert.... **/
    @Test
    public void shouldDecideWhoWillBeginWithServingCaseOne() {
        Game scenarioOne = new Game(getDummyOne(0,1,false,false), getDummyTwo(0,1,true,true));
        scenarioOne.whoBeginsServing();

        assertThat("", scenarioOne.getPlayerOne().amIServing(), is(true));
        assertThat("", scenarioOne.getPlayerOne().didIServeAtBeginning(), is(true));

        assertThat("", scenarioOne.getPlayerTwo().amIServing(), is(false));
        assertThat("", scenarioOne.getPlayerTwo().didIServeAtBeginning(), is(false));
    }

    @Test
    public void shouldDecideWhoWillBeginWithServingCaseTwo() {
        Game scenarioTwo = new Game(getDummyOne(0,1,true,true), getDummyTwo(0,1,false,false));
        scenarioTwo.whoBeginsServing();

        assertThat("", scenarioTwo.getPlayerOne().amIServing(), is(false));
        assertThat("", scenarioTwo.getPlayerOne().didIServeAtBeginning(), is(false));

        assertThat("", scenarioTwo.getPlayerTwo().amIServing(), is(true));
        assertThat("", scenarioTwo.getPlayerTwo().didIServeAtBeginning(), is(true));
    }

    @Test
    public void shouldDecideWhoServesCaseOne() {
        Game scenarioOne = new Game(getDummyOne(0,0,false,false), getDummyTwo(0,0,false,false));
        scenarioOne.decideWhoServes();

        assertThat("", scenarioOne.getPlayerOneServe(), is(false));
        assertThat("", scenarioOne.getPlayerTwoServe(), is(false));
    }

    @Test
    public void shouldDecideWhoServesCaseTwo() {
        Game scenarioTwo = new Game(getDummyOne(12, 0, true, true), getDummyTwo(11, 1, false, false));
        scenarioTwo.decideWhoServes();

        assertThat("", scenarioTwo.getPlayerOneServe(), is(false));
        assertThat("", scenarioTwo.getPlayerTwoServe(), is(true));
    }

    @Test
    public void shouldDecideWhoServesCaseThree() {
        Game scenarioThree = new Game(getDummyOne(12, 0, true, false), getDummyTwo(11, 1, false, true));
        scenarioThree.decideWhoServes();

        assertThat("", scenarioThree.getPlayerOneServe(), is(false));
        assertThat("", scenarioThree.getPlayerTwoServe(), is(true));
    }

    @Test
    public void shouldDecideWhoServesCaseFour() {
        Game scenarioFour = new Game(getDummyOne(8, 1, true, false), getDummyTwo(6, 1, false, true));
        scenarioFour.decideWhoServes();

        assertThat("", scenarioFour.getPlayerOneServe(), is(false));
        assertThat("", scenarioFour.getPlayerTwoServe(), is(true));
    }

    @Test
    public void shouldDecideWhoServesCaseFive() {
        Game scenarioFive = new Game(getDummyOne(8, 1, false, true), getDummyTwo(10, 1, true, false));
        scenarioFive.decideWhoServes();

        assertThat("", scenarioFive.getPlayerOneServe(), is(true));
        assertThat("", scenarioFive.getPlayerTwoServe(), is(false));
    }

}
