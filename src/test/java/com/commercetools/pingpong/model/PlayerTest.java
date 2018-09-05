package com.commercetools.pingpong.model;

import org.assertj.core.error.ShouldBe;
import org.assertj.core.error.ShouldBeEqual;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class PlayerTest {

    @Before
    public void shouldReturnNewPlayer() {
        Player playerTest = new Player();

        assertThat("It should return setScore of 0 for a newPlayer", playerTest.gameData.getSetScore(), is(0));
        assertThat("It should return matchScore of 0 for a newPlayer",playerTest.gameData.getMatchScore(), is (0));
        assertThat("It should return beginningServe of false for a newPlayer",playerTest.gameData.didIServeAtBeginning(), is(false));
        assertThat("It should return serve of false for a newPlayer",playerTest.gameData.amIServing(), is(false));
    }

    @Test
    public void shouldAddSetPoint() {
        Player testPlayer = new Player();
        testPlayer.gameData.addPoint();

        assertThat("It should add one SetPoint", testPlayer.gameData.getSetScore(), is(1));
    }

    @Test
    public void shouldAddMatchPoint() {
        Player testPlayer = new Player();
        testPlayer.gameData.addMatchScore();
        assertThat("It should add one MatchPoint", testPlayer.gameData.getMatchScore(), is(1));
    }

    @Test
    public void shouldSubSetPoint() {
        Player testPlayer = new Player();
        testPlayer.gameData.addPoint();
        testPlayer.gameData.addPoint();
        testPlayer.gameData.subPoint();

        assertThat("Should remove one SetPoint", testPlayer.gameData.getSetScore(), is(1));
    }

    @Test
    public void shouldResetSetPoints() {
        Player testPlayer = new Player();
        testPlayer.gameData.addPoint();
        testPlayer.gameData.addPoint();
        testPlayer.gameData.resetSet();

        assertThat("Should reset all SetPoints", testPlayer.gameData.getSetScore(), is(0));
    }

    @Test
    public void shouldResetMatchPoints() {
        Player testPlayer = new Player();

        testPlayer.gameData.addMatchScore();
        testPlayer.gameData.resetMatchScore();

        assertThat("Should reset all MatchPoints", testPlayer.gameData.getMatchScore(), is(0));
    }

    @Test
    public void shouldSetBeginningServe() {
        Player testPlayer = new Player();

        testPlayer.gameData.setBeginningServe();

        assertThat("It should set the beginning serve", testPlayer.gameData.didIServeAtBeginning(), is(true));
    }

}
