package ui;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import gamelogic.GamePlan;
import gameobjects.Room;
import util.RoomChangeObserver;

/**
 * Class for the room description window.
 * 
 * @author Martin Lukáš
 * @version 1.0
 */
public class RoomDescWindow implements RoomChangeObserver {

    private JDialog roomDescDialog;
    private JTextArea outputTextArea;
    private final GamePlan gamePlan;
    
    public RoomDescWindow(GamePlan gamePlan) {
        //třída se zaregistruje jako pozorovatel změny prostoru
        this.gamePlan = gamePlan;
        initComponents();
        gamePlan.registerObserver(this);
        
    }

    private void initComponents() {
        roomDescDialog = new JDialog();
        outputTextArea = new JTextArea(10, 40);
        outputTextArea.setEditable(false);
        roomDescDialog.add(new JScrollPane(outputTextArea));
        roomDescDialog.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
        outputTextArea.setText(gamePlan.getCurrentRoom().longDescription());

        roomDescDialog.pack();
    }
    
    /**
     * Method changes the game plan. This is necessary for example when a new game is launched.
     *
     * @param gamePlan new game plan
     */
    public void setGamePlan(GamePlan gamePlan) {
        gamePlan.registerObserver(this);
        this.update(gamePlan.getCurrentRoom());
    }

    /**
     * Method for updating the object after a change of the room.
     * 
     * @param currentRoom new current room
     */
    @Override
    public void update(Room currentRoom) {
        outputTextArea.setText(currentRoom.longDescription());
    }

    public void setVisible(boolean visibility) {
        roomDescDialog.setVisible(visibility);
    }
}
