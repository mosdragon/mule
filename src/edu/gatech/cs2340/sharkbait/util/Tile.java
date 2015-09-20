package edu.gatech.cs2340.sharkbait.util;

/**
 * Created by Bruck on 9/19/15.
 */


public enum Tile {
    Mountain1("Mountain1"), Mountain2("Mountian2"), Mountain3("Mountain3"),
    River("River"), Energy("Energy"), Food("Food"), Pub("Pub"),
    LandOffice("LandOffice"), AquaBorder("AquaBorder"), BeigeBorder("BeigeBorder"),
    FireBrickBorder("FireBrickBorder"), GoldBorder("GoldBorder"), Mule("Mule"),
    Ore("Ore"), Plains("Plains"), TownBackground("TownBackground"),
    TownTiel("TownTiel"), VioletBorder("VioletBorder");

    private String resource;

    Tile(String resource){this.resource = resource}

    @Override
    public String toString() {
        return mapName;
    }
}
