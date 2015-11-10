package edu.gatech.cs2340.sharkbait.model;


import edu.gatech.cs2340.sharkbait.util.*;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


/** 
* GameDuration Tester. 
 *
 * @author Osama Sakhi
 * @since <pre>Nov 8, 2015</pre>
 * @version 1.0
 * TODO: Finish the rest of the tests
*/
public class GameDurationTest {

    List<Player> players;

    private void setConfigs() {
        GameConfigs.setGameDifficulty(Difficulty.Beginner);
        GameConfigs.setMapType(MapType.StandardMap);
        GameConfigs.setNumPlayers(players.size());
    }

    @Before
    public void before() throws Exception {


        final double ALPHA = 0.5;
        String colorFireBrick = String.format("rgba(178,34,34,%f)",
                ALPHA);
        String colorGold = String.format("rgba(255,215,0,%f)", ALPHA);

        Player osama = new Player("Osama", colorFireBrick, Race.Human);
        osama.changeMoney(+2000);
        Player sayem = new Player("Sayem", colorGold, Race.Human);

        players = new ArrayList<>();
        players.add(osama);
        players.add(sayem);

        setConfigs();
        GameDuration.begin();
        GameDuration.setPlayers(new ArrayList<Player>());
        GameDuration.addPlayer(osama);
        GameDuration.addPlayer(sayem);

    }

    @After
    public void after() throws Exception {
    }

    /**
    *
    * Method: getInstance()
    *
    */
    @Test
    public void testGetInstance() throws Exception {
        //TODO: Test goes here...
        GameDuration instance = GameDuration.getInstance();
        assertNotNull(instance);
    }

    /**
    *
    * Method: hasBegun()
    *
    */
    @Test
    public void testHasBegun() throws Exception {
    //TODO: Test goes here...
    }

    /**
    *
    * Method: isPaused()
    *
    */
    @Test
    public void testIsPaused() throws Exception {
    //TODO: Test goes here...
    }

    /**
    *
    * Method: pause()
    *
    */
    @Test
    public void testPause() throws Exception {
    //TODO: Test goes here...
    }

    /**
    *
    * Method: resume()
    *
    */
    @Test
    public void testResume() throws Exception {
    //TODO: Test goes here...
    }

    /**
    *
    * Method: begin()
    *
    */
    @Test
    public void testBegin() throws Exception {
    //TODO: Test goes here...
    }

    /**
    *
    * Method: getActivePlayer()
    *
    */
    @Test
    public void testGetActivePlayer() throws Exception {
    //TODO: Test goes here...
    }

    /**
    *
    * Method: setActivePlayer(Player activePlayer)
    *
    */
    @Test
    public void testSetActivePlayer() throws Exception {
    //TODO: Test goes here...
    }

    /**
    *
    * Method: fetchProperty(Button button)
    *
    */
    @Test
    public void testFetchProperty() throws Exception {
    //TODO: Test goes here...
    }

    /**
    *
    * Method: addProperty(Button button, Property property)
    *
    */
    @Test
    public void testAddProperty() throws Exception {
    //TODO: Test goes here...
    }

    /**
    *
    * Method: getGameMap()
    *
    */
    @Test
    public void testGetGameMap() throws Exception {
    //TODO: Test goes here...
    }

    /**
    *
    * Method: setGameMap(Parent gameMap)
    *
    */
    @Test
    public void testSetGameMap() throws Exception {
    //TODO: Test goes here...
    }

    /**
    *
    * Method: getTownMap()
    *
    */
    @Test
    public void testGetTownMap() throws Exception {
    //TODO: Test goes here...
    }

    /**
    *
    * Method: setTownMap(Parent townMap)
    *
    */
    @Test
    public void testSetTownMap() throws Exception {
    //TODO: Test goes here...
    }

    /**
    *
    * Method: getTurn()
    *
    */
    @Test
    public void testGetTurn() throws Exception {
    //TODO: Test goes here...
    }

    /**
    *
    * Method: setTurn(int number)
    *
    */
    @Test
    public void testSetTurn() throws Exception {
    //TODO: Test goes here...
    }

    /**
    *
    * Method: getRound()
    *
    */
    @Test
    public void testGetRound() throws Exception {
    //TODO: Test goes here...
    }

    /**
    *
    * Method: getPhase()
    *
    */
    @Test
    public void testGetPhase() throws Exception {
    //TODO: Test goes here...
    }

    /**
     *
     * Method: endTurn()
     *
     */
    @Test
    public void testEndTurn() throws Exception {
        //TODO: Test goes here...
    }

