package edu.gatech.cs2340.sharkbait.controller;

import edu.gatech.cs2340.sharkbait.model.GameDuration;
import edu.gatech.cs2340.sharkbait.util.Player;
import edu.gatech.cs2340.sharkbait.util.Race;

/**
 * Created by osama on 10/7/15.
 */
public class PlayerConfigController {

    public static void createPlayer(String name, String color, Race race) {
        Player player = new Player(name, color, race);
        GameDuration.addPlayer(player);
    }
}
