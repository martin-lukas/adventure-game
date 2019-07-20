package logika;

import java.util.Arrays;

/**
 * Třída Hra - třída představující logiku adventury.
 *
 * Toto je hlavní třída logiky aplikace. Tato třída vytváří instanci třídy
 * HerniPlan, která inicializuje mistnosti hry a vytváří seznam platných příkazů
 * a instance tříd provádějící jednotlivé příkazy. Vypisuje uvítací a ukončovací
 * text hry. Také vyhodnocuje jednotlivé příkazy zadané uživatelem.
 *
 * @author Martin Lukáš
 * @version 1.0
 */
public class Hra {

    private SeznamPrikazu platnePrikazy;  // obsahuje seznam přípustných příkazů
    private HerniPlan herniPlan;
    private boolean konecHry = false;
    private Batoh batoh;

    /**
     * Konstruktor, který vytváří hru a inicializuje místnosti
     * (prostřednictvím třídy HerniPlan) a seznam platných příkazů.
     */
    public Hra() {
        herniPlan = new HerniPlan();
        batoh = new Batoh();
        platnePrikazy = new SeznamPrikazu();
        platnePrikazy.vlozPrikaz(new PrikazProhledej(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazSeber(herniPlan, batoh));
        platnePrikazy.vlozPrikaz(new PrikazJdi(this));
        platnePrikazy.vlozPrikaz(new PrikazDej(herniPlan, batoh));
        platnePrikazy.vlozPrikaz(new PrikazBatoh(batoh));
        platnePrikazy.vlozPrikaz(new PrikazMluv(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazNapoveda(platnePrikazy));
        platnePrikazy.vlozPrikaz(new PrikazKonec(this));
    }

    /**
     * Vrátí úvodní zprávu pro hráče.
     *
     * @return úvítání do hry
     */
    public String vratUvitani() {
        return "Vítejte!\n"
                + "Toto je příběh zloděje, který kradl v zámku knížete,\n"
                + "ale teď se odsud nemůže dostat. A ještě navíc musí po cestě\n"
                + "ven ukrást svícen, který se nachází v komnatě knížete.\n"
                + "Bez něho nemůže odejít! Ty jsi ten zloděj...\n"
                + "Napiš 'napoveda', pokud si nevíš rady, jak hrát dál.\n"
                + "\n" + herniPlan.getAktualniProstor().dlouhyPopis();
    }

    /**
     * Vrátí závěrečnou zprávu pro hráče.
     *
     * @return epilog hry
     */
    public String vratEpilog() {
        return "Dík, že sis zahrál.  Ahoj.";
    }

    /**
     * Vrátí příkazy.
     * 
     * @return pole prikazu
     */
    public String[] getPrikazy() {
        String prikazy = platnePrikazy.vratNazvyPrikazu();
        String[] oddeleneVeci;
        oddeleneVeci = prikazy.split(", ");
        Arrays.sort(oddeleneVeci);
        return oddeleneVeci;
    }
    
    /**
     * Vrací true, pokud hra skončila.
     *
     * @return true, pokud konec hry
     */
    public boolean konecHry() {
        return konecHry;
    }

    /**
     * Metoda zpracuje řetězec uvedený jako parametr, rozdělí ho na slovo
     * příkazu a další parametry. Pak otestuje zda příkaz je klíčovým slovem
     * např. jdi. Pokud ano spustí samotné provádění příkazu.
     *
     * @param radek - text, který zadal uživatel jako příkaz do hry.
     * @return vrací se řetězec, který se má vypsat na obrazovku
     */
    public String zpracujPrikaz(String radek) {
        String[] slova = radek.split("[ \t]+");
        String slovoPrikazu = slova[0];
        String[] parametry = new String[slova.length - 1];
        for (int i = 0; i < parametry.length; i++) {
            parametry[i] = slova[i + 1];
        }
        String textKVypsani;
        if (platnePrikazy.jePlatnyPrikaz(slovoPrikazu)) {
            IPrikaz prikaz = platnePrikazy.vratPrikaz(slovoPrikazu);
            textKVypsani = prikaz.proved(parametry);
            // zjistit zda aktualni prostor je svoboda a ukoncit hru
            if (herniPlan.getAktualniProstor() == herniPlan.getViteznyProstor()) {
                konecHry = true;
                textKVypsani += "\n";
            }
        } else {
            textKVypsani = "Nevím co tím myslíš? Tento příkaz neznám.";
        }
        return textKVypsani;
    }

    /**
     * Nastaví, že je konec hry, metodu využívá třída PrikazKonec, mohou ji
     * použít i další implementace rozhraní Prikaz.
     *
     * @param konecHry - hodnota false= konec hry, true = hra pokračuje
     */
    public void setKonecHry(boolean konecHry) {
        this.konecHry = konecHry;
    }

    /**
     * Metoda vrátí odkaz na herní plán, je využita hlavně v testech, kde se
     * jejím prostřednictvím získává aktualní místnost hry.
     *
     * @return odkaz na herní plán
     */
    public HerniPlan getHerniPlan() {
        return herniPlan;
    }

    /**
     * Metoda vrátí odkaz na batoh, je využita hlavně v testech, kde se jejím
     * prostřednictvím získává batoh ze hry.
     *
     * @return odkaz na batoh
     */
    public Batoh getBatoh() {
        return batoh;
    }
}
