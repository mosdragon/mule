package edu.gatech.cs2340.sharkbait.model;

/**
 * Created by osama on 10/17/15.
 */
public final class Constants {

    /**
     * Empty constructor.
     */
    private Constants() {
    }

    // Store prices.
    /**
     * Land cost.
     */
    public static final int LAND = 300;

    /**
     * Energy cost.
     */
    public static final int ENERGY = 25;

    /**
     * Food cost.
     */
    public static final int FOOD = 30;

    /**
     * Ore cost.
     */
    public static final int ORE = 50;

    /**
     * Mule cost.
     */
    public static final int MULE = 100;

    /**
     * Ore mule cost.
     */
    public static final int ORE_MULE = MULE + ORE;

    /**
     * Food mule cost.
     */
    public static final int FOOD_MULE = MULE + FOOD;

    /**
     * Energy mule cost.
     */
    public static final int ENERGY_MULE = MULE + ENERGY;

    /**
     * CSS transparent.
     */
    public static final String CSS_TRANSPARENT
            = "-fx-background-color:rgba(0,0,0,0);";

    /**
     * BG color.
     */
    public static final String BG_COLOR_TEMPLATE = "-fx-background-color:%s;";

//    Mongo Credentials
    /**
     * Database user.
     */
    public static final String DB_USER = "app";

    /**
     * Database password.
     */
    public static final String DB_PASS = "sharkbait";

    /**
     * Mongo connection.
     */
    public static final String MONGO_CONNECTION
            = String.format("mongodb://%s:%s@ds042138.mongolab.com:42138/mule",
            DB_USER, DB_PASS);

    /**
     * Database Name.
     */
    public static final String DB_NAME = "mule";

    /**
     * Game saves.
     */
    public static final String GAME_SAVES = "gamesaves";
}
