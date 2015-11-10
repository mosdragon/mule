package edu.gatech.cs2340.sharkbait.model;

import edu.gatech.cs2340.sharkbait.util.Difficulty;
import edu.gatech.cs2340.sharkbait.util.MapType;
import java.io.Serializable;

/**
 * Created on 9/12/15 by osama.
 */
public final class GameConfigs implements Serializable, Packable {
    /**
     * Difficulty gameDifficulty.
     */
    private Difficulty gameDifficulty;
    /**
     * int numPlayers.
     */
    private int numPlayers;
    /**
     * MapType mapType.
     */
    private MapType mapType;
    /**
     * GameConfigs instance.
     */
    private static transient GameConfigs instance;
    /**
     * GameConfigs constructor.
     */
    private GameConfigs() {
        gameDifficulty = Difficulty.Standard;
        mapType = MapType.StandardMap;
    }
    /**
     * GameConfigs method.
     * @return instance variable that is used throughout.
     */
    public static GameConfigs getInstance() {
        if (instance == null) {
            instance = new GameConfigs();
        }
        return instance;
    }
    /**
     * GameDifficulty getter.
     * @return gameDifficulty
     */
    public static Difficulty getGameDifficulty() {
        return getInstance().gameDifficulty;
    }
    /**
     * GameDifficulty setter.
     * @param gameDifficulty gameDifficulty to be set
     */
    public static void setGameDifficulty(final Difficulty gameDifficulty) {
        getInstance().gameDifficulty = gameDifficulty;
        Store.initializeStore();
    }
    /**
     * NumPlayers getter.
     * @return numPlayers
     */
    public static int getNumPlayers() {
        return getInstance().numPlayers;
    }
    /**
     * NumPlayers setter.
     * @param numPlayers numPlayers to be set
     */
    public static void setNumPlayers(final int numPlayers) {
        getInstance().numPlayers = numPlayers;
    }
    /**
     * MapType getter.
     * @return mapType
     */
    public static MapType getMapType() {
        return getInstance().mapType;
    }
    /**
     * MapType setter.
     * @param mapType numPlayers to be set
     */
    public static void setMapType(final MapType mapType) {
        getInstance().mapType = mapType;
    }


    /**
     * Redefine the single instance of a singleton using the provided source.
     * @param source the source object
     */
    private static void unpack(final GameConfigs source) {
        instance = source;
    }

    /**
     * Redefine the single instance of a singleton using the provided source,
     * which is JSON.
     * @param jsonSource source of the JSON
     */
    public static void unpackfromJson(final String jsonSource) {
        GameConfigs source = Packer.unpack(jsonSource, GameConfigs.class);
        unpack(source);
    }

    /**
     * Serialized instance as JSON.
     * @return a JSON-ified version of this object
     */
    public static String packAsJson() {
        return getInstance().pack();
    }

}
