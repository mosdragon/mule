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

    private static GameConfigs ourInstance = new GameConfigs();

    private static Difficulty gameDifficulty;
    private static int numPlayers;
    private static MapType mapType;

    public static List<Player> players;


    public static GameConfigs getInstance() {
        return ourInstance;
    }

    private GameConfigs() {
        gameDifficulty = Difficulty.Standard;
        mapType = MapType.StandardMap;
        players = new ArrayList<>();
        Store.initializeStore();
    }

    public static List<Player> getPlayers() {
        return players;
    }

    public static void setPlayers(List<Player> players) {
        GameConfigs.players = players;
    }

    public static void addPlayer(Player player) {
        players.add(player);
    }

    public Difficulty getGameDifficulty() {
        return gameDifficulty;
    }

    public static void setGameDifficulty(Difficulty gameDifficulty) {
        GameConfigs.gameDifficulty = gameDifficulty;
    }

    public static int getNumPlayers() {
        return numPlayers;
    }

    public void setNumPlayers(int numPlayers) {
        this.numPlayers = numPlayers;
    }

    public MapType getMapType() {
        return mapType;
    }

    public void setMapType(MapType mapType) {
        this.mapType = mapType;
    }


}
