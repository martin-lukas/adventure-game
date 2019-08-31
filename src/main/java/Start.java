import gamelogic.Game;
import ui.Gui;
import ui.TextInterface;

/**
 * Entry class for launching the application.
 *
 * @author Martin Lukáš
 * @version 1.0
 */
public class Start {
    public static void main(String[] args) {
        if (args.length == 0) {
            Gui graphics = new Gui(new Game());
        } else {
            if (args[0].equals("-text")) {
                TextInterface text = new TextInterface();
                text.play();
            } else {
                System.out.println("Neplatný parametr.");
            }
        }
    }
}
