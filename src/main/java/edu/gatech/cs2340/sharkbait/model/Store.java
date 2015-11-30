package edu.gatech.cs2340.sharkbait.model;

import edu.gatech.cs2340.sharkbait.util.Difficulty;
import edu.gatech.cs2340.sharkbait.util.Player;
import edu.gatech.cs2340.sharkbait.util.Resource;
import edu.gatech.cs2340.trydent.log.Log;

import java.io.Serializable;

/**
 * Handles logistic of the store, selling/buying mules, energy,..etc.
 * Created on 9/22/15.
 *
 * @author Osama
 */
public final class Store implements Serializable, Packable {

  /**
   * the energy count of the store.
   */
  private int energyCount;
  /**
   * the mule count of the store.
   */
  private int muleCount;
  /**
   * the food count of the store.
   */
  private int foodCount;
  /**
   * the ore count of the store.
   */
  private int oreCount;
  /**
   * Energy constant for the store.
   */
  private static final int ENERGY = Constants.ENERGY;
  /**
   * food constant for store.
   */
  private static final int FOOD = Constants.FOOD;
  /**
   * ore constant for store.
   */
  private static final int ORE = Constants.ORE;
  /**
   * oreMule constant for store.
   */
  private static final int ORE_MULE = Constants.ORE_MULE;
  /**
   * foodMule constant for store.
   */
  private static final int FOOD_MULE = Constants.FOOD_MULE;
  /**
   * energyMule constant for store.
   */
  private static final int ENERGY_MULE = Constants.ENERGY_MULE;
  /**
   * creates instance of store.
   */
  private static transient Store instance;
  /**
   * Constant: initial energy.
   */
  private static final int INITIAL_ENERGY_BEGINNER = 16;
  /**
   * Constant: initial food.
   */
  private static final int INITIAL_FOOD_BEGINNER = 16;
  /**
   * Constant: initial ore.
   */
  private static final int INITIAL_ORE_BEGINNER = 0;
  /**
   * Constant: initial mules.
   */
  private static final int INITIAL_MULE_BEGINNER = 25;
  /**
   * Constant: money message.
   */
  private static final String NOT_ENOUGH_MONEY_MESSAGE = "Not enough money!";


  /**
   * constructor for  store.
   */
  private Store() {

  }

  /**
   * gets the instance of store.
   * if instance is null creates a new instance of store
   *
   * @return the instance of store
   */
  private static Store getInstance() {
    if (instance == null) {
      instance = new Store();
    }
    return instance;
  }

  /**
   * initializes store.
   */
  public static void initializeStore() {

    if (GameConfigs.getGameDifficulty()
        == Difficulty.Beginner) {
      getInstance().energyCount = INITIAL_ENERGY_BEGINNER;
      getInstance().foodCount = INITIAL_FOOD_BEGINNER;
      getInstance().oreCount = INITIAL_ORE_BEGINNER;
      getInstance().muleCount = INITIAL_MULE_BEGINNER;

//To do: ExtraCredit initial store amounts forStandard & Tournament difficulties
    } else {
      getInstance().energyCount = INITIAL_ENERGY_BEGINNER;
      getInstance().foodCount = INITIAL_FOOD_BEGINNER;
      getInstance().oreCount = INITIAL_ORE_BEGINNER;
      getInstance().muleCount = INITIAL_MULE_BEGINNER;
    }
  }

  /**
   * lets player buy energy.
   * decreases players money by cost of energy and
   * increments player Energy by one
   *
   * @param player player that is buying the energy
   */
  public static void buyEnergy(final Player player) {
    if (player.getMoney() >= (ENERGY) && hasEnergy()) {
      player.changeMoney(-ENERGY);
      player.changeEnergy(1);
      getInstance().energyCount--;
    } else {
      Log.debug(NOT_ENOUGH_MONEY_MESSAGE);
    }
  }

  /**
   * lets player sell energy.
   * increases players money by cost of energy and
   * decrements player Energy by one
   *
   * @param player player that is selling the energy
   */
  public static void sellEnergy(final Player player) {
    if (player.getEnergy() > 0) {
      player.changeEnergy(-1);
      player.changeMoney(ENERGY);
      getInstance().energyCount++;
    } else {
      Log.debug(NOT_ENOUGH_MONEY_MESSAGE);
    }
  }

  /**
   * lets player buy Mule.
   * decreases players money by cost of mule
   * decrements mule count from store
   *
   * @param player player that is buying the Mule
   * @param type   the type of mule to buy
   */
  public static void buyMule(final Player player, final Resource type) {
    boolean canAfford = false;
    Log.debug("Mule Type: " + type.toString());
    if (hasMules()) {
      if (type == Resource.Ore) {
        if (player.getMoney() >= ORE_MULE) {
          player.changeMoney(-ORE_MULE);
          canAfford = true;
        } else {
          Log.debug(NOT_ENOUGH_MONEY_MESSAGE);
        }
      } else if (type == Resource.Energy) {
        if (player.getMoney() >= ENERGY_MULE) {
          player.changeMoney(-ENERGY_MULE);
          canAfford = true;
        } else {
          Log.debug(NOT_ENOUGH_MONEY_MESSAGE);
        }
      } else {
        if (player.getMoney() >= FOOD_MULE) {
          player.changeMoney(-FOOD_MULE);
          canAfford = true;
        } else {
          Log.debug(NOT_ENOUGH_MONEY_MESSAGE);
        }
      }

      if (canAfford) {
        getInstance().muleCount--;
      }
    } else {
      Log.debug("Not enough mules");
    }
  }

