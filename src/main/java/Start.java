import logika.Hra;
import ui.Gui;
import ui.TextoveRozhrani;

/**
 * Třída Start slouží ke spouštění aplikace ...
 *
 * @author Martin Lukáš
 * @version 1.0
 */
public class Start {

    /**
     * Metoda Start, main třída aplikace
     *
     * @param args - argumenty při spouštění
     */
    public static void main(String[] args) {
        if (args.length == 0) {
            Gui grafika = new Gui(new Hra());
            grafika.setVisible(true);
        } else {
            if (args[0].equals("-text")) {
                TextoveRozhrani text = new TextoveRozhrani();
                text.hraj();
            } else {
                System.out.println("Neplatný parametr.");
            }
        }
        
    }
}
