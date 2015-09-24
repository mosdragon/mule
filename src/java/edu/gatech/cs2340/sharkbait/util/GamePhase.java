package edu.gatech.cs2340.sharkbait.util;

/**
 * Created by sayem on 9/24/2015.
 */
public enum GamePhase {
    LandBuyPhase("Land Buy"), PlayerTurnPhase("Player Turn");

    private String name;

    GamePhase(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
