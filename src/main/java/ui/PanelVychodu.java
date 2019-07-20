package ui;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import logika.HerniPlan;
import logika.Prostor;
import util.ObserverZmenyProstoru;

/**
 * Třída panelu východů.
 * 
 * @author Martin Lukáš
 * @version 1.0
 */
public class PanelVychodu extends JPanel implements ObserverZmenyProstoru {

    private HerniPlan plan;
    private JTextArea seznamVychTextArea;

    /**
     * Konstruktor, který inicializuje panel, zaregistruje ho jako pozorovatele
     * u herního plánu
     *
     * @param plan - herní plán
     */
    public PanelVychodu(HerniPlan plan) {
        super();
        this.plan = plan;
        plan.zaregistrujPozorovatele(this);
        init();
        this.aktualizuj(plan.getAktualniProstor());
    }

    /**
     * Metoda pro inicializaci komponent panelu.
     */
    private void init() {
        seznamVychTextArea = new JTextArea(10, 10);
        seznamVychTextArea.setEditable(false);
        seznamVychTextArea.setBorder(BorderFactory.createTitledBorder("Východy:"));
        this.add(seznamVychTextArea);
    }

    /**
     * Metoda zaregistruje pozorovatele k hernímu plánu při spuštění nové hry.
     *
     * @param plan - herní plán
     */
    public void nastaveniHernihoPlanu(HerniPlan plan) {
        this.plan = plan;
        plan.zaregistrujPozorovatele(this);
        this.aktualizuj(plan.getAktualniProstor());
    }

    /**
     * Metoda pro aktulizaci panelu - je volána při změně subjektu
     * 
     * @param aktualniProstor - aktualni prostor
     */
    @Override
    public void aktualizuj(Prostor aktualniProstor) {
        String vychody = aktualniProstor.seznamVychodu();
        seznamVychTextArea.setText("");
        String[] oddeleneVychody = vychody.split(" ");
        for (int i = 1; i < oddeleneVychody.length; i++) {
            seznamVychTextArea.append(oddeleneVychody[i] + "\n");
        }
    }
}
