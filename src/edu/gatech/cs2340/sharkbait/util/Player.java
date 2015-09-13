package edu.gatech.cs2340.sharkbait.util;

import javafx.scene.paint.Color;

/**
 * Created by osama on 9/12/15.
 */
public class Player {

    private String name;
    private Color color;
    private Race race;

    public Player(String name, Color color, Race race) {
        this.name = name;
        this.color = color;
        this.race = race;
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


}
