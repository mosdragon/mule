package edu.gatech.cs2340.sharkbait.util;

import edu.gatech.cs2340.sharkbait.model.GameDuration;
import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by osama on 9/12/15.
 */
public class Player {

    private String name;
    private Color color;
    private Race race;
    private double money;
    private double energy;

    ArrayList<Property> playerProperties;

    public Player(String name, Color color, Race race) {
        this.name = name;
        this.color = color;
        this.race = race;
        if (Objects.equals(race.toString(), "Flapper")) {
            money = 1600;
        } else if (Objects.equals(race.toString(), "Human")) {
            money = 600;
        } else if (Objects.equals(race.toString(), "Others")){
            money = 1000;
        }
        this.playerProperties = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
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

// TODO: if round <= 2, do not decrement player money! (land grant)
    public void addProperty(Property property) {
        playerProperties.add(property);
        // if (GameDuration.getRound() > 2) {
        //      player.changeMoney(property.price());
        // }
    }





}
