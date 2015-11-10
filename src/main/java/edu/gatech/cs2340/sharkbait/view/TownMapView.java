package edu.gatech.cs2340.sharkbait.view;

import edu.gatech.cs2340.sharkbait.controller.TownMapController;
import edu.gatech.cs2340.sharkbait.model.Constants;
import edu.gatech.cs2340.sharkbait.model.GameDuration;
import edu.gatech.cs2340.sharkbait.model.Store;
import edu.gatech.cs2340.sharkbait.util.Player;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * The town map screen.
 */
public class TownMapView implements Initializable {
  /**
   * Button exitTown.
   */
  @FXML
  private Button exitTown;
  /**
   * Button enterPub.
   */
  @FXML
  private Button enterPub;
  /**
   * Button buyOre, sellOre, buyFood, sellFood, buyEnergy, sellEnergy.
   */
  @FXML private Button buyOre;
  @FXML private Button sellOre;
  @FXML private Button buyFood;
  @FXML private Button sellFood;
  @FXML private Button buyEnergy;
  @FXML private Button sellEnergy;
  /**
   * Button buyOreMule, sellOreMule, buyFoodMule, sellFoodMule, buyEnergyMule,
   * sellEnergyMule.
   */
  @FXML private Button buyOreMule;
  @FXML private Button sellOreMule;
  @FXML private Button buyFoodMule;
  @FXML private Button sellFoodMule;
  @FXML private Button buyEnergyMule;
  @FXML private Button sellEnergyMule;
  /**
   * Text oreText, foodText, energyText, muleText.
   */
  @FXML private Text oreText;
  @FXML private Text foodText;
  @FXML private Text energyText;
  @FXML private Text muleText;
  /**
   * Label phaseMsg.
   */
  @FXML
  private Label phaseMsg;
  /**
   * Label randomEventMsg.
   */
  @FXML
  private Label randomEventMsg;
  /**
   * Label playerMsg.
   */
  @FXML
  private Label playerMsg;
  /**
   * Label timerMsg.
   */
  @FXML
  private Label timerMsg;
  /**
   * VBox scorePanel1, scorePanel2, scorePanel3, scorePanel4,
   * activePlayerPanel.
   */
  @FXML
  private VBox scorePanel1;
  @FXML
  private VBox scorePanel2;
  @FXML
  private VBox scorePanel3;
  @FXML
  private VBox scorePanel4;
  @FXML
  private VBox activePlayerPanel;

  /**
   * initializes Labels for names.
   */
  @FXML
  private Label name1;
  @FXML
  private Label name2;
  @FXML
  private Label name3;
  @FXML
  private Label name4;

  /**
   * initializes Labels for money.
   */
  @FXML
  private Label money1;
  @FXML
  private Label money2;
  @FXML
  private Label money3;
  @FXML
  private Label money4;

  /**
   * initializes Labels for food.
   */
  @FXML
  private Label food1;
  @FXML
  private Label food2;
  @FXML
  private Label food3;
  @FXML
  private Label food4;

  /**
   * initializes Labels for mules.
   */
  @FXML
  private Label mules1;
  @FXML
  private Label mules2;
  @FXML
  private Label mules3;
  @FXML
  private Label mules4;

  /**
   * initializes labels for energy.
   */
  @FXML
  private Label energy1;
  @FXML
  private Label energy2;
  @FXML
  private Label energy3;
  @FXML
  private Label energy4;

  /**
   * initializes Labels for ores.
   */
  @FXML
  private Label ore1;
  @FXML
  private Label ore2;
  @FXML
  private Label ore3;
  @FXML
  private Label ore4;

