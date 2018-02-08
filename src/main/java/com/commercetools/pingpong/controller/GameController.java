package com.commercetools.pingpong.controller;

import com.commercetools.pingpong.model.Message;
import com.commercetools.pingpong.model.Player;
import com.commercetools.pingpong.service.GameService;
import com.sun.tools.javac.util.BasicDiagnosticFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/game")
public class GameController {

    @Autowired
    private GameService gameService;


    @RequestMapping(path = "", method = RequestMethod.GET)
    public String getPlayers(Model model) {
        model.addAttribute("rightPlayer", gameService.getRightPlayer());
        model.addAttribute("leftPlayer", gameService.getLeftPlayer());
        return "current-game";
    }

    @RequestMapping(path = "/score")
    public void updateScorePlusOne(@RequestParam(value="player", required = false) String player, Model model) {
        gameService.updateScore(new Message(player, 1));
    }

    @RequestMapping(path = "/delete")
    public void updateScoreDelete(@RequestParam(value="player", required = false) String player, Model model) {
        gameService.updateScore(new Message(player, 2));
    }

    @RequestMapping(path = "/reset")
    public void updateScoreReset(@RequestParam(value="player", required = false) String player, Model model) {
        gameService.updateScore(new Message(player, 3));
    }
}
