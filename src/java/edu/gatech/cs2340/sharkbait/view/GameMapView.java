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

    @FXML
    private Label money1, money2, money3, money4;

    @FXML
    private Label food1, food2, food3, food4;

    @FXML
    private Label mules1, mules2, mules3, mules4;

    @FXML
    private Label energy1, energy2, energy3, energy4;

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
        Log.debug("You pressed: " + button.getText());
        GameMapController.attemptGridButtonClick(button);
    }

    public void updateTimer(String timeText) {
        timerMsg.setText(timeText);
    }

    public void updateMessages() {
        if (GameDuration.hasBegun()) {
            if (GameDuration.getPhase() == GamePhase.PlayerTurnPhase) {
                passButton.setText("End Turn");
            } else {
                passButton.setText("Pass");
            }
            phaseMsg.setText(PHASE + GameDuration.getPhase().toString());
            playerMsg.setText(PLAYER + GameDuration.getActivePlayer().getName());

            List<Player> players = GameDuration.getPlayers();

            //Player1 scoreTable
            player1.setText(players.get(0).getName());
            money1.setText("Money: " + players.get(0).getMoney());
            food1.setText("Food: " + players.get(0).getFood());
            mules1.setText("Mules Owned: " + players.get(0).getNumMules());
            energy1.setText("Energy: " + players.get(0).getEnergy());

            //Player2 ScoreTable
            player2.setText(players.get(1).getName());
            money2.setText("Money: " + players.get(1).getMoney());
            food2.setText("Food: " + players.get(1).getFood());
            mules2.setText("Mules Owned: " + players.get(0).getNumMules());
            energy2.setText("Energy: " + players.get(1).getEnergy());

            //Player3 ScoreTable
            if (GameConfigs.getNumPlayers() >= 3) {
                player3.setText(players.get(2).getName());
                money3.setText("Money: " + players.get(2).getMoney());
                food3.setText("Food: " + players.get(2).getFood());
                mules3.setText("Mules Owned: " + players.get(0).getNumMules());
                energy3.setText("Energy: " + players.get(2).getEnergy());
            }
            //Player4  ScoreTable
            if (GameConfigs.getNumPlayers() == 4) {
                player4.setText(players.get(3).getName());
                money4.setText("Money: " + players.get(3).getMoney());
                food4.setText("Food: " + players.get(3).getFood());
                mules4.setText("Mules Owned: " + players.get(0).getNumMules());
                energy4.setText("Energy: " + players.get(3).getEnergy());
            }

        }
    }

}
