package edu.gatech.cs2340.sharkbait.model;

import edu.gatech.cs2340.sharkbait.util.*;
import edu.gatech.cs2340.sharkbait.view.GameMapView;
import edu.gatech.cs2340.sharkbait.view.TownMapView;
import javafx.scene.Parent;
import javafx.scene.control.Button;

import java.util.*;

/**
 * Created by osama on 9/22/15.
 */
public class GameDuration {

//    Start with 50 second turns
    private static final int TIME_START = 50;

    private static Parent gameMap = null;
    private static GameMapView gameMapView;

    private static Parent townMap = null;
    private static TownMapView townMapView;

    public static List<Player> players;
    private static int round = 1;
    private static int turn = 0;

    private static boolean begun = false;
    private static GamePhase phase = GamePhase.LandBuyPhase;

//    Time left of the turn, in seconds
    private static int timeRemaining;

    private static Player activePlayer = null;

//    Holds the type of the mule if one is just bought, clear it once it is set on a property
    private static Resource activeMuleType;

    private static Map<Button, Tile> tilesMap;

    private GameDuration() {
    }

    public static boolean hasBegun() {
        return begun;
    }

    public static void begin() {
        begun = true;
        phase = GamePhase.LandBuyPhase;
        resetTime();
        tilesMap = new HashMap<>();
    }

    public static Player getActivePlayer() {
        if (activePlayer == null && hasBegun()) {
            activePlayer = getPlayers().get(turn);
            resetTime();
        }
        return activePlayer;
    }

    public static void setActivePlayer(Player activePlayer) {
        GameDuration.activePlayer = activePlayer;
    }

    public static Tile fetchTile(Button button) {
        Tile found = tilesMap.get(button);
        return found;
    }

    public static void addTile(Tile tile) {
        Button button = tile.getHolder();
        tilesMap.put(button, tile);
    }

    public static Parent getGameMap() {
        return gameMap;
    }

    public static void setGameMap(Parent gameMap) {
        GameDuration.gameMap = gameMap;
    }

    public static Parent getTownMap() {
        return townMap;
    }

    public static void setTownMap(Parent townMap) {
        GameDuration.townMap = townMap;
    }


    public static int getTurn() {
        return turn;
    }
    public static void setTurn (int number) {
        turn = number;
    }


    public static int getRound() {
        return round;
    }

    public static GamePhase getPhase() {
        return phase;
    }

    public static void endTurn() {
        turn++;
        if (turn >= GameConfigs.getNumPlayers()) {
            turn = 0;
            if (phase != GamePhase.PlayerTurnPhase) {
                phase = GamePhase.PlayerTurnPhase;
            } else {
//                End of turn. Sort the list of players
                Collections.sort(getPlayers());
                phase = GamePhase.LandBuyPhase;
                round++;
            }
        }
        activePlayer = getPlayers().get(turn);
        timeRemaining = TIME_START;
    }



    public static void endGame() {

    }

    public static void changeTimeRemaining(int deltaT) {
        GameDuration.timeRemaining += deltaT;
    }

    public static int getTimeRemaining() {
        return GameDuration.timeRemaining;
    }

    public static void resetTime() {
        timeRemaining = TIME_START;
    }

    public static List<Player> getPlayers() {
        if (players == null) {
            players = new ArrayList<>();
        }
        return players;
    }

    public static void setPlayers(List<Player> players) {
        GameDuration.players = players;
    }

    public static void addPlayer(Player player) {
        if (players == null) {
            players = new ArrayList<>();
        }
        players.add(player);
    }

    public static Resource getActiveMuleType() {
        return activeMuleType;
    }

    public static void setActiveMuleType(Resource activeMuleType) {
        GameDuration.activeMuleType = activeMuleType;
    }

    public static void clearActiveMuleType() {
        setActiveMuleType(null);
    }

    public static void beginMulePlacementPhase() {
        phase = GamePhase.MulePlacementPhase;
    }

    public static void endMulePlacementPhase() {
        phase = GamePhase.PlayerTurnPhase;
    }

}
