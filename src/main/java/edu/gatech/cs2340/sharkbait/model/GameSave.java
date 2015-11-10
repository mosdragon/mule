package edu.gatech.cs2340.sharkbait.model;

import edu.gatech.cs2340.sharkbait.controller.MasterController;

import java.io.Serializable;

/**
 * Class to paid unique game saves with timestamps. Used
 * with @link{MongoPersistence} to save/load games from DB
 */
public final class GameSave implements Serializable, Comparable<GameSave> {

  /**
   * The unique game identifier.
   */
    private long gameId;

  /**
   * The numerical value of time in milliseconds.
   */
    private long timeStamp;

  /**
   * String format template for string representation.
   */
    private static final String TEMPLATE = "gameId:%d, timeStamp:%d";

  /**
   * Empty constructor to use existing gameId and timeStamp values.
   */
    public GameSave() {
        gameId = MasterController.getGameId();
        timeStamp = System.currentTimeMillis();
    }

  /**
   * Constructor that sets gameId and timeStamp passed in.
   * @param gameIdField the unique game identifier
   * @param timeStampField the timestamp in milliseconds of the game save
   */
    public GameSave(final long gameIdField, final long timeStampField) {
        gameId = gameIdField;
        timeStamp = timeStampField;
    }

  /**
   * Returns the gameId for this game.
   * @return the gameId
   */
    public long getGameId() {
        return gameId;
    }

  /**
   * Sets the gameId.
   * @param gameIdField the gameId to set to
   */
    public void setGameId(final long gameIdField) {
        gameId = gameIdField;
    }

  /**
   * Gets timestamp for save.
   * @return the timestamp
   */
    public long getTimeStamp() {
        return timeStamp;
    }

  /**
   * Set the timestamp.
   * @param timeStampField the timeStamp in milliseconds to be set
   */
    public void setTimeStamp(final long timeStampField) {
        timeStamp = timeStampField;
    }


    @Override
    public String toString() {
        String representation = String.format(TEMPLATE, getGameId(),
            getTimeStamp());
        return representation;
    }

  /**
   * Compare this GameSave to another.
   * @param o the other GameSave
   * @return a int comparing the timestamp of ths and the other GameSave
   */
    @Override
    public int compareTo(final GameSave o) {
        Long myTimestamp = timeStamp;
        Long otherTimestamp = o.timeStamp;
        return myTimestamp.compareTo(otherTimestamp);
    }
}
