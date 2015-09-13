package edu.gatech.cs2340.sharkbait;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class GameConfigController implements Initializable {

    @FXML
    private ComboBox<Difficulty> configDifficulty;
    @FXML
    private ComboBox<MapType> mapChoice;
    @FXML
    private Slider playerSlider;
    @FXML
    private Button next;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ObservableList<Difficulty> difficultyOptions = FXCollections.observableArrayList(
                Difficulty.Beginner,
                Difficulty.Standard,
                Difficulty.Tournament
        );
        configDifficulty.setItems(difficultyOptions);

        ObservableList<MapType> mapOptions = FXCollections.observableArrayList(
                MapType.StandardMap,
                MapType.RandomMap
        );
        mapChoice.setItems(mapOptions);

        next.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                final GameConfigs configs = GameConfigs.getInstance();
                int numPlayers = (int) playerSlider.getValue();
                System.out.println(numPlayers);
                Difficulty difficulty = configDifficulty.getValue();
                System.out.println(difficulty);
                MapType mapType = mapChoice.getValue();
                System.out.println(mapType);

                configs.setGameDifficulty(difficulty);
                configs.setMapType(mapType);
                configs.setNumPlayers(numPlayers);
            }
        });

    }
}