  /**
   * Timeline timeline.
   */
  private Timeline timeline;
  /**
   * String PHASE.
   */
  private static final String PHASE = "Phase: ";
  /**
   * String PLAYER.
   */
  private static final String PLAYER = "Active Player: ";
  /**
   * String TIME_LEFT.
   */
  private static final String TIME_LEFT = "Time Remaining: ";
  /**
   * String MULE_TEXT.
   */
  private static final String MULE_TEXT = "MULES: ";
  /**
   * String FOOD_TEXT.
   */
  private static final String FOOD_TEXT = "FOOD: ";
  /**
   * String ENERGY_TEXT.
   */
  private static final String ENERGY_TEXT = "ENERGY: ";
  /**
   * String ORE_TEXT.
   */
  private static final String ORE_TEXT = "ORE: ";
  /**
   * String BUY_ORE_TEXT.
   */
  private static final String BUY_ORE_TEXT = "Buy Ore: $";
  /**
   * String SELL_ORE_TEXT.
   */
  private static final String SELL_ORE_TEXT = "Sell Ore: $";
  /**
   * String BUY_FOOD_TEXT.
   */
  private static final String BUY_FOOD_TEXT = "Buy Food: $";
  /**
   * String SELL_FOOD_TEXT.
   */
  private static final String SELL_FOOD_TEXT = "Sell Food: $";
  /**
   * String BUY_ENERGY_TEXT.
   */
  private static final String BUY_ENERGY_TEXT = "Buy Energy: $";
  /**
   * String SELL_ENERGY_TEXT.
   */
  private static final String SELL_ENERGY_TEXT = "Sell Energy: $";
  /**
   * String BUY_ORE_MULE_TEXT.
   */
  private static final String BUY_ORE_MULE_TEXT = "Buy Ore Mule: $";
  /**
   * String SELL_ORE_MULE_TEXT.
   */
  private static final String SELL_ORE_MULE_TEXT = "Sell Ore Mule: $";
  /**
   * String BUY_FOOD_MULE_TEXT.
   */
  private static final String BUY_FOOD_MULE_TEXT = "Buy Food Mule: $";
  /**
   * String SELL_FOOD_MULE_TEXT.
   */
  private static final String SELL_FOOD_MULE_TEXT = "Sell Food Mule: $";
  /**
   * String BUY_ENERGY_MULE_TEXT.
   */
  private static final String BUY_ENERGY_MULE_TEXT = "Buy Energy Mule: $";
  /**
   * String SELL_ENERGY_MULE_TEXT.
   */
  private static final String SELL_ENERGY_MULE_TEXT = "Sell Energy Mule: $";

  /**
   * 3 player game.
   */
  private static final int TRIPLE_PLAYERS = 3;

  /**
   * 4 player game.
   */
  private static final int QUAD_PLAYERS = 4;


  @Override
  public final void initialize(final URL location,
                               final ResourceBundle resources) {
    setClickListeners();
    displayItemCosts();
  }

  /**
   * updateTimer method.
   *
   * @param timeText timeText to update time
   */
  public final void updateTimer(final String timeText) {
    timerMsg.setText(timeText);
  }

  /**
   * updateMessages method.
   */
  public final void updateMessages() {
    if (GameDuration.hasBegun()) {
      updateQuantities();
      phaseMsg.setText(PHASE + GameDuration.getPhase().toString());
      playerMsg.setText(PLAYER
          + GameDuration.getActivePlayer().getName());

      updateScorePanels();
    }
  }

  /**
   * updateMessages method.
   */
  private void updateScorePanels() {

    List<Player> players = GameDuration.getPlayers();
    final int numPlayers = players.size();

    int playerIndex = 0;
    Player player1 = players.get(playerIndex++);

    scorePanel1.setStyle(String.format(Constants.BG_COLOR_TEMPLATE,
        player1.getColor()));
    name1.setText(player1.getName());
    money1.setText("Money: " + player1.getMoney());
    food1.setText("Food: " + player1.getFood());
    mules1.setText("Mules Owned: " + player1.getMuleCount());
    energy1.setText("Energy: " + player1.getEnergy());

    Player player2 = players.get(playerIndex++);

    scorePanel2.setStyle(String.format(Constants.BG_COLOR_TEMPLATE,
        player2.getColor()));
    name2.setText(player2.getName());
    money2.setText("Money: " + player2.getMoney());
    food2.setText("Food: " + player2.getFood());
    mules2.setText("Mules Owned: " + player2.getMuleCount());
    energy2.setText("Energy: " + player2.getEnergy());

    if (numPlayers >= TRIPLE_PLAYERS) {

      Player player3 = players.get(playerIndex++);

      scorePanel3.setStyle(String.format(Constants.BG_COLOR_TEMPLATE,
          player3.getColor()));
      name3.setText(player3.getName());
      money3.setText("Money: " + player3.getMoney());
      food3.setText("Food: " + player3.getFood());
      mules3.setText("Mules Owned: " + player3.getMuleCount());
      energy3.setText("Energy: " + player3.getEnergy());
    }

    if (numPlayers >= QUAD_PLAYERS) {

      Player player4 = players.get(playerIndex++);

      scorePanel4.setStyle(String.format(Constants.BG_COLOR_TEMPLATE,
          player4.getColor()));
      name4.setText(player4.getName());
      money4.setText("Money: " + player4.getMoney());
      food4.setText("Food: " + player4.getFood());
      mules4.setText("Mules Owned: " + player4.getMuleCount());
      energy4.setText("Energy: " + player4.getEnergy());
    }

    Player activePlayer = GameDuration.getActivePlayer();
    activePlayerPanel.setStyle(String.format(Constants.BG_COLOR_TEMPLATE,
        activePlayer.getColor()));
  }

