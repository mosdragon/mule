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

/**
 * GameConfig class which handles logistics of the config View.
 */
public class GameConfigView implements Initializable {

    /**
     * initializes a comboBox configDifficulty.
     */
    @FXML
    private ComboBox<Difficulty> configDifficulty;
    /**
     * initializes a ComboBox mapChoice.
     */
    @FXML
    private ComboBox<MapType> mapChoice;
    /**
     * initializes a Slider playerSlider.
     */
    @FXML
    private Slider playerSlider;

    /**
     * initializes the GameConfigView.
     * @param location the url image for the config view
     * @param resources resources for the GameConfigView
     */
    @Override
    public final void initialize(final URL location,
                                 final ResourceBundle resources) {

        ObservableList<Difficulty> difficultyOptions =
                FXCollections.observableArrayList(
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
     * Called externally, most likely by ConfigScreenView.
     */
    public final void saveConfigs() {
        int numPlayers = (int) playerSlider.getValue();
        Difficulty difficulty = configDifficulty.getValue();
        MapType mapType = mapChoice.getValue();

        MasterController.saveConfigs(numPlayers, difficulty, mapType);
    }

}
