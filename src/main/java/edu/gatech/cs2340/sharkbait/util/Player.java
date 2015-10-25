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

    private String name;
    private String color;
    private Race race;
    private double money;
    private int energy;
    private int food;
    private int ore;
    private List<Mule> mules;
    private List<Property> properties;

    private static final int LAND_COST = Constants.LAND;
    private static final int ENERGY = Constants.ENERGY;
    private static final int FOOD = Constants.FOOD;
    private static final int ORE = Constants.ORE;
    private static final int MULE = Constants.MULE;
    private static final int ORE_MULE = Constants.ORE_MULE;
    private static final int FOOD_MULE = Constants.FOOD_MULE;
    private static final int ENERGY_MULE = Constants.ENERGY_MULE;

    private static final int MIN_PRODUCTION_ENERGY = 1;

    private static final int BEGINNER_FOOD = 8;
    private static final int BEGINNER_ENERGY = 4;
    private static final int BEGINNER_ORE = 0;

    private static final int STANDARD_FOOD = 4;
    private static final int STANDARD_ENERGY = 2;
    private static final int STANDARD_ORE = 0;

    private static final int TOURNAMENT_FOOD = 4;
    private static final int TOURNAMENT_ENERGY = 2;
    private static final int TOURNAMENT_ORE = 0;


    public Player(String name, String color, Race race) {
        this.name = name;
        this.color = color;
        this.race = race;

        initializeMoney();
        initializeResources();

        this.properties = new ArrayList<>();
        this.mules = new ArrayList<>();
    }

    private void initializeMoney() {
        if (race == Race.Flapper) {
            money = 1600;

        } else if (race == Race.Human) {
            money = 600;

        } else {
            money = 1000;
        }
    }

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    /**
     *
     * @param amount positive or negative amount of money
     */
    public void changeMoney(double amount) {
        money += amount;
    }

    public double getMoney() {
        return money;
    }

    /**
     *
     * @param amount positive or negative amount of energy
     */
    public void changeEnergy(int amount) {
        energy += amount;
    }

    public int getEnergy() {
        return energy;
    }

    /**
     *
     * @param amount positive or negative amount of food
     */
    public void changeFood(int amount) {
        food += amount;
    }

    public int getFood() {
        return food;
    }

    /**
     *
     * @param amount positive or negative amount of ore
     */
    public void changeOre(int amount) {
        ore += amount;
    }

    public int getOre() {
        return ore;
    }

    public int getMuleCount() {
        return mules.size();
    }

    public List<Mule> getMules() {
        return mules;
    }

    public void addMule(Mule mule) {
        mules.add(mule);
    }

    public void removeMule(Mule mule) {
        mules.remove(mule);
    }

    /**
     * Called at the beginning of every PlayerTurnPhase to update counts of resources
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

    public List<Property> getProperties() {
        return properties;
    }

    public boolean purchaseProperty(Property property) {
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

    public boolean sellProperty(Property property) {

        if (property.isOwner(this)) {
            properties.remove(property);
            changeMoney(LAND_COST);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return getName();
    }

    @Override
    public int compareTo(Player o) {
        Double myMoney = this.money;
        Double thatMoney = o.money;
        return myMoney.compareTo(thatMoney);
    }

}
