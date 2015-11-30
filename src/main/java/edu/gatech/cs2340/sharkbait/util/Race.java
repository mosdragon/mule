package edu.gatech.cs2340.sharkbait.util;

import edu.gatech.cs2340.sharkbait.model.Packable;

import java.io.Serializable;

/**
 * Created on 9/12/15 by osama.
 */
public enum Race implements Serializable, Packable {
  /**
   * Human.
   */
  Human("Human"),
  /**
   * Flapper.
   */
  Flapper("Flapper"),
  /**
   * Buzzite.
   */
  Buzzite("Buzzite"),
  /**
   * Ugaite.
   */
  Ugaite("Ugaite"),
  /**
   * Bonzoid.
   */
  Bonzoid("Bonzoid");

  /**
   * name field.
   */
  private final String name;

  /**
   * Race Constructor.
   *
   * @param nameField name of the race.
   */
  Race(final String nameField) {
    name = nameField;
  }
}
