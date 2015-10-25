package edu.gatech.cs2340.sharkbait.model;

import edu.gatech.cs2340.sharkbait.util.Difficulty;
import edu.gatech.cs2340.sharkbait.util.MapType;
import edu.gatech.cs2340.sharkbait.util.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by osama on 9/12/15.
 */
public class GameConfigs {

    private static Difficulty gameDifficulty;
    private static int numPlayers;
    private static MapType mapType;

    private GameConfigs() {
        gameDifficulty = Difficulty.Standard;
        mapType = MapType.StandardMap;
    }

    public static Difficulty getGameDifficulty() {
        return gameDifficulty;
    }

    public static void setGameDifficulty(Difficulty gameDifficulty) {
        GameConfigs.gameDifficulty = gameDifficulty;
        Store.initializeStore();
    }

    public static int getNumPlayers() {
        return numPlayers;
    }

    public static void setNumPlayers(int numPlayers) {
        GameConfigs.numPlayers = numPlayers;
    }

    public static MapType getMapType() {
        return mapType;
    }

    public static void setMapType(MapType mapType) {
        GameConfigs.mapType = mapType;
    }


}
