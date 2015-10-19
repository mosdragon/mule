package edu.gatech.cs2340.sharkbait.view;

import edu.gatech.cs2340.sharkbait.controller.MasterController;
import edu.gatech.cs2340.sharkbait.controller.TownMapController;
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
    private Label player1, player2, player3, player4;

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

    public void updateQuantities() {
        Log.debug("update quantities");

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
}
