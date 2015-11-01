package edu.gatech.cs2340.sharkbait.view;

import edu.gatech.cs2340.sharkbait.controller.GameMapController;
import edu.gatech.cs2340.sharkbait.controller.MasterController;
import edu.gatech.cs2340.sharkbait.model.Constants;
import edu.gatech.cs2340.sharkbait.model.GameDuration;
import edu.gatech.cs2340.sharkbait.util.GamePhase;
import edu.gatech.cs2340.sharkbait.util.Player;
import edu.gatech.cs2340.trydent.log.Log;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

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
    private Button pauseButton;

    @FXML
    private Label phaseMsg;

    @FXML
    private Label randomEventMsg;

    @FXML
    private Label playerMsg;

    @FXML
    private Label timerMsg;

    @FXML
    private VBox scorePanel1, scorePanel2, scorePanel3, scorePanel4, activePlayerPanel;

    @FXML
    private Label name1, name2, name3, name4;

    @FXML
    private Label money1, money2, money3, money4;

    @FXML
    private Label food1, food2, food3, food4;

    @FXML
    private Label mules1, mules2, mules3, mules4;

    @FXML
    private Label energy1, energy2, energy3, energy4;

    @FXML
    private Label ore1, ore2, ore3, ore4;

    private Timeline timeline;


    private static final String PHASE = "Phase: ";
    private static final String PLAYER = "Active Player: ";


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        town.setOnMouseClicked(event -> GameMapController.townClicked());

        passButton.setOnMouseClicked(event -> {
            GameMapController.pass();
        });

        pauseButton.setOnMouseClicked(event -> {
            MasterController.changeSceneToSave();
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

    public void handleRandomEvent(String randomEventText) {
        randomEventMsg.setText(randomEventText);
        randomEventMsg.setWrapText(true);
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

            updateScorePanels();
        }
    }

    private void updateScorePanels() {
        final String BG_COLOR_TEMPLATE = Constants.BG_COLOR_TEMPLATE;
        List<Player> players = GameDuration.getPlayers();
        int numPlayers = players.size();

//        Player1 score panel
        Player player1 = players.get(0);

        scorePanel1.setStyle(String.format(BG_COLOR_TEMPLATE, player1.getColor()));
        name1.setText(player1.getName());
        money1.setText("Money: " + player1.getMoney());
        food1.setText("Food: " + player1.getFood());
        mules1.setText("Mules: " + player1.getMuleCount());
        energy1.setText("Energy: " + player1.getEnergy());
        ore1.setText("Ore: " + player1.getOre());

//        Player2 score panel
        Player player2 = players.get(1);

        scorePanel2.setStyle(String.format(BG_COLOR_TEMPLATE, player2.getColor()));
        name2.setText(player2.getName());
        money2.setText("Money: " + player2.getMoney());
        food2.setText("Food: " + player2.getFood());
        mules2.setText("Mules: " + player2.getMuleCount());
        energy2.setText("Energy: " + player2.getEnergy());
        ore2.setText("Ore: " + player2.getOre());

//        Player3 score panel
        if (numPlayers >= 3) {

            Player player3 = players.get(2);

            scorePanel3.setStyle(String.format(BG_COLOR_TEMPLATE, player3.getColor()));
            name3.setText(player3.getName());
            money3.setText("Money: " + player3.getMoney());
            food3.setText("Food: " + player3.getFood());
            mules3.setText("Mules: " + player3.getMuleCount());
            energy3.setText("Energy: " + player3.getEnergy());
            ore3.setText("Ore: " + player3.getOre());
        }

//        Player4 score panel
        if (numPlayers >= 4) {

            Player player4 = players.get(3);

            scorePanel4.setStyle(String.format(BG_COLOR_TEMPLATE, player4.getColor()));
            name4.setText(player4.getName());
            money4.setText("Money: " + player4.getMoney());
            food4.setText("Food: " + player4.getFood());
            mules4.setText("Mules: " + player4.getMuleCount());
            energy4.setText("Energy: " + player4.getEnergy());
            ore4.setText("Ore: " + player4.getOre());
        }

        Player activePlayer = GameDuration.getActivePlayer();
        activePlayerPanel.setStyle(String.format(BG_COLOR_TEMPLATE, activePlayer.getColor()));
    }


}
