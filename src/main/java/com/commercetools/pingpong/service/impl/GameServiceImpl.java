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
    // FIXME: These hardcoded values will be removed later

    @Override
    public void updateScore(final Message message) {
        Player actingPlayer = getPlayer(message);
        if (match.areBothSetScoresZero() && !match.isSomeoneServing()) {
            match.setFirstServe(actingPlayer);
        } else {
            match.updateScoreOfPlayer(actingPlayer, message.getActionType());
            match.updateMatchScoreOfPlayers();
            match.isGameOver();

        }

    }

    private Player getPlayer(final Message message) {
        if (message.getButtonId().equals("1") && match.isMatchScoreEven()) {
            return match.getPlayerOne();
        } else if (message.getButtonId().equals("2") && match.isMatchScoreEven()) {
            return match.getPlayerTwo();
        } else if (message.getButtonId().equals("1")) {
            return match.getPlayerTwo();
        } else {
            return match.getPlayerOne();
        }
    }

    @Override
    public Player getLeftPlayer() {
        return match.isMatchScoreEven() ? match.getPlayerOne() : match.getPlayerTwo();
    }

    @Override
    public Player getRightPlayer() {
        return match.isMatchScoreEven() ? match.getPlayerTwo() : match.getPlayerOne();
    }

    @Override
    public boolean getLeftPlayerServe() {return match.getPlayerOneServe();}

    @Override
    public boolean getRightPlayerServe() {return match.getPlayerTwoServe();}
}

// {"button" : "1", "actionType": "1"}