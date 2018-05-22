package com.commercetools.pingpong.controller;

import com.commercetools.pingpong.model.ClientMessage;
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
        model.addAttribute("bubbleMessage", getBubbleMessage());
        model.addAttribute("leftServe", getIfLeftPlayerServes());
        model.addAttribute("rightServe", getIfRightPlayerServes());
        return "newScoreboard";
    }


    public String getBubbleMessage() {
        if (gameService.getFirstServingPlayer()) {
            return "Play for first serve!";

        } else if (gameService.getIfItsOvertime()) {
            return "Overtime!";
        }
        return "Play!";
    }

    public String getIfLeftPlayerServes() {
        if (gameService.getLeftPlayerServe()) {
            return "Serve!";

        } else if (gameService.getRightPlayerServe()) {
            return "";
        }
        return "";
    }

    public String getIfRightPlayerServes() {
        if (gameService.getRightPlayerServe()) {
            return "Serve!";
        } else if (gameService.getLeftPlayerServe()) {
            return "";
        }

        return "";
    }

    @RequestMapping(path = "/getupdate", method = RequestMethod.GET)
    @ResponseBody
    public String getUpdate() {
        ClientMessage clientMessage = updateService.getUpdate();
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
