package edu.gatech.cs2340.sharkbait.controller;

import edu.gatech.cs2340.sharkbait.model.GameConfigs;
import edu.gatech.cs2340.sharkbait.model.GameDuration;
import edu.gatech.cs2340.sharkbait.model.Packable;
import edu.gatech.cs2340.sharkbait.model.Packer;
import edu.gatech.cs2340.sharkbait.util.Difficulty;
import edu.gatech.cs2340.sharkbait.util.GamePhase;
import edu.gatech.cs2340.sharkbait.util.MapType;
import edu.gatech.cs2340.sharkbait.util.Player;
import edu.gatech.cs2340.sharkbait.view.*;
import edu.gatech.cs2340.trydent.log.Log;
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
import java.util.Random;
//import java.awt.event.KeyEvent;

/**
 * Created by osama on 9/23/15.
 */
public class MasterController implements Serializable, Packable {

//    Used to update timers
    private static final String TIME_LEFT = "Time Remaining: ";

//    For random events, which must occur 27% of the time to players that are not in last place
    private static final double CHANCE =  0.27;

//    Interval for timeline in milliseconds
    private static final int INTERVAL = 1000;


    private transient Random random;


    private transient Timeline timeline;

    private transient Scene configScene;
    private transient Scene gameMapScene;
    private transient Scene townMapScene;
    private transient Scene saveScene;
    private transient Scene eventScene;
    private transient Scene productionScene;
    private transient Scene loadScreenScene;

    private transient Stage gameStage;

    private transient GameMapView gameMapView;
    private transient TownMapView townMapView;
    private transient PauseScreenView pauseScreenView;
    private transient PopupEventView popupEventView;
    private transient ProductionView productionView;

    private transient static MasterController instance;

    private long gameId;


    private MasterController() {
        gameId = System.currentTimeMillis();
    }

    public static void initializeTimeline() {

//    Used to pass time every second
        getInstance().timeline = new Timeline(new KeyFrame(
                Duration.millis(INTERVAL),
                ae -> passTime()));
        getInstance().timeline.setCycleCount(Animation.INDEFINITE);
        getInstance().timeline.play();
    }

    public static MasterController getInstance() {
        if (instance == null) {
            instance = new MasterController();
        }
        return instance;
    }

    private static void passTime() {
        if (GameDuration.hasBegun() && !GameDuration.isPaused()) {
            passOneSecond();
            updateTimers();
            updateMessages();
        }
    }

    private static void updateTimers() {
        if (GameDuration.hasBegun()) {
            int time = GameDuration.getTimeRemaining();
            getInstance().gameMapView.updateTimer(TIME_LEFT + time);
            getInstance().townMapView.updateTimer(TIME_LEFT + time);
            updateMessages();
        }
    }

    public static void clearRandomEvent() {
        String event = "";
        getInstance().gameMapView.handleRandomEvent(event);
        getInstance().townMapView.handleRandomEvent(event);

    }

    public static void generateRandomEvent() {

        if (getInstance().random == null) {
            getInstance().random = new Random();
        }
        String event = "";

        double chanceOfEvent = getInstance().random.nextDouble();
        GamePhase phase = GameDuration.getPhase();
        Player player = GameDuration.getActivePlayer();

        if(phase == GamePhase.PlayerTurnPhase && chanceOfEvent <= CHANCE) {

            Log.debug("Random event occurring for: " + player.getName());

            int round = GameDuration.getRound();
//            m is the calculation factor
            int m = 0;

            switch (round) {
                case 1:
                    m = 25;
                    break;
                case 2:
                    m = 25;
                    break;
                case 3:
                    m = 25;
                    break;
                case 4:
                    m = 50;
                    break;
                case 5:
                    m = 50;
                    break;
                case 6:
                    m = 50;
                    break;
                case 7:
                    m = 50;
                    break;
                case 8:
                    m = 75;
                    break;
                case 9:
                    m = 75;
                    break;
                case 10:
                    m = 75;
                    break;
                case 11:
                    m = 75;
                    break;
                case 12:
                    m = 100;
                    break;
            }

            int eventId = getInstance().random.nextInt(7) + 1;

            switch (eventId) {
                case 1:
                    event = ("YOU JUST RECEIVED A PACKAGE FROM THE GT ALUMNI CONTAINING 3 FOOD AND 2 ENERGY UNITS.");
                    player.changeFood(3);
                    player.changeEnergy(2);
                    break;
                case 2:
                    event = ("A WANDERING TECH STUDENT REPAID YOUR HOSPITALITY BY LEAVING TWO BARS OF ORE.");
                    player.changeOre(2);
                    break;
                case 3:
                    event = ("THE MUSEUM BOUGHT YOUR ANTIQUE PERSONAL COMPUTER FOR $" + 8 * m);
                    player.changeMoney(8 * m);
                    break;
                case 4:
                    event = ("YOU FOUND A DEAD MOOSE RAT AND SOLD THE HIDE FOR $" + 2 * m);
                    player.changeMoney(2 * m);
                    break;
                case 5:
                    event = ("FLYING CAT-BUGS ATE THE ROOF OFF YOUR HOUSE. REPAIRS COST $" + 4 * m);
                    player.changeMoney(-4 * m);
                    break;
                case 6:
                    event = ("MISCHIEVOUS UGA STUDENTS BROKE INTO YOUR STORAGE SHED AND STOLE HALF YOUR FOOD.");
                    player.changeFood(-player.getFood() / 2);
                    break;
                case 7:
                    event = ("YOUR SPACE GYPSY IN-LAWS MADE A MESS OF THE TOWN. IT COST YOU $" + 6 * m + " TO CLEAN IT UP.");
                    player.changeMoney(-6 * m);
                    break;
            }
            changeSceneToEvent(event);
        }
        getInstance().gameMapView.handleRandomEvent(event);
        getInstance().townMapView.handleRandomEvent(event);
    }

