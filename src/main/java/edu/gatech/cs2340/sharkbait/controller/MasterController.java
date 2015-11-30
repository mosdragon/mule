package edu.gatech.cs2340.sharkbait.controller;

import edu.gatech.cs2340.sharkbait.model.*;
import edu.gatech.cs2340.sharkbait.util.Difficulty;
import edu.gatech.cs2340.sharkbait.util.MapType;
import edu.gatech.cs2340.sharkbait.util.Player;
import edu.gatech.cs2340.sharkbait.view.*;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.io.Serializable;

/**
 * A controller to serve as the glue between model and views.
 * Initiaizes all screens, switches the screen, manages time throughout game
 */
public final class MasterController implements Serializable, Packable {

  /**
   * Used to update timers.
   */
  private static final String TIME_LEFT = "Time Remaining: ";

  /**
   * Interval for timeline in milliseconds.
   */
  private static final int INTERVAL = 1000;
  /**
   * Single instance of this class. Helps serialization.
   */
  private static transient MasterController instance;
  /**
   * Game timeline to keep track of turns and rounds.
   */
  private transient Timeline timeline;
  /**
   * Game scenes.
   */
  private transient Scene configScene;
  /**
   * Game scenes.
   */
  private transient Scene gameMapScene;
  /**
   * Game scenes.
   */
  private transient Scene townMapScene;
  /**
   * Game scenes.
   */
  private transient Scene pauseScreenScene;
  /**
   * Game scenes.
   */
  private transient Scene eventScene;
  /**
   * Game scenes.
   */
  private transient Scene productionScene;
  /**
   * Game scenes.
   */
  private transient Scene loadScreenScene;
  /**
   * JavaFX stage for application's single window.
   */
  private transient Stage gameStage;
  /**
   * Game views. Used to switch screens.
   */
  private transient GameMapView gameMapView;
  /**
   * Game views. Used to switch screens.
   */
  private transient TownMapView townMapView;
  /**
   * Game views. Used to switch screens.
   */
  private transient PauseScreenView pauseScreenView;
  /**
   * Game views. Used to switch screens.
   */
  private transient PopupEventView popupEventView;
  /**
   * Game views. Used to switch screens.
   */
  private transient ProductionView productionView;
  /**
   * gameId used to associate a game play to a save in our db.
   * See @link{GameSave} for usage
   */
  private long gameId;

  /**
   * Initialize gameId.
   */
  private MasterController() {
    gameId = System.currentTimeMillis();
  }

  /**
   * Begin timeline for game.
   */
  public static void initializeTimeline() {
    instance.timeline = new Timeline(new KeyFrame(
        Duration.millis(INTERVAL),
        ae -> passTime()));
    instance.timeline.setCycleCount(Animation.INDEFINITE);
    instance.timeline.play();
  }

  /**
   * Used internally to get the MasterController instance.
   * Helps with static methods to do things like switch screens
   *
   * @return the instance of this controller
   */
  private static MasterController getInstance() {
    if (instance == null) {
      instance = new MasterController();
    }
    return instance;
  }

  /**
   * Passes time and updates views wherever time shows.
   */
  private static void passTime() {
    if (GameDuration.hasBegun() && !GameDuration.isPaused()) {
      passOneSecond();
      updateTimers();
      updateMessages();
    }
  }

  /**
   * Update timer views across the different screens.
   */
  private static void updateTimers() {
    if (GameDuration.hasBegun()) {
      int time = GameDuration.getTimeRemaining();
      instance.gameMapView.updateTimer(TIME_LEFT + time);
      instance.townMapView.updateTimer(TIME_LEFT + time);
      updateMessages();
    }
  }

  /**
   * Clear the views accross screens that display random event text.
   */
  public static void clearRandomEvent() {
    String event = "";
    instance.gameMapView.handleRandomEvent(event);
    instance.townMapView.handleRandomEvent(event);

  }

  /**
   * Generates a random event with a given probability and affects
   * a single player.
   */
  public static void generateRandomEvent() {

    Player player = GameDuration.getActivePlayer();
    String event = GameEvents.generateRandomEvent(player);
    if (!event.isEmpty()) {
      changeSceneToEvent(event);
    }
    instance.gameMapView.handleRandomEvent(event);
    instance.townMapView.handleRandomEvent(event);
  }

  /**
   * Used for low-score players to ensure random event that happens to them
   * is only good.
   */
  public static void generateRandomGoodEvent() {
    Player player = GameDuration.getActivePlayer();
    String event = GameEvents.generateRandomGoodEvent(player);
    if (!event.isEmpty()) {
      changeSceneToEvent(event);
    }
    instance.gameMapView.handleRandomEvent(event);
    instance.townMapView.handleRandomEvent(event);
  }

  /**
   * Update messages across all screens.
   */
  public static void updateMessages() {
    instance.gameMapView.updateMessages();
    instance.townMapView.updateMessages();
  }

  /**
   * Pass a single second in game. If time is up, end the turn
   * and switch to game map screen if needed.
   */
  private static void passOneSecond() {
    int oneSecond = -1;
    GameDuration.changeTimeRemaining(oneSecond);
    if (GameDuration.getTimeRemaining() <= 0) {
      GameDuration.endTurn();
      changeSceneToGameMap();
    }
  }

