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

    @Autowired
    private GameService gameService;


    @RequestMapping(path = "", method = RequestMethod.GET)
    public String getPlayers(Model model) {
        model.addAttribute("rightPlayer", gameService.getRightPlayer());
        model.addAttribute("leftPlayer", gameService.getLeftPlayer());
        model.addAttribute("servingPlayer", servingPlayer());
        return "current-game";
    }


    public String servingPlayer() {
        if (gameService.getRightPlayerServe()) {
            return "Serving Player: Philipp";

        } else if (gameService.getLeftPlayerServe()) {
            return "Serving Player: Nicola";
            // FIXME: names will be changed later
        }
        return "Um die Angabe";
    }

    @RequestMapping(path = "/score", method = RequestMethod.POST)
    @ResponseBody
    public String updateScorePlusOne(@RequestParam(value = "player", required = false) String player, Model model) {
        gameService.updateScore(new Message(player, 1));
        return "Score updated";
    }

    @RequestMapping(path = "/delete")
    @ResponseBody
    public String updateScoreDelete(@RequestParam(value = "player", required = false) String player, Model model) {
        gameService.updateScore(new Message(player, 2));
        return "Point deleted";
    }

    @RequestMapping(path = "/reset")
    @ResponseBody
    public String updateScoreReset(@RequestParam(value = "player", required = false) String player, Model model) {
        gameService.updateScore(new Message(player, 3));
        return "Score reset";
    }
}
