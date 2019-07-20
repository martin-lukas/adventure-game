package logika;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;

/**
 * Testovací třída.
 * 
 * @author Martin Lukáš
 * @version 1.0
 */
public class HraTest {
    
    private Hra hra1;
        
    /**
     * Metoda zakládá proměnné.
     */
    @Before
    public void setUp() {
        hra1 = new Hra();
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
        hra1.zpracujPrikaz("prohledej sousoší");
        hra1.zpracujPrikaz("seber víno");
        hra1.zpracujPrikaz("jdi podloubí");
        hra1.zpracujPrikaz("dej víno");
        hra1.zpracujPrikaz("jdi nádvoří");
        hra1.zpracujPrikaz("jdi chodba");
        hra1.zpracujPrikaz("prohledej truhla");
        hra1.zpracujPrikaz("seber chloroform");
        hra1.zpracujPrikaz("seber whisky");
        hra1.zpracujPrikaz("jdi komnata_knížete");
        hra1.zpracujPrikaz("dej chloroform");
        hra1.zpracujPrikaz("seber svícen");
        hra1.zpracujPrikaz("jdi chodba");
        hra1.zpracujPrikaz("jdi nádvoří");
        hra1.zpracujPrikaz("jdi brána");
        hra1.zpracujPrikaz("dej whisky");
        hra1.zpracujPrikaz("jdi svoboda");
        assertEquals(true, hra1.konecHry());
    }
    
    /**
     * Testuje prikaz jdi.
     */
    public void testPrikazJdi() {
        hra1.zpracujPrikaz("jdi podloubí");
        assertEquals("podloubí", hra1.getHerniPlan().getAktualniProstor().getNazev());
    }
}
