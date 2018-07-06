package com.commercetools.pingpong.controller;

import com.commercetools.pingpong.model.Message;
import com.commercetools.pingpong.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/game")
public class GameController {

    private GameService gameService;

    public GameController(@Autowired final GameService gameService) {
        this.gameService = gameService;
    }

    @RequestMapping(path = "", method = RequestMethod.GET)
    public String getPlayers(Model model) {
        model.addAttribute("leftPlayer", gameService.getLeftPlayer());
        model.addAttribute("rightPlayer", gameService.getRightPlayer());

        model.addAttribute("bubbleFirstServing", gameService.getFirstServingPlayer());
        model.addAttribute("bubbleCheckOvertime", gameService.getIfItsOvertime());

        return "newScoreboard";
    }

    @RequestMapping(path = "/score", method = RequestMethod.POST)
    @ResponseBody
    public String updateScorePlusOne(@RequestParam(value = "player", required = false) String player, Model model) {
        gameService.updateScore(new Message(player, 1));
        return "Point scored\n";
    }

    @RequestMapping(path = "/remove")
    @ResponseBody
    public String updateScoreDelete(@RequestParam(value = "player", required = false) String player, Model model) {
        gameService.updateScore(new Message(player, 2));
        return "Point removed\n";
    }

    @RequestMapping(path = "/reset")
    @ResponseBody
    public String updateScoreReset(@RequestParam(value = "player", required = false) String player, Model model) {
        gameService.updateScore(new Message(player, 3));
        return "Score reset\n";
    }
}
