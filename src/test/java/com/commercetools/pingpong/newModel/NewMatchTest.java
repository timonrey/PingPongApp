package com.commercetools.pingpong.newModel;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class NewMatchTest {

    private NewPlayer testPlayerOne;
    private NewPlayer testPlayerTwo;
    private NewMatch newMatch;


    @Before
    public void setup() {
        testPlayerOne = new NewPlayer("Nicola");
        testPlayerTwo = new NewPlayer("Timon");
        newMatch = new NewMatch(this.testPlayerOne, this.testPlayerTwo);
        newMatch.start(testPlayerOne);
    }


    @Test
    public void shouldReturnMatchPoints() {
        int[] expectedInitialScore = {0,0};
        assertThat("Should return 0:0", newMatch.getMatchPoints(), is(expectedInitialScore));

        int[] normalGameFlow = {2,1};
        newMatch.__setCustomMatchScore(2,1);
        assertThat("Should return 2:1", newMatch.getMatchPoints(), is(normalGameFlow));
    }


    @Test
    public void shouldCreateGameTwo() {
        newMatch.addGamePoint(testPlayerOne);
        newMatch.addGamePoint(testPlayerOne);
        newMatch.addGamePoint(testPlayerOne);
        newMatch.addGamePoint(testPlayerOne);
        newMatch.addGamePoint(testPlayerOne);
        newMatch.addGamePoint(testPlayerOne);
        newMatch.addGamePoint(testPlayerOne);
        newMatch.addGamePoint(testPlayerOne);
        newMatch.addGamePoint(testPlayerOne);
        newMatch.addGamePoint(testPlayerOne);

        assertThat("Game 2 should not be started yet", newMatch.getNumberOfGames(), is(1));

        newMatch.addGamePoint(testPlayerOne);
        assertThat("Game 1 finished, should create game 2", newMatch.getNumberOfGames(), is(2));
    }

    @Test
    public void shouldCheckIfMatchisOver() {
        newMatch.addGamePoint(testPlayerOne);
        newMatch.addGamePoint(testPlayerOne);
        newMatch.addGamePoint(testPlayerOne);
        newMatch.addGamePoint(testPlayerOne);
        newMatch.addGamePoint(testPlayerOne);
        newMatch.addGamePoint(testPlayerOne);
        newMatch.addGamePoint(testPlayerOne);
        newMatch.addGamePoint(testPlayerOne);
        newMatch.addGamePoint(testPlayerOne);
        newMatch.addGamePoint(testPlayerOne);
        newMatch.addGamePoint(testPlayerOne);

        // PlayerOne has won the Game
        newMatch.addGamePoint(testPlayerOne);
        newMatch.addGamePoint(testPlayerOne);
        newMatch.addGamePoint(testPlayerOne);
        newMatch.addGamePoint(testPlayerOne);
        newMatch.addGamePoint(testPlayerOne);
        newMatch.addGamePoint(testPlayerOne);
        newMatch.addGamePoint(testPlayerOne);
        newMatch.addGamePoint(testPlayerOne);
        newMatch.addGamePoint(testPlayerOne);
        newMatch.addGamePoint(testPlayerOne);

        assertNull("There should be no winner", newMatch.getWinner());

        newMatch.addGamePoint(testPlayerOne);

        //PlayerOne has won the second Game
        assertThat("PlayerOne should win two Games", newMatch.getNumberOfGames(), is(2));

        assertThat("PlayerOne is the winner", newMatch.getWinner(), is(this.testPlayerOne));

    }

    @Test
    public void shouldGetRightOrLeftSideForPlayer() {
        assertThat("Initially player one always plays on the left", newMatch.isPlayerOneOnTheLeftSide(), is(true));

        newMatch.addGamePoint(testPlayerOne);
        newMatch.addGamePoint(testPlayerOne);
        newMatch.addGamePoint(testPlayerOne);
        newMatch.addGamePoint(testPlayerOne);
        newMatch.addGamePoint(testPlayerOne);
        newMatch.addGamePoint(testPlayerOne);
        newMatch.addGamePoint(testPlayerOne);
        newMatch.addGamePoint(testPlayerOne);
        newMatch.addGamePoint(testPlayerOne);
        newMatch.addGamePoint(testPlayerOne);
        newMatch.addGamePoint(testPlayerOne);

        assertThat("After the first game, player one is on the right", newMatch.isPlayerOneOnTheLeftSide(), is(false));

        newMatch.addGamePoint(testPlayerTwo);
        newMatch.addGamePoint(testPlayerTwo);
        newMatch.addGamePoint(testPlayerTwo);
        newMatch.addGamePoint(testPlayerTwo);
        newMatch.addGamePoint(testPlayerTwo);
        newMatch.addGamePoint(testPlayerTwo);
        newMatch.addGamePoint(testPlayerTwo);
        newMatch.addGamePoint(testPlayerTwo);
        newMatch.addGamePoint(testPlayerTwo);
        newMatch.addGamePoint(testPlayerTwo);
        newMatch.addGamePoint(testPlayerTwo);

        assertThat("After the second game, player one is back to the left", newMatch.isPlayerOneOnTheLeftSide(), is(true));
    }

}