package edu.gatech.cs2340.sharkbait.controller;

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
import java.util.ResourceBundle;

/**
 * Created by osama on 9/13/15.
 */
public class PlayerConfigController implements Initializable {

    @FXML protected TextField selectName;
    @FXML private ComboBox<Color> selectColor;
    @FXML private ComboBox<Race> selectRace;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ObservableList<Color> colorOptions = FXCollections.observableArrayList(
                Color.AQUA,
                Color.BEIGE,
                Color.FIREBRICK,
                Color.DARKSEAGREEN,
                Color.GOLD,
                Color.VIOLET
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
     * @param defaultName, the default name for a player to be added
     * @return the player object constructed from this controller's selectors
     */
    public Player makePlayer(String defaultName) {
        if (selectName.getText().isEmpty()) {
            selectName.setText(defaultName);
        }
        Player player = new Player(selectName.getText(),
                selectColor.getValue(),
                selectRace.getValue());

        System.out.println(defaultName);
        System.out.println(player.getName());
        System.out.println(player.getColor());
        System.out.println(player.getRace());


        return player;
    }
}
