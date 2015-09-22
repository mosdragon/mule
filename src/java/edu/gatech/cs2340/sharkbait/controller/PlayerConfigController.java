package edu.gatech.cs2340.sharkbait.controller;

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
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by osama on 9/13/15.
 */
public class PlayerConfigController implements Initializable {

    @FXML protected TextField selectName;
    @FXML private ComboBox<String> selectColor;
    @FXML private ComboBox<Race> selectRace;

    private List<Color> colorList;
    private ObservableList<String> colorOptions;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        colorList = FXCollections.observableArrayList(
                Color.AQUA,
                Color.BEIGE,
                Color.FIREBRICK,
                Color.DARKSEAGREEN,
                Color.GOLD,
                Color.VIOLET
        );
        colorOptions = FXCollections.observableArrayList(
                "Aqua",
                "Beige",
                "Dark Sea Green",
                "Fire Brick",
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
     * @return the player object constructed from this controller's selectors
     */
    public void makePlayer() {
        int index = colorOptions.indexOf(selectColor.getValue());

        Player player = new Player(selectName.getText(),
                colorList.get(index),
                selectRace.getValue());

        GameConfigs configs = GameConfigs.getInstance();
        configs.addPlayer(player);

    }
}
