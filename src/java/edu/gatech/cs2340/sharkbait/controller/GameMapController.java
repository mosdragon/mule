package edu.gatech.cs2340.sharkbait.controller;

import edu.gatech.cs2340.sharkbait.model.GameDuration;
import edu.gatech.cs2340.sharkbait.util.Player;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.control.*;
import javafx.event.ActionEvent;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by osama on 9/22/15.
 */
public class GameMapController implements Initializable {

    @FXML
    private ImageView town;
    @FXML
    private GridPane grid;

    @FXML
    private void handleGridButtonPress(ActionEvent ev) {
        Button button = (Button) ev.getSource();

        //THIS SHOULD UPDATE THE PLAYER
        System.out.println("You pressed: " + button.getText());

        //THIS SHOULD BE THE PLAYER COLOR.
        button.setStyle("-fx-background-color:red;");

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        town.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("Town Clicked");

                Player active = GameDuration.getActivePlayer();
                Scene currentScene = town.getScene();

                if (active == null) {
                    if (GameDuration.getTownMap() == null) {
                        System.out.println("First time in town");

                        Parent gameMap = currentScene.getRoot();
                        GameDuration.setGameMap(gameMap);

                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource
                                ("../view/fxml/town_map.fxml"));

                        try {
                            Parent townMap = fxmlLoader.load();
                            currentScene.setRoot(townMap);
                            GameDuration.setTownMap(townMap);


                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    } else {
                        System.out.println("Been there, done that. In town before.");
                        Parent townMap = GameDuration.getTownMap();
                        currentScene.setRoot(townMap);
                    }
                }
            }
        });

//        For all grid cells
        for (Node n : grid.getChildren()) {

//            Unless you are the town cell
            if (!n.equals(town)) {

//                When clicked and in buying phase, buy for player, end buy phase
                n.setOnMouseClicked(new EventHandler<MouseEvent>() {

                    @Override
                    public void handle(MouseEvent event) {
                        Player player = GameDuration.getActivePlayer();

                        if (player != null) {
//                            TODO: Change color using activePlayer.getColor() if no other player
//                            bought
                            ImageView view = (ImageView) n;
                            Image img = view.getImage();
//                            img.getPixelReader().;
//                            Image other = new Image(img);

//                            TODO: End turn for this player
                        }
                    }
                });

//                When hoevered and is player's turn, change color
                n.setOnMouseEntered(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {

//                        if not bought, then change color.

                    }
                });
            }
        }



//        popup some message saying it's player X's turn
//        first 2
    }


    private void beginLandSelection(Player player, int cost) {

    }

//    TODO: Begin a round for a player
    /**
     * Begin a round for a player for this much time
     * @param player, the player who's turn it is
     * @param coundown, the time in second for the turn to proceed
     */
    private void beginTurn(Player player, int coundown) {

    }

//    TODO: Implement reward for ending turn

    /**
     * Use difficulty to determine how much player gets rewarded for ending turn
     * @param player
     */
    private void goToPub(Player player) {
    }

}
