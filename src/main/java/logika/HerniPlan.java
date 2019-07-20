package logika;

import java.util.ArrayList;
import java.util.List;
import util.ObserverZmenyProstoru;
import util.SubjektZmenyProstoru;

/**
 * Class HerniPlan - třída představující mapu a stav adventury.
 *
 * Tato třída inicializuje prvky ze kterých se hra skládá: - vytváří všechny
 * prostory, - propojuje je vzájemně pomocí východů - pamatuje si aktuální
 * prostor, ve kterém se hráč právě nachází - nastauje vítězný prostor.
 *
 * @author Martin Lukáš
 * @version 1.0
 */
public class HerniPlan implements SubjektZmenyProstoru {

    private Prostor aktualniProstor;
    private Prostor viteznyProstor;
    private List<ObserverZmenyProstoru> seznamPozorovatelu;

    /**
     * Konstruktor který vytváří jednotlivé prostory a propojuje je pomocí
     * východů.
     */
    public HerniPlan() {
        zalozProstoryHry();
        seznamPozorovatelu = new ArrayList<ObserverZmenyProstoru>();
    }

    /**
     * Metoda vytváří jednotlivé prostory a propojuje je pomocí východů. Jako
     * výchozí aktuální prostor nastaví kašnu.
     */
    private void zalozProstoryHry() {
        Prostor kasna = new Prostor("kašna", "kašna se sousoším", false);
        Prostor podloubi = new Prostor("podloubí", "podloubí, kde v noci prochází stráž", false);
        Prostor nadvori = new Prostor("nádvoří", "nádvoří, centrum zámku", true);
        Prostor chodba = new Prostor("chodba", "chodba, která vede ke komnatám", false);
        Prostor komnataKnizete = new Prostor("komnata_knížete", "komnata, ve které sídlí kníže", false);
        Prostor brana = new Prostor("brána", "brána, průchod do vnějšího světa", false);
        Prostor svoboda = new Prostor("svoboda", "", true);

        kasna.setVychod(podloubi);
        podloubi.setVychod(kasna);
        podloubi.setVychod(nadvori);
        nadvori.setVychod(chodba);
        chodba.setVychod(nadvori);
        chodba.setVychod(komnataKnizete);
        komnataKnizete.setVychod(chodba);
        nadvori.setVychod(brana);
        brana.setVychod(nadvori);
        brana.setVychod(svoboda);

        Vec sousosi = new Vec("sousoší", false, true);
        Vec vino = new Vec("víno", true, false);
        Vec truhla = new Vec("truhla", false, true);
        Vec chloroform = new Vec("chloroform", true, false);
        Vec whisky = new Vec("whisky", true, false);
        Vec svicen = new Vec("svícen", false, false);

        kasna.vlozVec(sousosi);
        sousosi.vlozVec(vino);
        chodba.vlozVec(truhla);
        truhla.vlozVec(chloroform);
        truhla.vlozVec(whisky);
        komnataKnizete.vlozVec(svicen);

        Postava straz = new Postava("stráž", "Stráž: Hej ty! Půjdeš se mnou... Ale ještě bych ti mohl dát šanci.\n"
                + "Když mi najdeš nějaké dobré víno, tak tě pustím dál.", vino);
        podloubi.vlozPostavu(straz);
        Postava knize = new Postava("kníže", "Kníže spí, ale jeho spaní je lehké. Abys mohl ukrást svícen,\n"
                + "musíš jeho spaní něčím prohloubit", chloroform);
        komnataKnizete.vlozPostavu(knize);
        Postava strazceBrany = new Postava("strážce_brány", "Strážce: Ty tu kradeš? Ále, mně už je to jedno, stejně si chci najít\n"
                + "jinou práci. Jen tak tě však nepustím, je mi zima a bodlo by něco ostřejšího.", whisky);
        brana.vlozPostavu(strazceBrany);

        aktualniProstor = kasna;  // hra začíná u kašny       
        viteznyProstor = svoboda; // hra končí na svobodě
    }

    /**
     * Metoda vrací odkaz na aktuální prostor, ve ktetém se hráč právě nachází.
     *
     * @return aktuální prostor
     */
    public Prostor getAktualniProstor() {
        return aktualniProstor;
    }

    /**
     * Metoda nastaví aktuální prostor, používá se nejčastěji při přechodu mezi
     * prostory.
     *
     * @param prostor - nový aktuální prostor
     */
    public void setAktualniProstor(Prostor prostor) {
        aktualniProstor = prostor;
        this.upozorniPozorovatele();
    }

    /**
     * Metoda vrací odkaz na vitezny prostor.
     *
     * @return vítězný prostor
     */
    public Prostor getViteznyProstor() {
        return viteznyProstor;
    }

    /**
     * Zaregistruje pozorovatele.
     * 
     * @param pozorovatel 
     */
    @Override
    public void zaregistrujPozorovatele(ObserverZmenyProstoru pozorovatel) {
        seznamPozorovatelu.add(pozorovatel);
    }

    /**
     * Odregistruje pozorovatele.
     * 
     * @param pozorovatel 
     */
    @Override
    public void odregistrujPozorovatele(ObserverZmenyProstoru pozorovatel) {
        seznamPozorovatelu.remove(pozorovatel);
    }

    /**
     * Upozorní pozorovatele.
     */
    @Override
    public void upozorniPozorovatele() {
        for (ObserverZmenyProstoru pozorovatel : seznamPozorovatelu) {
            pozorovatel.aktualizuj(aktualniProstor);
        }
    }
}
