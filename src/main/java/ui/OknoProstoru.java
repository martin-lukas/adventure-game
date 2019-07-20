package ui;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import logika.HerniPlan;
import logika.Prostor;
import util.ObserverZmenyProstoru;

/**
 * Třída okna prostoru.
 * 
 * @author Martin Lukáš
 * @version 1.0
 */
public class OknoProstoru implements ObserverZmenyProstoru {

    private JDialog oknoProstoruDialog;
    private JTextArea vystupTextArea;
    private HerniPlan plan;

    /**
     * Konstruktor, který vytvoří dialogové okno s popisem aktuální místnosti.
     *
     * @param plan - plán hry
     */
    public OknoProstoru(HerniPlan plan) {
        //třída se zaregistruje jako pozorovatel změny prostoru
        this.plan = plan;
        init();
        plan.zaregistrujPozorovatele(this);
        
    }

    /**
     * Metoda inicializuje okno prostoru.
     */
    public void init() {
        oknoProstoruDialog = new JDialog();
        vystupTextArea = new JTextArea(10, 40);
        vystupTextArea.setEditable(false);
        oknoProstoruDialog.add(new JScrollPane(vystupTextArea));
        oknoProstoruDialog.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);      // při zavření okna se skryje
        vystupTextArea.setText(plan.getAktualniProstor().dlouhyPopis());         // vypíše se popis aktuální místnosti

        oknoProstoruDialog.pack();
    }
    /**
     * Metoda zaregistruje pozorovatele k hernímu plánu při spuštění nové hry.
     *
     * @param plan - herni plan
     */
    public void nastaveniHernihoPlanu(HerniPlan plan) {
        plan.zaregistrujPozorovatele(this);
        this.aktualizuj(plan.getAktualniProstor());
    }

    /**
     * Metoda pro aktulizaci okna - je volána při změně subjektu.
     * 
     * @param aktualniProstor - aktualni prostor
     */
    @Override
    public void aktualizuj(Prostor aktualniProstor) {
        vystupTextArea.setText(aktualniProstor.dlouhyPopis());
    }

    /**
     * Metoda zajišťuje zobrazení nebo zneviditelnění dialogového okna.
     *
     * @param viditelnost - viditelnost komponenty
     */
    public void setVisible(boolean viditelnost) {
        oknoProstoruDialog.setVisible(viditelnost);
    }
}
