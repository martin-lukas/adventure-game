package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.net.URL;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import gamelogic.GamePlan;
import gameobjects.Room;
import util.RoomChangeObserver;

/**
 * Class for the JPanel containing the map.
 * 
 * @author Martin Lukáš
 * @version 1.0
 */
public class MapPanel extends JPanel implements RoomChangeObserver {

    private Icon logoIcon;
    private GamePlan gamePlan;
    private Room currentRoom;
    
    public MapPanel(GamePlan gamePlan) {
        super();
        this.gamePlan = gamePlan;
        gamePlan.registerObserver(this);
        initComponents();
        this.update(gamePlan.getCurrentRoom());
    }
    
    private void initComponents() {
        URL fileUrl = this.getClass().getResource("/map.png");
        if (fileUrl == null) {
            JOptionPane.showMessageDialog(null,
                    "Soubor s obrázkem nebyl nalezen",
                    "Chyba při načítání obrázku", JOptionPane.ERROR_MESSAGE);
        } else {
            logoIcon = new ImageIcon(fileUrl);
        }
        this.setPreferredSize(new Dimension(192, 255));
    }
    /**
     * Method draws the panel component, including a dot for the location of the player.
     *
     * @param graphics -graphic context
     */
    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        if (logoIcon != null) {
            logoIcon.paintIcon(this, graphics, 0, 0);
        }
        graphics.setColor(Color.MAGENTA);
        switch (currentRoom.getName()) {
            case "kašna":
                graphics.fillOval(30, 50, 8, 8);
                break;
            case "podloubí":
                graphics.fillOval(30, 105, 8, 8);
                break;
            case "nádvoří":
                graphics.fillOval(90, 105, 8, 8);
                break;
            case "chodba":
                graphics.fillOval(155, 105, 8, 8);
                break;
            case "komnata_knížete":
                graphics.fillOval(155, 50, 8, 8);
                break;
            case "brána":
                graphics.fillOval(90, 170, 8, 8);
                break;
            case "svoboda":
                graphics.fillOval(90, 235, 8, 8);
                break;
        }
    }
    
    /**
     * Method sets a new game plan, which is necessary when a new game is launched.
     * 
     * @param gamePlan herni plan
     */
    public void setGamePlan(GamePlan gamePlan) {
        this.gamePlan = gamePlan;
        gamePlan.registerObserver(this);
        this.update(gamePlan.getCurrentRoom());
    }

    /**
     * Method updates the contents of the panel based on the change of the room.
     * 
     * @param currentRoom current room
     */
    @Override
    public void update(Room currentRoom) {
        this.currentRoom = currentRoom;
        repaint();
    }
}
