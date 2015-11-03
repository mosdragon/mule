package edu.gatech.cs2340.sharkbait.util;

/**
 * Created by osama on 10/27/15.
 */
public class FoodMule extends Mule {

    public FoodMule(Property property) {
        super(property, Resource.Food);
    }

    @Override
    public void handleProduction(Player player) {
        PropertyType propertyType = getProperty().getType();

        if (propertyType == PropertyType.Plains) {

            player.changeFood(P_FOOD);

        } else if (propertyType == PropertyType.River) {

            player.changeFood(R_FOOD);

        } else if (propertyType == PropertyType.Mountain1) {

            player.changeFood(M1_FOOD);

        } else if (propertyType == PropertyType.Mountain2) {

            player.changeFood(M2_FOOD);

        } else if (propertyType == PropertyType.Mountain3) {

            player.changeFood(M3_FOOD);

        }
    }
}