    /**
    *
    * Method: updateTurn()
    *
    */
    @Test
    public void testUpdateTurn() throws Exception {

        GameDuration instance = GameDuration.getInstance();
        GameDuration.begin();
        Field turnField;
        Field roundField;
        Field phaseField;

        try {
            turnField = GameDuration.class.getDeclaredField("turn");
            turnField.setAccessible(true);

            roundField = GameDuration.class.getDeclaredField("round");
            roundField.setAccessible(true);


            phaseField = GameDuration.class.getDeclaredField("phase");
            phaseField.setAccessible(true);
            GamePhase landBuyPhase = GamePhase.LandBuyPhase;
            phaseField.set(instance, landBuyPhase);

//            Before anything happens, player0 in list is active
            int turn = (int) turnField.get(instance);
            assertEquals(turn, 0);

            int round = (int) roundField.get(instance);
            assertEquals(round, 1);

            GamePhase phase = (GamePhase) phaseField.get(instance);
            assertEquals(phase, GamePhase.LandBuyPhase);

            assertEquals(players.get(0), GameDuration.getActivePlayer());

            GameDuration.updateTurn();
//            After turn updated, player1 in list is active.

            turn = (int) turnField.get(instance);
            assertEquals(turn, 1);

            round = (int) roundField.get(instance);
            assertEquals(round, 1);

            phase = (GamePhase) phaseField.get(instance);
            assertEquals(phase, GamePhase.LandBuyPhase);

            assertEquals(players.get(1), GameDuration.getActivePlayer());

            GameDuration.updateTurn();

//            Now player0 is active and in PlayerTurnPhase
            turn = (int) turnField.get(instance);
            assertEquals(turn, 0);

            round = (int) roundField.get(instance);
            assertEquals(round, 1);

            phase = (GamePhase) phaseField.get(instance);
            assertEquals(phase, GamePhase.PlayerTurnPhase);

            assertEquals(players.get(0), GameDuration.getActivePlayer());

            GameDuration.updateTurn();
//            Now player1 is active and in PlayerTurnPhase

            turn = (int) turnField.get(instance);
            assertEquals(turn, 1);

            round = (int) roundField.get(instance);
            assertEquals(round, 1);

            phase = (GamePhase) phaseField.get(instance);
            assertEquals(phase, GamePhase.PlayerTurnPhase);

            assertEquals(players.get(1), GameDuration.getActivePlayer());

            GameDuration.updateTurn();
//            Now list is sorted, so player0, who has more money, will now take the place of
//            player 1 in the list

            turn = (int) turnField.get(instance);
            assertEquals(turn, 0);

            round = (int) roundField.get(instance);
            assertEquals(round, 2);

            phase = (GamePhase) phaseField.get(instance);
            assertEquals(phase, GamePhase.LandBuyPhase);

            assertEquals(players.get(1), GameDuration.getActivePlayer());

            GameDuration.updateTurn();
//            Now list is sorted, so player0, who has more money, will now take the place of
//            player 1 in the list

            turn = (int) turnField.get(instance);
            assertEquals(turn, 1);

            round = (int) roundField.get(instance);
            assertEquals(round, 2);

            phase = (GamePhase) phaseField.get(instance);
            assertEquals(phase, GamePhase.LandBuyPhase);

            assertEquals(players.get(0), GameDuration.getActivePlayer());


            GameDuration.updateTurn();
//            List positions same as previous, but now it's PlayerTurnPhase

            turn = (int) turnField.get(instance);
            assertEquals(turn, 0);

            round = (int) roundField.get(instance);
            assertEquals(round, 2);

            phase = (GamePhase) phaseField.get(instance);
            assertEquals(phase, GamePhase.PlayerTurnPhase);

            assertEquals(players.get(1), GameDuration.getActivePlayer());

            GameDuration.updateTurn();
//            List positions same as previous, but now it's PlayerTurnPhase

            turn = (int) turnField.get(instance);
            assertEquals(turn, 1);

            round = (int) roundField.get(instance);
            assertEquals(round, 2);

            phase = (GamePhase) phaseField.get(instance);
            assertEquals(phase, GamePhase.PlayerTurnPhase);

            assertEquals(players.get(0), GameDuration.getActivePlayer());

            GameDuration.updateTurn();
//            Lastly, ensure turn and rounds still keep updating as normal
            turn = (int) turnField.get(instance);
            assertEquals(turn, 0);

            round = (int) roundField.get(instance);
            assertEquals(round, 3);

            phase = (GamePhase) phaseField.get(instance);
            assertEquals(phase, GamePhase.LandBuyPhase);

            assertEquals(players.get(1), GameDuration.getActivePlayer());

        } catch(IllegalAccessException e) {

        }

    }

