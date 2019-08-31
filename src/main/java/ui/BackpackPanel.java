package ui;

import java.net.URL;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import gamelogic.Backpack;
import gamelogic.Game;
import gamelogic.GamePlan;
import gameobjects.Thing;
import util.BackpackChangeObserver;

/**
 * Backpack panel class. Implements observer interface, so it gets updated with the backpack's change.
 * 
 * author Martin Lukáš
 * @version 1.0
 */
public class BackpackPanel extends JPanel implements BackpackChangeObserver {
    private Game game;
    
    public BackpackPanel(Game game) {
        super();
        this.game = game;
        initComponents();
        game.getBackpack().registerObserver(this);
        this.update(game.getBackpack());
    }

    private void initComponents() {
        this.setBorder(BorderFactory.createTitledBorder("Věci v batohu:"));
        this.setVisible(true);
    }
    
    /**
     * This method registers this class as an observer to the new backpack in a new game.
     *
     * @param game new game
     */
    public void setGame(Game game) {
        this.game = game;
        Backpack newBackpack = game.getBackpack();
        newBackpack.registerObserver(this);
        this.update(newBackpack);
    }
    
    /**
     * Method receiving update from the backpack change.
     *
     * @param backpack changed backpack
     */
    @Override
    public void update(Backpack backpack) {
        this.removeAll();
        this.updateUI();
        
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
