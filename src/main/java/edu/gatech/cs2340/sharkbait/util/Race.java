package edu.gatech.cs2340.sharkbait.util;

import edu.gatech.cs2340.sharkbait.model.Packable;

import java.io.Serializable;

/**
 * Created on 9/12/15 by osama.
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
     * Race Constructor.
     * @param newName name of the race.
     */
    Race(final String newName) {
        /*
      String name.
     */
        //String name = newName;
    }

//    @Override
//    public String toString() {
//        return super.toString();
//    }
}
