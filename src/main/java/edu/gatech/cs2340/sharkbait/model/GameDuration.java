package edu.gatech.cs2340.sharkbait.model;


import edu.gatech.cs2340.sharkbait.controller.MasterController;
import edu.gatech.cs2340.sharkbait.util.GamePhase;
import edu.gatech.cs2340.sharkbait.util.Player;
import edu.gatech.cs2340.sharkbait.util.Property;
import edu.gatech.cs2340.sharkbait.util.Resource;
import javafx.scene.control.Button;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Collections;
import java.util.ArrayList;
import java.io.Serializable;

/**
 * class that handles logistics of the length and.
 * Duration of the game
 * @author Osama
 * @version 1
 */
public final class GameDuration implements Serializable, Packable {
    /**
     *  Food requirement by round. Index offset by 1
     */
    private static final int[] FOOD_REQUIREMENTS = {3, 3, 3, 3, 4, 4,
        4, 4, 5, 5, 5, 5};
    /**
     * Constant for time if no food.
     */
    private static final int TIME_NO_FOOD = 5;
    /**
     * Constant for time if food low.
     */
    private static final int TIME_FOOD_LOW = 30;
    /**
     * Constant for the default time.
     */
    private static final int TIME_DEFAULT = 50;

    /**
     * Constant for max round.
     */
    private static final int MAX_ROUNDS = 12;

    /**
     * List of players.
     */
    private List<Player> players;

    /**
     * current round of game.
     */
    private int round;

    /**
     * current turn of round.
     */
    private int turn;

    /**
     * boolean to determine if game has begun.
     */
    private boolean begun = false;

    /**
     * boolean to determine if game is paused.
     */
    private boolean paused = false;
    /**
     * Switches GamePhase to LandBuyPhase.
     */
    private GamePhase phase = GamePhase.LandBuyPhase;

    /**
     * Time left of the turn, in seconds.
     */
    private int timeRemaining;

    /**
     * The active player in the game.
     */
    private Player activePlayer;
    /**
     * Holds the type of the mule if one is just bought,
     * clear it once it is set on a property.
     */
    private Resource activeMuleType;
    /**
     * Maps UI Buttons to properties in the game.
     */
    private transient Map<Button, Property> propertiesMap;

    /**
     * Creates an instance of GameDuration.
     */
    private static transient  GameDuration instance;

    /**
     * Constructor for Game Duration.
     * Initializes with activePlayer set to null, round set to 1
     * and turn set to 0.
     */
    private GameDuration() {
        activePlayer = null;
        round = 1;
        turn = 0;
    }

    /**
     * gets the instance of gameDuration.
     * if instance is null creates new instance of GameDuration
     * * @return the instance of the GameDuration
     * */
    private static GameDuration getInstance() {
        if (instance == null) {
            instance = new GameDuration();
        }
        return instance;
    }
    /**
     * Determine if Game has begun.
     * @return a boolean determining if game has begun
     */
    public static boolean hasBegun() {
        return getInstance().begun;
    }

    /**
     * Determine if Game is paused.
     * @return a boolean determining if game is paused
     */
    public static boolean isPaused() {
        return getInstance().paused;
    }

    /**
     * pauses the gameDuration.
     */
    public static void pause() {
        getInstance().paused = true;
    }

    /**
     * resumes the gameDuration.
     */
    public static void resume() {
        getInstance().paused = false;
    }

    /**
     * begins the GameDuration.
     */
    public static void begin() {
        getInstance().begun = true;
        getInstance().phase = GamePhase.LandBuyPhase;
        resetTime();
        getInstance().propertiesMap = new HashMap<>();
    }
    /**
     * gets the Active Player.
     * @return the active player
     */
    public static Player getActivePlayer() {
        if (getInstance().activePlayer == null && hasBegun()) {
            getInstance().activePlayer = getPlayers().get(getInstance().turn);
            resetTime();
        }
        return getInstance().activePlayer;
    }


    /**
     * gets the property at a specific button.
     * @param button the button to which to get property form
     * @return the property at the button passed in
     */
    public static Property fetchProperty(final Button button) {
        Property property = getInstance().propertiesMap.get(button);
        if (property == null) {
            property = new Property(button);
            getInstance().propertiesMap.put(button, property);
        }
        return property;
    }

    /**
     * Adds property to a button.
     * @param button the button to add property to
     * @param property the property to add to the button
     */
    public static void addProperty(final Button button,
                                   final Property property) {
        if (getInstance().propertiesMap == null) {
            getInstance().propertiesMap = new HashMap<>();
        }
        getInstance().propertiesMap.put(button, property);
    }


    /**
     * gets the current round.
     * @return the current round
     *
     */
    public static int getRound() {
        return getInstance().round;
    }

    /**
     * gets the current GamePhase.
     * @return the current GamePhase
     */
    public static GamePhase getPhase() {
        return getInstance().phase;
    }

