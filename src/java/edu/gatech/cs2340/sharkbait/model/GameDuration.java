package edu.gatech.cs2340.sharkbait.model;


import edu.gatech.cs2340.sharkbait.controller.MasterController;

import edu.gatech.cs2340.sharkbait.util.GamePhase;
import edu.gatech.cs2340.sharkbait.util.Player;
import edu.gatech.cs2340.sharkbait.util.Property;
import edu.gatech.cs2340.sharkbait.util.Resource;
import edu.gatech.cs2340.sharkbait.view.GameMapView;
import edu.gatech.cs2340.sharkbait.view.TownMapView;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.util.*;

/**
 * Created by osama on 9/22/15.
 */
public class GameDuration {

//    Start with 50 second turns
    private static final int TIME_START = 50;

    private static final int[] foodRequirements = {3, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 5};

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

//    Maps UI Buttons to properties in the game
    private static Map<Button, Property> propertiesMap;

    private GameDuration() {
    }

    public static boolean hasBegun() {
        return begun;
    }

    public static void begin() {
        begun = true;
        phase = GamePhase.LandBuyPhase;
        resetTime();
        propertiesMap = new HashMap<>();
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

    public static Property fetchProperty(Button button) {
        Property property = propertiesMap.get(button);
        if (property == null) {
            property = new Property(button);
            propertiesMap.put(button, property);
        }
        return property;
    }

    public static void addProperty(Button button, Property property) {
        propertiesMap.put(button, property);
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
        determineTimeRemaining();
        handleRandomEvents();
        handleProduction();
    }

    /**
     * Determines length of player turn based on resources left
     */
    private static void determineTimeRemaining() {
//        TODO: Consider food and energy minimums

        Player player = getActivePlayer();
        int foodCount = player.getFood();
        int foodRequirement = foodRequirements[getRound() - 1];

        if (foodCount == 0) {
//            If you have no food, turn lasts 5 seconds
            timeRemaining = 5;
        } else if (foodCount > 0 && foodCount < foodRequirement) {
//            If you have food but not enough as the round demands, turn is 30 seconds
            timeRemaining = 30;
        } else {
            timeRemaining = TIME_START;
        }
    }

    /**
     * If player turn begins and player isn't the lowest scoring player, a random event should
     * happen
     */
    private static void handleRandomEvents() {
        if (phase == GamePhase.PlayerTurnPhase && players.indexOf(activePlayer) == 0) {
            MasterController.generateRandomGoodEvent();
        } else if (phase == GamePhase.PlayerTurnPhase) {
            MasterController.generateRandomEvent();
        } else {
            MasterController.clearRandomEvent();
        }
    }

    /**
     * When a player's turn begins, production must be computed immediately
     */
    private static void handleProduction() {
        if (phase == GamePhase.PlayerTurnPhase) {
            activePlayer.handleProduction();
        }
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
