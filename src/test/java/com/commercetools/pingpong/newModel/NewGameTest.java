package com.commercetools.pingpong.newModel;


import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class NewGameTest {

    private NewPlayer testPlayerOne;
    private NewPlayer testPlayerTwo;
    private NewGame newGame;

    @Before
    public void setup() {
        testPlayerOne = new NewPlayer("Nicola");
        testPlayerTwo = new NewPlayer("Timon");
        newGame = new NewGame(this.testPlayerOne, this.testPlayerTwo, true);
    }


    @Test
    public void shouldReturnEmptyArray() {
        int[] expectedInitialScore = {0,0};
        assertThat("Should give default value of score as 0,0 at position 0,1", newGame.getScore(), is(expectedInitialScore));
    }

    @Test
    public void shouldAddSetPoint() {
        newGame.addSetPoint(this.testPlayerOne);
        assertThat("Should set score of testPlayerOne as 1 at position 0", newGame.getScore()[0], is(1));
    }

    @Test
    public void shouldSubtractPointCaseOne() {
        newGame.addSetPoint(this.testPlayerOne);
        newGame.addSetPoint(this.testPlayerOne);
        newGame.subtractSetPoint(this.testPlayerOne);
        assertThat("Should set score of testPlayerOne from 2 to 1 at position 0", newGame.getScore()[0], is(1));
    }

    @Test
    public void shouldSubtractPointCaseTwo() {
        newGame.addSetPoint(this.testPlayerOne);
        newGame.addSetPoint(this.testPlayerOne);
        newGame.subtractSetPoint(this.testPlayerOne);
        newGame.subtractSetPoint(this.testPlayerOne);
        newGame.subtractSetPoint(this.testPlayerOne);
        assertThat("Should not subtract score below 0", newGame.getScore()[0], is(0));
    }

    @Test
    public void shouldResetGamePartOne() {
        newGame.__setCustomScore(5, 7);
        newGame.resetGame();
        assertThat("Should reset score to null", newGame.getScore()[0], is(0));
    }

    @Test
    public void shouldSetServingPlayerInitially() {
        assertThat("Should set servingPlayer to playerOne based on isPlayerOneBeginning", newGame.getServingPlayer(), is(this.testPlayerOne));
    }

    @Test
    public void shouldChangeServingPlayerAfterTwoServes() {
        newGame.addSetPoint(this.testPlayerOne);
        newGame.addSetPoint(this.testPlayerOne);
        assertThat("Should change servingPlayer after two serves from playerOne to playerTwo", newGame.getServingPlayer(), is(this.testPlayerTwo));
    }

    @Test
    public void shouldNotChangeServingPlayer() {
        newGame.addSetPoint(this.testPlayerOne);
        assertThat("Should not change servingPlayer if the score is not even", newGame.getServingPlayer(), is(this.testPlayerOne));
    }

    @Test
    public void shouldChangeServingPlayerBackToPlayerOne() {
        newGame.addSetPoint(this.testPlayerOne);
        newGame.addSetPoint(this.testPlayerOne);
        // --> here it will switch to player two
        newGame.subtractSetPoint(this.testPlayerOne);
        assertThat("Should change the servingPlayer back to playerOne after it switched to playerTwo and a point was subtracted", newGame.getServingPlayer(), is(this.testPlayerOne));
    }


    @Test
    public void shouldResetGame() {
        newGame.addSetPoint(this.testPlayerOne);
        newGame.addSetPoint(this.testPlayerOne);
        newGame.addSetPoint(this.testPlayerTwo);
        newGame.addSetPoint(this.testPlayerOne);
        newGame.addSetPoint(this.testPlayerTwo);
        newGame.addSetPoint(this.testPlayerOne);
        newGame.resetGame();
        assertThat("Should reset servingPlayer to playerOne based on isPlayerOneBeginning", newGame.getServingPlayer(), is(this.testPlayerOne));
    }

    @Test
    public void shouldCheckIfThereIsOvertime() {
        assertThat("Game is not in overtime", newGame.getIsOvertime(), is(false));
    }

    @Test
    public void shouldCheckIfThereIsOvertimePartTwo() {
        newGame.__setCustomScore(9, 9);
        assertThat("Game is not in overtime on 9:9", newGame.getIsOvertime(), is(false));

        newGame.__setCustomScore(10, 9);
        assertThat("Game is not in overtime on 10:9", newGame.getIsOvertime(), is(false));

        newGame.__setCustomScore(9, 10);
        assertThat("Game is not in overtime on 9:10", newGame.getIsOvertime(), is(false));
    }

    @Test
    public void shouldCheckIfThereIsOvertimePartThree() {
        newGame.__setCustomScore(10, 10);
        assertThat("Game is in overtime on 10:10", newGame.getIsOvertime(), is(true));

        newGame.__setCustomScore(11, 10);
        assertThat("Game is in overtime on 11:10", newGame.getIsOvertime(), is(true));

        newGame.__setCustomScore(10, 11);
        assertThat("Game is in overtime on 10:11", newGame.getIsOvertime(), is(true));
    }


    @Test
    public void shouldCheckIfGameIsOver() {
        assertThat("Game is not finished", newGame.getIsGameIsOver(), is(false));

        newGame.__setCustomScore(2, 6);
        assertThat("Game is not finished", newGame.getIsGameIsOver(), is(false));

        newGame.__setCustomScore(10, 10);
        assertThat("Game is not finished", newGame.getIsGameIsOver(), is(false));

        newGame.__setCustomScore(13, 12);
        assertThat("Game is not finished", newGame.getIsGameIsOver(), is(false));

        newGame.__setCustomScore(11, 9);
        assertThat("Game is finished", newGame.getIsGameIsOver(), is(true));

        newGame.__setCustomScore(14, 12);
        assertThat("Game is finished after overtime", newGame.getIsGameIsOver(), is(true));
    }

    @Test
    public void shouldSwitchServingPlayerOnEveryPointInOvertime() {
        // Set up to 10 points for player one
        newGame.addSetPoint(this.testPlayerOne);
        newGame.addSetPoint(this.testPlayerOne);
        newGame.addSetPoint(this.testPlayerOne);
        newGame.addSetPoint(this.testPlayerOne);
        newGame.addSetPoint(this.testPlayerOne);
        newGame.addSetPoint(this.testPlayerOne);
        newGame.addSetPoint(this.testPlayerOne);
        newGame.addSetPoint(this.testPlayerOne);
        newGame.addSetPoint(this.testPlayerOne);
        newGame.addSetPoint(this.testPlayerOne);

        // Set up to 10 points for player two
        newGame.addSetPoint(this.testPlayerTwo);
        newGame.addSetPoint(this.testPlayerTwo);
        newGame.addSetPoint(this.testPlayerTwo);
        newGame.addSetPoint(this.testPlayerTwo);
        newGame.addSetPoint(this.testPlayerTwo);
        newGame.addSetPoint(this.testPlayerTwo);
        newGame.addSetPoint(this.testPlayerTwo);
        newGame.addSetPoint(this.testPlayerTwo);
        newGame.addSetPoint(this.testPlayerTwo);
        newGame.addSetPoint(this.testPlayerTwo);

        // Score 10:10
        assertThat("At this point playerOne is serving again and overtime starts", newGame.getServingPlayer(), is(this.testPlayerOne));

        newGame.addSetPoint(this.testPlayerOne);
        // Score 11:10
        assertThat("In overtime, player two should now serve", newGame.getServingPlayer(), is(this.testPlayerTwo));

        newGame.addSetPoint(this.testPlayerTwo);
        // Score 11:11
        assertThat("In overtime, player one should now serve again", newGame.getServingPlayer(), is(this.testPlayerOne));

        newGame.addSetPoint(this.testPlayerTwo);
        // Score 11:12
        assertThat("In overtime, player two should now serve again", newGame.getServingPlayer(), is(this.testPlayerTwo));
    }

    @Test
    public void shouldReturnWinnerOfGame() {
        assertNull("There is no winner yet", newGame.getWinner());

        newGame.__setCustomScore(11, 8);
        assertThat("PlayerOne won the game with 11 points", newGame.getWinner(), is(this.testPlayerOne));

        newGame.__setCustomScore(10, 12);
        assertThat("PlayerTwo won the game with 12 points in overtime", newGame.getWinner(), is(this.testPlayerTwo));
    }

}
