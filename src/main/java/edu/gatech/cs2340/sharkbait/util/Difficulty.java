package edu.gatech.cs2340.sharkbait.util;

import edu.gatech.cs2340.sharkbait.model.Packable;

import java.io.Serializable;

/**
 * Created by osama on 9/12/15.
 */
public enum Difficulty implements Serializable, Packable {
    Beginner("Beginner"), Standard("Standard"), Tournament("Tournament");

    private String name;

    Difficulty(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
