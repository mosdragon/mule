package edu.gatech.cs2340.sharkbait.view;

import edu.gatech.cs2340.sharkbait.controller.GameMapController;
import edu.gatech.cs2340.sharkbait.controller.MasterController;
import edu.gatech.cs2340.sharkbait.model.Constants;
import edu.gatech.cs2340.sharkbait.model.GameDuration;
import edu.gatech.cs2340.sharkbait.util.GamePhase;
import edu.gatech.cs2340.sharkbait.util.Player;
import edu.gatech.cs2340.trydent.log.Log;
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
 * GameMapView class.
 * Created on 9/22/15.
 * @author Osama
 */
public class GameMapView implements Initializable {

    /**
     * initializes an ImageView town.
     */
    @FXML
    private ImageView town;
    /**
     * initializes a GridPane grid.
     */
    @FXML
    private GridPane grid;
    /**
     * initializes a Button passButton.
     */
    @FXML
    private Button passButton;
    /**
     * initializes a Button pauseButton.
     */
    @FXML
    private Button pauseButton;
    /**
     * initializes a Label phaseMsg.
     */
    @FXML
    private Label phaseMsg;

    /**
     * initializes  a Label randomEventMsg.
     */
    @FXML
    private Label randomEventMsg;

    /**
     * initializes a Label playerMsg.
     */
    @FXML
    private Label playerMsg;
    /**
     * initializes a Label timerMsg.
     * */
    @FXML
    private Label timerMsg;
    /**
     * Initializes Vbox for scorePanels.
     */
    @FXML
    private VBox scorePanel1, scorePanel2, scorePanel3,
            scorePanel4, activePlayerPanel;

    /**
     * initializes Labels for names.
     */
    @FXML
    private Label name1, name2, name3, name4;

    /**
     * initializes Labels for money.
     */
    @FXML
    private Label money1, money2, money3, money4;

    /**
     * initializes Labels for food.
     */
    @FXML
    private Label food1, food2, food3, food4;

    /**
     * initializes Labels for mules.
     */
    @FXML
    private Label mules1, mules2, mules3, mules4;

    /**
     * initializes labels for energy.
     */
    @FXML
    private Label energy1, energy2, energy3, energy4;

    /**
     * initializes Labels for ores.
     */
    @FXML
    private Label ore1, ore2, ore3, ore4;

    /**
     * initializes a String Phase.
     */
    private static final String PHASE = "Phase: ";

    /**
     * initializes a Sting Player.
     */
    private static final String PLAYER = "Active Player: ";


    @Override
    public final void initialize(final URL location,
                                 final ResourceBundle resources) {

        town.setOnMouseClicked(event -> GameMapController.townClicked());

        passButton.setOnMouseClicked(event -> {
            GameMapController.pass();
        });

        pauseButton.setOnMouseClicked(event -> {
            MasterController.changeSceneToPauseScreen();
        });
    }

    /**
     * handles gridButtonPresses on the gameMap.
     * calls attemptGridButton from GameMapController
     * @param ev represents some type of action
     */
    @FXML
    private void handleGridButtonPress(final ActionEvent ev) {
        Button button = (Button) ev.getSource();
        Log.debug("You pressed: " + button.getStyleClass().toString());
        GameMapController.attemptGridButtonClick(button);
    }

    /**
     * updates the timer in the GameMapView.
     * @param timeText the time to update timer too
     */
    public final void updateTimer(final String timeText) {
        timerMsg.setText(timeText);
    }

    /**
     * handles random events.
     * @param randomEventText the text to which to set the randomEventMsg too.
     */
    public final void handleRandomEvent(final String randomEventText) {
        randomEventMsg.setText(randomEventText);
        randomEventMsg.setWrapText(true);
    }

    /**
     * updates messages in the GameMapView.
     */
    public final void updateMessages() {
        if (GameDuration.hasBegun()) {
            if (GameDuration.getPhase() == GamePhase.PlayerTurnPhase) {
                passButton.setText("End Turn");
            } else if (GameDuration.getPhase() == GamePhase.LandBuyPhase) {
                passButton.setText("Pass");
            }
            phaseMsg.setText(PHASE + GameDuration.getPhase().toString());
            playerMsg.setText(PLAYER
                    + GameDuration.getActivePlayer().getName());

            updateScorePanels();
        }
    }


    /**
     * updates the score panel in the game map view.
     */
    private void updateScorePanels() {
        final int maxPlayers = 4;
        final int panel3 = 3;
        final String bgColorTemplate = Constants.BG_COLOR_TEMPLATE;
        List<Player> players = GameDuration.getPlayers();
        int numPlayers = players.size();

//        Player1 score panel
        Player player1 = players.get(0);

        scorePanel1.setStyle(String.format(bgColorTemplate,
                player1.getColor()));
        name1.setText(player1.getName());
        money1.setText("Money: " + player1.getMoney());
        food1.setText("Food: " + player1.getFood());
        mules1.setText("Mules: " + player1.getMuleCount());
        energy1.setText("Energy: " + player1.getEnergy());
        ore1.setText("Ore: " + player1.getOre());

//        Player2 score panel
        Player player2 = players.get(1);

        scorePanel2.setStyle(String.format(bgColorTemplate,
                player2.getColor()));
        name2.setText(player2.getName());
        money2.setText("Money: " + player2.getMoney());
        food2.setText("Food: " + player2.getFood());
        mules2.setText("Mules: " + player2.getMuleCount());
        energy2.setText("Energy: " + player2.getEnergy());
        ore2.setText("Ore: " + player2.getOre());

//        Player3 score panel
        if (numPlayers >= panel3) {

            Player player3 = players.get(2);

            scorePanel3.setStyle(String.format(bgColorTemplate,
                    player3.getColor()));
            name3.setText(player3.getName());
            money3.setText("Money: " + player3.getMoney());
            food3.setText("Food: " + player3.getFood());
            mules3.setText("Mules: " + player3.getMuleCount());
            energy3.setText("Energy: " + player3.getEnergy());
            ore3.setText("Ore: " + player3.getOre());
        }

//        Player4 score panel
        if (numPlayers >= maxPlayers) {

            Player player4 = players.get(panel3);

            scorePanel4.setStyle(String.format(bgColorTemplate,
                    player4.getColor()));
            name4.setText(player4.getName());
            money4.setText("Money: " + player4.getMoney());
            food4.setText("Food: " + player4.getFood());
            mules4.setText("Mules: " + player4.getMuleCount());
            energy4.setText("Energy: " + player4.getEnergy());
            ore4.setText("Ore: " + player4.getOre());
        }

        Player activePlayer = GameDuration.getActivePlayer();
        activePlayerPanel.setStyle(String.format(bgColorTemplate,
                activePlayer.getColor()));
    }


    /**
     * gets the grid of the GameMap.
     * @return the grid of the GameMap
     */
    public final GridPane getGrid() {
        return grid;
    }
}
