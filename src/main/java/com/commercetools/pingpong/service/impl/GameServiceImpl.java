package com.commercetools.pingpong.service.impl;

import com.commercetools.pingpong.model.Message;
import com.commercetools.pingpong.model.Game;
import com.commercetools.pingpong.model.Match;
import com.commercetools.pingpong.model.Player;
import com.commercetools.pingpong.service.GameService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class GameServiceImpl implements GameService {
    private Match match;

    Player playerOne;
    Player playerTwo;

    public GameServiceImpl() {
        this.playerOne = new Player("Nicola");
        this.playerTwo = new Player("Timon");

        match = new Match(this.playerOne, this.playerTwo);
    }

    public void killMatch() {
        match = new Match(this.playerOne, this.playerTwo);
    }

    public void updateScore(Message message) {
        Player actingPlayer = getPlayer(message);
        if (message.getActionType() == 1) {
            addScore(actingPlayer);

        } else if (message.getActionType() == 2) {
            removeScore(actingPlayer);

        } else if (message.getActionType() == 3) {
            resetScore();

        } else {
            throw new Error("Expected ActionType to be 1, 2 or 3. Got instead: " + message.getActionType());
        }
    }


// todo: Check if match is over and do something else

    public void addScore(Player actingPlayer) {
        if (match.getNumberOfGames() == 0) {
            match.start(actingPlayer);

        } else {
            match.addGamePoint(actingPlayer);
        }


        /*
        if (match.areBothSetScoresZero() && !match.isSomeoneServing()) {
            match.setFirstServe(actingPlayer);
        } else {
            match.updateScoreOfPlayer(actingPlayer, message.getActionType());
            match.updateMatchScoreOfPlayers();

            if (match.isMatchOver()) {
                match.gameOver();
            }

        }
 */
    }

    public void removeScore(Player actingPlayer) {
        if (match.getNumberOfGames() != 0) {
            match.subtractGamePoint(actingPlayer);
        }
    }

    public void resetScore() {
        if (match.getNumberOfGames() != 0) {
            match.resetGamePoint();
        }
    }

    private Player getPlayer(Message message) {
        if (message.getButtonId().equals("1") && match.isPlayerOneOnTheLeftSide()) {
            return this.playerOne;
        } else if (message.getButtonId().equals("2") && match.isPlayerOneOnTheLeftSide()) {
            return this.playerTwo;
        } else if (message.getButtonId().equals("1")) {
            return this.playerTwo;
        } else {
            return this.playerOne;
        }
    }

    @Override
    public Game getGame() {
        return match.getCurrentGame();
    }

    @Override
    public Match getMatch() {
        return  match;
    }

    @Override
    public boolean hasMatchStarted() {
        return match.getNumberOfGames() != 0;
    }
}
