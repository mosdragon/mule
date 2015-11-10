package edu.gatech.cs2340.sharkbait.util;

import edu.gatech.cs2340.sharkbait.model.Packable;

import java.io.Serializable;

/**
 * PropertyType class.
 *
 * @author Arihan Shah
 * @version 1.0
 */
public enum PropertyType implements Serializable, Packable {
  /**
   * sets mountain1 to "M1".
   */
  Mountain1("M1"),
  /**
   * sets mountain2 to "M2".
   */
  Mountain2("M2"),
  /**
   * sets mountain3 to "M3".
   */
  Mountain3("M3"),
  /**
   * sets Plains to "P".
   */
  Plains("P"),
  /**
   * sets River to "R".
   */
  River("R");

  /**
   * initializes a String text.
   */
  private final String text;

  /**
   * constructs a property type.
   *
   * @param textInput the text belonging to the propertyType
   */
  PropertyType(final String textInput) {
    text = textInput;
  }

  /**
   * gets the text of the property.
   *
   * @return the text of the property
   */
  public String getText() {
    return text;
  }

  @Override
  public String toString() {
    return text;
  }
}
