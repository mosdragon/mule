package edu.gatech.cs2340.sharkbait.controller;

import edu.gatech.cs2340.sharkbait.model.GameDuration;
import edu.gatech.cs2340.sharkbait.util.Player;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by osama on 9/22/15.
 */
public class TownMapController implements Initializable {

    @FXML
    private Button exitTown;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        get player;
//        if clicks buy energy
//                player.money -= 2
//                        player.energy += 1


        exitTown.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Scene currentScene = exitTown.getScene();
                Parent gameMap = GameDuration.getGameMap();
                currentScene.setRoot(gameMap);
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
