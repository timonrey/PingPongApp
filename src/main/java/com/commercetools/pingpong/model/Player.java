package com.commercetools.pingpong.model;

public class Player {
    private String name;
    GameData gameData = new GameData();

    public Player() {

    }

    public Player(String name, int numberOfGamesWon, int totalPoints) {
    }

    public Player(String name) {
        this.name = name;
    }
}