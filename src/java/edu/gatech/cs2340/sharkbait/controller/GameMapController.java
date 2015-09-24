package edu.gatech.cs2340.sharkbait.controller;

import edu.gatech.cs2340.sharkbait.MasterController;
import edu.gatech.cs2340.sharkbait.model.GameConfigs;
import edu.gatech.cs2340.sharkbait.model.GameDuration;
import edu.gatech.cs2340.sharkbait.util.GamePhase;
import edu.gatech.cs2340.sharkbait.util.Player;
import edu.gatech.cs2340.sharkbait.util.Property;
import edu.gatech.cs2340.sharkbait.util.PropertyType;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import javax.imageio.ImageIO;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by osama on 9/22/15.
 */
public class GameMapController implements Initializable {

    @FXML
    private ImageView town;
    @FXML
    private GridPane grid;
    @FXML
    private Button passButton;

    @FXML
    private Label phaseMsg;

    @FXML
    private Label playerMsg;

    @FXML
    private Label player1, player2, player3, player4;


    private static final String PHASE = "Phase: ";
    private static final String PLAYER = "Active Player: ";

    @Override
    public void initialize(URL location, ResourceBundle resources) {



//        phaseMsg.setText(PHASE + GameDuration.getPhase().toString());
//        playerMsg.setText(PLAYER + GameDuration.getActivePlayer().getName());

        town.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("Town Clicked");

                Player active = GameDuration.getActivePlayer();
                Scene currentScene = town.getScene();

//                TODO: Only if play active then go into store with that player
                if (active == null) {
                    MasterController.changeSceneToTownMap();
                }
            }
        });



//        For all grid cells
        for (Node n : grid.getChildren()) {

//            System.out.println(n.getClass().toString());
//            ImageView imgView = (ImageView) n;
//            System.out.println(imgView.getImage().getPixelReader());

//            System.out.println(grid.getRow);
//            System.out.println(n.getLayoutY());

//            Unless you are the town cell
            if (!n.equals(town)) {

//                When clicked and in buying phase, buy for player, end buy phase
                n.setOnMouseClicked(new EventHandler<MouseEvent>() {

                    @Override
                    public void handle(MouseEvent event) {
                        Player player = GameDuration.getActivePlayer();

                        if (player == null) {
//                            TODO: Change color using activePlayer.getColor() if no other player
//                            bought
                            ImageView view = (ImageView) n;
                            Image img = view.getImage();
//                            tint(view);
//                            img.getPixelReader().;
//                            Image other = new Image(img);

//                            TODO: End turn for this player
                            GameDuration.endTurn();
                            player = GameDuration.getActivePlayer();
                        }
                    }
                });

//                When hoevered and is player's turn, change color
                n.setOnMouseEntered(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {

//                        if not bought, then change color.

                    }
                });

                passButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        GameDuration.endTurn();
                        updateMessages();
                    }
                });
            }
        }



//        popup some message saying it's player X's turn
//        first 2
    }

    @FXML
    private void handleGridButtonPress(ActionEvent ev) {

        Button button = (Button) ev.getSource();
        System.out.println("You pressed: " + button.getText());

        boolean available = button.getStyle().contains("-fx-background-color:rgba(0,0,0,0);");

        if (!available) {
//            Property already bought, do nothing

        } else {
//            Buy property
            //THIS SHOULD UPDATE THE PLAYER

            Player activePlayer = GameDuration.getActivePlayer();
            String type = button.getText();
            PropertyType propertyType = null;

            if (activePlayer != null) {


                if (type.equals("plains")) {
                    propertyType = PropertyType.Plains;
                } else if (type.equals("mountain1")) {
                    propertyType = PropertyType.Mountain;
                } else if (type.equals("River")) {
                    propertyType = PropertyType.River;
                }

                Property property = new Property(propertyType, activePlayer);
                activePlayer.addProperty(property);

                System.out.println(activePlayer.getProperties().size());

                String color = activePlayer.getColor();
                String styleVal = "-fx-background-color:" + color;
                button.setStyle(styleVal);

                GameDuration.endTurn();
                updateMessages();

            }

        }


    }

    private void updateMessages() {
        if (GameDuration.getPhase() == GamePhase.PlayerTurnPhase) {
            passButton.setText("End Turn");
        } else {
            passButton.setText("Pass");
        }
        phaseMsg.setText(PHASE + GameDuration.getPhase().toString());
        playerMsg.setText(PLAYER + GameDuration.getActivePlayer().getName());
        player1.setText("Player 1: " + GameConfigs.players.get(0).getMoney());
        player2.setText("Player 2: " + GameConfigs.players.get(1).getMoney());
        if (GameConfigs.getNumPlayers() == 3) {
            player3.setText("Player 3: " + GameConfigs.players.get(2).getMoney());
        }
        if (GameConfigs.getNumPlayers() == 4) {
            player3.setText("Player 3: " + GameConfigs.players.get(2).getMoney());
            player4.setText("Player 4: " + GameConfigs.players.get(3).getMoney());
        }
    }


    private void beginLandSelection(Player player, int cost) {

    }

//    TODO: Begin a round for a player
    /**
     * Begin a round for a player for this much time
     * @param player, the player who's turn it is
     * @param coundown, the time in second for the turn to proceed
     */
    private void beginTurn(Player player, int coundown) {
    }

//    TODO: Implement reward for ending turn

    /**
     * Use difficulty to determine how much player gets rewarded for ending turn
     * @param player
     */
    private void goToPub(Player player) {
    }

    public static void tint(ImageView view) {

        Image img = view.getImage();
        PixelReader reader = img.getPixelReader();

        WritableImage write = new WritableImage(reader,
                (int) img.getWidth(),
                (int) img.getHeight());

        PixelWriter writer = write.getPixelWriter();

        view.setStyle("-fx-background-color:red;");

        System.out.println("Tinting image now");

//        double red;

//        for (int x = 0; x < img.getWidth(); x++) {
//            for (int y = 0; y < img.getHeight(); y++) {
//
//                Color orig = reader.getColor(x, y);
//                red = orig.getRed();
//                Color after;

//                Image updated = new Image()

//                Color color = new Color(img.getRGB(x, y));
//
//                // do something with the color :) (change the hue, saturation and/or brightness)
//                // float[] hsb = new float[3];
//                // Color.RGBtoHSB(color.getRed(), old.getGreen(), old.getBlue(), hsb);
//
//                // or just call brighter to just tint it
//                Color brighter = color.brighter();

//                img.setRGB(x, y, brighter.getRGB());
//            }
//        }
    }

}
