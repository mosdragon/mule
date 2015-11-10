package edu.gatech.cs2340.sharkbait.util;

import edu.gatech.cs2340.sharkbait.model.Constants;
import edu.gatech.cs2340.sharkbait.model.GameConfigs;
import edu.gatech.cs2340.sharkbait.model.GameDuration;
import edu.gatech.cs2340.sharkbait.model.Packable;
import edu.gatech.cs2340.trydent.log.Log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created on 9/12/15 by osama.
 */
public final class Player implements Comparable<Player>, Serializable,
    Packable {
  /**
   * String name.
   */
  private String name;
  /**
   * String color.
   */
  private String color;
  /**
   * Race race.
   */
  private Race race;
  /**
   * double money.
   */
  private double money;
  /**
   * int energy.
   */
  private int energy;
  /**
   * int food.
   */
  private int food;
  /**
   * int ore.
   */
  private int ore;

  /**
   * List mules.
   */
  private List<Mule> mules;

  /**
   * List properties.
   */
  private List<Property> properties;
  /**
   * int LAND_COST.
   */
  private static final int LAND_COST = Constants.LAND;

  /**
   * int ENERGY.
   */
  private static final int ENERGY = Constants.ENERGY;

  /**
   * int FOOD.
   */
  private static final int FOOD = Constants.FOOD;

  /**
   * int ORE.
   */
  private static final int ORE = Constants.ORE;

  /**
   * int MULE.
   */
  private static final int MULE = Constants.MULE;

  /**
   * int ORE_MULE.
   */
  private static final int ORE_MULE = Constants.ORE_MULE;

  /**
   * int FOOD_MULE.
   */
  private static final int FOOD_MULE = Constants.FOOD_MULE;

  /**
   * int ENERGY_MULE.
   */
  private static final int ENERGY_MULE = Constants.ENERGY_MULE;

  /**
   * int MIN_PRODUCTION_ENERGY.
   */
  private static final int MIN_PRODUCTION_ENERGY = 1;

  /**
   * int BEGINNER_FOOD.
   */
  private static final int BEGINNER_FOOD = 8;

  /**
   * int BEGINNER_ENERGY.
   */
  private static final int BEGINNER_ENERGY = 4;

  /**
   * int BEGINNER_ORE.
   */
  private static final int BEGINNER_ORE = 0;

  /**
   * int STANDARD_FOOD.
   */
  private static final int STANDARD_FOOD = 4;

  /**
   * int STANDARD_ENERGY.
   */
  private static final int STANDARD_ENERGY = 2;
  /**
   * int STANDARD_ORE.
   */
  private static final int STANDARD_ORE = 0;
  /**
   * int TOURNAMENT_FOOD.
   */
  private static final int TOURNAMENT_FOOD = 4;

  /**
   * int TOURNAMENT_ENERGY.
   */
  private static final int TOURNAMENT_ENERGY = 2;

  /**
   * int TOURNAMENT_ORE.
   */
  private static final int TOURNAMENT_ORE = 0;

  /**
   * int FLAPPER_MONEY.
   */
  private static final int FLAPPER_MONEY = 1600;

  /**
   * int HUMAN_MONEY.
   */
  private static final int HUMAN_MONEY = 600;

  /**
   * int MONEY.
   */
  private static final int MONEY = 1600;

  /**
   * Player constructor.
   *
   * @param newColor color of the player
   * @param newName  name of the player
   * @param newRace  race of the player
   */
  public Player(final String newName, final String newColor,
                final Race newRace) {
    name = newName;
    color = newColor;
    race = newRace;

    initializeMoney();
    initializeResources();

    this.properties = new ArrayList<>();
    this.mules = new ArrayList<>();
  }

  /**
   * initializeMoney method.
   */
  private void initializeMoney() {
    if (race == Race.Flapper) {
      money = FLAPPER_MONEY;

    } else if (race == Race.Human) {
      money = HUMAN_MONEY;

    } else {
      money = MONEY;
    }
  }

  /**
   * initializeResources method.
   */
  private void initializeResources() {
    Difficulty difficulty = GameConfigs.getGameDifficulty();

    if (difficulty == Difficulty.Beginner) {
      food = BEGINNER_FOOD;
      energy = BEGINNER_ENERGY;
      ore = BEGINNER_ORE;

    } else if (difficulty == Difficulty.Standard) {
      food = STANDARD_FOOD;
      energy = STANDARD_ENERGY;
      ore = STANDARD_ORE;

    } else if (difficulty == Difficulty.Tournament) {
      food = TOURNAMENT_FOOD;
      energy = TOURNAMENT_ENERGY;
      ore = TOURNAMENT_ORE;
    }
  }

  /**
   * getName getter.
   *
   * @return name
   */
  public String getName() {
    return name;
  }

  /**
   * setName setter.
   *
   * @param nameInput name of the player
   */
  public void setName(final String nameInput) {
    name = nameInput;
  }

  /**
   * getColor getter.
   *
   * @return color
   */
  public String getColor() {
    return color;
  }

  /**
   * setColor setter.
   * @param colorInput color of the player
   */
  public void setColor(final String colorInput) {
    color = colorInput;
  }

  /**
   * getRace getter.
   *
   * @return race
   */
  public Race getRace() {
    return race;
  }

  /**
   * setRace setter.
   *
   * @param raceInput race of the player
   */
  public void setRace(final Race raceInput) {
    race = raceInput;
  }

  /**
   * changeMoney method.
   *
   * @param amount positive or negative amount of money
   */
  public void changeMoney(final double amount) {
    money += amount;
  }

  /**
   * getMoney method.
   *
   * @return money
   */
  public double getMoney() {
    return money;
  }

  /**
   * changeEnergy method.
   *
   * @param amount positive or negative amount of energy
   */
  public void changeEnergy(final int amount) {
    energy += amount;
  }

  /**
   * getEnergy method.
   *
   * @return energy
   */
  public int getEnergy() {
    return energy;
  }

  /**
   * changeFood method.
   *
   * @param amount positive or negative amount of food
   */
  public void changeFood(final int amount) {
    food += amount;
  }

  /**
   * getFood method.
   *
   * @return food
   */
  public int getFood() {
    return food;
  }

  /**
   * changeOre method.
   *
   * @param amount positive or negative amount of ore
   */
  public void changeOre(final int amount) {
    ore += amount;
  }

  /**
   * getOre method.
   *
   * @return ore
   */
  public int getOre() {
    return ore;
  }

  /**
   * getMuleCount method.
   *
   * @return number of mules
   */
  public int getMuleCount() {
    return mules.size();
  }

  /**
   * getMules method.
   *
   * @return list of mules
   */
  public List<Mule> getMules() {
    return mules;
  }

  /**
   * addMule method.
   *
   * @param mule mule to be added
   */
  public void addMule(final Mule mule) {
    mules.add(mule);
  }

  /**
   * Called at the beginning of every PlayerTurnPhase to update
   * counts of resources.
   */
  public void handleProduction() {
    Log.debug("Computing Production");
    if (getEnergy() >= MIN_PRODUCTION_ENERGY) {
      for (Mule mule : mules) {
        mule.handleProduction(this);
      }
    } else {
      Log.debug("Not enough energy for production");
    }
  }

  @Override
  public void unpack() {
    if (properties != null) {
      for (Property property : properties) {
        property.unpack();
      }
    }
  }

  /**
   * getProperties method.
   *
   * @return the properties.
   */
  public List<Property> getProperties() {
    return properties;
  }

  /**
   * purchaseProperties method.
   *
   * @param property to be purchased.
   * @return true if property bought, otherwise false
   */
  public boolean purchaseProperty(final Property property) {
    if (GameDuration.getRound() <= 2) {
      properties.add(property);
      property.purchase(this);
      return true;
    } else if (GameDuration.getRound() > 2 && (money >= LAND_COST)) {
      properties.add(property);
      property.purchase(this);
      this.changeMoney(-LAND_COST);
      return true;
    }
    return false;
  }

  @Override
  public String toString() {
    return getName();
  }

  @Override
  public int compareTo(final Player object) {
    Double myMoney = this.money;
    Double thatMoney = object.money;
    return myMoney.compareTo(thatMoney);
  }

  @Override
  public boolean equals(final Object obj) {
    if (!(obj instanceof Player)) {
      return false;
    }
    Player other = (Player) obj;
    return name.equals(other.name) && color.equals(other.color)
        && race.equals(other.race) && money == other.money;
  }

  @Override
  public int hashCode() {
    final int prime = 41;
    int multiplier = 0;
    return name.hashCode() * ((int) Math.pow(prime, ++multiplier))
        + color.hashCode() * ((int) Math.pow(prime, ++multiplier))
        + race.hashCode() * ((int) Math.pow(prime, ++multiplier))
        + (int) (money * Math.pow(prime, ++multiplier));
  }
}
