package edu.gatech.cs2340.sharkbait.util;

import org.junit.After;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by sayem on 10/31/2015.
 */
public class PlayerTest {

    Player player1 = new Player("Zayn", "Brown", Race.Human);

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testGetName() throws Exception {
        assertEquals(player1.getName(), "Zayn");
    }

    @Test
    public void testSetName() throws Exception {
        player1.setName("Malik");
        assertEquals(player1.getName(), "Malik");
    }

    @Test
    public void testGetColor() throws Exception {
        assertEquals(player1.getColor(), "Brown");
    }

    @Test
    public void testSetColor() throws Exception {
        player1.setColor("Black");
        assertEquals(player1.getColor(), "Black");
    }

    @Test
    public void testGetRace() throws Exception {
        assertEquals(player1.getRace(), Race.Human);
    }

    @Test
    public void testSetRace() throws Exception {
        player1.setRace(Race.Flapper);
        assertEquals(player1.getRace(), Race.Flapper);
    }

    @Test
    public void testChangeMoney() throws Exception {
        player1.changeMoney(-20);
        assertEquals(player1.getMoney(), 580, 1);
    }

    @Test
    public void testGetMoney() throws Exception {
        assertEquals(player1.getMoney(), 600, 1);
    }

    @Test
    public void testChangeEnergy() throws Exception {
        player1.changeEnergy(-2);
        assertEquals(player1.getEnergy(), 0);
    }

    @Test
    public void testGetEnergy() throws Exception {
        assertEquals(player1.getEnergy(), 2);
    }

    @Test
    public void testChangeFood() throws Exception {
        player1.changeFood(10);
        assertEquals(player1.getFood(), 14);
    }

    @Test
    public void testGetFood() throws Exception {
        assertEquals(player1.getFood(), 4);
    }

    @Test
    public void testGetMuleCount() throws Exception {
        assertEquals(player1.getMuleCount(), 0);
    }

    @Test
    public void testGetProperties() throws Exception {
        List<Property> nothing = new ArrayList<>();
        assertEquals(player1.getProperties(), nothing);
    }

    @Test
    public void testPurchaseProperty() throws Exception {
        List<Property> nothing = new ArrayList<>();
//        Property property = new Property();
//        player1.purchaseProperty(property);
        assertEquals(player1.getProperties(), nothing);
    }

    @Test
    public void testSellProperty() throws Exception {

    }

    @Test
    public void testToString() throws Exception {

    }

    @Test
    public void testCompareTo() throws Exception {

    }
}