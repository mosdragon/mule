package edu.gatech.cs2340.sharkbait.view;

import edu.gatech.cs2340.sharkbait.controller.PlayerConfigController;
import edu.gatech.cs2340.sharkbait.model.GameConfigs;
import edu.gatech.cs2340.sharkbait.util.Player;
import edu.gatech.cs2340.sharkbait.util.Race;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by osama on 9/13/15.
 */
public class PlayerConfigView implements Initializable {

    @FXML protected TextField selectName;
    @FXML private ComboBox<String> selectColor;
    @FXML private ComboBox<Race> selectRace;

    private ObservableList<String> colorOptions;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        colorOptions = FXCollections.observableArrayList(
                "Aqua",
                "DarkSeaGreen",
                "FireBrick",
                "Gold",
                "Violet"
        );
        selectColor.setItems(colorOptions);
        selectColor.setValue(colorOptions.get(0));

        ObservableList<Race> raceOptions = FXCollections.observableArrayList(
                Race.Flapper,
                Race.Human,
                Race.Bonzoid,
                Race.Buzzite,
                Race.Ugaite
        );
        selectRace.setItems(raceOptions);
        selectRace.setValue(raceOptions.get(0));


    }

    /**
     * Called by an external class to create a Player object and save it to configs
     */
    public void makePlayer() {
        String rgbColor = selectColor.getValue();
        switch (rgbColor) {
            case "Aqua": rgbColor = "rgba(0,255,255,0.3)"; break;
            case "DarkSeaGreen": rgbColor = "rgba(143,188,143,0.3)"; break;
            case "FireBrick": rgbColor = "rgba(178,34,34,0.3)"; break;
            case "Gold": rgbColor = "rgba(255,215,0,0.3)"; break;
            case "Violet": rgbColor = "rgba(238,130,238,0.3)"; break;
        }
        PlayerConfigController.createPlayer(selectName.getText(),
                rgbColor,
                selectRace.getValue());
    }
}
