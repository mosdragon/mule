package edu.gatech.cs2340.sharkbait.util;

import edu.gatech.cs2340.sharkbait.model.Packable;

import java.io.Serializable;

/**
 * Resource class
 * Created on 9/22/15.
 *
 * @author Osama
 */
public enum Resource implements Serializable, Packable {

  /**
   * sets Energy to "Energy".
   */
  Energy("Energy"),
  /**
   * sets Food to "food".
   */
  Food("Food"),
  /**
   * sets ore to "Ore".
   */
  Ore("Ore");

  /**
   * The name to display for the resource.
   */
  private final String name;

  /**
   * Constructs a Resource with a String name.
   *
   * @param nameField the name of the Resource
   */
  Resource(final String nameField) {
    name = nameField;
  }

  /**
   * Return the name.
   *
   * @return the name.
   */
  public String getName() {
    return name;
  }

  @Override
  public String toString() {
    return name;
  }

}
