package edu.gatech.cs2340.sharkbait.model;

import edu.gatech.cs2340.sharkbait.util.Difficulty;
import edu.gatech.cs2340.sharkbait.util.MapType;

import java.io.Serializable;

/**
 * Created by osama on 9/12/15.
 */
public class GameConfigs implements Serializable, Packable {

    private Difficulty gameDifficulty;
    private int numPlayers;
    private MapType mapType;
    private transient static GameConfigs instance;

    private GameConfigs() {
        gameDifficulty = Difficulty.Standard;
        mapType = MapType.StandardMap;
    }

    public static GameConfigs getInstance() {
        if (instance == null) {
            instance = new GameConfigs();
        }
        return instance;
    }

    public static Difficulty getGameDifficulty() {
        return getInstance().gameDifficulty;
    }

    public static void setGameDifficulty(Difficulty gameDifficulty) {
        getInstance().gameDifficulty = gameDifficulty;
        Store.initializeStore();
    }

    public static int getNumPlayers() {
        return getInstance().numPlayers;
    }

    public static void setNumPlayers(int numPlayers) {
        getInstance().numPlayers = numPlayers;
    }

    public static MapType getMapType() {
        return getInstance().mapType;
    }

    public static void setMapType(MapType mapType) {
        getInstance().mapType = mapType;
    }


    /**
     * Redefine the single instance of a singleton using the provided source
     * @param source, the source object
     */
    public static void unpack(GameConfigs source) {
        instance = source;
    }

    /**
     * Redefine the single instance of a singleton using the provided source, which is JSON
     * @param jsonSource
     */
    public static void unpackfromJson(String jsonSource) {
        GameConfigs source = Packer.unpack(jsonSource, GameConfigs.class);
        unpack(source);
    }

    /**
     * Serialized instance as JSON
     * @return a JSONified version of this object
     */
    public static String packAsJson() {
        return getInstance().pack();
    }

}
