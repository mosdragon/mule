package edu.gatech.cs2340.sharkbait.model;

import com.google.gson.JsonObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import edu.gatech.cs2340.sharkbait.controller.MasterController;
import edu.gatech.cs2340.sharkbait.util.Player;
import edu.gatech.cs2340.trydent.log.Log;
import org.bson.Document;

/**
 * Connets to MongoDB and handles all load/save operations.
 */
public final class MongoPersistence {

  /**
   * MasterController tag.
   */
  private static final String MASTER_CONTROLLER = "MasterController";

  /**
   * GameConfigs tag.
   */
  private static final String GAME_CONFIGS = "GameConfigs";

  /**
   * GameDuration tag.
   */
  private static final String GAME_DURATION = "GameDuration";

  /**
   * Store tag.
   */
  private static final String STORE = "Store";

  /**
   * gameId tag.
   */
  private static final String GAME_ID = "gameId";

  /**
   * The actual mongo client that creates the connection to db.
   */
  private static MongoClient client;

  /**
   * The connection uri for mongo.
   */
  private static MongoClientURI uri;

  /**
   * Want only a single result from our find.
   */
  private static final int SINGLE_RESULT = 1;

  /**
   * Private constructor to prevent instantiation.
   */
  private MongoPersistence() {
  }

  /**
   * Helper method to connect to remote Mongo instance.
   */
  private static void connect() {
      uri = new MongoClientURI(Constants.MONGO_CONNECTION);
      client = new MongoClient(uri);
  }

  /**
   * Save the game entirely in its current state.
   */
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

      MongoDatabase database = client.getDatabase(Constants.DB_NAME);
      Document document = Document.parse(gameStateJson);
      MongoCollection<Document> collection = database
          .getCollection(Constants.GAME_SAVES);

      if (collection != null) {
          collection.insertOne(document);
      }

  }

  /**
   * Pass in a gameId, and the fetch will be made from Mongo to
   * get all the needed fields to reconstruct the game again.
   * @param gameSave the actual gameSave used to retrieve the game
   */
  public static void loadGame(final GameSave gameSave) {
      connect();
      MongoDatabase database = client.getDatabase(Constants.DB_NAME);
      MongoCollection<Document> collection = database
          .getCollection(Constants.GAME_SAVES);


      FindIterable<Document> foundDocuments = collection
              .find(new Document(GAME_ID, gameSave.getGameId()))
              .limit(SINGLE_RESULT);

      Document gameSaveDocument = null;
//        There will only be one document matching the gameId at most
      for (Document doc : foundDocuments) {
          gameSaveDocument = doc;
      }

      if (gameSaveDocument != null) {

        String gameDuration = gameSaveDocument.getString(GAME_DURATION);
        GameDuration.unpackFromJson(gameDuration);

          String gameConfigs = gameSaveDocument.getString(GAME_CONFIGS);
          GameConfigs.unpackfromJson(gameConfigs);

          String masterController = gameSaveDocument
              .getString(MASTER_CONTROLLER);
          MasterController.unpackfromJson(masterController);

          for (Player player : GameDuration.getPlayers()) {
              player.unpack();
          }
          MasterController.initializeTimeline();
          MasterController.resumeTime();

          Log.debug("Everything deserialized");

      } else {
          Log.debug("No result found. Starting New Game");
      }

  }

}
