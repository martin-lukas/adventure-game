package logika;

/**
 * Třída PrikazNapoveda implementuje pro hru příkaz napoveda. Tato třída je
 * součástí jednoduché textové hry.
 *
 * @author Martin Lukáš
 * @version 1.0
 */
class PrikazNapoveda implements IPrikaz {

    private static final String NAZEV = "napoveda";
    private SeznamPrikazu platnePrikazy;

    /**
     * Konstruktor třídy.
     *
     * @param platnePrikazy - seznam příkazů, které je možné použít
     */
    public PrikazNapoveda(SeznamPrikazu platnePrikazy) {
        this.platnePrikazy = platnePrikazy;
    }

    /**
     * Vrací základní nápovědu po zadání příkazu "napoveda". Nyní se vypisuje
     * vcelku primitivní zpráva a seznam dostupných příkazů.
     *
     * @param parametry - parametry příkazu
     * @return napoveda ke hre
     */
    @Override
    public String proved(String... parametry) {
        return "Tvým úkolem je dojít pro svícen a rychle odtud vypadnout.\n"
                + "Můžeš zadat tyto příkazy:\n"
                + platnePrikazy.vratNazvyPrikazu() + "\n\n"
                + "Mapa: <kašna>-<podloubí>-<nádvoří>-<chodba>-<komnata_knížete>\n"
                + "       start                 |\n"
                + "                          <brána>-<svoboda>\n"
                + "                                     cíl";
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
