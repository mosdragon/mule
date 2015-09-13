package edu.gatech.cs2340.sharkbait;

/**
 * Created by osama on 9/12/15.
 */
public class Player {

    private String name;
    private String color;
    private Race race;

    public Player(String name, String color, Race race) {
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


}
