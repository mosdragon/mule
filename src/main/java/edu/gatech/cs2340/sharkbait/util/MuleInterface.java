package edu.gatech.cs2340.sharkbait.util;

import edu.gatech.cs2340.sharkbait.model.Packable;
import edu.gatech.cs2340.trydent.log.Log;

import java.io.Serializable;

/**
 * Created by osama on 10/27/15.
 */

public interface MuleInterface extends Packable {


    int R_FOOD = 4;
    int R_ENERGY = 2;
    int R_ORE = 0;

    int P_FOOD = 2;
    int P_ENERGY = 3;
    int P_ORE = 1;

    int M1_FOOD = 1;
    int M1_ENERGY = 1;
    int M1_ORE = 2;

    int M2_FOOD = 1;
    int M2_ENERGY = 1;
    int M2_ORE = 3;

    int M3_FOOD = 1;
    int M3_ENERGY = 1;
    int M3_ORE = 4;

    Resource getType();

    void setType(Resource type);

    Property getProperty();

    void setProperty(Property property);

    /**
     * Called for every MULE at the beginning of a player's turn. Updates quantities of resources
     * @param player, the player whose turn it is right now
     */
    void handleProduction(Player player);
}
