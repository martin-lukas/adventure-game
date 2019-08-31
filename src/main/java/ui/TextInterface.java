package ui;

import java.util.Scanner;

import gamelogic.Game;

/**
 * Class for textual interface for the game. Intitializes the game,
 * and also handles user input from the command line.
 *
 * @author Martin Lukáš
 * @version 1.0
 */
public class TextInterface {

    private final Game game;

    public TextInterface() {
        game = new Game();
    }

    /**
     * Prints the welcoming message, and creates the command input cycle.
     */
    public void play() {
        System.out.println(game.welcomeMessage());
        while (!game.isFinished()) {
            String line = readInput();
            System.out.println(game.handleCommand(line));
        }
        System.out.println(Game.END_MESSAGE);
    }

    /**
     * Reads input from the command line.
     *
     * @return read input from the cmd
     */
    private String readInput() {
        // the encoding is for reading the czech input
        Scanner scanner = new Scanner(System.in, "CP1250");
        System.out.print("> ");
        return scanner.nextLine();
    }
}
