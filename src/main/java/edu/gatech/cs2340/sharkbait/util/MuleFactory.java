package edu.gatech.cs2340.sharkbait.util;

/**
 * Factory for creating Mules using a resource type.
 */
public final class MuleFactory {

  /**
   * Prevents instantiation.
   */
  private MuleFactory() {
  }

  /**
   * Creates the desire type of mule.
   * @param property the location where this mule will be placed.
   * @param muleType the type of mule desired.
   * @return a mule on this property with the correct muleType.
   */
  public static Mule createMule(final Property property,
                                final Resource muleType) {
    Mule created = null;

    if (muleType == Resource.Ore) {
        created = new OreMule(property);

    } else if (muleType == Resource.Energy) {
        created = new EnergyMule(property);

    } else if (muleType == Resource.Food) {
        created = new FoodMule(property);
    }

    return created;
  }

}
