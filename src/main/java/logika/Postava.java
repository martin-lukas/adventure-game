package logika;

/**
 * Instance třídy Postava představují ...
 *
 * @author Martin Lukáš
 * @version 1.0
 */
public class Postava {

    private String jmeno;
    private String proslov;
    private Vec vecDostat;

    /**
     * Konstruktor
     *
     * @param jmeno - jméno postavy
     * @param proslov - co má postava říci
     * @param vecDostat - co postava požaduje za věc
     */
    public Postava(String jmeno, String proslov, Vec vecDostat) {
        this.jmeno = jmeno;
        this.proslov = proslov;
        this.vecDostat = vecDostat;
    }

    /**
     * Vrací jméno postavy.
     *
     * @return jméno postavy
     */
    public String getJmeno() {
        return jmeno;
    }

    /**
     * Vrací proslov postavy.
     *
     * @return proslov postavy
     */
    public String getProslov() {
        return proslov;
    }

    /**
     * Metoda nastaví proslov postavě
     *
     * @param novyProslov - co má postava odpovědět
     * @return nový proslov
     */
    public String setProslov(String novyProslov) {
        return this.proslov = novyProslov;
    }

    /**
     * Vrací věc, kterou má postava dostat.
     *
     * @return věc, kterou má postav dostat
     */
    public Vec getVecDostat() {
        return vecDostat;
    }
}