    public static void generateRandomGoodEvent() {
        String event = "";
        Random random = new Random();
        double chanceOfEvent = random.nextDouble();
        GamePhase phase = GameDuration.getPhase();

        Player player = GameDuration.getActivePlayer();

        if(phase != GamePhase.LandBuyPhase && chanceOfEvent <= CHANCE) {
            int round = GameDuration.getRound();

            Log.debug("Random GOOD event occurring for: " + player.getName());

//            m is the calculation factor
            int m = 0;

            switch(round) {
                case 1: m = 25; break;
                case 2: m = 25; break;
                case 3: m = 25; break;
                case 4: m = 50; break;
                case 5: m = 50; break;
                case 6: m = 50; break;
                case 7: m = 50; break;
                case 8: m = 75; break;
                case 9: m = 75; break;
                case 10: m = 75; break;
                case 11: m = 75; break;
                case 12: m = 100; break;
            }

            int eventId = random.nextInt(4) + 1;

            switch(eventId) {
                case 1:
                    event = ("YOU JUST RECEIVED A PACKAGE FROM THE GT ALUMNI CONTAINING 3 FOOD AND 2 ENERGY UNITS.");
                    player.changeFood(3);
                    player.changeEnergy(2);
                    break;
                case 2:
                    event = ("A WANDERING TECH STUDENT REPAID YOUR HOSPITALITY BY LEAVING TWO BARS OF ORE.");
                    player.changeOre(2);
                    break;
                case 3:
                    event = ("THE MUSEUM BOUGHT YOUR ANTIQUE PERSONAL COMPUTER FOR $" + 8*m);
                    player.changeMoney(8*m);
                    break;
                case 4:
                    event = ("YOU FOUND A DEAD MOOSE RAT AND SOLD THE HIDE FOR $" + 2*m);
                    player.changeMoney(2*m);
                    break;
            }
            changeSceneToEvent(event);
        }
        getInstance().gameMapView.handleRandomEvent(event);
        getInstance().townMapView.handleRandomEvent(event);
    }

    public static void updateMessages() {
        getInstance().gameMapView.updateMessages();
        getInstance().townMapView.updateMessages();
    }

    private static void passOneSecond() {
        int oneSecond = -1;
        GameDuration.changeTimeRemaining(oneSecond);
        if (GameDuration.getTimeRemaining() <= 0) {
            GameDuration.endTurn();
//            New turns always begin on game map
            changeSceneToGameMap();
        }
    }


