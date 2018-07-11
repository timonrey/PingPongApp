package com.commercetools.pingpong.service.impl;

import com.commercetools.pingpong.model.Game;
import com.commercetools.pingpong.model.Message;
import com.commercetools.pingpong.model.Player;
import com.commercetools.pingpong.service.GameService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class GameServiceImpl implements GameService {
    private Game match;

    public GameServiceImpl() {
        match = new Game(new Player("HeshamOne"), new Player("HeshamTwo"));
    }
    // FIXME: These hardcoded values will be removed later

    @Override
    public void updateScore(Message message) {
        Player actingPlayer = getPlayer(message);
        if (match.areBothSetScoresZero() && !match.isSomeoneServing()) {
            match.setFirstServe(actingPlayer);
        } else {
            match.updateScoreOfPlayer(actingPlayer, message.getActionType());
            match.updateMatchScoreOfPlayers();

            if (match.isMatchOver()) {
                match.gameOver();
            }

        }

    }

    private Player getPlayer(Message message) {
        if (message.getButtonId().equals("1") && match.isMatchScoreEven()) {
            return match.getLeftPlayer();
        } else if (message.getButtonId().equals("2") && match.isMatchScoreEven()) {
            return match.getRightPlayer();
        } else if (message.getButtonId().equals("1")) {
            return match.getRightPlayer();
        } else {
            return match.getLeftPlayer();
        }
    }

    @Override
    public Player getLeftPlayer() {
        return match.isMatchScoreEven() ? match.getLeftPlayer() : match.getRightPlayer();
    }

    @Override
    public Player getRightPlayer() {
        return match.isMatchScoreEven() ? match.getRightPlayer() : match.getLeftPlayer();
    }

    @Override
    public boolean getLeftPlayerServe() {
        return match.getPlayerOneServe();
    }

    @Override
    public boolean getRightPlayerServe() {
        return match.getPlayerTwoServe();
    }

    @Override
    public boolean getIfItsOvertime() {
        return match.isOvertime(getLeftPlayer(), getRightPlayer());
    }

    @Override
    public boolean getFirstServingPlayer() {
        return !match.isSomeoneServing();
    }
}

// {"button" : "1", "actionType": "1"}