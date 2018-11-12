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

        if (!gameService.hasMatchStarted()) {
            return "pickASide";

        } else if (gameService.getMatch().isMatchOver()) {
            return "winnerSide";
        }

        model.addAttribute("game", gameService.getGame());

        model.addAttribute("leftSetSore", gameService.getGame().getScore()[0]);
        model.addAttribute("rightSetSore", gameService.getGame().getScore()[1]);

        model.addAttribute("match", gameService.getMatch());

        if (gameService.getMatch().isPlayerOneOnTheLeftSide()) {
            model.addAttribute("leftMatchScore", gameService.getMatch().getMatchPoints()[0]);
            model.addAttribute("rightMatchScore", gameService.getMatch().getMatchPoints()[1]);

        } else {
            model.addAttribute("leftMatchScore", gameService.getMatch().getMatchPoints()[1]);
            model.addAttribute("rightMatchScore", gameService.getMatch().getMatchPoints()[0]);
        }
        return "newScoreboard";
    }

    @RequestMapping(path = "/score", method = RequestMethod.POST)
    @ResponseBody
    public String updateScorePlusOne(@RequestParam(value = "button", required = false) String playerId, Model model) {
        gameService.updateScore(new Message(playerId, 1));
        return "Point scored\n";
    }

    @RequestMapping(path = "/remove")
    @ResponseBody
    public String updateScoreMinusOne(@RequestParam(value = "button", required = false) String playerId, Model model) {
        gameService.updateScore(new Message(playerId, 2));
        return "Point removed\n";
    }

    @RequestMapping(path = "/reset")
    @ResponseBody
    public String updateScoreReset(@RequestParam(value = "button", required = false) String playerId, Model model) {
        gameService.updateScore(new Message(playerId, 3));
        return "Score reset\n";
    }

    @RequestMapping(path = "/kill")
    @ResponseBody
    public String startNewMatch(@RequestParam(value = "button", required = false) String playerId, Model model) {
        gameService.killMatch();
        return "Match killed\n";
    }
}
