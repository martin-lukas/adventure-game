package logika;

/**
 * Instance třídy PrikazSeber představují ...
 *
 * @author Martin Lukáš
 * @version 1.0
 */
public class PrikazSeber implements IPrikaz {

    private static final String NAZEV = "seber";
    private HerniPlan plan;
    private Batoh batoh;

    /**
     * Konstruktor
     *
     * @param plan - herní plán
     * @param batoh - batoh
     */
    public PrikazSeber(HerniPlan plan, Batoh batoh) {
        this.plan = plan;
        this.batoh = batoh;
    }

    /**
     * Metoda provede příkaz "seber", který sebere věc z prostoru a vloží ji do
     * batohu, pokud možno.
     *
     * @param parametry - parametry příkazu
     * @return zpráva o provedení nebo chybová hláška
     */
    @Override
    public String proved(String... parametry) {
        if (parametry.length == 0) {
            // pokud chybí druhé slovo , tak ....
            return "Co mám sebrat? Musíš zadat jméno věci.";
        }
        String jmenoVeci = parametry[0];
        Prostor aktualniProstor = plan.getAktualniProstor();
        if (aktualniProstor.obsahujeVec(jmenoVeci)) {
            // v prostoru je věc
            // zkoušíme sebrat věc z aktuálního prostoru
            Vec nalezenaVec = plan.getAktualniProstor().vyberVec(jmenoVeci);
            if (nalezenaVec == null) {
                return "Tato věc není přenositelná.";
            }
            // uložíme věc do batohu
            batoh.vlozVec(nalezenaVec);
            if (batoh.obsahujeVec(nalezenaVec.getNazev())) {
                plan.upozorniPozorovatele();
                batoh.upozorniPozorovatele();
                
                return "Sebral jsi " + jmenoVeci + ".";
            }
            aktualniProstor.vlozVec(nalezenaVec);
            return "Věc " + jmenoVeci + " se nevejde do batohu.";
        }
        return "Taková věc tu není.";
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
