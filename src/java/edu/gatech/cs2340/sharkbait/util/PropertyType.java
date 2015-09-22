package edu.gatech.cs2340.sharkbait.util;

/**
 * Created by arihanshah on 9/22/15.
 */
public enum PropertyType {
    Mountain1("Mountain 1"),
    Mountain2("Mountain 2"),
    Mountain3("Mountain 3"),
    FlatLand("FlatLand"),
    RiverLand("RiverLand"),;

    private String name;

    PropertyType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
