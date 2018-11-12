package com.commercetools.pingpong.newModel;

import com.commercetools.pingpong.model.Match;
import com.commercetools.pingpong.model.Player;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class NewMatchTest {

    private Player testPlayerOne;
    private Player testPlayerTwo;
    private Match newMatch;


    @Before
    public void setup() {
        testPlayerOne = new Player("Nicola");
        testPlayerTwo = new Player("Timon");
        newMatch = new Match(this.testPlayerOne, this.testPlayerTwo);
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

    @Test
    public void shouldSubtractPointOfGame() {
        newMatch.createNewGame(true).__setCustomScore(5,3);
        System.out.println();
        this.newMatch.subtractGamePoint(this.testPlayerOne);
        assertThat("Should chage game score of playerOne from 5 to 4", newMatch.getCurrentGame().getScore()[0], is(4));

    }

}