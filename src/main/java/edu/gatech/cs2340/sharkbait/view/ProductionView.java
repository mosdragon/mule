package edu.gatech.cs2340.sharkbait.view;

import edu.gatech.cs2340.sharkbait.util.Player;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class ProductionView implements Initializable {

    @FXML
    private Button okayButton;

    @FXML
    private TextField player1Name;
    @FXML
    private TextField player2Name;
    @FXML
    private TextField player3Name;
    @FXML
    private TextField player4Name;

    @FXML
    private TextField player1Food;
    @FXML
    private TextField player2Food;
    @FXML
    private TextField player3Food;
    @FXML
    private TextField player4Food;

    @FXML
    private TextField player1Energy;
    @FXML
    private TextField player2Energy;
    @FXML
    private TextField player3Energy;
    @FXML
    private TextField player4Energy;

    @FXML
    private TextField player1Ore;
    @FXML
    private TextField player2Ore;
    @FXML
    private TextField player3Ore;
    @FXML
    private TextField player4Ore;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        okayButton.setOnMouseClicked(event -> {
            //Return to the game.
        });
    }

    public void setPlayer1Production(Player player) {
        player1Name.setText(player.getName());
        player1Food.setText(Integer.toString(player.getFood()));
        player1Energy.setText(Integer.toString(player.getEnergy()));
        player1Ore.setText(Integer.toString(player.getOre()));
    }

    public void setPlayer2Production(Player player) {
        player2Name.setText(player.getName());
        player2Food.setText(Integer.toString(player.getFood()));
        player2Energy.setText(Integer.toString(player.getEnergy()));
        player2Ore.setText(Integer.toString(player.getOre()));
    }

    public void setPlayer3Production(Player player) {
        player3Name.setText(player.getName());
        player3Food.setText(Integer.toString(player.getFood()));
        player3Energy.setText(Integer.toString(player.getEnergy()));
        player3Ore.setText(Integer.toString(player.getOre()));
    }

    public void setPlayer4Production(Player player) {
        player4Name.setText(player.getName());
        player4Food.setText(Integer.toString(player.getFood()));
        player4Energy.setText(Integer.toString(player.getEnergy()));
        player4Ore.setText(Integer.toString(player.getOre()));
    }
}
