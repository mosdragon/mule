package edu.gatech.cs2340.sharkbait.controller;

import edu.gatech.cs2340.sharkbait.MasterController;
import edu.gatech.cs2340.sharkbait.model.GameConfigs;
import edu.gatech.cs2340.sharkbait.model.GameDuration;
import edu.gatech.cs2340.sharkbait.util.GamePhase;
import edu.gatech.cs2340.sharkbait.util.Player;
import edu.gatech.cs2340.sharkbait.util.Property;
import edu.gatech.cs2340.sharkbait.util.PropertyType;
import edu.gatech.cs2340.trydent.log.Log;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

import javax.imageio.ImageIO;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by osama on 9/22/15.
 */
public class GameMapController implements Initializable {

    @FXML
    private ImageView town;
    @FXML
    private GridPane grid;
    @FXML
    private Button passButton;

    @FXML
    private Label phaseMsg;

    @FXML
    private Label playerMsg;

    @FXML
    private Label timerMsg;

    @FXML
    private Label player1, player2, player3, player4;

    private Timeline timeline;


    private static final String PHASE = "Phase: ";
    private static final String PLAYER = "Active Player: ";
    private static final String TIME_LEFT = "Time Remaining: ";

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        timeline = new Timeline(new KeyFrame(
                Duration.millis(1000),
                ae -> passTime()));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

        int time = GameDuration.getTimeRemaining();
        timerMsg.setText(TIME_LEFT + time + 1);

        town.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (GameDuration.getPhase() == GamePhase.PlayerTurnPhase) {
                    MasterController.changeSceneToTownMap();
                }
            }
        });

        passButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                GameDuration.endTurn();
                updateTimer();
            }
        });
//        popup some message saying it's player X's turn
//        first 2
    }

    private void passTime() {
        if (GameDuration.hasBegun()) {
            GameDuration.passOneSecond();
            updateTimer();
        }
    }


    private void updateTimer() {
        if (GameDuration.hasBegun()) {
            int time = GameDuration.getTimeRemaining();
            if (time == 0) {
                GameDuration.endTurn();
//                Changes to game map if you're in town
                MasterController.changeSceneToGameMap();
                time = GameDuration.getTimeRemaining();
            }
            timerMsg.setText(TIME_LEFT + GameDuration.getTimeRemaining());
            updateMessages();
        }
    }

    @FXML
    private void handleGridButtonPress(ActionEvent ev) {

        Button button = (Button) ev.getSource();
        Log.debug("You pressed: " + button.getText());

        boolean available = button.getStyle().contains("-fx-background-color:rgba(0,0,0,0);");
        boolean isLandBuyPhase = GameDuration.getPhase() == GamePhase.LandBuyPhase;

        if (available && isLandBuyPhase) {

            Player activePlayer = GameDuration.getActivePlayer();
            String type = button.getText();
            PropertyType propertyType = null;

            if (activePlayer != null) {


                if (type.contains("plain")) {
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
                updateTimer();
            }
        }


    }

    public void updateMessages() {
        if (GameDuration.getPhase() == GamePhase.PlayerTurnPhase) {
            passButton.setText("End Turn");
        } else {
            passButton.setText("Pass");
        }
        phaseMsg.setText(PHASE + GameDuration.getPhase().toString());
        playerMsg.setText(PLAYER + GameDuration.getActivePlayer().getName());

        player1.setText(GameConfigs.players.get(0).getName() + ": " + GameConfigs.players
                .get(0).getMoney());
        player2.setText(GameConfigs.players.get(1).getName() + ": " + GameConfigs.players
                .get(1).getMoney());


        if (GameConfigs.getNumPlayers() >= 3) {
            player3.setText(GameConfigs.players.get(2).getName() + ": " + GameConfigs.players
                    .get(2).getMoney());
        }
        if (GameConfigs.getNumPlayers() == 4) {

            player4.setText(GameConfigs.players.get(3).getName() + ": " + GameConfigs.players
                    .get(3).getMoney());
        }
    }


    private void beginLandSelection(Player player, int cost) {

    }

//    TODO: Begin a round for a player
    /**
     * Begin a round for a player for this much time
     * @param player, the player who's turn it is
     * @param coundown, the time in second for the turn to proceed
     */
    private void beginTurn(Player player, int coundown) {
    }

//    TODO: Implement reward for ending turn

    /**
     * Use difficulty to determine how much player gets rewarded for ending turn
     * @param player
     */
    private void goToPub(Player player) {
    }

}
