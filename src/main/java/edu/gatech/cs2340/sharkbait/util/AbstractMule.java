package edu.gatech.cs2340.sharkbait.util;

import edu.gatech.cs2340.trydent.log.Log;

import java.io.Serializable;

/**
 * Created by osama on 9/22/15.
 */
public abstract class AbstractMule implements Serializable, Mule {

    private Resource type;
    private Property property;

    public AbstractMule(Property property, Resource type) {
        this.property = property;
        this.type = type;
    }

    @Override
    public Resource getType() {
        return type;
    }

    @Override
    public void setType(Resource type) {
        this.type = type;
    }

    @Override
    public Property getProperty() {
        return property;
    }

    @Override
    public void setProperty(Property property) {
        this.property = property;
    }

    /**
     * Called for every MULE at the beginning of a player's turn. Updates quantities of resources
     * @param player, the player whose turn it is right now
     */
    @Override
    public void handleProduction(Player player) {
        if (player.getEnergy() >= 1) {

            if (property.getType() == PropertyType.Plains) {

                if (type == Resource.Food) {
                    player.changeFood(P_FOOD);
                } else if (type == Resource.Energy) {
                    player.changeEnergy(P_ENERGY);
                } else if (type == Resource.Ore) {
                    player.changeOre(P_ORE);
                }

            } else if (property.getType() == PropertyType.River) {

                if (type == Resource.Food) {
                    player.changeFood(R_FOOD);
                } else if (type == Resource.Energy) {
                    player.changeEnergy(R_ENERGY);
                } else if (type == Resource.Ore) {
                    player.changeOre(R_ORE);
                }

            } else if (property.getType() == PropertyType.Mountain1) {

                if (type == Resource.Food) {
                    player.changeFood(M1_FOOD);
                } else if (type == Resource.Energy) {
                    player.changeEnergy(M1_ENERGY);
                } else if (type == Resource.Ore) {
                    player.changeOre(M1_ORE);
                }

            } else if (property.getType() == PropertyType.Mountain2) {

                if (type == Resource.Food) {
                    player.changeFood(M2_FOOD);
                } else if (type == Resource.Energy) {
                    player.changeEnergy(M2_ENERGY);
                } else if (type == Resource.Ore) {
                    player.changeOre(M2_ORE);
                }

            } else if (property.getType() == PropertyType.Mountain3) {

                if (type == Resource.Food) {
                    player.changeFood(M3_FOOD);
                } else if (type == Resource.Energy) {
                    player.changeEnergy(M3_ENERGY);
                } else if (type == Resource.Ore) {
                    player.changeOre(M3_ORE);
                }

            }
        } else {
            Log.debug("Not enough energy for player: " + player.getName());
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof AbstractMule)) {
            return false;
        }
        AbstractMule other = (AbstractMule) obj;
        return this.type == other.type;
    }

    @Override
    public String toString() {
        return type.toString() + " AbstractMule";
    }
}
