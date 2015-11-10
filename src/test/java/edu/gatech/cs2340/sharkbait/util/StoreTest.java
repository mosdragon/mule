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
    public void testSellFood() throws Exception {
        Player player1 =  new Player("Bruck", "Brown", Race.Human);
        if(GameConfigs.getInstance().getGameDifficulty() == Difficulty.Beginner) {
            assertEquals(store.getFoodCount(), 16);
            assertEquals(player1.getFood(), 0);
            store.sellFood(player1);
            assertEquals(player1.getFood(), 1);
            assertEquals(store.getFoodCount(), 15);
        } else {
            assertEquals(8, store.getFoodCount(),8);
            assertEquals(player1.getFood(), 0);
            store.sellFood(player1);
            assertEquals(player1.getFood(), 1);
            assertEquals(store.getFoodCount(), 7);

        }
        // Should throw exception if not enough money.
        Player pLayer2 = new Player("Michael", "Brown", Race.Human);
        pLayer2.changeMoney(-600);

    }


}
