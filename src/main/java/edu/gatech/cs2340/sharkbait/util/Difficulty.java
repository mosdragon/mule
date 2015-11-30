package edu.gatech.cs2340.sharkbait.util;

import edu.gatech.cs2340.sharkbait.model.Packable;

import java.io.Serializable;

/**
 * Difficulty enum.
 */
public enum Difficulty implements Serializable, Packable {
  Beginner("Beginner"), Standard("Standard"), Tournament("Tournament");

  private final String name;

  Difficulty(String nameInput) {
    name = nameInput;
  }

  @Override
  public String toString() {
    return name;
  }
}
