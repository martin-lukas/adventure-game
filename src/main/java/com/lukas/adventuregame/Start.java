package com.lukas.adventuregame;

import com.lukas.adventuregame.gamelogic.Game;
import com.lukas.adventuregame.ui.Gui;
import com.lukas.adventuregame.ui.TextInterface;

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
