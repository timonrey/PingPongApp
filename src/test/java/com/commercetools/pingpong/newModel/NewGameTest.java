package com.commercetools.pingpong.newModel;


import com.commercetools.pingpong.model.Game;
import com.commercetools.pingpong.model.Player;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class NewGameTest {

    private Player testPlayerOne;
    private Player testPlayerTwo;
    private Game game;

    @Before
    public void setup() {
        testPlayerOne = new Player("Nicola");
        testPlayerTwo = new Player("Timon");
        game = new Game(this.testPlayerOne, this.testPlayerTwo, true);
    }


    @Test
    public void shouldReturnEmptyArray() {
        int[] expectedInitialScore = {0,0};
        assertThat("Should give default value of score as 0,0 at position 0,1", game.getScore(), is(expectedInitialScore));
    }

    @Test
    public void shouldAddSetPoint() {
        game.addSetPoint(this.testPlayerOne);
        assertThat("Should set score of testPlayerOne as 1 at position 0", game.getScore()[0], is(1));
    }

    @Test
    public void shouldSubtractPointCaseOne() {
        game.addSetPoint(this.testPlayerOne);
        game.addSetPoint(this.testPlayerOne);
        game.subtractSetPoint(this.testPlayerOne);
        assertThat("Should set score of testPlayerOne from 2 to 1 at position 0", game.getScore()[0], is(1));
    }

    @Test
    public void shouldSubtractPointCaseTwo() {
        game.addSetPoint(this.testPlayerOne);
        game.addSetPoint(this.testPlayerOne);
        game.subtractSetPoint(this.testPlayerOne);
        game.subtractSetPoint(this.testPlayerOne);
        game.subtractSetPoint(this.testPlayerOne);
        assertThat("Should not subtract score below 0", game.getScore()[0], is(0));
    }

    @Test
    public void shouldResetGamePartOne() {
        game.__setCustomScore(5, 7);
        game.resetGame();
        assertThat("Should reset score to null", game.getScore()[0], is(0));
    }

    @Test
    public void shouldSetServingPlayerInitially() {
        assertThat("Should set servingPlayer to playerOne based on isPlayerOneBeginning", game.getServingPlayer(), is(this.testPlayerOne));
    }

    @Test
    public void shouldChangeServingPlayerAfterTwoServes() {
        game.addSetPoint(this.testPlayerOne);
        game.addSetPoint(this.testPlayerOne);
        assertThat("Should change servingPlayer after two serves from playerOne to playerTwo", game.getServingPlayer(), is(this.testPlayerTwo));
    }

    @Test
    public void shouldNotChangeServingPlayer() {
        game.addSetPoint(this.testPlayerOne);
        assertThat("Should not change servingPlayer if the score is not even", game.getServingPlayer(), is(this.testPlayerOne));
    }

    @Test
    public void shouldChangeServingPlayerBackToPlayerOne() {
        game.addSetPoint(this.testPlayerOne);
        game.addSetPoint(this.testPlayerOne);
        // --> here it will switch to player two
        game.subtractSetPoint(this.testPlayerOne);
        assertThat("Should change the servingPlayer back to playerOne after it switched to playerTwo and a point was subtracted", game.getServingPlayer(), is(this.testPlayerOne));
    }


    @Test
    public void shouldResetGame() {
        game.addSetPoint(this.testPlayerOne);
        game.addSetPoint(this.testPlayerOne);
        game.addSetPoint(this.testPlayerTwo);
        game.addSetPoint(this.testPlayerOne);
        game.addSetPoint(this.testPlayerTwo);
        game.addSetPoint(this.testPlayerOne);
        game.resetGame();
        assertThat("Should reset servingPlayer to playerOne based on isPlayerOneBeginning", game.getServingPlayer(), is(this.testPlayerOne));
    }

    @Test
    public void shouldCheckIfThereIsOvertime() {
        assertThat("Game is not in overtime", game.getIsOvertime(), is(false));
    }

    @Test
    public void shouldCheckIfThereIsOvertimePartTwo() {
        game.__setCustomScore(9, 9);
        assertThat("Game is not in overtime on 9:9", game.getIsOvertime(), is(false));

        game.__setCustomScore(10, 9);
        assertThat("Game is not in overtime on 10:9", game.getIsOvertime(), is(false));

        game.__setCustomScore(9, 10);
        assertThat("Game is not in overtime on 9:10", game.getIsOvertime(), is(false));
    }

    @Test
    public void shouldCheckIfThereIsOvertimePartThree() {
        game.__setCustomScore(10, 10);
        assertThat("Game is in overtime on 10:10", game.getIsOvertime(), is(true));

        game.__setCustomScore(11, 10);
        assertThat("Game is in overtime on 11:10", game.getIsOvertime(), is(true));

        game.__setCustomScore(10, 11);
        assertThat("Game is in overtime on 10:11", game.getIsOvertime(), is(true));
    }


    @Test
    public void shouldCheckIfGameIsOver() {
        assertThat("Game is not finished", game.getIfGameIsOver(), is(false));

        game.__setCustomScore(2, 6);
        assertThat("Game is not finished", game.getIfGameIsOver(), is(false));

        game.__setCustomScore(10, 10);
        assertThat("Game is not finished", game.getIfGameIsOver(), is(false));

        game.__setCustomScore(13, 12);
        assertThat("Game is not finished", game.getIfGameIsOver(), is(false));

        game.__setCustomScore(11, 9);
        assertThat("Game is finished", game.getIfGameIsOver(), is(true));

        game.__setCustomScore(14, 12);
        assertThat("Game is finished after overtime", game.getIfGameIsOver(), is(true));
    }

    @Test
    public void shouldSwitchServingPlayerOnEveryPointInOvertime() {
        // Set up to 10 points for player one
        game.addSetPoint(this.testPlayerOne);
        game.addSetPoint(this.testPlayerOne);
        game.addSetPoint(this.testPlayerOne);
        game.addSetPoint(this.testPlayerOne);
        game.addSetPoint(this.testPlayerOne);
        game.addSetPoint(this.testPlayerOne);
        game.addSetPoint(this.testPlayerOne);
        game.addSetPoint(this.testPlayerOne);
        game.addSetPoint(this.testPlayerOne);
        game.addSetPoint(this.testPlayerOne);

        // Set up to 10 points for player two
        game.addSetPoint(this.testPlayerTwo);
        game.addSetPoint(this.testPlayerTwo);
        game.addSetPoint(this.testPlayerTwo);
        game.addSetPoint(this.testPlayerTwo);
        game.addSetPoint(this.testPlayerTwo);
        game.addSetPoint(this.testPlayerTwo);
        game.addSetPoint(this.testPlayerTwo);
        game.addSetPoint(this.testPlayerTwo);
        game.addSetPoint(this.testPlayerTwo);
        game.addSetPoint(this.testPlayerTwo);

        // Score 10:10
        assertThat("At this point playerOne is serving again and overtime starts", game.getServingPlayer(), is(this.testPlayerOne));

        game.addSetPoint(this.testPlayerOne);
        // Score 11:10
        assertThat("In overtime, player two should now serve", game.getServingPlayer(), is(this.testPlayerTwo));

        game.addSetPoint(this.testPlayerTwo);
        // Score 11:11
        assertThat("In overtime, player one should now serve again", game.getServingPlayer(), is(this.testPlayerOne));

        game.addSetPoint(this.testPlayerTwo);
        // Score 11:12
        assertThat("In overtime, player two should now serve again", game.getServingPlayer(), is(this.testPlayerTwo));
    }

    @Test
    public void shouldReturnWinnerOfGame() {
        assertNull("There is no winner yet", game.getGameWinner());

        game.__setCustomScore(11, 8);
        assertThat("PlayerOne won the game with 11 points", game.getGameWinner(), is(this.testPlayerOne));

        game.__setCustomScore(10, 12);
        assertThat("PlayerTwo won the game with 12 points in overtime", game.getGameWinner(), is(this.testPlayerTwo));
    }

}
