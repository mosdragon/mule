package edu.gatech.cs2340.sharkbait.model;

import com.google.gson.*;
import com.google.gson.stream.JsonWriter;
import edu.gatech.cs2340.trydent.log.Log;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by osama on 11/2/15.
 */
public class LocalGameSaves {

    private static final String FILEPATH = "/gamesaves.json";
    private static final String GAME_SAVES_FIELD = "gameSaves";

    private static LocalGameSaves instance;

    private LocalGameSaves() {}

    private static LocalGameSaves getInstance() {
        if (instance == null) {
            instance = new LocalGameSaves();
        }
        return instance;
    }

    private JsonElement readFile() {
        URI filepath;
        FileReader fileReader;
        JsonElement json = null;

        try {
            filepath = getClass().getResource(FILEPATH).toURI();
            File file = new File(filepath);
            if (file.exists()) {
                Log.debug("FILE EXISTS");
                fileReader = new FileReader(new File(filepath));

                JsonParser parser = new JsonParser();
                json = parser.parse(fileReader);
                fileReader.close();
            } else {
                Log.debug("FILE DOES NOT EXIST");
                json = new JsonObject();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (json != null) {
            Log.debug(json.toString());
        } else {
            Log.debug("JSON NULL");
        }

        return json;

    }

    private void writeFile(JsonElement fileContents) {
        URI filepath;
        FileWriter fileWriter;

        try {
            filepath = getClass().getResource(FILEPATH).toURI();
            fileWriter = new FileWriter(new File(filepath));
            fileWriter.write(fileContents.toString());
            fileWriter.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<GameSave> getGameSaves() {
        Gson gson = new Gson();
        JsonElement fileContentsJson = getInstance().readFile();
        List<GameSave> saves = new ArrayList<>();

        if (!fileContentsJson.isJsonNull()) {

            JsonObject fileContents = fileContentsJson.getAsJsonObject();

            if (fileContents.has(GAME_SAVES_FIELD)) {
                JsonArray gameSaves = fileContents.getAsJsonArray(GAME_SAVES_FIELD);

                for (JsonElement gameSave : gameSaves) {
                    if (gameSave != null && !gameSave.isJsonNull()) {
                        GameSave tempSave = gson.fromJson(gameSave, GameSave.class);
                        saves.add(tempSave);
                    }
                }
            }
        }

        return saves;
    }

    public static void saveGameSave(GameSave latestGameSave) {
        List<GameSave> saves = getGameSaves();
        saves.add(latestGameSave);
        Gson gson = new Gson();
        JsonArray gameSaves = new JsonArray();
        for (GameSave save : saves) {
            JsonElement saveJson = gson.toJsonTree(save);
            gameSaves.add(saveJson);
        }

        JsonObject fileContents = new JsonObject();
        fileContents.add(GAME_SAVES_FIELD, gameSaves);
        getInstance().writeFile(fileContents);
    }
}
