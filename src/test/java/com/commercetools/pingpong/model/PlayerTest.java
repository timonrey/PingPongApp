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

        assertThat("It should return setScore of 0 for a newPlayer", playerTest.getSetScore(), is(0));
        assertThat("It should return matchScore of 0 for a newPlayer",playerTest.getMatchScore(), is (0));
        assertThat("It should return beginningServe of false for a newPlayer",playerTest.didIServeAtBeginning(), is(false));
        assertThat("It should return serve of false for a newPlayer",playerTest.amIServing(), is(false));
    }

    @Test
    public void shouldAddSetPoint() {
        Player testPlayer = new Player();
        testPlayer.addPoint();

        assertThat("It should add one SetPoint", testPlayer.getSetScore(), is(1));
    }

    @Test
    public void shouldAddMatchPoint() {
        Player testPlayer = new Player();
        testPlayer.addMatchScore();
        assertThat("It should add one MatchPoint", testPlayer.getMatchScore(), is(1));
    }

    @Test
    public void shouldSubSetPoint() {
        Player testPlayer = new Player();
        testPlayer.addPoint();
        testPlayer.addPoint();
        testPlayer.subPoint();

        assertThat("Should remove one SetPoint", testPlayer.getSetScore(), is(1));
    }

    @Test
    public void shouldResetSetPoints() {
        Player testPlayer = new Player();
        testPlayer.addPoint();
        testPlayer.addPoint();
        testPlayer.resetSet();

        assertThat("Should reset all SetPoints", testPlayer.getSetScore(), is(0));
    }

    @Test
    public void shouldResetMatchPoints() {
        Player testPlayer = new Player();

        testPlayer.addMatchScore();
        testPlayer.resetMatchScore();

        assertThat("Should reset all MatchPoints", testPlayer.getMatchScore(), is(0));
    }

    @Test
    public void shouldSetBeginningServe() {
        Player testPlayer = new Player();

        testPlayer.setBeginningServe();

        assertThat("It should set the beginning serve", testPlayer.didIServeAtBeginning(), is(true));
    }

}
