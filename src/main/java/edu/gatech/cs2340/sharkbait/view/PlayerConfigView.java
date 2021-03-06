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
import java.util.*;

/**
 * Created by osama on 9/13/15.
 */
public class PlayerConfigView implements Initializable {

    private static final double ALPHA = 0.5;

    @FXML protected TextField selectName;
    @FXML private ComboBox<String> selectColor;
    @FXML private ComboBox<Race> selectRace;

    private ObservableList<String> colorOptions;

    private Map<String, String> colorMap;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        colorMap = new HashMap<>();

//        These are the tinted versions of the colors
        colorMap.put("Aqua", String.format("rgba(0,255,255,%f)", ALPHA));
        colorMap.put("DarkSeaGreen", String.format("rgba(143,188,143,%f)", ALPHA));
        colorMap.put("FireBrick", String.format("rgba(178,34,34,%f)", ALPHA));
        colorMap.put("Gold", String.format("rgba(255,215,0,%f)", ALPHA));
        colorMap.put("Violet", String.format("rgba(238,130,238,%f)", ALPHA));

        Collection<String> colors = colorMap.keySet();
        colorOptions = FXCollections.observableArrayList(colors);

        selectColor.setItems(colorOptions);
        selectColor.setValue(colorOptions.get(0));

        ObservableList<Race> raceOptions = FXCollections.observableArrayList(
                Race.Flapper,
                Race.Human,
                Race.Bonzoid,
                Race.Buzzite,
                Race.Ugaite,
                Race.Alien,
                Race.CollegeStudent
        );
        selectRace.setItems(raceOptions);
        selectRace.setValue(raceOptions.get(0));


    }

    public TextField getSelectName() {
        return selectName;
    }

    /**
     * Called by an external class to create a Player object and save it to configs
     */
    public void makePlayer() {
        String color = selectColor.getValue();

        PlayerConfigController.createPlayer(selectName.getText(),
                colorMap.get(color),
                selectRace.getValue());
    }
}
