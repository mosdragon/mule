package edu.gatech.cs2340.sharkbait.util;

import edu.gatech.cs2340.sharkbait.model.GameConfigs;
import edu.gatech.cs2340.sharkbait.model.GameDuration;
import edu.gatech.cs2340.sharkbait.model.Constants;
import edu.gatech.cs2340.sharkbait.model.Packable;
import edu.gatech.cs2340.trydent.log.Log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by osama on 9/12/15.
 */
public class Player implements Comparable<Player>, Serializable, Packable {
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
     * List<Mule> mules.
     */
    private List<Mule> mules;
    /**
     * List<Property> properties.
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
     * Player constructor.
     * @param color color of the player
     * @param name name of the player
     * @param race race of the player
     */
    public Player(final String name, final String color, final Race race) {
        this.name = name;
        this.color = color;
        this.race = race;

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
            money = 1600;

        } else if (race == Race.Human) {
            money = 600;

        } else {
            money = 1000;
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
     * @return name
     */
    public final String getName() {
        return name;
    }
    /**
     * setName setter.
     * @param name name of the player
     */
    public final void setName(final String name) {
        this.name = name;
    }
    /**
     * getColor getter.
     * @return color
     */
    public final String getColor() {
        return color;
    }
    /**
     * setColor setter.
     * @param color color of the player
     */
    public final void setColor(final String color) {
        this.color = color;
    }
    /**
     * getRace getter.
     * @return race
     */
    public final Race getRace() {
        return race;
    }
    /**
     * setRace setter.
     * @param race race of the player
     */
    public final void setRace(final Race race) {
        this.race = race;
    }
    /**
     * changeMoney method.
     * @param amount positive or negative amount of money
     */
    public final void changeMoney(final double amount) {
        money += amount;
    }
    /**
     * getMoney method.
     * @return money
     */
    public final double getMoney() {
        return money;
    }
    /**
     * changeEnergy method.
     * @param amount positive or negative amount of energy
     */
    public final void changeEnergy(final int amount) {
        energy += amount;
    }
    /**
     * getEnergy method.
     * @return energy
     */
    public final int getEnergy() {
        return energy;
    }
    /**
     * changeFood method.
     * @param amount positive or negative amount of food
     */
    public final void changeFood(final int amount) {
        food += amount;
    }
    /**
     * getFood method.
     * @return food
     */
    public final int getFood() {
        return food;
    }
    /**
     * changeOre method.
     * @param amount positive or negative amount of ore
     */
    public final void changeOre(final int amount) {
        ore += amount;
    }
    /**
     * getOre method.
     * @return ore
     */
    public final int getOre() {
        return ore;
    }
    /**
     * getMuleCount method.
     * @return number of mules
     */
    public final int getMuleCount() {
        return mules.size();
    }
    /**
     * getMules method.
     * @return list of mules
     */
    public final List<Mule> getMules() {
        return mules;
    }
    /**
     * addMule method.
     * @param mule mule to be added
     */
    public final void addMule(final Mule mule) {
        mules.add(mule);
    }
    /**
     * removeMule method.
     * @param mule mule to be removed
     */
    public final void removeMule(final Mule mule) {
        mules.remove(mule);
    }
    /**
     * Called at the beginning of every PlayerTurnPhase to update
     * counts of resources.
     */
    public final void handleProduction() {
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
    public final void unpack() {
        if (properties != null) {
            for (Property property : properties) {
                property.unpack();
            }
        }
    }
    /**
     * getProperties method.
     * @return properties
     */
    public final List<Property> getProperties() {
        return properties;
    }
    /**
     * purchaseProperties method.
     * @param property to be purchased
     * @return properties
     */
    public final boolean purchaseProperty(final Property property) {
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
    /**
     * sellProperty method.
     * @param property property to be sold.
     * @return boolean
     */
    public final boolean sellProperty(final Property property) {

        if (property.isOwner(this)) {
            properties.remove(property);
            changeMoney(LAND_COST);
            return true;
        }
        return false;
    }

    @Override
    public final String toString() {
        return getName();
    }

    @Override
    public int compareTo(final Player o) {
        Double myMoney = this.money;
        Double thatMoney = o.money;
        return myMoney.compareTo(thatMoney);
    }

}
