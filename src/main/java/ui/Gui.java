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

import gamelogic.Game;

/**
 * Class for creating the entire graphical user interface.
 * 
 * @author Martin Lukáš
 * @version 1.0
 */
public class Gui {

    private Game game;
    private JTextField inputTextField;
    private JTextArea outputTextArea;
    private ExitsPanel exitsPanel;
    private RoomDescWindow roomDescWindow;
    private JMenuBar menuBar;
    private HelpWindow helpWindow;
    private BackpackPanel backpackPanel;
    private JToolBar toolBar;
    
    private Action newGameAction;
    private Action endGameAction;
    private Action roomDescAction;
    private Action aboutAction;
    private Action helpAction;
    
    private class CommandListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            
            backpackPanel.update(game.getBackpack());
            String command = inputTextField.getText();
            String output = game.handleCommand(command);
            outputTextArea.append("\n\n" + command + "\n");
            outputTextArea.append("\n" + output + "\n");
            inputTextField.setText("");
            outputTextArea.setCaretPosition(
                outputTextArea.getDocument().getLength());
            if (game.isFinished()) {
                inputTextField.setEditable(false);
            }
        }
    }

    public Gui(Game game) {
        this.game = game;
        initActions();
        initComponents();
        outputTextArea.setText(game.welcomeMessage());
        inputTextField.requestFocusInWindow();
    }
    
    private void initActions() {
        newGameAction = new AbstractAction(
                "Nová hra",
                new ImageIcon(getClass().getResource("/newIcon.gif"))) {
            
            @Override
            public void actionPerformed(ActionEvent event) {
                game = new Game();
                roomDescWindow.setGamePlan(game.getGamePlan());
                exitsPanel.setGamePlan(game.getGamePlan());
                outputTextArea.setText(game.welcomeMessage());
                inputTextField.setEditable(true);
                inputTextField.requestFocusInWindow();
            }
        };
        
        endGameAction = new AbstractAction(
                "Konec hry",
                new ImageIcon(getClass().getResource("/stopIcon.gif"))) {
            
            @Override
            public void actionPerformed(ActionEvent event) {
                System.exit(0);
            }
        };
        endGameAction.putValue(AbstractAction.ACCELERATOR_KEY,
                KeyStroke.getKeyStroke(KeyEvent.VK_F4, InputEvent.ALT_MASK));
        
        roomDescAction = new AbstractAction("Zobrazit popis místnosti") {
            
            @Override
            public void actionPerformed(ActionEvent event) {
                roomDescWindow.setVisible(true);
                
            }
        };
        
        aboutAction = new AbstractAction("O programu") {
            
            @Override
            public void actionPerformed(ActionEvent event) {
                JOptionPane.showMessageDialog(null,
                        "<html><body><h2>Hra adventura<h2>"
                                + "<p>Grafická verze hry pro kurz 4IT115.</p>"
                                + "<p><i>Verze: srpen 2019 (opravy chyby, původně 2015)</i></p>"
                                + "</body></html>");
            }
        };
        aboutAction.putValue(AbstractAction.MNEMONIC_KEY, KeyEvent.VK_O);
        
        helpAction = new AbstractAction(
                "Nápověda k aplikaci",
                new ImageIcon(getClass().getResource("/helpIcon.gif"))) {
            
            @Override
            public void actionPerformed(ActionEvent event) {
                helpWindow.setVisible(true);
            }
        };
    }
    
    private void initComponents() {
        JFrame mainWindow = new JFrame("Adventura");
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.setSize(800, 480);
        mainWindow.setLocation(200, 200);
        mainWindow.setResizable(false);

        JPanel dolniPanel = new JPanel();
        inputTextField = new JTextField(20);
        inputTextField.addActionListener(new CommandListener());
        JLabel prikazLabel = new JLabel("Příkaz: ");

        dolniPanel.add(prikazLabel);
        dolniPanel.add(inputTextField);
        

        mainWindow.add(dolniPanel, BorderLayout.SOUTH);
        outputTextArea = new JTextArea(20, 70);
        outputTextArea.setEditable(false);
        outputTextArea.setFont(new Font("monospaced", Font.PLAIN, 12));
        mainWindow.add(new JScrollPane(outputTextArea));
    
        MapPanel mapPanel = new MapPanel(game.getGamePlan());
        mainWindow.add(mapPanel, BorderLayout.WEST);

        JPanel pravyPanel = new JPanel(new GridLayout(2, 1));
        exitsPanel = new ExitsPanel(game.getGamePlan());
        backpackPanel = new BackpackPanel(game);
        backpackPanel.setBackground(Color.WHITE);
        pravyPanel.add(exitsPanel);
        pravyPanel.add(backpackPanel);
        mainWindow.add(pravyPanel, BorderLayout.EAST);

        roomDescWindow = new RoomDescWindow(game.getGamePlan());
        helpWindow = new HelpWindow("/help.htm");

        initMenu();
        mainWindow.setJMenuBar(menuBar);
        
        initToolBar();
        mainWindow.add(toolBar, BorderLayout.NORTH);
        
        mainWindow.pack();
        mainWindow.setVisible(true);
    }

    private void initMenu() {
        menuBar = new JMenuBar();
    
        JMenu fileMenu = new JMenu("Soubor");
        fileMenu.setMnemonic(KeyEvent.VK_S);
        menuBar.add(fileMenu);
    
        JMenu viewMenu = new JMenu("Zobrazit");
        menuBar.add(viewMenu);
    
        JMenu helpMenu = new JMenu("Nápověda");
        helpMenu.setMnemonic(KeyEvent.VK_N);
        menuBar.add(helpMenu);
    
        JMenuItem newGameMenuItem = new JMenuItem(newGameAction);
        fileMenu.add(newGameMenuItem);

        fileMenu.addSeparator();
    
        JMenuItem endMenuItem = new JMenuItem(endGameAction);
        fileMenu.add(endMenuItem);
    
        JMenuItem roomDescMenuItem = new JMenuItem(roomDescAction);
        viewMenu.add(roomDescMenuItem);
    
        JMenuItem aboutMenuItem = new JMenuItem(aboutAction);
        helpMenu.add(aboutMenuItem);
    
        JMenuItem helpMenuItem = new JMenuItem(helpAction);
        helpMenu.add(helpMenuItem);
    }

    private void initToolBar() {
        toolBar = new JToolBar("Nastrojova lista", JToolBar.HORIZONTAL);
        toolBar.setRollover(true);
        toolBar.add(newGameAction);
        toolBar.add(endGameAction);
        toolBar.add(roomDescAction);
        toolBar.add(aboutAction);
        toolBar.add(helpAction);
    }
}
