package edu.gatech.cs2340.sharkbait.controller;

import edu.gatech.cs2340.sharkbait.model.GameDuration;
import edu.gatech.cs2340.sharkbait.util.Player;
import edu.gatech.cs2340.sharkbait.util.Race;

/**
 * PlayerConfigController class.
 */
public final class PlayerConfigController {

  /**
   * Empty constructor.
   */
  private PlayerConfigController() {
  }

  /**
   * Creates a player.
   *
   * @param name  player name
   * @param color player color
   * @param race  player race
   */
  public static void createPlayer(
      final String name, final String color, final Race race) {
    Player player = new Player(name, color, race);
    GameDuration.addPlayer(player);
  }
}
