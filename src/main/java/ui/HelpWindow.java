package ui;

import java.io.IOException;
import java.net.URL;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

/**
 * Class for the help window.
 * 
 * @author Martin Lukáš
 * @version 1.0
 */
public class HelpWindow {

    private JDialog helpDialog;
    private final URL fileUrl;

    public HelpWindow(String soubor) {
        fileUrl = this.getClass().getResource(soubor);
        initComponents();
    }

    private void initComponents() {
        helpDialog = new JDialog();
        helpDialog.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
        helpDialog.setTitle("Nápověda k aplikaci");
    
        JEditorPane outputPane = new JEditorPane();
        outputPane.setEditable(false);
        helpDialog.add(new JScrollPane(outputPane));
        try {
            outputPane.setPage(fileUrl);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null,
                    "Soubor s nápovědou nebyl nalezen",
                    "Chyba při načítání nápovědy",
                    JOptionPane.ERROR_MESSAGE);
        }
        helpDialog.setLocation(700, 200);
        helpDialog.setSize(400, 500);
    }

    public void setVisible(boolean visibility) {
        helpDialog.setVisible(visibility);
    }
}
