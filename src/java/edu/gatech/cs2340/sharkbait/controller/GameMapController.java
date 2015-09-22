package edu.gatech.cs2340.sharkbait.controller;

import edu.gatech.cs2340.sharkbait.model.GameDuration;
import edu.gatech.cs2340.sharkbait.util.Player;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by osama on 9/22/15.
 */
public class GameMapController implements Initializable {

    @FXML
    private ImageView town;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Player active = null;

        town.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("Town Clicked");

                if (active == null) {
                    if (GameDuration.getTownMap() == null) {
                        Scene gameScene = town.getScene();

                        Parent map = gameScene.getRoot();
                        GameDuration.setGameMap(map);

                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource
                                ("../view/fxml/town_map.fxml"));

                        try {
                            Parent townMap = fxmlLoader.load();
                            System.out.println("Creating town for the first time");
                            gameScene.setRoot(townMap);


                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                }
            }
        });

    }
}
