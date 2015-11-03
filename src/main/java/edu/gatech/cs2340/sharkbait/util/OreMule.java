package edu.gatech.cs2340.sharkbait.util;

/**
 * Created by osama on 10/27/15.
 */
public class OreMule extends Mule {


    public OreMule(Property property) {
        super(property, Resource.Ore);
    }

    @Override
    public void handleProduction(Player player) {
        PropertyType propertyType = getProperty().getType();

        if (propertyType == PropertyType.Plains) {

            player.changeOre(P_ORE);

        } else if (propertyType == PropertyType.River) {

            player.changeOre(R_ORE);

        } else if (propertyType == PropertyType.Mountain1) {

            player.changeOre(M1_ORE);

        } else if (propertyType == PropertyType.Mountain2) {

            player.changeOre(M2_ORE);

        } else if (propertyType == PropertyType.Mountain3) {

            player.changeOre(M3_ORE);

        }
    }
}
