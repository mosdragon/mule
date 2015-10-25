package edu.gatech.cs2340.sharkbait.util;

import java.io.Serializable;

/**
 * Created by osama on 9/22/15.
 */
public enum Resource implements Serializable {
    Energy("Energy"),
    Food("Food"),
    Ore("Ore"),
    Crystite("Crystite"),;

    private String name;

    Resource(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
