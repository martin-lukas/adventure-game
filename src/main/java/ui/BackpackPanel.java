package ui;

import java.net.URL;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import gamelogic.Backpack;
import gamelogic.Game;
import gameobjects.Thing;
import util.BackpackChangeObserver;

/**
 * Backpack panel class. Implements observer interface, so it gets updated with the backpack's change.
 * 
 * author Martin Lukáš
 * @version 1.0
 */
public class BackpackPanel extends JPanel implements BackpackChangeObserver {
    public BackpackPanel(Game game) {
        super();
        init();
        game.getBackpack().registerObserver(this);
        this.update(game.getBackpack());
    }

    private void init() {
        this.setBorder(BorderFactory.createTitledBorder("Věci v batohu:"));
        this.setVisible(true);
    }
    
    /**
     * Method receiving update from the backpack change.
     *
     * @param backpack changed backpack
     */
    @Override
    public void update(Backpack backpack) {
        this.removeAll();
        
        URL imgUrl;
        for (Thing thing : backpack.getSetOfThings()) {
            imgUrl = this.getClass().getResource("/" + thing.getImgUrl());
            
            if (imgUrl == null) {
                JOptionPane.showMessageDialog(null,
                        "Soubor s obrázkem nebyl nalezen.",
                        "Chyba při načítání obrázku", JOptionPane.ERROR_MESSAGE);
            } else {
                JLabel image = new JLabel(new ImageIcon(imgUrl));
                this.add(image);
            }
        }
    }
}
