package com.commercetools.pingpong.controller;

import com.commercetools.pingpong.model.Player;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/list")
public class PlayerListController {

    @RequestMapping(path = "", method = RequestMethod.GET)
    public String get(@RequestParam(value="id", required = false) String id, Model model) {
        final Player player1 = new Player("Andi", 12, 265);
        final Player player2 = new Player("Roman", 12, 264);
        final Player player3 = new Player("Hasan", 11, 242);
        final Player player4 = new Player("Hesham", 10, 220);
        // FIXME: Hardcoded players will be replaced later with real players

        final List<Player> players = Arrays.asList(player1, player2, player3, player4);

        if (id == null) {
            model.addAttribute("players", players);
        } else {
            final int playerIndex = players.indexOf(id);
            model.addAttribute("players", players.get(playerIndex));
        }
        return "leaderboard";
    }
}

