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

import java.io.Serializable;
import java.util.*;

/**
 * Created by osama on 9/22/15.
 */
public class GameDuration implements Serializable, Packable {

//    Food requirement by round. Index offset by 1
    private static final int[] FOOD_REQUIREMENTS = {3, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 5};

//    Constants for time if not enough food
    private static final int TIME_NO_FOOD = 5;
    private static final int TIME_FOOD_LOW = 30;
    private static final int TIME_DEFAULT = 50;

    private static final int MAX_ROUNDS = 12;

    private transient Parent gameMap = null;
    private transient GameMapView gameMapView;

    private transient Parent townMap = null;
    private transient TownMapView townMapView;

    private List<Player> players;
    private int round;
    private int turn;

    private boolean begun = false;
    private boolean paused = false;
    private GamePhase phase = GamePhase.LandBuyPhase;

//    Time left of the turn, in seconds
    private int timeRemaining;

    private Player activePlayer;

//    Holds the type of the mule if one is just bought, clear it once it is set on a property
    private Resource activeMuleType;

//    Maps UI Buttons to properties in the game
    private transient Map<Button, Property> propertiesMap;

    private transient static GameDuration instance;

    private GameDuration() {
        activePlayer = null;
        round = 1;
        turn = 0;
    }

    public static GameDuration getInstance() {
        if (instance == null) {
            instance = new GameDuration();
        }
        return instance;
    }

    public static boolean hasBegun() {
        return getInstance().begun;
    }

    public static boolean isPaused() {return getInstance().paused; }

    public static void pause() { getInstance().paused = true; }

    public static void resume() { getInstance().paused = false; }

    public static void begin() {
        getInstance().begun = true;
        getInstance().phase = GamePhase.LandBuyPhase;
        resetTime();
        getInstance().propertiesMap = new HashMap<>();
        MasterController.startMusic();
    }

    public static Player getActivePlayer() {
        if (getInstance().activePlayer == null && hasBegun()) {
            getInstance().activePlayer = getPlayers().get(getInstance().turn);
            resetTime();
        }
        return getInstance().activePlayer;
    }

    public static void setActivePlayer(Player activePlayer) {
        getInstance().activePlayer = activePlayer;
    }

    public static Property fetchProperty(Button button) {
        Property property = getInstance().propertiesMap.get(button);
        if (property == null) {
            property = new Property(button);
            getInstance().propertiesMap.put(button, property);
        }
        return property;
    }

    public static void addProperty(Button button, Property property) {
        if (getInstance().propertiesMap == null) {
            getInstance().propertiesMap = new HashMap<>();
        }
        getInstance().propertiesMap.put(button, property);
    }

    public static Parent getGameMap() {
        return getInstance().gameMap;
    }

    public static void setGameMap(Parent gameMap) {
        getInstance().gameMap = gameMap;
    }

    public static Parent getTownMap() {
        return getInstance().townMap;
    }

    public static void setTownMap(Parent townMap) {
        getInstance().townMap = townMap;
    }


    public static int getTurn() {
        return getInstance().turn;
    }
    public static void setTurn (int number) {
        getInstance().turn = number;
    }


    public static int getRound() {
        return getInstance().round;
    }

    public static GamePhase getPhase() {
        return getInstance().phase;
    }

    public static void endTurn() {
        int oldTurn = getInstance().turn;
        getInstance().turn++;
        if (getInstance().turn >= GameConfigs.getInstance().getNumPlayers()) {
            getInstance().turn = 0;
            if (getInstance().phase != GamePhase.PlayerTurnPhase) {
                getInstance().phase = GamePhase.PlayerTurnPhase;
            } else {
//                End of turn. Sort the list of players
                Collections.sort(getPlayers());
                getInstance().phase = GamePhase.LandBuyPhase;
                getInstance().round++;
                MasterController.roundRandomEvent();
            }
        }
        getInstance().activePlayer = getPlayers().get(getInstance().turn);
        determineTimeRemaining();
        handleRandomEvents();
        handleProductionIfApplicable();
    }

    /**
     * Determines length of player turn based on resources left
     */
    private static void determineTimeRemaining() {
//        TODO: Consider food and energy minimums

        if (getInstance().round >= MAX_ROUNDS) {
            endGame();
        } else if (getInstance().phase == GamePhase.PlayerTurnPhase) {
            Player player = getActivePlayer();
            int foodCount = player.getFood();

            int foodRequirement = FOOD_REQUIREMENTS[getRound() - 1];

            if (foodCount == 0) {
//            If you have no food, turn lasts 5 seconds
                getInstance().timeRemaining = TIME_NO_FOOD;
            } else if (foodCount > 0 && foodCount < foodRequirement) {
//            If you have food but not enough as the round demands, turn is 30 seconds
                getInstance().timeRemaining = TIME_FOOD_LOW;
            } else {
                getInstance().timeRemaining = TIME_DEFAULT;
            }
        } else {
            getInstance().timeRemaining = TIME_DEFAULT;
        }

    }

    /**
     * If player turn begins and player isn't the lowest scoring player, a random event should
     * happen
     */
    private static void handleRandomEvents() {
        if (getInstance().phase == GamePhase.PlayerTurnPhase &&
                getInstance().players.indexOf(getInstance().activePlayer) == 0) {
            MasterController.generateRandomGoodEvent();
        } else if (getInstance().phase == GamePhase.PlayerTurnPhase) {
            MasterController.generateRandomEvent();
        } else {
            MasterController.clearRandomEvent();
        }
    }

    /**
     * When a player's turn begins, production must be computed immediately
     */
    private static void handleProductionIfApplicable() {
        if (getInstance().phase == GamePhase.PlayerTurnPhase) {
            getInstance().activePlayer.handleProduction();
        }
    }

    public static void endGame() {
//        TODO: End game somehow
    }

    public static void changeTimeRemaining(int deltaT) {
        getInstance().timeRemaining += deltaT;
    }

    public static int getTimeRemaining() {
        return getInstance().timeRemaining;
    }

    public static void resetTime() {
        getInstance().timeRemaining = TIME_DEFAULT;
    }

    public static List<Player> getPlayers() {
        if (getInstance().players == null) {
            getInstance().players = new ArrayList<>();
        }
        return getInstance().players;
    }

    public static void setPlayers(List<Player> players) {
        getInstance().players = players;
    }

    public static void addPlayer(Player player) {
        if (getInstance().players == null) {
            getInstance().players = new ArrayList<>();
        }
        getInstance().players.add(player);
    }

    public static Resource getActiveMuleType() {
        return getInstance().activeMuleType;
    }

    public static void setActiveMuleType(Resource activeMuleType) {
        getInstance().activeMuleType = activeMuleType;
    }

    public static void clearActiveMuleType() {
        setActiveMuleType(null);
    }

    public static void beginMulePlacementPhase() {
        getInstance().phase = GamePhase.MulePlacementPhase;
    }

    public static void endMulePlacementPhase() {
        getInstance().phase = GamePhase.PlayerTurnPhase;
    }


    /**
     * Redefine the single instance of a singleton using the provided source
     * @param source, the source object
     */
    public static void unpack(GameDuration source) {
        instance = source;
        if (getInstance().propertiesMap == null) {
            getInstance().propertiesMap = new HashMap<>();
        }
    }

    /**
     * Redefine the single instance of a singleton using the provided source, which is JSON
     * @param jsonSource
     */
    public static void unpackfromJson(String jsonSource) {
        GameDuration source = Packer.unpack(jsonSource, GameDuration.class);
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
