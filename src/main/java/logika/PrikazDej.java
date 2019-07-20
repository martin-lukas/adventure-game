package logika;

/**
 * Instance třídy PrikazDej představují ...
 *
 * @author Martin Lukáš
 * @version 1.0
 */
public class PrikazDej implements IPrikaz {

    private static final String NAZEV = "dej";
    private HerniPlan plan;
    private Batoh batoh;

    /**
     * Konstruktor třídy
     *
     * @param plan - herní plán
     * @param batoh - batoh
     */
    public PrikazDej(HerniPlan plan, Batoh batoh) {
        this.plan = plan;
        this.batoh = batoh;
    }

    /**
     * Provádí příkaz "dej". Pokud možno, předá věc z batohu postavě a postava
     * něco udělá.
     *
     * @param parametry - parametry příkazu
     * @return zpráva, kterou vypíše hra hráči
     */
    @Override
    public String proved(String... parametry) {
        if (parametry.length == 0) {
            // pokud chybí druhé slovo (sousední prostor), tak ....
            return "Co mám dát? Musíš napsat jméno věci.";
        }
        String darovana = parametry[0];
        Prostor aktualniProstor = plan.getAktualniProstor();
        Vec vec = batoh.getVec(darovana);
        Postava postava;
        if (aktualniProstor.obsahujePostavu()) {
            postava = aktualniProstor.getPostavu();
        } else {
            return "Není tu nikdo, kdo by tuto věc chtěl.";
        }

        if (vec == null) {
            return "Tuto věc nemáš v batohu.";
        }

        if (vec.equals(postava.getVecDostat())) {
            batoh.zahodVec(vec);
            batoh.upozorniPozorovatele();
            plan.upozorniPozorovatele();
            switch (vec.getNazev()) {
                case "víno":
                    aktualniProstor.vratSousedniProstor("nádvoří").setZamceno(false);
                    postava.setProslov("Stráž: Co ještě chceš? Už můžeš jít.");
                    return "Díky, teď tedy můžeš projít.";
                case "chloroform":
                    aktualniProstor.getVec("svícen").setPrenositelna(true);
                    postava.setProslov("Kníže tvrdě spí, takže už můžeš sebrat svícen.");
                    return "Kníže teď tvrdě spí. Seber svícen a rychle odsud!";
                case "whisky":
                    aktualniProstor.vratSousedniProstor("svoboda").setZamceno(false);
                    postava.setProslov("Strážce: No tak jdi!");
                    return "Dobře, udělal jsi, co jsem chtěl. Teď můžeš jít. A už se nevracej!";
                default:
                    return "Tato věc neexistuje.";
            }
        }
        return "Postava v tomto prostoru tuto věc nechce.";
    }

    /**
     * Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *
     * @return nazev prikazu
     */
    @Override
    public String getNazev() {
        return NAZEV;
    }
}