  /**
   * displayItemCosts method.
   */
  private void displayItemCosts() {
    buyOre.setText(BUY_ORE_TEXT + Store.getOreCost());
    sellOre.setText(SELL_ORE_TEXT + Store.getOreCost());
    buyFood.setText(BUY_FOOD_TEXT + Store.getFoodCost());
    sellFood.setText(SELL_FOOD_TEXT + Store.getFoodCost());
    buyEnergy.setText(BUY_ENERGY_TEXT + Store.getEnergyCost());
    sellEnergy.setText(SELL_ENERGY_TEXT + Store.getEnergyCost());
    buyOreMule.setText(BUY_ORE_MULE_TEXT + Store.getOreMuleCost());
    sellOreMule.setText(SELL_ORE_MULE_TEXT + Store.getOreMuleCost());
    buyFoodMule.setText(BUY_FOOD_MULE_TEXT + Store.getFoodMuleCost());
    sellFoodMule.setText(SELL_FOOD_MULE_TEXT + Store.getFoodMuleCost());
    buyEnergyMule.setText(BUY_ENERGY_MULE_TEXT + Store.getEnergyMuleCost());
    sellEnergyMule.setText(SELL_ENERGY_MULE_TEXT
        + Store.getEnergyMuleCost());
  }

  /**
   * updateQuantities method.
   */
  private void updateQuantities() {
    oreText.setText(ORE_TEXT + Store.getOreCount());
    foodText.setText(FOOD_TEXT + Store.getFoodCount());
    energyText.setText(ENERGY_TEXT + Store.getEnergyCount());
    muleText.setText(MULE_TEXT + Store.getMuleCount());
  }

  /**
   * setClickListeners method.
   */
  private void setClickListeners() {

    exitTown.setOnMouseClicked(event -> TownMapController.exitTown());

    enterPub.setOnMouseClicked(event -> TownMapController.enterPub());

    buyOre.setOnMouseClicked(event -> {
      TownMapController.buyOre();
      updateQuantities();
    });

    sellOre.setOnMouseClicked(event -> {
      TownMapController.sellOre();
      updateQuantities();
    });

    buyFood.setOnMouseClicked(event -> {
      TownMapController.buyFood();
      updateQuantities();
    });

    sellFood.setOnMouseClicked(event -> {
      TownMapController.sellFood();
      updateQuantities();
    });

    buyEnergy.setOnMouseClicked(event -> {
      TownMapController.buyEnergy();
      updateQuantities();
    });

    sellEnergy.setOnMouseClicked(event -> {
      TownMapController.sellEnergy();
      updateQuantities();
    });

    buyOreMule.setOnMouseClicked(event -> {
      TownMapController.buyOreMule();
      updateQuantities();
    });

    sellOreMule.setOnMouseClicked(event -> {
      TownMapController.sellOreMule();
      updateQuantities();
    });

    buyFoodMule.setOnMouseClicked(event -> {
      TownMapController.buyFoodMule();
      updateQuantities();
    });

    sellFoodMule.setOnMouseClicked(event -> {
      TownMapController.sellFoodMule();
      updateQuantities();
    });

    buyEnergyMule.setOnMouseClicked(event -> {
      TownMapController.buyEnergyMule();
      updateQuantities();
    });

    sellEnergyMule.setOnMouseClicked(event -> {
      TownMapController.sellEnergyMule();
      updateQuantities();
    });

  }

  /**
   * handleRandomEvent method.
   *
   * @param randomEventText random event text
   */
  public final void handleRandomEvent(final String randomEventText) {
    randomEventMsg.setText(randomEventText);
    randomEventMsg.setWrapText(true);
  }
}
