package edu.gatech.cs2340.sharkbait.model;

/**
 * Created by osama on 10/17/15.
 */
public interface Constants {

//    Prices
    int LAND = 300;
    int ENERGY = 25;
    int FOOD = 30;
    int ORE = 50;
    int MULE = 100;
    int ORE_MULE = MULE + ORE;
    int FOOD_MULE = MULE + FOOD;
    int ENERGY_MULE = MULE + ENERGY;

    String CSS_TRANSPARENT = "-fx-background-color:rgba(0,0,0,0);";
    String BG_COLOR_TEMPLATE = "-fx-background-color:%s;";

//    Mongo Credentials
    String DB_USER = "app";
    String DB_PASS = "sharkbait";
    String MONGO_CONNECTION = String.format("mongodb://%s:%s@ds042138.mongolab.com:42138/mule",
            DB_USER, DB_PASS);

    String DB_NAME = "mule";
    String GAME_SAVES = "gamesaves";
}
