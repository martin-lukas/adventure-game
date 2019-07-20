package ui;

import java.io.IOException;
import java.net.URL;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

/**
 * Třída okna nápovědy.
 * 
 * @author Martin Lukáš
 * @version 1.0
 */
public class OknoNapovedy {

    private JDialog oknoNapovedyDialog;
    private JEditorPane vystupEditorPane;
    private final URL umisteniSouboru;

    /**
     * Konstruktor okna.
     *
     * @param soubor - soubor s obsahem nápovědy ve formátu html
     */
    public OknoNapovedy(String soubor) {
        umisteniSouboru = this.getClass().getResource(soubor);
        init();
    }

    /**
     * Metoda inicializuje okno nápovědy.
     */
    private void init() {
        oknoNapovedyDialog = new JDialog();
        oknoNapovedyDialog.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
        oknoNapovedyDialog.setTitle("Nápověda k aplikaci");
        
        vystupEditorPane = new JEditorPane();
        vystupEditorPane.setEditable(false);
        oknoNapovedyDialog.add(new JScrollPane(vystupEditorPane));
        try {
            vystupEditorPane.setPage(umisteniSouboru);
        } catch (IOException exception) {
            JOptionPane.showMessageDialog(null,
                    "Soubor s nápovědou nebyl nalezen",
                    "Chyba při načítání nápovědy",
                    JOptionPane.ERROR_MESSAGE);
        }
        oknoNapovedyDialog.setLocation(700, 200);
        oknoNapovedyDialog.setSize(400, 600);
    }

    /**
     * Zajišťuje zobrazení nebo skrytí okna.
     *
     * @param viditelnost - viditelnost komponent
     */
    public void setVisible(boolean viditelnost) {
        oknoNapovedyDialog.setVisible(viditelnost);
    }
}
