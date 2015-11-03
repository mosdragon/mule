package edu.gatech.cs2340.sharkbait.util;

import com.google.gson.InstanceCreator;

import java.lang.reflect.Type;

/**
 * Created by osama on 11/2/15.
 */
public class MuleFactory {

    public static Mule createMule(Property property, Resource muleType) {
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
