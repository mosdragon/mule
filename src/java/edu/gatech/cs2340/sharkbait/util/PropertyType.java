package edu.gatech.cs2340.sharkbait.util;

/**
 * Created by arihanshah on 9/22/15.
 */
public enum PropertyType {
    Mountain("Mountain"),
    Plains("Plains"),
    River("River"),;

    private String name;

    PropertyType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
