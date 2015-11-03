package edu.gatech.cs2340.sharkbait.util;

/**
 * Created by osama on 11/2/15.
 */
public class MuleFactory {

    public static MuleInterface createMule(Property property, Resource muleType) {
        MuleInterface created = null;

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
