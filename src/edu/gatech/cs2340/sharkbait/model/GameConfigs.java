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

    private Difficulty gameDifficulty;
    private int numPlayers;
    private MapType mapType;

    private List<Player> players;


    public static GameConfigs getInstance() {
        return ourInstance;
    }

    private GameConfigs() {
        gameDifficulty = Difficulty.Standard;
        mapType = MapType.StandardMap;
        players = new ArrayList<>();
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public Difficulty getGameDifficulty() {
        return gameDifficulty;
    }

    public void setGameDifficulty(Difficulty gameDifficulty) {
        this.gameDifficulty = gameDifficulty;
    }

    public int getNumPlayers() {
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
