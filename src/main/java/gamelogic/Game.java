package gamelogic;

import commands.*;

import java.util.Arrays;

/**
 * Main class of the logic of the application. Creates an instance of the class {@code GamePlan},
 * handles user's commands and prints the welcoming and ending messages.
 *
 * @author Martin Lukáš
 * @version 1.0
 */
public class Game {
    
    private final CommandList validCommands;
    private final GamePlan gamePlan;
    private boolean isFinished = false;
    private final Backpack backpack;
    
    public static final String END_MESSAGE = "Dík, že sis zahrál. Ahoj.";
    
    public Game() {
        gamePlan = new GamePlan();
        backpack = new Backpack();
        validCommands = new CommandList();
        validCommands.addCommand(new ExamineCommand(gamePlan, "prohledej"));
        validCommands.addCommand(new TakeCommand(gamePlan, backpack, "seber"));
        validCommands.addCommand(new GoCommand(this, "jdi"));
        validCommands.addCommand(new GiveCommand(gamePlan, backpack, "dej"));
        validCommands.addCommand(new BackpackCommand(backpack, "batoh"));
        validCommands.addCommand(new TalkCommand(gamePlan, "mluv"));
        validCommands.addCommand(new HelpCommand(validCommands, "napoveda"));
        validCommands.addCommand(new QuitCommand(this, "konec"));
    }
    
    /**
     * Handles the received command and creates an approppriate output.
     *
     * @param line line of text received from the command line
     * @return output of the command given
     */
    public String handleCommand(String line) {
        String[] words = line.split("[ \t]+");
        String commandWord = words[0];
        String outputText;
        ICommand command = validCommands.getCommand(commandWord);
        if (command != null) {
            outputText = command.execute(Arrays.copyOfRange(words, 1, words.length));
            // Find out if the game was won
            if (gamePlan.getCurrentRoom().equals(gamePlan.getEndRoom())) {
                isFinished = true;
                outputText += "\n" + END_MESSAGE;
            }
        } else {
            outputText = "Nevím co tím myslíš? Tento příkaz neznám.";
        }
        return outputText;
    }
    
    /**
     * Returns the welcome message, which appears at the beginning of the game.
     *
     * @return welcoming message
     */
    public String welcomeMessage() {
        return "Vítejte!\n"
                + "Toto je příběh zloděje, který kradl v zámku knížete,\n"
                + "ale teď se odsud nemůže dostat. A ještě navíc musí po cestě\n"
                + "ven ukrást svícen, který se nachází v komnatě knížete.\n"
                + "Bez něho nemůže odejít! Ty jsi ten zloděj...\n"
                + "Napiš 'napoveda', pokud si nevíš rady, jak hrát dál.\n"
                + "\n" + gamePlan.getCurrentRoom().longDescription();
    }
    
    public boolean isFinished() {
        return isFinished;
    }
    
    public void setFinished(boolean finished) {
        this.isFinished = finished;
    }
    
    /**
     * Returns the {@code GamePlan} object of the {@code Game} object.
     *
     * @return reference to the {@code GamePlan} object
     */
    public GamePlan getGamePlan() {
        return gamePlan;
    }
    
    /**
     * Returns the {@code Backpack} object of the game.
     *
     * @return reference to the {@code Backpack} object
     */
    public Backpack getBackpack() {
        return backpack;
    }
}
