package edu.gatech.cs2340.sharkbait.controller;

import edu.gatech.cs2340.sharkbait.model.GameDuration;
import edu.gatech.cs2340.sharkbait.model.Store;
import edu.gatech.cs2340.sharkbait.util.Player;
import edu.gatech.cs2340.sharkbait.util.Resource;
import edu.gatech.cs2340.trydent.log.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Controls actions taken on the @link{TownMapView}.
 */
public final class TownMapController {

  /**
   * Bonus multiplier for each round.
   */
  private static final List<Integer> ROUND_BONUS = new ArrayList<>(Arrays
      .asList(0, 50, 50, 50, 100, 100, 100, 100, 150, 150, 150, 150, 200));

  /**
   * Private constructor to prevent instantiation.
   */
  private TownMapController() {

  }

  /**
   * Enter the pub to win money and end turn.
   */
  public static void enterPub() {
    Random rand = new Random();
    int timeRemaining = GameDuration.getTimeRemaining();
    int timeBonus = rand.nextInt(timeRemaining + 1);

    Player player = GameDuration.getActivePlayer();

    int moneyBonus = ROUND_BONUS.get(GameDuration.getRound()) * timeBonus;
    Log.debug("Bonus: " + moneyBonus);
    player.changeMoney(moneyBonus);

    MasterController.changeSceneToGameMap();
    GameDuration.endTurn();

  }

  /**
   * Purchase ore from store.
   */
  public static void buyOre() {
    Player activePlayer = GameDuration.getActivePlayer();
    Store.buyOre(activePlayer);
  }

  /**
   * Sell ore to store.
   */
  public static void sellOre() {
    Player activePlayer = GameDuration.getActivePlayer();
    Store.sellOre(activePlayer);
  }

  /**
   * Purchase energy from store.
   */
  public static void buyEnergy() {
    Player activePlayer = GameDuration.getActivePlayer();
    Store.buyEnergy(activePlayer);
  }

  /**
   * Sell energy from store.
   */
  public static void sellEnergy() {
    Player activePlayer = GameDuration.getActivePlayer();
    Store.sellEnergy(activePlayer);
  }

  /**
   * Buy food.
   */
  public static void buyFood() {
    Player activePlayer = GameDuration.getActivePlayer();
    Store.buyFood(activePlayer);
  }

  /**
   * Sell food.
   */
  public static void sellFood() {
    Player activePlayer = GameDuration.getActivePlayer();
    Store.sellFood(activePlayer);
  }

  /**
   * Mule gets bought. Change GameState.
   */
  private static void purchasedMule() {
    GameDuration.beginMulePlacementPhase();
    MasterController.changeSceneToGameMap();
  }


  /**
   * Buy ore mule.
   */
  public static void buyOreMule() {
    Player activePlayer = GameDuration.getActivePlayer();
    Store.buyMule(activePlayer, Resource.Ore);
    GameDuration.setActiveMuleType(Resource.Ore);
    purchasedMule();
  }

  /**
   * Sell ore mule.
   */
  public static void sellOreMule() {
    //Player activePlayer = GameDuration.getActivePlayer();
    //Store.sellMule(activePlayer, Resource.Ore);
    GameDuration.setActiveMuleType(Resource.Ore);
  }

  /**
   * Buy food mule.
   */
  public static void buyFoodMule() {
    Player activePlayer = GameDuration.getActivePlayer();
    Store.buyMule(activePlayer, Resource.Food);
    GameDuration.setActiveMuleType(Resource.Food);
    purchasedMule();
  }

  /**
   * Sell food mule.
   */
  public static void sellFoodMule() {
    //Player activePlayer = GameDuration.getActivePlayer();
    //Store.sellMule(activePlayer, Resource.Food);
    GameDuration.setActiveMuleType(Resource.Food);
  }

  /**
   * Buy energy mule.
   */
  public static void buyEnergyMule() {
    Player activePlayer = GameDuration.getActivePlayer();
    Store.buyMule(activePlayer, Resource.Energy);
    GameDuration.setActiveMuleType(Resource.Energy);
    purchasedMule();
  }

  /**
   * Sell energy mule.
   */
  public static void sellEnergyMule() {
    //Player activePlayer = GameDuration.getActivePlayer();
    //Store.sellMule(activePlayer, Resource.Energy);
    GameDuration.setActiveMuleType(Resource.Energy);
  }

  /**
   * Leave town without ending turn.
   */
  public static void exitTown() {
    MasterController.changeSceneToGameMap();
  }
}
