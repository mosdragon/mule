package edu.gatech.cs2340.sharkbait.util;

import edu.gatech.cs2340.sharkbait.model.Packable;

import java.io.Serializable;

/**
 * Resource class
 * Created on 9/22/15.
 * @author Osama
 */
public enum Resource implements Serializable, Packable {
    /**
     * sets Energy to "Energy".
     */
    Energy("Energy"),
    /**
     * sets Food to "food".
     */
    Food("Food"),
    /**
     * sets ore to "Ore".
     */
    Ore("Ore");

    /**
     * Constructs a Resource with a String name.
     * @param name the name of the Resource
     */
    Resource(final String name) {
    }

}
