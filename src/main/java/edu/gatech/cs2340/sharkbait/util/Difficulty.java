package edu.gatech.cs2340.sharkbait.util;

/**
 * Created by osama on 9/12/15.
 */
public enum Difficulty {
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
