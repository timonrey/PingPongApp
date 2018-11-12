package com.commercetools.pingpong.service;

import com.commercetools.pingpong.model.Message;
import com.commercetools.pingpong.model.Game;
import com.commercetools.pingpong.model.Match;
import org.springframework.stereotype.Service;

@Service
public interface GameService {

    void updateScore(Message message);

    void killMatch();

    Game getGame();

    Match getMatch();

    boolean hasMatchStarted();

}