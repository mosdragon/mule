package edu.gatech.cs2340.sharkbait.controller;

import edu.gatech.cs2340.sharkbait.MasterController;
import edu.gatech.cs2340.sharkbait.model.GameConfigs;
import edu.gatech.cs2340.sharkbait.model.GameDuration;
import edu.gatech.cs2340.sharkbait.util.GamePhase;
import edu.gatech.cs2340.sharkbait.util.Player;
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

//    Does nothing at the moment
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
                ae -> updateTimer()));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

        exitTown.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                MasterController.changeSceneToGameMap();
            }
        });


        enterPub.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                List<Integer> roundBonus = new ArrayList<>(Arrays.asList(50,50,50,100,100,100,100,150,150,150,150,200));

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
            }
        });

    }



    private void updateTimer() {
        if (GameDuration.hasBegun()) {
            int time = GameDuration.getTimeRemaining();
            timerMsg.setText(TIME_LEFT + GameDuration.getTimeRemaining());
            Log.debug("updateTimer: " + time);
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
}
