package edu.gatech.cs2340.sharkbait.util;

/**
 * Created by arihanshah on 9/22/15.
 */
public enum PropertyType {
    Mountain1("M1"),
    Mountain2("M2"),
    Mountain3("M3"),
    Plains("P"),
    River("R"),;

    private String text;

    PropertyType(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return text;
    }
}
