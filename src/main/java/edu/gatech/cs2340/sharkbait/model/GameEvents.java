package edu.gatech.cs2340.sharkbait.model;

import edu.gatech.cs2340.sharkbait.util.GamePhase;
import edu.gatech.cs2340.sharkbait.util.Player;
import edu.gatech.cs2340.trydent.log.Log;

import java.util.Random;

/**
 * Utility class to handle creating random events and
 * updating player resources simultaneously.
 */
public final class GameEvents {

  /**
   * Private constructor to prevent instantiation.
   */
  private GameEvents() {

  }

  /**
   * For random events, which must occur 27% of the time to players that are
   * not in last place.
   */
  private static final double CHANCE = 0.27;

  /**
   * Used for calculation factor for random events.
   * Index into array with round to get the calculation factor for that
   * round.
   */
  private static final int[] CALC_FACTOR_PER_ROUND = {0, 25, 25, 25, 50,
      50, 50, 50, 75, 75, 75, 75, 100};


  /**
   * Event1 food change.
   */
  private static final int EVENT1_FOOD = 3;
  /**
   * Event1 energy change.
   */
  private static final int EVENT1_ENERGY = 2;


  /**
   * Event2 ore change.
   */
  private static final int EVENT2_ORE = 2;

  /**
   * Event3 money multiplier.
   */
  private static final int EVENT3_MONEY_MULTIPLIER = 8;

  /**
   * Event4 money multiplier.
   */
  private static final int EVENT4_MONEY_MULTIPLIER = 2;

  /**
   * Event5 money multiplier.
   */
  private static final int EVENT5_MONEY_MULTIPLIER = -4;

  /**
   * Event6 food divisor.
   */
  private static final int EVENT6_FOOD_DIVISOR = -2;

  /**
   * Event7 money multiplier.
   */
  private static final int EVENT7_MONEY_MULTIPLIER = -6;

  /**
   * Event 1.
   */
  private static final int EVENT1 = 1;
  /**
   * Event 2.
   */
  private static final int EVENT2 = 2;
  /**
   * Event 3.
   */
  private static final int EVENT3 = 3;
  /**
   * Event 4.
   */
  private static final int EVENT4 = 4;

  /**
   * Event 5.
   */
  private static final int EVENT5 = 5;

  /**
   * Event 6.
   */
  private static final int EVENT6 = 6;

  /**
   * Event 7.
   */
  private static final int EVENT7 = 7;


  /**
   * Event 1 text.
   */
  private static final String TEXT_EVENT1 = "YOU JUST RECEIVED A PACKAGE"
      + "FROM THE GT ALUMNI CONTAINING 3 FOOD AND 2 ENERGY UNITS.";

  /**
   * Event 2 text.
   */
  private static final String TEXT_EVENT2 = "A WANDERING TECH STUDENT REPAID"
      + "YOUR HOSPITALITY BY LEAVING TWO BARS OF ORE.";

  /**
   * Event 3 text.
   */
  private static final String TEXT_FORMAT_EVENT3 = "THE MUSEUM BOUGHT YOUR"
      + "ANTIQUE PERSONAL COMPUTER FOR $%d";

  /**
   * Event 4 text.
   */
  private static final String TEXT_FORMAT_EVENT4 = "YOU FOUND A DEAD"
      + " MOOSE RAT AND SOLD THE HIDE FOR $%d";

  /**
   * Event 5 text.
   */
  private static final String TEXT_FORMAT_EVENT5 = "FLYING CAT-BUGS ATE "
      + "THE ROOF OFF YOUR HOUSE. REPAIRS COST $%d";

  /**
   * Event 6 text.
   */
  private static final String TEXT_EVENT6 = "MISCHIEVOUS UGA STUDENTS"
      + "BROKE INTO YOUR STORAGE SHED AND STOLE HALF YOUR FOOD.";

  /**
   * Event 7 text.
   */
  private static final String TEXT_FORMAT_EVENT7 = "YOUR SPACE GYPSY IN-LAWS"
      + " MADE A MESS OF THE TOWN. IT COST YOU"
      + " $%d TO CLEAN IT UP.";

  /**
   * Last index of good events among the random events.
   */
  private static final int INDEX_FINAL_GOOD_EVENT = 4;

  /**
   * Last index of events among the random events.
   */
  private static final int INDEX_DEFAULT_EVENTS = 7;

  /**
   * Method to create random event string and make it take effect
   * on the passed in player.
   *
   * @param player         the activePlayer who is experiencing event
   * @param goodEventsOnly whether or not we want to restrict events to being
   *                       only positive events
   * @return the event string
   */
  private static String makeRandomEvent(final Player player, final boolean
      goodEventsOnly) {

    int maxIndex = INDEX_DEFAULT_EVENTS;

    if (goodEventsOnly) {
      maxIndex = INDEX_FINAL_GOOD_EVENT;
    }

    Random random = new Random();
    String event = "";
    double chanceOfEvent = random.nextDouble();
    GamePhase phase = GameDuration.getPhase();

    if (phase == GamePhase.PlayerTurnPhase && chanceOfEvent <= CHANCE) {

      Log.debug("Random event occurring for: " + player.getName());
      int round = GameDuration.getRound();
      int calculationFactor = CALC_FACTOR_PER_ROUND[round];
      int eventId = random.nextInt(maxIndex) + 1;

      switch (eventId) {

        case EVENT1:
          event = TEXT_EVENT1;
          player.changeFood(EVENT1_FOOD);
          player.changeEnergy(EVENT1_ENERGY);
          break;

        case EVENT2:
          event = TEXT_EVENT2;
          player.changeOre(EVENT2_ORE);
          break;

        case EVENT3:
          event = String.format(TEXT_FORMAT_EVENT3,
              EVENT3_MONEY_MULTIPLIER * calculationFactor);
          player.changeMoney(EVENT3_MONEY_MULTIPLIER * calculationFactor);
          break;

        case EVENT4:
          event = String.format(TEXT_FORMAT_EVENT4,
              EVENT4_MONEY_MULTIPLIER * calculationFactor);
          player.changeMoney(EVENT4_MONEY_MULTIPLIER * calculationFactor);
          break;

        case EVENT5:
          event = String.format(TEXT_FORMAT_EVENT5,
              EVENT5_MONEY_MULTIPLIER * calculationFactor);
          player.changeMoney(EVENT5_MONEY_MULTIPLIER * calculationFactor);
          break;

        case EVENT6:
          event = TEXT_EVENT6;
          player.changeFood(player.getFood() / EVENT6_FOOD_DIVISOR);
          break;

        case EVENT7:
          event = String.format(TEXT_FORMAT_EVENT7,
              EVENT7_MONEY_MULTIPLIER * calculationFactor);
          player.changeMoney(EVENT7_MONEY_MULTIPLIER * calculationFactor);
          break;

        default:
          break;
      }
    }
    return event;
  }

  /**
   * Generate a random event for a player.
   *
   * @param player the active player who will receive this random event
   * @return the event the occurred as a String
   */
  public static String generateRandomEvent(final Player player) {
    return makeRandomEvent(player, false);
  }

  /**
   * Generate a random good event for a player.
   *
   * @param player the active player who will receive this random event
   * @return the event the occurred as a String
   */
  public static String generateRandomGoodEvent(final Player player) {
    return makeRandomEvent(player, true);
  }
}
