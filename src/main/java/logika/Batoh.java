package logika;

import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import util.ObserverZmenyBatohu;
import util.SubjektZmenyBatohu;

/**
 * Instance třídy Batoh představují ...
 *
 * @author Martin Lukáš
 * @version 1.0
 */
public class Batoh implements SubjektZmenyBatohu {

    private Set<Vec> seznamVeci = new HashSet<Vec>();
    private final static int MAX_VECI = 2; //maximum unositelných věcí
    private List<ObserverZmenyBatohu> seznamPozorovatelu;

    /**
     * Konstruktor
     */
    public Batoh() {
        seznamPozorovatelu = new ArrayList<>();
    }

    /**
     * Metoda vloží věc do batohu, pokud se tam vejde.
     *
     * @param vec - objekt třídy Vec
     */
    public void vlozVec(Vec vec) {
        if (seznamVeci.size() <= MAX_VECI) {
            seznamVeci.add(vec);
            this.upozorniPozorovatele();
        } 
    }

    /**
     * Metoda zahodí nepotřebnou/přebytečnou věc.
     *
     * @param vec
     */
    public void zahodVec(Vec vec) {
        if (seznamVeci.contains(vec)) {
                seznamVeci.remove(vec);
                this.upozorniPozorovatele();
        }
    }
    
    /**
     * Metoda zkoumá, zda je daná věc v inventáři.
     *
     * @param jmeno název věci
     * @return vrací false, pokud věc nebyla nalezena
     */
    public boolean obsahujeVec(String jmeno) {
        for (Vec vec : seznamVeci) {
            if (vec.getNazev().equals(jmeno)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Metoda vrací věc.
     *
     * @param jmeno název věci
     * @return vrací null, pokud věc nebyla nalezena
     */
    public Vec getVec(String jmeno) {
        for (Vec vec : seznamVeci) {
            if (vec.getNazev().equals(jmeno)) {
                return vec;
            }
        }
        return null;
    }
    
    public Set<Vec> getSeznamVeci() {
        return seznamVeci;
    }

    /**
     * Metoda vypíše seznam věcí v inventáři.
     * 
     * @return seznam veci
     */
    public String seznamVeci() {
        String vypis;
        if (seznamVeci.isEmpty()) {
            return "V batohu nic není.\n";
        } else {
            vypis = "Věci v batohu jsou:";
            for (Vec vec : seznamVeci) {
                vypis = vypis + "  " + vec.getNazev();
            }
        }
        return vypis;
    }

    /**
     * Metoda zaregistruje pozorovatele.
     * 
     * @param pozorovatel 
     */
    @Override
    public void zaregistrujPozorovatele(ObserverZmenyBatohu pozorovatel) {
        seznamPozorovatelu.add(pozorovatel);
    }

    /**
     * Metoda odregistruje pozorovatele.
     * 
     * @param pozorovatel 
     */
    @Override
    public void odregistrujPozorovatele(ObserverZmenyBatohu pozorovatel) {
        seznamPozorovatelu.remove(pozorovatel);
    }

    /**
     * Metoda upozorní pozorovatele.
     */
    @Override
    public void upozorniPozorovatele() {
        for (ObserverZmenyBatohu pozorovatel : seznamPozorovatelu) {
            pozorovatel.aktualizuj(this);
        }
    }
}
