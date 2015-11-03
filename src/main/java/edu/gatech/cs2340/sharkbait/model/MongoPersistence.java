package edu.gatech.cs2340.sharkbait.model;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import edu.gatech.cs2340.sharkbait.controller.MasterController;
import edu.gatech.cs2340.sharkbait.util.Player;
import edu.gatech.cs2340.trydent.log.Log;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;

/**
 * Created by osama on 10/25/15.
 */
public class MongoPersistence implements Persistence {

    private static MongoClient client;
    private static MongoClientURI uri;

    private static final int SINGLE_RESULT = 1;

    private MongoPersistence() {}

    private static void connect() {
        uri = new MongoClientURI(Constants.MONGO_CONNECTION);
        client = new MongoClient(uri);
    }

    public static void saveGame() {

        connect();

        GameSave save = new GameSave();
        LocalGameSaves.addGameSave(save);

        JsonObject gameState = new JsonObject();

        gameState.addProperty(MASTER_CONTROLLER, MasterController.packAsJson());
        gameState.addProperty(GAME_CONFIGS, GameConfigs.packAsJson());
        gameState.addProperty(GAME_DURATION, GameDuration.packAsJson());
        gameState.addProperty(STORE, Store.packAsJson());
        gameState.addProperty(GAME_ID, MasterController.getGameId());

        String gameStateJson = gameState.toString();

        Log.debug("SAVE GAME - MongoPersistence");
        Log.debug(gameStateJson);

//        TODO: Add code to save to DB using gameId
        MongoDatabase database = client.getDatabase(Constants.DB_NAME);

        Document document = Document.parse(gameStateJson);

        MongoCollection<Document> collection = database.getCollection(Constants.GAME_SAVES);

        if (collection != null) {
            collection.insertOne(document);
            Log.debug("Added it!");
        }

    }

    /**
     * Pass in a gameId, and the fetch will be made from Mongo to get all the needed fields to
     * reconstruct the game again
     * @param gameSave, the actual gameSave used to retrieve the game
     */
    public static void loadGame(GameSave gameSave) {
        connect();
        MongoDatabase database = client.getDatabase(Constants.DB_NAME);
        MongoCollection<Document> collection = database.getCollection(Constants.GAME_SAVES);


        FindIterable<Document> foundDocuments = collection
                .find(new Document(GAME_ID, gameSave.getGameId()))
                .limit(SINGLE_RESULT);

        Document gameSaveDocument = null;
//        There will only be one document matching the gameId at most
        for (Document doc : foundDocuments) {
            gameSaveDocument = doc;
        }

        if (gameSaveDocument != null) {

            String gameConfigs = gameSaveDocument.getString(GAME_CONFIGS);
            GameConfigs.unpackfromJson(gameConfigs);

            String gameDuration = gameSaveDocument.getString(GAME_DURATION);
            GameDuration.unpackfromJson(gameDuration);

            String masterController = gameSaveDocument.getString(MASTER_CONTROLLER);
            MasterController.unpackfromJson(masterController);

            MasterController.initializeTimeline();
            for (Player player : GameDuration.getPlayers()) {
                player.unpack();
            }
            MasterController.updateMessages();



            Log.debug("Everything deserialized");

        } else {
            Log.debug("No result found. Starting New Game");
        }

    }


}
