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

import java.net.URL;
import java.util.ResourceBundle;

public class GameConfigController implements Initializable {

    @FXML
    private ComboBox<String> configDifficulty;
    @FXML
    private ComboBox<String> mapChoice;
    @FXML
    private Slider playerSlider;

    @FXML
    private Button startButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ObservableList<String> difficultyOptions = FXCollections.observableArrayList(
                "Beginner",
                "Standard",
                "Tournament"
        );
        configDifficulty.setItems(difficultyOptions);

        ObservableList<String> mapOptions = FXCollections.observableArrayList(
                "Standard",
                "Randomized"
        );
        mapChoice.setItems(mapOptions);

        int val = (int) playerSlider.getValue();

        System.out.println(val);

//        playerSlider.setOnDragDropped(new EventHandler<DragEvent>() {
//            @Override
//            public void handle(DragEvent event) {
//                System.out.println("Moved");
//                System.out.println(playerSlider.getValue());
//            }
//        });


    }
}
