package ui;

import java.net.URL;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import logika.Batoh;
import logika.HerniPlan;
import logika.Hra;
import util.ObserverZmenyBatohu;

/**
 * Třída vytváří panel batohu.
 * 
 * author Martin Lukáš
 * @version 1.0
 */
public class PanelBatohu extends JPanel implements ObserverZmenyBatohu {
    
    private Icon logoIcon;
    private String[] oddeleneVeci;
    
    /**
     * Konstruktor
     * 
     * @param hra 
     */
    public PanelBatohu(Hra hra) {
        super();
        init();
        hra.getBatoh().zaregistrujPozorovatele(this);
        this.aktualizuj(hra.getBatoh());
    }

    /**
     * Metoda incializuje panel batohu.
     */
    public void init() {
        this.setBorder(BorderFactory.createTitledBorder("Věci v batohu:"));
        this.setVisible(true);
    }
    /**
     * Metoda, ve které proběhne aktualizace pozorovatele.
     *
     * @param aktualniVeci logika
     */
    @Override
    public void aktualizuj(Batoh aktualniVeci) {
        this.removeAll();
        String veci = aktualniVeci.seznamVeci();
        oddeleneVeci = veci.split("  ");

        URL umisteniObrazku;
        for (int i = 1; i < oddeleneVeci.length; i++) {
            umisteniObrazku = this.getClass().getResource("/" + oddeleneVeci[i] + ".jpg");
            
            if (umisteniObrazku == null) {
                JOptionPane.showMessageDialog(null,
                        "Soubor s obrázkem nebyl nalezen.",
                        "Chyba při načítání obrázku", JOptionPane.ERROR_MESSAGE);
            } else {
                logoIcon = new ImageIcon(umisteniObrazku);
                JLabel obrazek = new JLabel(logoIcon);
                this.add(obrazek);
            }
        }
    }
}
