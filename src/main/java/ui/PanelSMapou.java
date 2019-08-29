package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.net.URL;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import logika.HerniPlan;
import logika.Prostor;
import util.ObserverZmenyProstoru;

/**
 * Třída vytváří instanci panelu s mapou.
 * 
 * @author Martin Lukáš
 * @version 1.0
 */
public class PanelSMapou extends JPanel implements ObserverZmenyProstoru {

    private Icon logoIcon;
    private HerniPlan plan;
    private Prostor aktualniProstor;

    /**
     * Konstruktor se pokusí načíst obrázek, který má být na panelu nakreslen.
     * Pokud ho nenajde, oznámí to uživateli pomocí dialogového okna. Nastavi
     * preferovanou velikost panelu.
     * 
     * @param plan herni plan
     */
    public PanelSMapou(HerniPlan plan) {
        super();
        this.plan = plan;
        plan.zaregistrujPozorovatele(this);
        init();
        this.aktualizuj(plan.getAktualniProstor());
        
    }

    /**
     * Metoda inicializuje panel s mapou.
     */
    public void init() {
        URL umisteniObrazku = this.getClass().getResource("/map.png");
        if (umisteniObrazku == null) {
            JOptionPane.showMessageDialog(null,
                    "Soubor s obrázkem nebyl nalezen",
                    "Chyba při načítání obrázku", JOptionPane.ERROR_MESSAGE);
        } else {
            logoIcon = new ImageIcon(umisteniObrazku);
        }
        this.setPreferredSize(new Dimension(192, 255));
    }
    /**
     * Metoda vykreslí panel, přidá na něj obrázek, pokud byl úspěšně načten, a
     * nakreslí červené kolečko na souřadnice 10,10 panelu. Metoda se
     * automaticky volá při zobrazování komponenty.
     *
     * @param graph - grafický kontext
     */
    @Override
    public void paintComponent(Graphics graph) {
        super.paintComponent(graph);
        if (logoIcon != null) {
            logoIcon.paintIcon(this, graph, 0, 0);
        }
        graph.setColor(Color.MAGENTA);
        switch (aktualniProstor.getNazev()) {
            case "kašna":
                graph.fillOval(30, 50, 8, 8);
                break;
            case "podloubí":
                graph.fillOval(30, 105, 8, 8);
                break;
            case "nádvoří":
                graph.fillOval(90, 105, 8, 8);
                break;
            case "chodba":
                graph.fillOval(155, 105, 8, 8);
                break;
            case "komnata_knížete":
                graph.fillOval(155, 50, 8, 8);
                break;
            case "brána":
                graph.fillOval(90, 170, 8, 8);
                break;
            case "svoboda":
                graph.fillOval(90, 235, 8, 8);
                break;
            default:
                graph.fillOval(10, 10, 8, 8);
                break;
        }
    }
    
    /**
     * Metoda zaregistruje pozorovatele.
     * 
     * @param plan herni plan
     */
    public void nastaveniHernihoPlanu(HerniPlan plan) {
        this.plan = plan;
        plan.zaregistrujPozorovatele(this);
        this.aktualizuj(plan.getAktualniProstor());
    }

    /**
     * Metoda, ve které proběhne aktualizace pozorovatele.
     * 
     * @param aktualniProstor  aktualni prostor
     */
    @Override
    public void aktualizuj(Prostor aktualniProstor) {
        this.aktualniProstor = aktualniProstor;
        repaint();
    }
}
