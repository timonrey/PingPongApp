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
    public Game getGame() {
        return match;
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
    public boolean getIfItsOvertime() {
        return match.isOvertime(getLeftPlayer(), getRightPlayer());
    }

    @Override
    public boolean getFirstServingPlayer() {
        return !match.isSomeoneServing();
    }
}

// {"button" : "1", "actionType": "1"}