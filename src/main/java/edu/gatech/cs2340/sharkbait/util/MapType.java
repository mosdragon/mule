package edu.gatech.cs2340.sharkbait.util;

import edu.gatech.cs2340.sharkbait.model.Packable;

import java.io.Serializable;

/**
 * Map type enum.
 */
public enum MapType implements Serializable, Packable {
  StandardMap("Standard Map"), RandomMap("Random Map");

  private final String mapName;

  MapType(String mapNameInput) {
    mapName = mapNameInput;
  }

  @Override
  public String toString() {
    return mapName;
  }
}
