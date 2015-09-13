package edu.gatech.cs2340.sharkbait;

/**
 * Created by osama on 9/12/15.
 */
public enum Race {
    Human("Human"), Flapper("Flapper"), Buzzite("Buzzite"), Ugaite("Ugaite"),
    Bonzoid("Bonzoid");

    private String name;

    Race(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
