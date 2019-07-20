package logika;

import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;

/**
 * Trida Prostor - popisuje jednotlivé prostory (místnosti) hry
 *
 * Tato třída je součástí jednoduché textové hry.
 *
 * "Prostor" reprezentuje jedno místo (místnost, prostor, ..) ve scénáři hry.
 * Prostor může mít sousední prostory připojené přes východy. Pro každý východ
 * si prostor ukládá odkaz na sousedící prostor.
 *
 * @author Martin Lukáš
 * @version 1.0
 */
public class Prostor {

    private String jmeno;
    private String popis;
    private boolean zamceno;
    private Set<Prostor> vychody;   // obsahuje sousední prostory
    private Map<String, Vec> seznamVeci;
    private Postava postava;

    /**
     * Vytvoření prostoru se zadaným popisem, např. "kuchyň", "hala", "trávník
     * před domem"
     *
     * @param jmeno - jméno prostoru
     * @param popis - popis prostoru
     * @param zamceno - zamčenost prostoru
     */
    public Prostor(String jmeno, String popis, boolean zamceno) {
        this.jmeno = jmeno;
        this.popis = popis;
        this.zamceno = zamceno;
        vychody = new HashSet<>();
        seznamVeci = new HashMap<>();
    }

    /**
     * Metoda vloží věc do prostoru.
     *
     * @param vec - věc, která se vkládá do prostoru
     */
    public void vlozVec(Vec vec) {
        seznamVeci.put(vec.getNazev(), vec);
    }

    /**
     * Metoda vloží postavu do prostoru.
     *
     * @param postava - postava, která se vkládá do prostoru
     */
    public void vlozPostavu(Postava postava) {
        this.postava = postava;
    }

    /**
     * Metoda zkontroluje, zda je věc v prostoru.
     *
     * @param jmenoVeci - jméno věci
     * @return true, když věc je v prostoru, false když není
     */
    public boolean obsahujeVec(String jmenoVeci) {
        return seznamVeci.containsKey(jmenoVeci);
    }

    /**
     * Metoda zkontroluje, zda je postava v prostoru.
     *
     * @return true, když postava je v prostoru, false když není
     */
    public boolean obsahujePostavu() {
        return (postava != null);
    }

    /**
     * Vrací, jestli je prostor zamčen.
     *
     * @return zamčeno
     */
    public boolean getZamceno() {
        return zamceno;
    }

    /**
     * Mění stav zamknutí prostoru.
     *
     * @param noveZamceno - nová hodnota zamčení
     */
    public void setZamceno(boolean noveZamceno) {
        this.zamceno = noveZamceno;
    }

    /**
     * Vrátí věc bez ohledu na vlastnosti.
     *
     * @param jmenoVeci - jméno věci
     * @return věc
     */
    public Vec getVec(String jmenoVeci) {
        Vec nalezenaVec;
        if (!seznamVeci.containsKey(jmenoVeci)) {
            return null;
        }
        nalezenaVec = seznamVeci.get(jmenoVeci);
        return nalezenaVec;
    }

    /**
     * Metoda vybere věc z prostoru.
     *
     * @param jmenoVeci - jméno věci
     * @return objekt věci, když prostor obsahuje věc a věc je přenositelná,
     * nebo null, když ne
     */
    public Vec vyberVec(String jmenoVeci) {
        Vec nalezenaVec = null;
        if (seznamVeci.containsKey(jmenoVeci)) {
            nalezenaVec = seznamVeci.get(jmenoVeci);
            if (nalezenaVec.isPrenositelna()) {
                seznamVeci.remove(jmenoVeci);
            } else {
                nalezenaVec = null;
            }
        }
        return nalezenaVec;
    }

    /**
     * Vrací postavu, pokud se nachází v místnosti.
     *
     * @return nalezená postava
     */
    public Postava getPostavu() {
        return postava;
    }

    /**
     * Vrátí výpis všech věcí v prostoru.
     *
     * @return výpis věcí v prostoru
     */
    public String nazvyVeci() {
        String nazvy = "věci:";
        for (String jmenoVeci : seznamVeci.keySet()) {
            nazvy += " " + jmenoVeci;
        }
        return nazvy + "\n";
    }