  /**
   * lets player buy Food.
   * decreases player's money by cost of food
   * increments player's food count
   * decrements food count from store
   *
   * @param player player that is buying the food
   */
  public static void buyFood(final Player player) {
    if (player.getMoney() >= (FOOD) && hasFood()) {
      player.changeMoney(-FOOD);
      player.changeFood(1);
      getInstance().foodCount--;
    } else {
      Log.debug(NOT_ENOUGH_MONEY_MESSAGE);
    }
  }

  /**
   * lets player sell Food.
   * increases player's money by cost of food
   * decrements player's food count
   * increments food count in store
   *
   * @param player player that is selling the food
   */
  public static void sellFood(final Player player) {
    if (player.getFood() > 0) {
      player.changeFood(-1);
      player.changeMoney(FOOD);
      getInstance().foodCount++;
    } else {
      Log.debug("Not enough food!");
    }
  }

  /**
   * lets player buy Ore.
   * decreases players money by cost of ore
   * increments players ore count
   * decrements ore count from store
   *
   * @param player player that is buying the energy
   */
  public static void buyOre(final Player player) {
    if (player.getMoney() >= (ORE) && hasOre()) {
      player.changeMoney(-ORE);
      player.changeOre(1);
      getInstance().oreCount--;
    } else {
      Log.debug(NOT_ENOUGH_MONEY_MESSAGE);
    }
  }

  /**
   * lets player sell ore.
   * increases players money by cost of ore
   * decrements players ore count
   * increments ore count in store
   *
   * @param player player that is selling the ore
   */
  public static void sellOre(final Player player) {
    if (player.getOre() > 0) {
      player.changeOre(-1);
      player.changeMoney(ORE);
      getInstance().oreCount++;
    } else {
      Log.debug("Not enough Ore!");
    }
  }

  /**
   * determines if the store has mules left.
   *
   * @return true if store has mules left, false if store has no more mules
   */
  private static boolean hasMules() {
    return getInstance().muleCount > 0;
  }

  /**
   * determines if the store has energy left.
   *
   * @return true if store has energy left, false if store has no more energy
   */
  private static boolean hasEnergy() {
    return getInstance().energyCount > 0;
  }

  /**
   * determines if the store has ore left.
   *
   * @return true if store has ore left, false if store has no more ore
   */
  private static boolean hasOre() {
    return getInstance().oreCount > 0;
  }

  /**
   * determines if the store has food left.
   *
   * @return true if store has food left, false if store has no more food
   */
  private static boolean hasFood() {
    return getInstance().foodCount > 0;
  }

  /**
   * gets the energyCount of the store.
   *
   * @return the energyCount of the store
   */
  public static int getEnergyCount() {
    return getInstance().energyCount;
  }

  /**
   * gets the foodCount of the store.
   *
   * @return the foodCount of the store
   */
  public static int getFoodCount() {
    return getInstance().foodCount;
  }

  /**
   * gets the oreCount of the store.
   *
   * @return the oreCount of the store
   */
  public static int getOreCount() {
    return getInstance().oreCount;
  }

  /**
   * gets the muleCount of the store.
   *
   * @return the muleCount of the store
   */
  public static int getMuleCount() {
    return getInstance().muleCount;
  }

  /**
   * gets the cost of an ore.
   *
   * @return the cost of an ore
   */
  public static int getOreCost() {
    return ORE;
  }

  /**
   * gets the cost of food.
   *
   * @return the cost of food
   */
  public static int getFoodCost() {
    return FOOD;
  }

  /**
   * gets the cost of Energy.
   *
   * @return the cost of Energy
   */
  public static int getEnergyCost() {
    return ENERGY;
  }

  /**
   * gets the cost of an oreMule.
   *
   * @return the cost of an oreMule
   */
  public static int getOreMuleCost() {
    return ORE_MULE;
  }

  /**
   * gets the cost of a foodMule.
   *
   * @return the cost of a foodMule
   */
  public static int getFoodMuleCost() {
    return FOOD_MULE;
  }

  /**
   * gets the cost of an energyMule.
   *
   * @return the cost of an energyMule
   */
  public static int getEnergyMuleCost() {
    return ENERGY_MULE;
  }

  /**
   * Redefine the single instance of a singleton using the provided source.
   * @param source the source object.
   */
  private static void unpack(final Store source) {
    instance = source;
  }

  /**
   * Redefine the single instance of a singleton using
   * the provided source, which is JSON.
   * @param jsonSource a String representing the jsonSource
   */
  public static void unpackFromJson(final String jsonSource) {
    Store source = Packer.unpack(jsonSource, Store.class);
    unpack(source);
  }

  /**
   * Serialized instance as JSON.
   *
   * @return a JSON version of this object
   */
  public static String packAsJson() {
    return getInstance().pack();
  }

}
