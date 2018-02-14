package com.commercetools.pingpong.controller;

        import com.commercetools.pingpong.model.Message;
        import com.commercetools.pingpong.service.GameService;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.http.HttpStatus;
        import org.springframework.stereotype.Controller;
        import org.springframework.ui.Model;
        import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(path = "/score", method = RequestMethod.POST)
    @ResponseBody
    public String updateScorePlusOne(@RequestParam(value="player", required = false) String player, Model model) {
        gameService.updateScore(new Message(player, 1));
        return "Score updated";
    }

    @RequestMapping(path = "/delete")
    @ResponseBody
    public String updateScoreDelete(@RequestParam(value="player", required = false) String player, Model model) {
        gameService.updateScore(new Message(player, 2));
        return "Point deleted";
    }

    @RequestMapping(path = "/reset")
    @ResponseBody
    public String updateScoreReset(@RequestParam(value="player", required = false) String player, Model model) {
        gameService.updateScore(new Message(player, 3));
        return "Score reseted";
    }
}
