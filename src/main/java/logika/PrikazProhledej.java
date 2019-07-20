package logika;

import java.util.ArrayList;
import java.util.List;

/**
 * Instance třídy PrikazDej představují ...
 *
 * @author Martin Lukáš
 * @version 1.0
 */
public class PrikazProhledej implements IPrikaz {

    private static final String NAZEV = "prohledej";
    private HerniPlan plan;
    private List<Vec> seznamVeci;

    /**
     * Konstruktor třídy
     *
     * @param plan - herní plán
     */
    public PrikazProhledej(HerniPlan plan) {
        this.plan = plan;
        this.seznamVeci = new ArrayList<>();
    }

    /**
     * Provádí příkaz "prohledej". Pokud je co prohledat věc něco ukrývá,
     * zjistí, co ukrývá.
     *
     * @param parametry - jméno prohledávané věci
     * @return zpráva, kterou vypíše hra hráči
     */
    @Override
    public String proved(String... parametry) {
        if (parametry.length == 0) {
            // pokud chybí druhé slovo (sousední prostor), tak ....
            return "Co mám prohledat? Musíš napsat jméno věci.";
        }
        String jmenoVeci = parametry[0];
        Prostor aktualniProstor = plan.getAktualniProstor();
        Vec vec = aktualniProstor.getVec(jmenoVeci);
        if (vec == null) {
            return "Tato věc není v prostoru.";
        }
        if (vec.isProhledana()) {
            return vec.nazvyVeci();
        }
        if (vec.isUlozna()) {
            seznamVeci = vec.getSeznam();
            if (seznamVeci != null) {
                for (int i = 0; i < seznamVeci.size(); i++) {
                    aktualniProstor.vlozVec(seznamVeci.get(i));
                }
            }
            vec.setProhledana(true);
            return vec.nazvyVeci();
        } else {
            return "Tato věc se nedá prohledat.";
        }
    }

    /**
     * Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání).
     *
     * @return nazev prikazu
     */
    @Override
    public String getNazev() {
        return NAZEV;
    }
}
