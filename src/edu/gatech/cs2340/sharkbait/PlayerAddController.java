package edu.gatech.cs2340.sharkbait;

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
public class PlayerAddController implements Initializable {

    @FXML private TextField selectName;
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

        ObservableList<Race> raceOptions = FXCollections.observableArrayList(
                Race.Flapper,
                Race.Human,
                Race.Bonzoid,
                Race.Buzzite,
                Race.Ugaite
        );
        selectRace.setItems(raceOptions);


    }

    public Player addPlayer(String defaultName) {
        if (selectName.getText().isEmpty()) {
            selectName.setText(defaultName);
        }
        Player player = new Player(selectName.getText(),
                selectColor.getValue(),
                selectRace
                .getValue());

        System.out.println(defaultName);
        System.out.println(player.getName());
        System.out.println(player.getColor());
        System.out.println(player.getRace());


        return player;
    }
}
