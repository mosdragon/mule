package edu.gatech.cs2340.sharkbait.controller;

import edu.gatech.cs2340.sharkbait.MasterController;
import edu.gatech.cs2340.sharkbait.model.GameDuration;
import edu.gatech.cs2340.sharkbait.util.Player;
import edu.gatech.cs2340.trydent.log.Log;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

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


    @Override
    public void initialize(URL location, ResourceBundle resources) {

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