    /**
     * Vrací výpis postav, které se nachází v prostoru.
     *
     * @return výpis postav v prostoru
     */
    private String nazvyPostav() {
        String vracenyText = "postavy:";
        if (postava == null) {
            return vracenyText;
        } else {
            return vracenyText + " " + postava.getJmeno();
        }
    }

    /**
     * Definuje východ z prostoru (sousední/vedlejší prostor). Vzhledem k tomu,
     * že je použit Set pro uložení východů, může být sousední prostor uveden
     * pouze jednou (tj. nelze mít dvoje dveře do stejné sousední místnosti).
     * Druhé zadání stejného prostoru tiše přepíše předchozí zadání (neobjeví se
     * žádné chybové hlášení). Lze zadat též cestu ze do sebe sama.
     *
     * @param vedlejsi - vedlejší prostor
     */
    public void setVychod(Prostor vedlejsi) {
        vychody.add(vedlejsi);
    }

    /**
     * Metoda equals pro porovnání dvou prostorů.
     *
     * @param obj - porovnávaný objekt
     * @return true, pokud má zadaný prostor stejný název, jinak false
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Prostor) {
            Prostor druhy = (Prostor) obj;
            return jmeno.equals(druhy.jmeno);
        } else {
            return false;
        }
    }

    /**
     * Metoda hashCode vraci ciselny identifikator instance, ktery se pouziva
     * pro optimalizaci ukladani v dynamickych datovych strukturach. Pri
     * prekryti metody equals je potreba prekryt i metodu hashCode. Podrobny
     * popis pravidel pro vytvareni metody hashCode je u metody hashCode ve
     * tride Object.
     *
     * @return výsledek výpočtu
     */
    @Override
    public int hashCode() {
        int vysledek = 3;
        int hashNazvu = java.util.Objects.hashCode(this.jmeno);
        vysledek = 37 * vysledek + hashNazvu;
        return vysledek;
    }

    /**
     * Vrací název prostoru (byl zadán při vytváření prostoru jako parametr
     * konstruktoru)
     *
     * @return jméno prostoru
     */
    public String getNazev() {
        return jmeno;
    }

    /**
     * Vrací "dlouhý" popis prostoru, který může vypadat následovně: Jsi v
     * mistnosti/prostoru vstupni hala budovy VSE na Jiznim meste. vychody:
     * chodba bufet ucebna
     *
     * @return dlouhý popis prostoru
     */
    public String dlouhyPopis() {
        return "Jsi v prostoru " + jmeno + " - " + popis + ".\n"
                + seznamVychodu()
                + nazvyVeci()
                + nazvyPostav();
    }

    /**
     * Vrací textový řetězec, který popisuje sousední východy, například:
     * "vychody: hala".
     *
     * @return popis východů - názvů sousedních prostorů
     */
    public String seznamVychodu() {
        String vracenyText = "východy:";
        for (Prostor sousedni : vychody) {
            vracenyText += " " + sousedni.getNazev();
        }
        return vracenyText + "\n";
    }

    /**
     * Vrací prostor, který sousedí s aktuálním prostorem a jehož název je zadán
     * jako parametr. Pokud prostor s udaným jménem nesousedí s aktuálním
     * prostorem, vrací se hodnota null.
     *
     * @param nazevSouseda Jméno sousedního prostoru (východu)
     * @return sousední prostor nebo null, pokud neexistuje
     */
    public Prostor vratSousedniProstor(String nazevSouseda) {
        if (!nazevSouseda.equals("")) {
            for (Prostor sousedni : vychody) {
                if (sousedni.getNazev().equals(nazevSouseda)) {
                    return sousedni;
                }
            }
        }
        return null;  // prostor nenalezen
    }

    /**
     * Vrací kolekci obsahující prostory, se kterými tento prostor sousedí.
     * Takto získaný seznam sousedních prostor nelze upravovat (přidávat,
     * odebírat východy) protože z hlediska správného návrhu je to plně
     * záležitostí třídy Prostor.
     *
     * @return Nemodifikovatelná kolekce prostorů (východů), se kterými tento
     * prostor sousedí.
     */
//    public Collection<Prostor> getVychody() {
//        return Collections.unmodifiableCollection(vychody);
//    }
}