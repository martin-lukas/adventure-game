package logika;

/**
 * Instance třídy PrikazBatoh představují ...
 *
 * @author Martin Lukáš
 * @version 1.0
 */
public class PrikazBatoh implements IPrikaz {

    private static final String NAZEV = "batoh";
    private Batoh batoh;

    /**
     * Konstruktor
     *
     * @param batoh - vytvoření batohu
     */
    public PrikazBatoh(Batoh batoh) {
        this.batoh = batoh;
    }

    /**
     * Metoda provede příkaz "batoh", který vypíše obsah batohu pomocí metody
     * nazvyVeci() zavolané na batohu.
     *
     * @param parametry - parametry příkazu
     * @return výčet věcí v batohu nebo chybová hláška
     */
    @Override
    public String proved(String... parametry) {
        if (parametry.length == 0) {
            return batoh.seznamVeci();
        } else {
            return "Tento příkaz nesmí mít parametry.";
        }
    }

    /**
     * Vrací název příkazu.
     *
     * @return název příkazu
     */
    @Override
    public String getNazev() {
        return NAZEV;
    }
}
