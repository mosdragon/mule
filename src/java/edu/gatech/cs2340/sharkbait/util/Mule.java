package edu.gatech.cs2340.sharkbait.util;

import edu.gatech.cs2340.trydent.log.Log;

/**
 * Created by osama on 9/22/15.
 */
public class Mule {

    private static final int R_FOOD = 4;
    private static final int R_ENERGY = 2;
    private static final int R_ORE = 0;

    private static final int P_FOOD = 2;
    private static final int P_ENERGY = 3;
    private static final int P_ORE = 1;

    private static final int M1_FOOD = 1;
    private static final int M1_ENERGY = 1;
    private static final int M1_ORE = 2;

    private static final int M2_FOOD = 1;
    private static final int M2_ENERGY = 1;
    private static final int M2_ORE = 3;

    private static final int M3_FOOD = 1;
    private static final int M3_ENERGY = 1;
    private static final int M3_ORE = 4;

    private Resource type;
    private Property property;

    public Mule(Property property, Resource type) {
        this.property = property;
        this.type = type;
    }

    public Resource getType() {
        return type;
    }

    public void setType(Resource type) {
        this.type = type;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    /**
     * Called for every MULE at the beginning of a player's turn. Updates quantities of resources
     * @param player, the player whose turn it is right now
     */
    public void handleProduction(Player player) {
        if (player.getEnergy() >= 1) {

            if (property.getType() == PropertyType.Plains) {

                if (type == Resource.Food) {
                    player.changeFood(P_FOOD);
                } else if (type == Resource.Energy) {
                    player.changeEnergy(P_ENERGY);
                } else if (type == Resource.Smithore) {
                    player.changeOre(P_ORE);
                }

            } else if (property.getType() == PropertyType.River) {

                if (type == Resource.Food) {
                    player.changeFood(R_FOOD);
                } else if (type == Resource.Energy) {
                    player.changeEnergy(R_ENERGY);
                } else if (type == Resource.Smithore) {
                    player.changeOre(R_ORE);
                }

            } else if (property.getType() == PropertyType.Mountain1) {

                if (type == Resource.Food) {
                    player.changeFood(M1_FOOD);
                } else if (type == Resource.Energy) {
                    player.changeEnergy(M1_ENERGY);
                } else if (type == Resource.Smithore) {
                    player.changeOre(M1_ORE);
                }

            } else if (property.getType() == PropertyType.Mountain2) {

                if (type == Resource.Food) {
                    player.changeFood(M2_FOOD);
                } else if (type == Resource.Energy) {
                    player.changeEnergy(M2_ENERGY);
                } else if (type == Resource.Smithore) {
                    player.changeOre(M2_ORE);
                }

            } else if (property.getType() == PropertyType.Mountain3) {

                if (type == Resource.Food) {
                    player.changeFood(M3_FOOD);
                } else if (type == Resource.Energy) {
                    player.changeEnergy(M3_ENERGY);
                } else if (type == Resource.Smithore) {
                    player.changeOre(M3_ORE);
                }

            }
        } else {
            Log.debug("Not enough energy for player: " + player.getName());
        }
    }



    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Mule)) {
            return false;
        }
        Mule other = (Mule) obj;
        return this.type == other.type;
    }

    @Override
    public String toString() {
        return type.toString() + " Mule";
    }
}
