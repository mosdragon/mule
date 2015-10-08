package edu.gatech.cs2340.sharkbait.controller;

import edu.gatech.cs2340.sharkbait.model.GameDuration;
import edu.gatech.cs2340.sharkbait.util.GamePhase;
import edu.gatech.cs2340.sharkbait.util.Player;
import edu.gatech.cs2340.sharkbait.util.Property;
import edu.gatech.cs2340.sharkbait.util.PropertyType;
import edu.gatech.cs2340.sharkbait.view.GameMapView;
import edu.gatech.cs2340.sharkbait.view.TownMapView;
import edu.gatech.cs2340.trydent.log.Log;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.swing.text.html.CSS;
import java.io.IOException;

/**
 * Created by osama on 9/23/15.
 */
public class MasterController {

//    Used to update timers
    private static final String TIME_LEFT = "Time Remaining: ";

//    Used for Grid Button Press
    private static final String CSS_TRANSPARENT = "-fx-background-color:rgba(0,0,0,0);";
    private static final String PLAINS = "plain";
    private static final String MOUNTAIN = "mountain";
    private static final String RIVER = "river";

    private static Scene configScene;
    private static Scene gameMapScene;
    private static Scene townMapScene;

    private static Stage gameStage;

    private static MasterController instance;

    private static GameMapView gameMapView;
    private static TownMapView townMapView;

    private Timeline timeline;

    private MasterController() {
//          Used to pass time every second
        timeline = new Timeline(new KeyFrame(
                Duration.millis(1000),
                ae -> passTime()));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    public static MasterController getInstance() {
        if (instance == null) {
            instance = new MasterController();
        }
        return instance;
    }

    private void passTime() {
        if (GameDuration.hasBegun()) {
            passOneSecond();
            updateTimers();
            updateMessages();
        }
    }

    private void updateTimers() {
        if (GameDuration.hasBegun()) {
            int time = GameDuration.getTimeRemaining();
            gameMapView.updateTimer(TIME_LEFT + time);
            townMapView.updateTimer(TIME_LEFT + time);
            updateMessages();
        }
    }



    private void updateMessages() {
        gameMapView.updateMessages();
        townMapView.updateMessages();
    }

    private static void passOneSecond() {
        int oneSecond = -1;
        GameDuration.changeTimeRemaining(oneSecond);
        if (GameDuration.getTimeRemaining() == 0) {
            GameDuration.endTurn();
//            Changes to game map if you're in town
            changeSceneToGameMap();
        }
    }




    public void initialize(Stage gameStage) {
        MasterController.gameStage = gameStage;

        try {
            Parent configRoot = new FXMLLoader(getClass().getResource
                    ("../view/fxml/config/config_screen.fxml")).load();
            gameStage.setTitle("M.U.L.E");
            configScene = new Scene(configRoot);

            gameStage.setScene(configScene);
            gameStage.show();

            FXMLLoader gameMapLoader = new FXMLLoader(getInstance().getClass().getResource
                    ("../view/fxml/game_map.fxml"));
            Parent gameMapRoot = gameMapLoader.load();
            gameMapScene = new Scene(gameMapRoot);
            gameMapView = gameMapLoader.getController();

            FXMLLoader townMapLoader = new FXMLLoader(getInstance().getClass().getResource
                    ("../view/fxml/town_map.fxml"));
            Parent townMapRoot = townMapLoader.load();
            townMapScene = new Scene(townMapRoot);
            townMapView = townMapLoader.getController();



        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void changeSceneToGameMap() {
        gameStage.setScene(gameMapScene);
    }

    public static void changeSceneToTownMap() {
        gameStage.setScene(townMapScene);
    }

    public static void changeSceneToConfig() {
        gameStage.setScene(configScene);
    }

    public static GameMapView getGameMapView() {
        return gameMapView;
    }

    public static TownMapView getTownMapView() {
        return townMapView;
    }


    public static void attemptGridButtonClick(Button button) {
        boolean available = button.getStyle().contains(CSS_TRANSPARENT);
        boolean isLandBuyPhase = GameDuration.getPhase() == GamePhase.LandBuyPhase;

        if (available && isLandBuyPhase) {

            Player activePlayer = GameDuration.getActivePlayer();
            String type = button.getText().toLowerCase();
            PropertyType propertyType = null;

            if (activePlayer != null) {

                if (type.toLowerCase().contains("plain")) {
                    propertyType = PropertyType.Plains;

                } else if (type.contains("mountain")) {
                    propertyType = PropertyType.Mountain;

                } else if (type.contains("river")) {
                    propertyType = PropertyType.River;
                }

                Property property = new Property(propertyType, activePlayer);
                activePlayer.addProperty(property);
                Log.debug("Type: " + type);
                Log.debug("Added " + propertyType.toString()  + " property to player " + activePlayer
                        .getName());

                String color = activePlayer.getColor();
                String styleVal = "-fx-background-color:" + color;
                button.setStyle(styleVal);

                GameDuration.endTurn();
            }
        }
    }



}
