package edu.gatech.cs2340.sharkbait.util;

/**
 * Created by osama on 10/27/15.
 */
public class EnergyMule extends AbstractMule {

    public EnergyMule(Property property) {
        super(property, Resource.Energy);
    }

    @Override
    public void handleProduction(Player player) {
        PropertyType propertyType = getProperty().getType();

        if (propertyType == PropertyType.Plains) {

            player.changeEnergy(P_ENERGY);

        } else if (propertyType == PropertyType.River) {

            player.changeEnergy(R_ENERGY);

        } else if (propertyType == PropertyType.Mountain1) {

            player.changeEnergy(M1_ENERGY);

        } else if (propertyType == PropertyType.Mountain2) {

            player.changeEnergy(M2_ENERGY);

        } else if (propertyType == PropertyType.Mountain3) {

            player.changeEnergy(M3_ENERGY);

        }
    }
}
