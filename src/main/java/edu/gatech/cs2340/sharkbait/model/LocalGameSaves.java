package edu.gatech.cs2340.sharkbait.model;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

/**
 * Created by osama on 11/2/15.
 */
public class LocalGameSaves {

    private static final String GAME_SAVES = "gameSaves";
    private static final String EMPTY_ARRAY = "[]";

    private LocalGameSaves() {}

    public static List<GameSave> loadGameSaves() {
        Preferences prefs = Preferences.userRoot();
        String gameSavesArray = prefs.get(GAME_SAVES, EMPTY_ARRAY);

        Gson gson = new Gson();
        Type typeOfList = new TypeToken<List<GameSave>>(){}.getType();
        List<GameSave> saves = gson.fromJson(gameSavesArray, typeOfList);

        return saves;
    }

    private static void storeGameSaves(List<GameSave> saves) {
        Preferences prefs = Preferences.userRoot();
        Gson gson = new Gson();
        String gameSavesArray = gson.toJson(saves);

        prefs.put(GAME_SAVES, gameSavesArray);
        try {
            prefs.flush();
        } catch (BackingStoreException e) {
            e.printStackTrace();
        }
    }

    public static void addGameSave(GameSave latestGameSave) {
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
