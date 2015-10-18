package edu.gatech.cs2340.sharkbait.model;

/**
 * Created by osama on 10/17/15.
 */
public interface Prices {

    int ENERGY = 25;
    int FOOD = 30;
    int ORE = 50;
    int MULE = 100;
    int ORE_MULE = MULE + ORE;
    int FOOD_MULE = MULE + FOOD;
    int ENERGY_MULE = MULE + ENERGY;
}
