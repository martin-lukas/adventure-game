package logika;

/**
 * Třída PrikazJdi implementuje pro hru příkaz jdi. Tato třída je součástí
 * jednoduché textové hry.
 *
 * @author Martin Lukáš
 * @version 1.0
 */
class PrikazJdi implements IPrikaz {

    private static final String NAZEV = "jdi";
    private Hra hra;

    /**
     * Konstruktor třídy
     *
     * @param plan - herní plán, ve kterém se bude ve hře "chodit"
     */
    public PrikazJdi(Hra hra) {
        this.hra = hra;
    }

    /**
     * Provádí příkaz "jdi". Zkouší se vejít do zadaného prostoru. Pokud prostor
     * existuje, hráč vstoupí do nového prostoru. Pokud zadaný sousední prostor
     * (východ) není, vypíše se chybové hlášení.
     *
     * @param parametry - jako parametr obsahuje jméno prostoru (východu), do
     * kterého se má jít.
     * @return zpráva, kterou vypíše hra hráči
     */
    @Override
    public String proved(String... parametry) {
        if (parametry.length == 0) {
            // pokud chybí druhé slovo (sousední prostor), tak ....
            return "Kam mám jít? Musíš zadat jméno východu.";
        }
        String smer = parametry[0];
        
        if (hra.getHerniPlan().getAktualniProstor().getNazev().equals(smer)) {
            return "Tady už jsi.";
        }

        // zkoušíme přejít do sousedního prostoru
        Prostor sousedniProstor = hra.getHerniPlan().getAktualniProstor().vratSousedniProstor(smer);

        boolean zamceno = false;
        if (sousedniProstor != null && sousedniProstor.getZamceno()) {
            sousedniProstor = null;
            zamceno = true;
        }

        if (sousedniProstor == null) {
            if (zamceno) {
                return "Tento prostor je zamčený. Teď tam nemůžeš.";
            } else {
                return "Tam se odsud jít nedá!";
            }
        }
        
        if (smer.equals("svoboda")) {
            if (hra.getBatoh().obsahujeVec("svícen")) {
                hra.getHerniPlan().setAktualniProstor(sousedniProstor);
                return "A jsi na svobodě. Teď jenom najít další místo na vyloupení."; 
            } else {
                return "Tam ještě nemůžeš. Něco jsi zapomněl.";
            }         
        } else {
            hra.getHerniPlan().setAktualniProstor(sousedniProstor);
            return sousedniProstor.dlouhyPopis();
        }
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

