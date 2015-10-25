package edu.gatech.cs2340.sharkbait.util;

import edu.gatech.cs2340.sharkbait.model.Packable;

import java.io.Serializable;

/**
 * Created by osama on 9/12/15.
 */
public enum MapType implements Serializable, Packable {
    StandardMap("Standard Map"), RandomMap("Random Map");

    private String mapName;

    MapType(String mapName) {
        this.mapName = mapName;
    }

    @Override
    public String toString() {
        return mapName;
    }
}
