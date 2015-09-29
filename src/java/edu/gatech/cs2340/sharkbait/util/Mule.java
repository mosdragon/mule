package edu.gatech.cs2340.sharkbait.util;

/**
 * Created by osama on 9/22/15.
 */
public class Mule {

    private Resource type;
    private Player owner;

    public Mule (Player owner, Resource type) {
        this.owner = owner;
        this.type = type;
    }
}
