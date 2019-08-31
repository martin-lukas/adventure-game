package com.lukas.adventuregame.commands;

/**
 * Command help to print help on the screen.
 *
 * @author Martin Lukáš
 * @version 1.0
 */
public class HelpCommand implements ICommand {

    private final CommandList validCommands;
    private final String name;

    public HelpCommand(CommandList validCommands, String name) {
        this.validCommands = validCommands;
        this.name = name;
    }

    /**
     * Returns the help message.
     *
     * @param parameters parameters of the command
     * @return help for the game
     */
    @Override
    public String execute(String... parameters) {
        return "Tvým úkolem je dojít pro svícen a rychle odtud vypadnout.\n"
                + "Můžeš zadat tyto příkazy:\n"
                + validCommands.listOfCommands() + "\n\n"
                + "Mapa: <kašna>-<podloubí>-<nádvoří>-<chodba>-<komnata_knížete>\n"
                + "       start                 |\n"
                + "                          <brána>-<svoboda>\n"
                + "                                     cíl";
    }
    
    @Override
    public String getName() {
        return name;
    }
}
