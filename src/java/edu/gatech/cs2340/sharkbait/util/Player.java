package edu.gatech.cs2340.sharkbait.util;

import edu.gatech.cs2340.sharkbait.model.GameDuration;
import edu.gatech.cs2340.trydent.log.Log;
import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

/**
 * Created by osama on 9/12/15.
 */
public class Player implements Comparable<Player> {

    private String name;
    private String color;
    private Race race;
    private double money;
    private int energy;
    private int food;
    private int ore;
    private int mules;
    private List<Mule> ownedMules;
    private List<Property> properties;


    public Player(String name, String color, Race race) {
        this.name = name;
        this.color = color;
        this.race = race;

        if (race == Race.Flapper) {
            money = 1600;

        } else if (race == Race.Human) {
            money = 600;

        } else {
            money = 1000;
        }
        this.properties = new ArrayList<>();
        this.ownedMules = new ArrayList<>();
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
     * @param amount positive or negative amount of money
     */
    public void changeFood(int amount) {
        food += amount;
    }

    public int getFood() {
        return food;
    }

    public void changeOre(int amount) {
        ore += amount;
    }

    public int getOre() {
        return ore;
    }

    /**
     *
     * @param amount positive or negative amount of money
     */
    public void changeMules(int amount) {
        mules += amount;
    }

    public int getMules() {
        return mules;
    }

    public List<Mule> getOwnedMules() {
        return ownedMules;
    }

    public void addMule(List<Mule> mules, Mule mule) {
        mules.add(mule);
    }

    public void removeMule(List<Mule> mules, Mule mule) {
        mules.remove(mule);
    }

    public List<Property> getProperties() {
        return properties;
    }

    public void setProperties(List<Property> properties) {
        this.properties = properties;
    }

    public boolean addProperty(Property property) {
        if (GameDuration.getRound() < 2) {
            properties.add(property);
            return true;
        } else if (GameDuration.getRound() > 2 && (money >= 300)) {
            properties.add(property);
            this.changeMoney(-300);
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

    public void print() {
        String everything = "";
        everything += "Mules: "+ mules;
        everything += "\nFood: "+ food;
        everything += "\nEnergy: "+ energy;
        everything += "\nOre: " + ore;
        everything += "\n"+ name;
        Log.debug(everything);
    }

}
