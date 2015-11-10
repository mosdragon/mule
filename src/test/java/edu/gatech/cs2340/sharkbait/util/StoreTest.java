package edu.gatech.cs2340.sharkbait.util;
import edu.gatech.cs2340.sharkbait.model.GameConfigs;
import edu.gatech.cs2340.sharkbait.model.Store;
import edu.gatech.cs2340.sharkbait.util.*;
import javafx.scene.control.Button;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


/**
 * Created by bruckw on 11/10/15.
 */
public class StoreTest {
    Store store = new Store();
    @Test
    public void testSellFood() {
        GameConfigs.setGameDifficulty(Difficulty.Beginner);
        Player player1 =  new Player("Bruck", "Brown", Race.Human);
        store.initializeStore();
        assertEquals(store.getFoodCount(), 16);
        assertEquals(player1.getFood(), 8);
        store.sellFood(player1);
        assertEquals(player1.getFood(), 7);
        assertEquals(store.getFoodCount(), 17);

//        Get rid of all player's food, watch it do nothing
        player1.changeFood(-player1.getFood());
        assertEquals(0, player1.getFood());
        int playerFood = player1.getFood();
        int storeFood = Store.getFoodCount();
        store.sellFood(player1);
        assertEquals(storeFood, store.getFoodCount());
        assertEquals(playerFood, player1.getFood());




    }


}
