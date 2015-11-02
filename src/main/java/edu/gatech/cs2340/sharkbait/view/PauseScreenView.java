package edu.gatech.cs2340.sharkbait.view;

import edu.gatech.cs2340.sharkbait.controller.MasterController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class PauseScreenView implements Initializable {

    @FXML
    private Button saveButton;
    @FXML
    private Button resumeButton;
    @FXML
    private Button loadButton;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        saveButton.setOnMouseClicked(event -> {
            //DO save
        });

        resumeButton.setOnMouseClicked(event -> {
            MasterController.resumeTime();
            MasterController.changeSceneToGameMap();
        });

        loadButton.setOnMouseClicked(event -> {
            //Load game
        });
    }
}
