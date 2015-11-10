package edu.gatech.cs2340.sharkbait.view;

import edu.gatech.cs2340.sharkbait.controller.MasterController;
import edu.gatech.cs2340.sharkbait.util.Difficulty;
import edu.gatech.cs2340.sharkbait.util.MapType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;

import java.net.URL;
import java.util.ResourceBundle;

public class GameConfigView implements Initializable {

    @FXML
    private ComboBox<Difficulty> configDifficulty;
    @FXML
    private ComboBox<MapType> mapChoice;
    @FXML
    private Slider playerSlider;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ObservableList<Difficulty> difficultyOptions = FXCollections.observableArrayList(
                Difficulty.Beginner,
                Difficulty.Standard,
                Difficulty.Tournament
        );
        configDifficulty.setItems(difficultyOptions);
        configDifficulty.setValue(difficultyOptions.get(0));

        ObservableList<MapType> mapOptions = FXCollections.observableArrayList(
                MapType.StandardMap,
                MapType.RandomMap
        );
        mapChoice.setItems(mapOptions);
        mapChoice.setValue(mapOptions.get(0));
    }

    /**
     * Called externally, most likely by ConfigScreenView
     */
    public void saveConfigs() {
        int numPlayers = (int) playerSlider.getValue();
        Difficulty difficulty = configDifficulty.getValue();
        MapType mapType = mapChoice.getValue();

        MasterController.saveConfigs(numPlayers, difficulty, mapType);
    }

}
