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
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Observable;
import java.util.Random;
import java.util.List;
import java.util.Set;
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

    private static class Coordinate {
        private int row;
        private int col;
        private Node button;
        private Node image;

        public Coordinate(int mRow, int mCol) {
            row = mRow;
            col = mCol;
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof  Coordinate)) {
                return false;
            }
            Coordinate other = (Coordinate) obj;
            return row == other.row && col == other.col;
        }

        @Override
        public int hashCode() {
            return (row * 13 + col * 13 * 11 * 61);
        }

        public Coordinate clone() {
            Coordinate coord = new Coordinate(row, col);
            return coord;
        }
    }


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

//            m is the calculation factor
            int m = getMultiplier();

            int eventId = getInstance().random.nextInt(14) + 1;

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
                case 8:
                    event = ("TRIBBLES TOOK $" + 10 * m + " FROM YOUR BANK ACCOUNT. DON'T ASK HOW.");
                    player.changeMoney(-10 * m);
                    break;
                case 9:
                    event = ("YOU ATE 2 PIECES OF ORE BECAUSE YOU ARE STARVING.");
                    player.changeOre(-2);
                    break;
                case 10:
                    event = ("YOU GAVE 5 PIECES OF FOOD TO YOUR MOM.");
                    player.changeFood(-5);
                    break;
                case 11:
                    event = ("YOU GOT A COOKIE FROM GRANDMA.");
                    player.changeFood(1);
                    break;
                case 12:
                    event = ("YOU PILLAGED ANOTHER PLAYER AND GOT A TON OF RANDOM STUFF.");
                    player.changeMoney(12);
                    player.changeEnergy(2);
                    player.changeFood(1);
                    player.changeOre(2);
                    break;
                case 13:
                    event = ("ONE OF YOUR FAN-GIRLS DIED. SHE LEFT HER INHERITANCE OF $" + 9*m + " Dollars.");
                    player.changeMoney(9 * m);
                    break;
                case 14:
                    event = ("YOU LOST YOUR PANTS AND HAD TO BARTER FOR NEW ONES WITH 2 PIECES OF FOOD.");
                    player.changeFood(-2);
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
            Log.debug("Random GOOD event occurring for: " + player.getName());

//            m is the calculation factor
            int m = getMultiplier();

            int eventId = random.nextInt(8) + 1;

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
                    player.changeMoney(8 * m);
                    break;
                case 4:
                    event = ("YOU FOUND A DEAD MOOSE RAT AND SOLD THE HIDE FOR $" + 2*m);
                    player.changeMoney(2*m);
                    break;
                case 5:
                    event = ("YOU FOUND A GIANT BATTERY AND GOT " + 2*m + " ENERGY.");
                    player.changeEnergy(2*m);
                    break;
                case 6:
                    event = ("YOU GOT SOME MONEY BY RECYCLING A CAN.");
                    player.changeMoney(1);
                    break;
                case 7:
                    event = ("YOU LOST THE LOTTERY BUT THE GUY WHO WON GAVE YOU 3 PIECES OF ORE.");
                    player.changeOre(3);
                    break;
                case 8:
                    event = ("YOU KILLED A DEER AND GOT 10 PIECES OF FOOD.");
                    player.changeFood(10);
                    break;
            }
            changeSceneToEvent(event);
        }
        getInstance().gameMapView.handleRandomEvent(event);
        getInstance().townMapView.handleRandomEvent(event);
    }

    public static void roundRandomEvent() {
        String event = "ROUND EVENT - ALL PLAYERS: ";
        List<Player> players = GameDuration.getPlayers();
        Random random = new Random();
        int eventId = random.nextInt(5) + 1;
        switch(eventId) {
            case 1:
                event += " EVERYONE GETS 2 ORE.";
                for (Player p : players) {
                    p.changeOre(2);
                }
                break;
            case 2:
                event += " EVERYONE GETS $200";
                for (Player p : players) {
                    p.changeMoney(200);
                }
                break;
            case 3:
                event += " EVERYONE GETS 5 FOOD.";
                for (Player p : players) {
                    p.changeFood(2);
                }
                break;
            case 4:
                event += " EVERYONE GETS 3 ENERGY.";
                for (Player p : players) {
                    p.changeEnergy(3);
                }
                break;
            case 5:
                event += " EVERYONE GETS 1 ORE AND 4 FOOD.";
                for (Player p : players) {
                    p.changeFood(4);
                    p.changeOre(1);
                }
                break;
        }
        changeSceneToEvent(event);
    }

    public static int getMultiplier() {
        int round = GameDuration.getRound();
        int m = 1;
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
        return m;
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


    public static void setupGameMap() {
        if (GameConfigs.getMapType() == MapType.RandomMap) {
            Log.debug("Setup Game Map for random map");
            GridPane grid = getGrid();

            int num_cols = grid.getColumnConstraints().size();
            int num_rows = grid.getRowConstraints().size();

            Set<Coordinate> unused_coords = new HashSet<>();

            for (int r = 0; r < num_rows; r++)
            {
                for (int c = 0; c < num_cols; c++) {
                    Coordinate coord = new Coordinate(r, c);
                    unused_coords.add(coord);
                }
            }


            List<Coordinate> coord_keys = new ArrayList<>(unused_coords);

            List<Coordinate> shuffled_coords = new ArrayList<>(unused_coords);
            Collections.shuffle(shuffled_coords);

//            Map coordinate keys to shuffled coordinates
            Map<Coordinate, Coordinate> updates = new HashMap<>();

            for (int i = 0; i < coord_keys.size() && i < shuffled_coords.size(); i++) {
                updates.put(coord_keys.get(i), shuffled_coords.get(i));
            }

            List<Node> children = new ArrayList<>(grid.getChildren());

            for (Node child : children) {
                int row = GridPane.getRowIndex(child);
                int col = GridPane.getColumnIndex(child);
                Coordinate old_pos = new Coordinate(row, col);
                Coordinate next_pos = updates.get(old_pos);

                GridPane.setColumnIndex(child, next_pos.col);
                GridPane.setRowIndex(child, next_pos.row);

            }
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
        startMusic();
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

    public static void startMusic() {
        String path = "./src/music3.wav";
        Media media = new Media(new File(path).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);
        mediaPlayer.onRepeatProperty();
        mediaPlayer.setCycleCount(AudioClip.INDEFINITE);
//        MediaView mediaView = new MediaView(mediaPlayer);
    }
}
