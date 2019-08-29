package ui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import logika.Hra;

/**
 * Třída pro vytvoření grafického rozhraní.
 * 
 * @author Martin Lukáš
 * @version 1.0
 */
public class Gui {

    private Hra hra;
    private JFrame hlavniOknoFrame;
    private JTextField vstPrikTextField;
    private JTextArea vystupTextArea;
    private PanelVychodu panelVychodu;
    private OknoProstoru oknoProstoru;
    private JMenuBar listaMenuBar;
    private JMenu souborMenu;
    private JMenu zobrazitMenu;
    private JMenu napovedaMenu;
    private JMenuItem novaHraMenuItem;
    private JMenuItem konecMenuItem;
    private JMenuItem zobrPopisMenuItem;
    private JMenuItem oProgramuMenuItem;
    private JMenuItem napKApMenuItem;
    private OknoNapovedy oknoNapovedy;
    private PanelSMapou panelSMapou;
    private PanelBatohu panelBatohu;
    
    private JToolBar listaToolBar;
    Action novaHraAction,
        konecHryAction,
        zobrPopisuMistAction,
        oProgramuAction,
        napovedaAction;

    /**
     * Vnitřní trida pro zpracovani prikazu.
     */
    private class ZpracovaniPrikazu implements ActionListener {

        /**
         * Metoda zpracovává příkaz.
         * 
         * @param event 
         */
        @Override
        public void actionPerformed(ActionEvent event) {
            
            panelBatohu.aktualizuj(hra.getBatoh());
            String prikaz = vstPrikTextField.getText();
            String text = hra.zpracujPrikaz(prikaz);
            vystupTextArea.append("\n\n" + prikaz + "\n");
            vystupTextArea.append("\n" + text + "\n");
            vstPrikTextField.setText("");
            vystupTextArea.setCaretPosition(
                vystupTextArea.getDocument().getLength());
            if (hra.konecHry()) {
                vstPrikTextField.setEditable(false);
            }
        }
    }

    /**
     * Konstruktor, který propojí grafiku a logiku, inicializuje grafické
     * komponenty.
     *
     * @param hra - logika hry
     */
    public Gui(Hra hra) {
        this.hra = hra;
        vytvorAkce();
        initMenu();
        initToolBaru();
        init();
        vystupTextArea.setText(hra.vratUvitani());
        vstPrikTextField.requestFocusInWindow();
    }

    /**
     * Metoda inicializuje a propojuje jednotlivé komponenty GUI.
     */
    private void init() {
        hlavniOknoFrame = new JFrame("Adventura");
        hlavniOknoFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        hlavniOknoFrame.setSize(800, 480);
        hlavniOknoFrame.setLocation(200, 200);

        JPanel dolniPanel = new JPanel();
        vstPrikTextField = new JTextField(20);
        vstPrikTextField.addActionListener(new ZpracovaniPrikazu());
        JLabel prikazLabel = new JLabel("Příkaz: ");

        dolniPanel.add(prikazLabel);
        dolniPanel.add(vstPrikTextField);
        

        hlavniOknoFrame.add(dolniPanel, BorderLayout.SOUTH);
        vystupTextArea = new JTextArea(20, 70);
        vystupTextArea.setEditable(false);
        vystupTextArea.setFont(new Font("monospaced", Font.PLAIN, 12));
        hlavniOknoFrame.add(new JScrollPane(vystupTextArea));

        panelSMapou = new PanelSMapou(hra.getHerniPlan());
        hlavniOknoFrame.add(panelSMapou, BorderLayout.WEST);

        JPanel pravyPanel = new JPanel(new GridLayout(2, 1));
        panelVychodu = new PanelVychodu(hra.getHerniPlan());
        panelBatohu = new PanelBatohu(hra);
        panelBatohu.setBackground(Color.WHITE);
        pravyPanel.add(panelVychodu);
        pravyPanel.add(panelBatohu);
        hlavniOknoFrame.add(pravyPanel, BorderLayout.EAST);

        oknoProstoru = new OknoProstoru(hra.getHerniPlan());
        oknoNapovedy = new OknoNapovedy("/help.htm");

        hlavniOknoFrame.setJMenuBar(listaMenuBar);
        hlavniOknoFrame.add(listaToolBar, BorderLayout.NORTH);
    
        
        hlavniOknoFrame.pack();
    }

