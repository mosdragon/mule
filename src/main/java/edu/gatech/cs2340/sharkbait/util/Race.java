package edu.gatech.cs2340.sharkbait.util;

import edu.gatech.cs2340.sharkbait.model.Packable;

import java.io.Serializable;

/**
 * Created by osama on 9/12/15.
 */
public enum Race implements Serializable, Packable {
    Human("Human"), Flapper("Flapper"), Buzzite("Buzzite"), Ugaite("Ugaite"),
    Bonzoid("Bonzoid"), Alien("Alien"), CollegeStudent("CollegeStudent");

    private String name;

    Race(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