  /**
   * Initialize all scenes from the fxml resources.
   *
   * @param gameStage the JavaFX stage.
   */
  private static void constructScenes(final Stage gameStage) {
    instance.gameStage = gameStage;

    try {
      Parent configRoot = new FXMLLoader(instance.getClass()
          .getResource("/fxml/config/config_screen.fxml")).load();
      gameStage.setTitle("M.U.L.E");
      instance.configScene = new Scene(configRoot);

      Parent loadScreenRoot = new FXMLLoader(instance.getClass()
          .getResource("/fxml/config/load_screen.fxml")).load();
      gameStage.setTitle("M.U.L.E");
      instance.loadScreenScene = new Scene(loadScreenRoot);


      FXMLLoader configScreenLoader = new FXMLLoader(getInstance()
          .getClass().getResource("/fxml/config/config_screen.fxml"));
      Parent configSceneRoot = configScreenLoader.load();
      instance.configScene = new Scene(configSceneRoot);

      FXMLLoader gameMapLoader = new FXMLLoader(getInstance()
          .getClass().getResource("/fxml/game_map.fxml"));
      Parent gameMapRoot = gameMapLoader.load();
      instance.gameMapScene = new Scene(gameMapRoot);
      instance.gameMapView = gameMapLoader.getController();

      FXMLLoader townMapLoader = new FXMLLoader(getInstance()
          .getClass().getResource("/fxml/town_map.fxml"));
      Parent townMapRoot = townMapLoader.load();

      instance.townMapScene = new Scene(townMapRoot);
      instance.townMapView = townMapLoader.getController();

      FXMLLoader pauseScreenLoader = new FXMLLoader(getInstance()
          .getClass().getResource("/fxml/pause_screen.fxml"));
      Parent pauseScreenRoot = pauseScreenLoader.load();
      instance.pauseScreenScene = new Scene(pauseScreenRoot);
      instance.pauseScreenView = pauseScreenLoader.getController();

      FXMLLoader eventLoader = new FXMLLoader(getInstance()
          .getClass().getResource("/fxml/event.fxml"));
      Parent eventRoot = eventLoader.load();
      instance.eventScene = new Scene(eventRoot);
      instance.popupEventView = eventLoader.getController();

      FXMLLoader productionLoader = new FXMLLoader(getInstance()
          .getClass().getResource("/fxml/production.fxml"));
      Parent productionRoot = productionLoader.load();
      instance.productionScene = new Scene(productionRoot);
      instance.productionView = productionLoader.getController();

      instance.gameMapScene.setOnKeyPressed(event -> {
        if (event.getText().toLowerCase().equals("p")) {
          changeSceneToPauseScreen();
        }
      });

      instance.townMapScene.setOnKeyPressed(event -> {
        if (event.getText().toLowerCase().equals("p")) {
          changeSceneToPauseScreen();
        }
      });


    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Create the application given a game stage.
   *
   * @param gameStage the visible application screen.
   */
  public static void initialize(final Stage gameStage) {
    instance = new MasterController();
    constructScenes(gameStage);

    initializeTimeline();
    gameStage.setScene(instance.loadScreenScene);
    gameStage.show();

  }

  /**
   * Screen should show game map screen.
   */
  public static void changeSceneToGameMap() {
    instance.gameStage.setScene(instance.gameMapScene);
  }

  /**
   * Screen should show town map screen.
   */
  public static void changeSceneToTownMap() {
    instance.gameStage.setScene(instance.townMapScene);
  }

  /**
   * Screen should show config screen.
   */
  public static void changeSceneToConfig() {
    instance.gameStage.setScene(instance.configScene);
  }

  /**
   * Screen should show pause screen.
   */
  public static void changeSceneToPauseScreen() {
    pauseTime();
    instance.gameStage.setScene(instance.pauseScreenScene);
  }

  /**
   * Screen should show event occurrence screen.
   *
   * @param event the text of a random event
   */
  private static void changeSceneToEvent(final String event) {
    pauseTime();
    instance.gameStage.setScene(instance.eventScene);
    instance.popupEventView.setText(event);
  }

  /**
   * Screen should show production screen.
   */
  public static void changeSceneToProduction() {
    pauseTime();
    instance.gameStage.setScene(instance.productionScene);
  }

  /**
   * Pause the game timer.
   */
  private static void pauseTime() {
    GameDuration.pause();
  }

  /**
   * Resume the game timer.
   */
  public static void resumeTime() {
    GameDuration.resume();
  }

  /**
   * Save configuration settings from the config screens.
   *
   * @param numPlayers the number of players for the game.
   * @param difficulty the difficulty of the game.
   * @param mapType    the map generated.
   */
  public static void saveConfigs(final int numPlayers, final Difficulty
      difficulty, final MapType mapType) {

    GameConfigs.setGameDifficulty(difficulty);
    GameConfigs.setMapType(mapType);
    GameConfigs.setNumPlayers(numPlayers);
  }

  /**
   * Redefine the single instance of a singleton using the provided source.
   *
   * @param source the source MasterController instance
   */
  private static void unpack(final MasterController source) {
    Stage currentStage = instance.gameStage;
    instance = source;
    constructScenes(currentStage);
  }

  /**
   * Redefine the single instance of a singleton using the provided source,
   * which is JSON.
   *
   * @param jsonSource the string with a serialized MasterController.
   */
  public static void unpackfromJson(final String jsonSource) {
    if (instance.timeline != null) {
      instance.timeline.stop();
    }
    MasterController source = Packer.unpack(jsonSource,
        MasterController.class);
    unpack(source);
  }

  /**
   * Serialized instance as JSON.
   *
   * @return a JSONified version of this object
   */
  public static String packAsJson() {
    return instance.pack();
  }

  /**
   * Gives the GameMapView's grid.
   *
   * @return the gameMapView's grid.
   */
  public static GridPane getGrid() {
    return instance.gameMapView.getGrid();
  }

  /**
   * Returns the unique game id.
   *
   * @return the unique game id.
   */
  public static long getGameId() {
    return instance.gameId;
  }

  /**
   * Set a unique game id.
   *
   * @param gameIdField a unique game id.
   */
  public void setGameId(final long gameIdField) {
    instance.gameId = gameIdField;
  }
}
