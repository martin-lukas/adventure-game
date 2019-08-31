package gamelogic;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;

/**
 * Testovací třída.
 * 
 * @author Martin Lukáš
 * @version 1.0
 */
public class GameTest {
    
    private Game game1;
        
    /**
     * Metoda zakládá proměnné.
     */
    @Before
    public void setUp() {
        game1 = new Game();
    }
    
    /**
     * Metoda ukončuje test.
     */
    @After
    public void tearDown() {
    }
    
    /**
     * Testuje nejkratší možný průběh hrou.
     */
    public void testKratkyPrubehHry() {
        game1.handleCommand("prohledej sousoší");
        game1.handleCommand("seber víno");
        game1.handleCommand("jdi podloubí");
        game1.handleCommand("dej víno");
        game1.handleCommand("jdi nádvoří");
        game1.handleCommand("jdi chodba");
        game1.handleCommand("prohledej truhla");
        game1.handleCommand("seber chloroform");
        game1.handleCommand("seber whisky");
        game1.handleCommand("jdi komnata_knížete");
        game1.handleCommand("dej chloroform");
        game1.handleCommand("seber svícen");
        game1.handleCommand("jdi chodba");
        game1.handleCommand("jdi nádvoří");
        game1.handleCommand("jdi brána");
        game1.handleCommand("dej whisky");
        game1.handleCommand("jdi svoboda");
        assertEquals(true, game1.isFinished());
    }
    
    /**
     * Testuje prikaz jdi.
     */
    public void testPrikazJdi() {
        game1.handleCommand("jdi podloubí");
        assertEquals("podloubí", game1.getGamePlan().getCurrentRoom().getName());
    }
}
