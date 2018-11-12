package com.commercetools.pingpong.controller;

import org.junit.Test;

public class GameControllerTest {

    @Test
    public void shouldReturnBubbleMessage() {
        Player playerTestOne = new Player("Bob");

        playerTestOne.setBeginningServe();

        Player playerTestTwo = new Player("Kelso");

        Game matchTest = new Game(playerTestOne, playerTestTwo);
        //GameController gameControllerTest = new GameController();

    }
}

