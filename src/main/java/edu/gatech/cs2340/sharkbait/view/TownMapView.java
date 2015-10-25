package edu.gatech.cs2340.sharkbait.view;

import edu.gatech.cs2340.sharkbait.controller.MasterController;
import edu.gatech.cs2340.sharkbait.controller.TownMapController;
import edu.gatech.cs2340.sharkbait.model.Constants;
import edu.gatech.cs2340.sharkbait.model.GameConfigs;
import edu.gatech.cs2340.sharkbait.model.GameDuration;
import edu.gatech.cs2340.sharkbait.model.Store;
import edu.gatech.cs2340.sharkbait.util.Player;
import edu.gatech.cs2340.sharkbait.util.Resource;
import edu.gatech.cs2340.trydent.log.Log;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.*;

import java.net.URL;

/**
 * Created by osama on 9/22/15.
 */
public class TownMapView implements Initializable {

    @FXML
    private Button exitTown;
    @FXML
    private Button enterPub;

//    These are buttons for buying/selling resources/mules
    @FXML
    private Button buyOre, sellOre, buyFood, sellFood, buyEnergy, sellEnergy;

    @FXML
    private Button buyOreMule, sellOreMule, buyFoodMule, sellFoodMule, buyEnergyMule,
            sellEnergyMule;

    @FXML
    private Text oreText, foodText, energyText, muleText;


//    These are for the info pane at the bottom
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

    private Timeline timeline;


    private static final String PHASE = "Phase: ";
    private static final String PLAYER = "Active Player: ";
    private static final String TIME_LEFT = "Time Remaining: ";

    private static final String MULE_TEXT = "MULES: ";
    private static final String FOOD_TEXT = "FOOD: ";
    private static final String ENERGY_TEXT = "ENERGY: ";
    private static final String ORE_TEXT = "ORE: ";


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setClickListeners();
    }


    public void updateTimer(String timeText) {
        timerMsg.setText(timeText);
    }

    public void updateMessages() {
        if (GameDuration.hasBegun()) {
            updateQuantities();
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
        mules1.setText("Mules Owned: " + player1.getMuleCount());
        energy1.setText("Energy: " + player1.getEnergy());

//        Player2 score panel
        Player player2 = players.get(1);

        scorePanel2.setStyle(String.format(BG_COLOR_TEMPLATE, player2.getColor()));
        name2.setText(player2.getName());
        money2.setText("Money: " + player2.getMoney());
        food2.setText("Food: " + player2.getFood());
        mules2.setText("Mules Owned: " + player2.getMuleCount());
        energy2.setText("Energy: " + player2.getEnergy());

//        Player3 score panel
        if (numPlayers >= 3) {

            Player player3 = players.get(2);

            scorePanel3.setStyle(String.format(BG_COLOR_TEMPLATE, player3.getColor()));
            name3.setText(player3.getName());
            money3.setText("Money: " + player3.getMoney());
            food3.setText("Food: " + player3.getFood());
            mules3.setText("Mules Owned: " + player3.getMuleCount());
            energy3.setText("Energy: " + player3.getEnergy());
        }

//        Player4 score panel
        if (numPlayers >= 4) {

            Player player4 = players.get(3);

            scorePanel4.setStyle(String.format(BG_COLOR_TEMPLATE, player4.getColor()));
            name4.setText(player4.getName());
            money4.setText("Money: " + player4.getMoney());
            food4.setText("Food: " + player4.getFood());
            mules4.setText("Mules Owned: " + player4.getMuleCount());
            energy4.setText("Energy: " + player4.getEnergy());
        }

        Player activePlayer = GameDuration.getActivePlayer();
        activePlayerPanel.setStyle(String.format(BG_COLOR_TEMPLATE, activePlayer.getColor()));
    }

    public void updateQuantities() {

        oreText.setText(ORE_TEXT + Store.getOreCount());
        foodText.setText(FOOD_TEXT + Store.getFoodCount());
        energyText.setText(ENERGY_TEXT + Store.getEnergyCount());
        muleText.setText(MULE_TEXT + Store.getMuleCount());
    }

    private void setClickListeners() {

        exitTown.setOnMouseClicked(event -> TownMapController.exitTown());

        enterPub.setOnMouseClicked(event -> TownMapController.enterPub());

//        Resources
        buyOre.setOnMouseClicked(event -> {
            TownMapController.buyOre();
            updateQuantities();
        });

        sellOre.setOnMouseClicked(event -> {
            TownMapController.sellOre();
            updateQuantities();
        });

        buyFood.setOnMouseClicked(event -> {
            TownMapController.buyFood();
            updateQuantities();
        });

        sellFood.setOnMouseClicked(event -> {
            TownMapController.sellFood();
            updateQuantities();
        });

        buyEnergy.setOnMouseClicked(event -> {
            TownMapController.buyEnergy();
            updateQuantities();
        });

        sellEnergy.setOnMouseClicked(event -> {
            TownMapController.sellEnergy();
            updateQuantities();
        });


//        Mules
        buyOreMule.setOnMouseClicked(event -> {
            TownMapController.buyOreMule();
            updateQuantities();
        });

        sellOreMule.setOnMouseClicked(event -> {
            TownMapController.sellOreMule();
            updateQuantities();
        });

        buyFoodMule.setOnMouseClicked(event -> {
            TownMapController.buyFoodMule();
            updateQuantities();
        });

        sellFoodMule.setOnMouseClicked(event -> {
            TownMapController.sellFoodMule();
            updateQuantities();
        });

        buyEnergyMule.setOnMouseClicked(event -> {
            TownMapController.buyEnergyMule();
            updateQuantities();
        });

        sellEnergyMule.setOnMouseClicked(event -> {
            TownMapController.sellEnergyMule();
            updateQuantities();
        });

    }

    public void handleRandomEvent(String randomEventText) {
        randomEventMsg.setText(randomEventText);
        randomEventMsg.setWrapText(true);
    }
}
