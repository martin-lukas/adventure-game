package logika;

/**
 * Instance třídy PrikazMluv představují ...
 *
 * @author Martin Lukáš
 * @version 1.0
 */
public class PrikazMluv implements IPrikaz {

    private static final String NAZEV = "mluv";
    private HerniPlan plan;

    /**
     * Konstruktor ....
     *
     * @param plan - herní plán
     */
    public PrikazMluv(HerniPlan plan) {
        this.plan = plan;
    }

    /**
     * Metoda provede příkaz "mluv", který promluví se zadanou postavou a
     * zjistí, co požaduje.
     *
     * @param parametry - parametry příkazu
     * @return proslov postavy nebo chybová hláška
     */
    public String proved(String... parametry) {
        if (parametry.length == 0) {
            // pokud chybí druhé slovo , tak ....
            return "S kým mám promluvit? Musíš zadat jméno postavy.";
        }
        String jmenoPostavy = parametry[0];
        Prostor aktualniProstor = plan.getAktualniProstor();
        Postava postava = aktualniProstor.getPostavu();
        if (postava == null) {
            return "Tato postava tu není.";
        }
        if (postava.getJmeno().equals(jmenoPostavy)) {
            // v prostoru je postava
            return aktualniProstor.getPostavu().getProslov();
        }
        return "Taková postava tu není.";
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