    /**
     * ends the current turn and increments turn.
     * sets activePLayer to next player.
     */
    public static void endTurn() {
        getInstance().turn++;
        if (getInstance().turn >= GameConfigs.getNumPlayers()) {
            getInstance().turn = 0;
            if (getInstance().phase != GamePhase.PlayerTurnPhase) {
                getInstance().phase = GamePhase.PlayerTurnPhase;
            } else {
//                End of turn. Sort the list of players
                Collections.sort(getPlayers());
                getInstance().phase = GamePhase.LandBuyPhase;
                getInstance().round++;
            }
        }
        getInstance().activePlayer = getPlayers().get(getInstance().turn);
        determineTimeRemaining();
        handleRandomEvents();
        handleProductionIfApplicable();
    }

    /**
     * Determines length of player turn based on resources left.
     */
    private static void determineTimeRemaining() {

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
//     If you have food but not enough as the round demands, turn is 30 seconds
                getInstance().timeRemaining = TIME_FOOD_LOW;
            } else {
                getInstance().timeRemaining = TIME_DEFAULT;
            }
        } else {
            getInstance().timeRemaining = TIME_DEFAULT;
        }

    }

    /**
     * If player turn begins and player isn't
     * the lowest scoring player, a random event should.
     * happen
     */
    private static void handleRandomEvents() {
        if (getInstance().phase == GamePhase.PlayerTurnPhase
                && getInstance().players.indexOf(getInstance().activePlayer)
                == 0) {
            MasterController.generateRandomGoodEvent();
        } else if (getInstance().phase == GamePhase.PlayerTurnPhase) {
            MasterController.generateRandomEvent();
        } else {
            MasterController.clearRandomEvent();
        }
    }

    /**
     * When a player's turn begins, production must be computed immediately.
     */
    private static void handleProductionIfApplicable() {
        if (getInstance().phase == GamePhase.PlayerTurnPhase) {
            getInstance().activePlayer.handleProduction();
        }
    }
    /**
     * Ends the game.
     */
    private static void endGame() {
//       End game somehow
    }

    /**
     * changes TimeRemaining for a player turn to deltaT.
     * @param deltaT the int to change TimeRemaining too.
     */
    public static void changeTimeRemaining(final int deltaT) {
        getInstance().timeRemaining += deltaT;
    }

    /**
     * gets the time remaining.
     * @return the time remaining
     */
    public static int getTimeRemaining() {
        return getInstance().timeRemaining;
    }

    /**
     * resets the timeRemaining to the default time.
     */
    private static void resetTime() {
        getInstance().timeRemaining = TIME_DEFAULT;
    }

    /**
     * gets list of players.
     * @return list of players
     */
    public static List<Player> getPlayers() {
        if (getInstance().players == null) {
            getInstance().players = new ArrayList<>();
        }
        return getInstance().players;
    }

    /**
     * adds a player to the list of players.
     * @param player the player to be added to the list
     */
    public static void addPlayer(final Player player) {
        if (getInstance().players == null) {
            getInstance().players = new ArrayList<>();
        }
        getInstance().players.add(player);
    }

    /**
     * gets the activeMuleType.
     * @return the activeMuleType
     */
    public static Resource getActiveMuleType() {
        return getInstance().activeMuleType;
    }

    /**
     * sets the Active Mule type to Mule Type passed in.
     * @param activeMuleType the muleType to set ActiveMule type too.
     */
    public static void setActiveMuleType(final Resource activeMuleType) {
        getInstance().activeMuleType = activeMuleType;
    }

    /**
     * sets activeMuleType to null.
     */
    public static void clearActiveMuleType() {
        setActiveMuleType(null);
    }

    /**
     * changes phase to MulePlacementPhase.
     */
    public static void beginMulePlacementPhase() {
        getInstance().phase = GamePhase.MulePlacementPhase;
    }

    /**
     * ends MulePlacementPhase and sets phase to PlayerTurnPhase.
     */
    public static void endMulePlacementPhase() {
        getInstance().phase = GamePhase.PlayerTurnPhase;
    }


    /**
     * Redefine the single instance of a singleton using the provided source.
     * @param source the source object
     */
    private static void unpack(final GameDuration source) {
        instance = source;
        if (getInstance().propertiesMap == null) {
            getInstance().propertiesMap = new HashMap<>();
        }
    }

    /**
     * Redefine the single instance of a singleton using
     * the provided source, which is JSON.
     * @param jsonSource the jsonSource
     */
    public static void unpackfromJson(final String jsonSource) {
        GameDuration source = Packer.unpack(jsonSource, GameDuration.class);
        unpack(source);
    }

    /**
     * Serialized instance as JSON.
     * @return a JSON version of this object
     */
    public static String packAsJson() {
        return getInstance().pack();
    }

}
