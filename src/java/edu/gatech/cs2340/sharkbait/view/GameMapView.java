package edu.gatech.cs2340.sharkbait.view;

import edu.gatech.cs2340.sharkbait.controller.GameMapController;
import edu.gatech.cs2340.sharkbait.controller.MasterController;
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
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by osama on 9/22/15.
 */
public class GameMapView implements Initializable {

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


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        town.setOnMouseClicked(event -> GameMapController.townClicked());

        passButton.setOnMouseClicked(event -> {
            GameMapController.pass();
        });
    }

    @FXML
    private void handleGridButtonPress(ActionEvent ev) {
        Button button = (Button) ev.getSource();
        Log.debug("You pressed: " + button.getStyleClass().toString());
        GameMapController.attemptGridButtonClick(button);
    }

    public void updateTimer(String timeText) {
        timerMsg.setText(timeText);
    }

    public void updateMessages() {
        if (GameDuration.hasBegun()) {
            if (GameDuration.getPhase() == GamePhase.PlayerTurnPhase) {
                passButton.setText("End Turn");
            } else if (GameDuration.getPhase() == GamePhase.LandBuyPhase) {
                passButton.setText("Pass");
            }
            phaseMsg.setText(PHASE + GameDuration.getPhase().toString());
            playerMsg.setText(PLAYER + GameDuration.getActivePlayer().getName());

            List<Player> players = GameDuration.getPlayers();

            player1.setText(players.get(0).getName() + ": " + players
                    .get(0).getMoney());
            player2.setText(players.get(1).getName() + ": " + players
                    .get(1).getMoney());

            if (GameConfigs.getNumPlayers() >= 3) {
                player3.setText(players.get(2).getName() + ": " + players
                        .get(2).getMoney());
            }
            if (GameConfigs.getNumPlayers() == 4) {
                player4.setText(players.get(3).getName() + ": " + players
                        .get(3).getMoney());
            }
        }
    }

}
