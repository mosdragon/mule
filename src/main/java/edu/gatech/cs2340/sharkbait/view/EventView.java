package edu.gatech.cs2340.sharkbait.view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.ResourceBundle;

public class EventView implements Initializable {

    @FXML
    private Button okayButton;
    @FXML
    private TextArea eventText;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        okayButton.setOnMouseClicked(event -> {
            //Return to the main screen.
        });
    }

    public void setEventText(String input) {
        eventText.setText(input);
    }
}