    /**
     * Metoda inicializuje menu aplikace.
     */
    private void initMenu() {
        listaMenuBar = new JMenuBar();

        souborMenu = new JMenu("Soubor");
        souborMenu.setMnemonic(KeyEvent.VK_S);
        listaMenuBar.add(souborMenu);

        zobrazitMenu = new JMenu("Zobrazit");
        listaMenuBar.add(zobrazitMenu);

        napovedaMenu = new JMenu("Napoveda");
        napovedaMenu.setMnemonic(KeyEvent.VK_N);
        listaMenuBar.add(napovedaMenu);

        novaHraMenuItem = new JMenuItem(novaHraAction);
        souborMenu.add(novaHraMenuItem);

        souborMenu.addSeparator();

        konecMenuItem = new JMenuItem(konecHryAction);
        souborMenu.add(konecMenuItem);

        zobrPopisMenuItem = new JMenuItem(zobrPopisuMistAction);
        zobrazitMenu.add(zobrPopisMenuItem);

        oProgramuMenuItem = new JMenuItem(oProgramuAction);
        napovedaMenu.add(oProgramuMenuItem);

        napKApMenuItem = new JMenuItem(napovedaAction);
        napovedaMenu.add(napKApMenuItem);
    }

    /**
     * Metoda vytváří různé akce.
     */
    private void vytvorAkce() {
        // akce pro novou hru
        // vytvoření instance akce pomocí anonymní vnitřní třídy odvozené od AbstractAction
        novaHraAction = new AbstractAction(
                "Nová hra",
                new ImageIcon(getClass().getResource("/newIcon.gif"))) {

            @Override
            public void actionPerformed(ActionEvent event) {
                hra = new Hra();
                oknoProstoru.nastaveniHernihoPlanu(hra.getHerniPlan());
                panelVychodu.nastaveniHernihoPlanu(hra.getHerniPlan());
                vystupTextArea.setText(hra.vratUvitani());
                vstPrikTextField.setEditable(true);
                vstPrikTextField.requestFocusInWindow();
            }
        };

        // 	akce konec hry
        konecHryAction = new AbstractAction(
                "Konec hry",
                new ImageIcon(getClass().getResource("/stopIcon.gif"))) {

            @Override
            public void actionPerformed(ActionEvent event) {
                System.exit(0);
            }
        };

        // nastavení akcelerátoru - Alt + F4
        konecHryAction.putValue(AbstractAction.ACCELERATOR_KEY,
                KeyStroke.getKeyStroke(KeyEvent.VK_F4, InputEvent.ALT_MASK));

        // akce zobrazeni popisu
        zobrPopisuMistAction = new AbstractAction("Zobrazit popis místnosti") {

            @Override
            public void actionPerformed(ActionEvent event) {
                oknoProstoru.setVisible(true);

            }
        };

        oProgramuAction = new AbstractAction("O programu") {

            @Override
            public void actionPerformed(ActionEvent event) {
                JOptionPane.showMessageDialog(null,
                        "<html><body><h2>Hra adventura<h2>"
                        + "<p>Grafická verze hry pro kurz 4IT115.</p>"
                        + "<p><i>Verze: srpen 2019 (opravy chyby, původně 2015)</i></p>"
                        + "</body></html>");
            }
        };

        // nastavení mnemonické klávesy
        oProgramuAction.putValue(AbstractAction.MNEMONIC_KEY, KeyEvent.VK_O);
        napovedaAction = new AbstractAction(
                "Nápověda k aplikaci",
                new ImageIcon(getClass().getResource("/helpIcon.gif"))) {

            @Override
            public void actionPerformed(ActionEvent event) {
                oknoNapovedy.setVisible(true);
            }
        };
    }
    
    /**
     * Metoda inicializuje toolbar aplikace.
     */
    private void initToolBaru() {
        listaToolBar = new JToolBar("Nastrojova lista", JToolBar.HORIZONTAL);
        listaToolBar.setRollover(true);
        listaToolBar.add(novaHraAction);
        listaToolBar.add(konecHryAction);
        listaToolBar.add(zobrPopisuMistAction);
        listaToolBar.add(oProgramuAction);
        listaToolBar.add(napovedaAction);
    }
    
    /**
     * Metoda, která zviditelňuje GUI
     *
     * @param viditelnost GUI
     */
    public void setVisible(boolean viditelnost) {
        hlavniOknoFrame.setVisible(viditelnost);
    }
}
