package edu.gatech.cs2340.sharkbait.util;

import edu.gatech.cs2340.sharkbait.model.Packable;

import java.io.Serializable;

/**
 * Created by osama on 9/12/15.
 */
public enum Race implements Serializable, Packable {
    /**
     * Human.
     */
    Human("Human"),
    /**
     * Flapper.
     */
    Flapper("Flapper"),
    /**
     * Buzzite.
     */
    Buzzite("Buzzite"),
    /**
     * Ugaite.
     */
    Ugaite("Ugaite"),
    /**
     * Bonzoid.
     */
    Bonzoid("Bonzoid");
    /**
     * String name.
     */
    private String name;
    /**
     * Race Constructor.
     * @param newName name of the race.
     */
    Race(final String newName) {
        name = newName;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