    /**
    *
    * Method: endGame()
    *
    */
    @Test
    public void testEndGame() throws Exception {
    //TODO: Test goes here...
    }

    /**
    *
    * Method: changeTimeRemaining(int deltaT)
    *
    */
    @Test
    public void testChangeTimeRemaining() throws Exception {
    //TODO: Test goes here...
    }

    /**
    *
    * Method: getTimeRemaining()
    *
    */
    @Test
    public void testGetTimeRemaining() throws Exception {
    //TODO: Test goes here...
    }

    /**
    *
    * Method: resetTime()
    *
    */
    @Test
    public void testResetTime() throws Exception {
    //TODO: Test goes here...
    }

    /**
    *
    * Method: getPlayers()
    *
    */
    @Test
    public void testGetPlayers() throws Exception {
    //TODO: Test goes here...
    }

    /**
    *
    * Method: setPlayers(List<Player> players)
    *
    */
    @Test
    public void testSetPlayers() throws Exception {
    //TODO: Test goes here...
    }

    /**
    *
    * Method: addPlayer(Player player)
    *
    */
    @Test
    public void testAddPlayer() throws Exception {
    //TODO: Test goes here...
    }

    /**
    *
    * Method: getActiveMuleType()
    *
    */
    @Test
    public void testGetActiveMuleType() throws Exception {
    //TODO: Test goes here...
    }

    /**
    *
    * Method: setActiveMuleType(Resource activeMuleType)
    *
    */
    @Test
    public void testSetActiveMuleType() throws Exception {
    //TODO: Test goes here...
    }

    /**
    *
    * Method: clearActiveMuleType()
    *
    */
    @Test
    public void testClearActiveMuleType() throws Exception {
    //TODO: Test goes here...
    }

    /**
    *
    * Method: beginMulePlacementPhase()
    *
    */
    @Test
    public void testBeginMulePlacementPhase() throws Exception {
    //TODO: Test goes here...
    }

    /**
    *
    * Method: endMulePlacementPhase()
    *
    */
    @Test
    public void testEndMulePlacementPhase() throws Exception {
    //TODO: Test goes here...
    }

    /**
    *
    * Method: unpack(GameDuration source)
    *
    */
    @Test
    public void testUnpack() throws Exception {
    //TODO: Test goes here...
    }

    /**
    *
    * Method: unpackfromJson(String jsonSource)
    *
    */
    @Test
    public void testUnpackfromJson() throws Exception {
    //TODO: Test goes here...
    }

    /**
    *
    * Method: packAsJson()
    *
    */
    @Test
    public void testPackAsJson() throws Exception {
    //TODO: Test goes here...
    }


    /**
    *
    * Method: determineTimeRemaining()
    *
    */
    @Test
    public void testDetermineTimeRemaining() throws Exception {
    //TODO: Test goes here...
    /*
    try {
       Method method = GameDuration.getClass().getMethod("determineTimeRemaining");
       method.setAccessible(true);
       method.invoke(<Object>, <Parameters>);
    } catch(NoSuchMethodException e) {
    } catch(IllegalAccessException e) {
    } catch(InvocationTargetException e) {
    }
    */
    }

    /**
    *
    * Method: handleRandomEvents()
    *
    */
    @Test
    public void testHandleRandomEvents() throws Exception {
    //TODO: Test goes here...
    /*
    try {
       Method method = GameDuration.getClass().getMethod("handleRandomEvents");
       method.setAccessible(true);
       method.invoke(<Object>, <Parameters>);
    } catch(NoSuchMethodException e) {
    } catch(IllegalAccessException e) {
    } catch(InvocationTargetException e) {
    }
    */
    }

    /**
    *
    * Method: handleProductionIfApplicable()
    *
    */
    @Test
    public void testHandleProductionIfApplicable() throws Exception {
    //TODO: Test goes here...
    /*
    try {
       Method method = GameDuration.getClass().getMethod("handleProductionIfApplicable");
       method.setAccessible(true);
       method.invoke(<Object>, <Parameters>);
    } catch(NoSuchMethodException e) {
    } catch(IllegalAccessException e) {
    } catch(InvocationTargetException e) {
    }
    */
    }

} 
