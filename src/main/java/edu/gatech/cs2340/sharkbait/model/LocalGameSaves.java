package edu.gatech.cs2340.sharkbait.model;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import edu.gatech.cs2340.trydent.log.Log;

import java.lang.reflect.Type;
import java.util.List;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

/**
 * Class for local game saves.
 */
public final class LocalGameSaves {

  /**
   * Game Saves string.
   */
  private static final String GAME_SAVES = "gameSaves";

  /**
   * Empty array.
   */
  private static final String EMPTY_ARRAY = "[]";

  /**
   * Empty constructor.
   */
  private LocalGameSaves() {
  }

  /**
   * Loads saved games.
   *
   * @return the list of the games
   */
  public static List<GameSave> loadGameSaves() {
    Preferences prefs = Preferences.userRoot();
    String gameSavesArray = prefs.get(GAME_SAVES, EMPTY_ARRAY);

    Gson gson = new Gson();
    Type typeOfList = new TypeToken<List<GameSave>>() {}.getType();
    return gson.fromJson(gameSavesArray, typeOfList);
  }

  /**
   * Stores the saved games.
   *
   * @param saves the saves.
   */
  private static void storeGameSaves(final List<GameSave> saves) {
    Preferences prefs = Preferences.userRoot();
    Gson gson = new Gson();
    String gameSavesArray = gson.toJson(saves);

    prefs.put(GAME_SAVES, gameSavesArray);
    try {
      prefs.flush();
    } catch (BackingStoreException e) {
      Log.debug(e.toString());
    }
  }

  /**
   * Adds a saved game.
   *
   * @param latestGameSave the latest game save.
   */
  public static void addGameSave(final GameSave latestGameSave) {
    List<GameSave> saves = loadGameSaves();
    saves.add(latestGameSave);
    Gson gson = new Gson();
    JsonArray gameSaves = new JsonArray();
    for (GameSave save : saves) {
      JsonElement saveJson = gson.toJsonTree(save);
      gameSaves.add(saveJson);
    }
    storeGameSaves(saves);

  }
}
