package edu.gatech.cs2340.sharkbait.controller;

import edu.gatech.cs2340.sharkbait.MasterController;
import edu.gatech.cs2340.sharkbait.model.GameConfigs;
import edu.gatech.cs2340.sharkbait.model.GameDuration;
import edu.gatech.cs2340.sharkbait.model.Store;
import edu.gatech.cs2340.sharkbait.util.GamePhase;
import edu.gatech.cs2340.sharkbait.util.Player;
import edu.gatech.cs2340.sharkbait.util.Resource;
import edu.gatech.cs2340.trydent.log.Log;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.util.*;

import java.net.URL;

/**
 * Created by osama on 9/22/15.
 */
public class TownMapController implements Initializable {

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

    private boolean clickListenersSet;
    private static final List<Integer> roundBonus = new ArrayList<>(Arrays.asList(50, 50, 50, 100,
            100, 100, 100, 150, 150, 150, 150, 200));


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        timeline = new Timeline(new KeyFrame(
                Duration.millis(1000),
                ae -> updateTimer()));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

    }



    private void updateTimer() {
        if (GameDuration.hasBegun()) {

            if (!clickListenersSet) {
                clickListenersSet = true;
                setClickListeners();
                updateQuantities();
            }

            timerMsg.setText(TIME_LEFT + GameDuration.getTimeRemaining());
            updateMessages();
        }
    }

    private void updateMessages() {

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

//    TODO: Buy energy, decrement player money
    private void buyEnergy(Player player) {
        player.changeEnergy(10);
        player.changeMoney(-10);
    }

//    TODO: Sell energy, increment player money
    private void sellEnergy(Player player) {
        player.changeEnergy(-10);
        player.changeMoney(10);
    }

    private void updateQuantities() {
        Log.debug("update quantities");

        oreText.setText(ORE_TEXT + Store.getOreCount());
        foodText.setText(FOOD_TEXT + Store.getFoodCount());
        energyText.setText(ENERGY_TEXT + Store.getEnergyCount());
        muleText.setText(MULE_TEXT + Store.getMuleCount());

        updateMessages();
    }

    private void setClickListeners() {


        exitTown.setOnMouseClicked(event1 -> MasterController.changeSceneToGameMap());


        enterPub.setOnMouseClicked(v -> {

            Random rand = new Random();
            int timeRemaining = GameDuration.getTimeRemaining();
            int timeBonus = rand.nextInt(timeRemaining + 1);

            Player player = GameDuration.getActivePlayer();

            int moneyBonus = roundBonus.get(GameDuration.getRound() - 1) * timeBonus;
            Log.debug("Bonus: " + moneyBonus);
            player.changeMoney(moneyBonus);

            MasterController.changeSceneToGameMap();
            GameDuration.endTurn();

            GameMapController controller = GameDuration.getGameMapController();
            controller.updateMessages();
        });

//        Resources
        buyOre.setOnMouseClicked(event -> {
            Player activePlayer = GameDuration.getActivePlayer();
            Store.buyOre(activePlayer);
            updateQuantities();
        });

        sellOre.setOnMouseClicked(event -> {
            Player activePlayer = GameDuration.getActivePlayer();
            Store.sellOre(activePlayer);
            updateQuantities();
        });

        buyFood.setOnMouseClicked(event -> {
            Player activePlayer = GameDuration.getActivePlayer();
            Store.buyFood(activePlayer);
            updateQuantities();
        });

        sellFood.setOnMouseClicked(event -> {
            Player activePlayer = GameDuration.getActivePlayer();
            Store.sellFood(activePlayer);
            updateQuantities();
        });

        buyEnergy.setOnMouseClicked(event -> {
            Player activePlayer = GameDuration.getActivePlayer();
            Store.buyEnergy(activePlayer);
            updateQuantities();
        });

        sellEnergy.setOnMouseClicked(event -> {
            Player activePlayer = GameDuration.getActivePlayer();
            Store.sellEnergy(activePlayer);
            updateQuantities();
        });

//        Mules
        buyOreMule.setOnMouseClicked(event -> {
            Player activePlayer = GameDuration.getActivePlayer();
            Store.buyMule(activePlayer, Resource.Smithore);
            updateQuantities();
        });

        sellOreMule.setOnMouseClicked(event -> {
            Player activePlayer = GameDuration.getActivePlayer();
            Store.sellMule(activePlayer, Resource.Smithore);
            updateQuantities();
        });

        buyFoodMule.setOnMouseClicked(event -> {
            Player activePlayer = GameDuration.getActivePlayer();
            Store.buyMule(activePlayer, Resource.Food);
            updateQuantities();
        });

        sellFoodMule.setOnMouseClicked(event -> {
            Player activePlayer = GameDuration.getActivePlayer();
            Store.sellMule(activePlayer, Resource.Food);
            updateQuantities();
        });

        buyEnergyMule.setOnMouseClicked(event -> {
            Player activePlayer = GameDuration.getActivePlayer();
            Store.buyMule(activePlayer, Resource.Energy);
            updateQuantities();
        });

        sellEnergyMule.setOnMouseClicked(event -> {
            Player activePlayer = GameDuration.getActivePlayer();
            Store.sellMule(activePlayer, Resource.Energy);
            updateQuantities();
        });

    }
}
