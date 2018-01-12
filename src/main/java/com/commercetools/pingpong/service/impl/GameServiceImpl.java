package com.commercetools.pingpong.service.impl;

import com.commercetools.pingpong.model.Game;
import com.commercetools.pingpong.model.Message;
import com.commercetools.pingpong.model.Player;
import com.commercetools.pingpong.service.GameService;
import org.springframework.stereotype.Component;

@Component
public class GameServiceImpl implements GameService{
    private Game match;

    public GameServiceImpl() {
        this.match = new Game(new Player("HeshamOne"), new Player("HeshamTwo"));
    }

    @Override
    public void updateScore(final Message message) {
        Player actingPlayer = getPlayer(message);
        match.updateScoreOfPlayer(actingPlayer, message.getActionType());

        if (match.hasSomebodyWon()) {
            match.updateMatchScoreOfPlayers();
        }
    }

    private Player getPlayer(final Message message) {
        if (message.getButtonId().equals("1") && match.isSetScoreEven()) {
            return match.getPlayerOne();
        } else if (message.getButtonId().equals("2") && match.isSetScoreEven()) {
            return match.getPlayerTwo();
        } else if (message.getButtonId().equals("1")) {
            return match.getPlayerTwo();
        } else {
            return match.getPlayerOne();
        }
    }

    @Override
    public Player getLeftPlayer() {
        return match.isSetScoreEven() ? match.getPlayerOne() : match.getPlayerTwo();
    }

    @Override
    public Player getRightPlayer() {
        return match.isSetScoreEven() ? match.getPlayerTwo() : match.getPlayerOne();
    }
}

// {"button" : "1", "actionType": "1"}