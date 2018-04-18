package com.commercetools.pingpong.model;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class PlayerTest {


    @Test
    public void shouldReturnGivenSetScores() {
        Player playerOne = new Player("playerOne");
        Player playerTwo = new Player("playerTwo");
        assertThat("SetScore of playerOne must be 0 at the beginning", playerOne.getSetScore(), is(0));
        assertThat("SetScore of playerTwo must be 0 at the beginning", playerTwo.getSetScore(), is(0));
    }

    @Test
    public void shouldReturnServes() {
        Player playerOne = new Player("playerOne");
        Player playerTwo = new Player("playerTwo");
        assertThat("Serve of playerOne must be false at the beginning", playerOne.didIServeAtBeginning(), is(false));
        assertThat("Serve of playerTwo must be false at the beginning", playerTwo.didIServeAtBeginning(), is(false));

        assertThat("Serve of playerOne must be false at the beginning", playerOne.amIServing(), is(false));
        assertThat("Serve of playerTwo must be false at the beginning", playerTwo.amIServing(), is(false));
    }
}
