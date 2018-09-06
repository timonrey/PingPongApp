package com.commercetools.pingpong.newModel;

import com.commercetools.pingpong.model.Player;

public class NewPlayer {

    public String name;
    public NewMatch currentMatch;
    public NewGame currentGame;


    public NewPlayer(String playerName) {
        this.name = playerName;
    }

    public String getName() {
        return name;
    }
}
