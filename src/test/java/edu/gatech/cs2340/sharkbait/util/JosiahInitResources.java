package edu.gatech.cs2340.sharkbait.util;
import edu.gatech.cs2340.sharkbait.model.GameConfigs;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Created by JosiahMacbook on 11/8/15.
 */
public class JosiahInitResources {

    GameConfigs configs = GameConfigs.getInstance();
    Difficulty originalDifficulty = configs.getGameDifficulty();
    private static final int BEGINNER_FOOD = 8;
    private static final int BEGINNER_ENERGY = 4;
    private static final int BEGINNER_ORE = 0;

    private static final int STANDARD_FOOD = 4;
    private static final int STANDARD_ENERGY = 2;
    private static final int STANDARD_ORE = 0;

    private static final int TOURNAMENT_FOOD = 4;
    private static final int TOURNAMENT_ENERGY = 2;
    private static final int TOURNAMENT_ORE = 0;

    @After
    public void cleanUp() throws Exception {
        configs.setGameDifficulty(originalDifficulty);
    }

    @Test
    public void beginnerDifficulty() throws Exception {
        configs.setGameDifficulty(Difficulty.Beginner);
        Player player = new Player("Bob", "Waters", Race.Human);
        assertEquals(player.getFood(), BEGINNER_FOOD);
        assertEquals(player.getEnergy(), BEGINNER_ENERGY);
        assertEquals(player.getOre(), BEGINNER_ORE);
    }

    @Test
    public void standardDifficulty() throws Exception {
        configs.setGameDifficulty(Difficulty.Standard);
        Player player = new Player("Bob", "Waters", Race.Human);
        assertEquals(player.getFood(), STANDARD_FOOD);
        assertEquals(player.getEnergy(), STANDARD_ENERGY);
        assertEquals(player.getOre(), STANDARD_ORE);
    }

    @Test
    public void tournamentDifficulty() throws Exception {
        configs.setGameDifficulty(Difficulty.Tournament);
        Player player = new Player("Bob", "Waters", Race.Human);
        assertEquals(player.getFood(), TOURNAMENT_FOOD);
        assertEquals(player.getEnergy(), TOURNAMENT_ENERGY);
        assertEquals(player.getOre(), TOURNAMENT_ORE);
    }
}