    private static void constructScenes(Stage gameStage) {
        getInstance().gameStage = gameStage;

        try {

            Parent configRoot = new FXMLLoader(getInstance().getClass().getResource
                    ("/fxml/config/config_screen.fxml")).load();
            gameStage.setTitle("M.U.L.E");
            getInstance().configScene = new Scene(configRoot);

            Parent loadScreenRoot = new FXMLLoader(getInstance().getClass().getResource
                    ("/fxml/config/load_screen.fxml")).load();
//                    ("/fxml/config/config_screen.fxml")).load();
            gameStage.setTitle("M.U.L.E");
            getInstance().loadScreenScene = new Scene(loadScreenRoot);


            FXMLLoader configScreenLoader = new FXMLLoader(getInstance().getClass().getResource
                    ("/fxml/config/config_screen.fxml"));
            Parent configSceneRoot = configScreenLoader.load();
            getInstance().configScene = new Scene(configSceneRoot);
//            configSceneView = configScreenLoader.getController();

            FXMLLoader gameMapLoader = new FXMLLoader(getInstance().getClass().getResource
                    ("/fxml/game_map.fxml"));
            Parent gameMapRoot = gameMapLoader.load();
            getInstance().gameMapScene = new Scene(gameMapRoot);
            getInstance().gameMapView = gameMapLoader.getController();

            FXMLLoader townMapLoader = new FXMLLoader(getInstance().getClass().getResource
                    ("/fxml/town_map.fxml"));
            Parent townMapRoot = townMapLoader.load();

            getInstance().townMapScene = new Scene(townMapRoot);
            getInstance().townMapView = townMapLoader.getController();

            FXMLLoader saveLoader = new FXMLLoader(getInstance().getClass().getResource("/fxml/save.fxml"));
            Parent saveRoot = saveLoader.load();
            getInstance().saveScene = new Scene(saveRoot);
            getInstance().pauseScreenView = saveLoader.getController();

            FXMLLoader eventLoader = new FXMLLoader(getInstance().getClass().getResource("/fxml/event.fxml"));
            Parent eventRoot = eventLoader.load();
            getInstance().eventScene = new Scene(eventRoot);
            getInstance().popupEventView = eventLoader.getController();

            FXMLLoader productionLoader = new FXMLLoader(getInstance().getClass().getResource("/fxml/production.fxml"));
            Parent productionRoot = productionLoader.load();
            getInstance().productionScene = new Scene(productionRoot);
            getInstance().productionView = productionLoader.getController();

            getInstance().gameMapScene.setOnKeyPressed(event -> {
                if (event.getText().toLowerCase().equals("p")) {
                    changeSceneToSave();
                }
            });

            getInstance().townMapScene.setOnKeyPressed(event -> {
                if (event.getText().toLowerCase().equals("p")) {
                    changeSceneToSave();
                }
            });


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void initialize(Stage gameStage) {
        constructScenes(gameStage);

        initializeTimeline();
        gameStage.setScene(getInstance().loadScreenScene);
        gameStage.show();

    }

    public static void changeSceneToGameMap() {
        getInstance().gameStage.setScene(getInstance().gameMapScene);
    }

    public static void changeSceneToTownMap() {
        getInstance().gameStage.setScene(getInstance().townMapScene);
    }

    public static void changeSceneToConfig() {
        getInstance().gameStage.setScene(getInstance().configScene);
    }

    public static void changeSceneToSave() {
        pauseTime();
        getInstance().gameStage.setScene(getInstance().saveScene);
    }

    public static void changeSceneToEvent(String event) {
        pauseTime();
        getInstance().gameStage.setScene(getInstance().eventScene);
        getInstance().popupEventView.setText(event);
    }

    public static void changeSceneToProduction() {
        pauseTime();
        getInstance().gameStage.setScene(getInstance().productionScene);
    }

    public static void pauseTime() {
        GameDuration.pause();
    }

    public static void resumeTime() {
        GameDuration.resume();
    }

    public static GameMapView getGameMapView() {
        return getInstance().gameMapView;
    }

    public static TownMapView getTownMapView() {
        return getInstance().townMapView;
    }

    public static void saveConfigs(int numPlayers, Difficulty difficulty, MapType mapType) {
        GameConfigs.setGameDifficulty(difficulty);
        GameConfigs.setMapType(mapType);
        GameConfigs.setNumPlayers(numPlayers);
    }

    /**
     * Redefine the single instance of a singleton using the provided source
     * @param source, the source object
     */
    public static void unpack(MasterController source) {
        Stage currentStage = getInstance().gameStage;
        instance = source;
        constructScenes(currentStage);
    }

    /**
     * Redefine the single instance of a singleton using the provided source, which is JSON
     * @param jsonSource
     */
    public static void unpackfromJson(String jsonSource) {
        if (getInstance().timeline != null) {
            getInstance().timeline.stop();
        }
        MasterController source = Packer.unpack(jsonSource, MasterController.class);
        unpack(source);
    }

    /**
     * Serialized instance as JSON
     * @return a JSONified version of this object
     */
    public static String packAsJson() {
        return getInstance().pack();
    }

    public static GridPane getGrid() {
        return getInstance().gameMapView.getGrid();
    }

    public static long getGameId() {
        return getInstance().gameId;
    }

    public void setGameId(long gameId) {
        getInstance().gameId = gameId;
    }
}
