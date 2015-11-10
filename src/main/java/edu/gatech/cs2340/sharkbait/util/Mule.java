package edu.gatech.cs2340.sharkbait.util;

import edu.gatech.cs2340.sharkbait.model.Packable;

import java.io.Serializable;

/**
 * Created by osama on 9/22/15.
 */
public abstract class Mule implements Serializable, Packable, MuleInterface {

  /**
   * The mule type.
   */
  private Resource type;

  /**
   * The mule property.
   */
  private Property property;

  /**
   * The hash constant for hashCode().
   */
  private static final int HASH_CONSTANT = 41;

  /**
   * Constructor.
   *
   * @param propertyInput the property for the mule.
   * @param typeInput     the type of the mule.
   */
  public Mule(final Property propertyInput, final Resource typeInput) {
    property = propertyInput;
    type = typeInput;
  }

  @Override
  public final Resource getType() {
    return type;
  }

  @Override
  public final void setType(final Resource typeInput) {
    type = typeInput;
  }

  @Override
  public final Property getProperty() {
    return property;
  }

  @Override
  public final void setProperty(final Property propertyInput) {
    property = propertyInput;
  }

  @Override
  public final boolean equals(final Object obj) {
    if (!(obj instanceof Mule)) {
      return false;
    }
    Mule other = (Mule) obj;
    return this.type == other.type;
  }

  @Override
  public final int hashCode() {
    return HASH_CONSTANT * type.hashCode()
        + HASH_CONSTANT * HASH_CONSTANT * property.hashCode();
  }

  @Override
  public final String toString() {
    return type.toString() + " Mule";
  }
}
