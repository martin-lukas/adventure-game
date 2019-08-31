package ui;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import gamelogic.GamePlan;
import gameobjects.Room;
import util.RoomChangeObserver;

/**
 * Class for exits panel. It is a registered observer to the room changes.
 * 
 * @author Martin Lukáš
 * @version 1.0
 */
public class ExitsPanel extends JPanel implements RoomChangeObserver {

    private GamePlan plan;
    private JTextArea exitsTextArea;

    public ExitsPanel(GamePlan plan) {
        super();
        this.plan = plan;
        plan.registerObserver(this);
        initComponents();
        this.update(plan.getCurrentRoom());
    }

    private void initComponents() {
        exitsTextArea = new JTextArea(10, 10);
        exitsTextArea.setEditable(false);
        exitsTextArea.setBorder(BorderFactory.createTitledBorder("Východy:"));
        this.add(exitsTextArea);
    }

    /**
     * This method registers this class as an observer to the new game plan when
     * a new game is launched.
     *
     * @param plan new game plan
     */
    public void setGamePlan(GamePlan plan) {
        this.plan = plan;
        plan.registerObserver(this);
        this.update(plan.getCurrentRoom());
    }

    /**
     * Metoda for updating the exits panel with the change of the current room.
     * 
     * @param currentRoom current room
     */
    @Override
    public void update(Room currentRoom) {
        exitsTextArea.setText("");
        for (Room exit : currentRoom.getExits()) {
            exitsTextArea.append(exit.getName() + "\n");
        }
    }
}
