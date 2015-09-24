package edu.gatech.cs2340.sharkbait.util;

import edu.gatech.cs2340.sharkbait.model.GameDuration;
import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by osama on 9/12/15.
 */
public class Player {

    private String name;
    private String color;
    private Race race;
    private double money;
    private double energy;
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
    public void changeEnergy(double amount) {
        energy += amount;
    }

    public double getEnergy() {
        return energy;
    }

    public List<Property> getProperties() {
        return properties;
    }

    public void setProperties(List<Property> properties) {
        this.properties = properties;
    }


// TODO: if round <= 2, do not decrement player money! (land grant)

    public boolean addProperty(Property property) {
        properties.add(property);
        if (GameDuration.getRound() > 2 && (money >= 300)) {
            this.changeMoney(-300);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return getName();
    }

    //    public boolean canBuyProperty(Property property) {
//
//    }
}
