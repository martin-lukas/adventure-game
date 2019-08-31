package commands;

import commands.ICommand;

import java.util.HashMap;
import java.util.Map;

/**
 * This class manages the list of valid commands.
 */
public class CommandList {

    private final Map<String, ICommand> commandMap;
    
    public CommandList() {
        commandMap = new HashMap<>();
    }

    /**
     * Adds a new valid command.
     *
     * @param command instance of a class implementing the {@code ICommand} interface
     */
    public void addCommand(ICommand command) {
        commandMap.put(command.getName(), command);
    }

    /**
     * Returns a command with the given name.
     *
     * @param retezec - searched command
     * @return found command object, or {@code null} if not found
     */
    public ICommand getCommand(String retezec) {
        return commandMap.get(retezec);
    }

    /**
     * Returns a string with the list of all the valid commands.
     *
     * @return string with valid commands
     */
    public String listOfCommands() {
        StringBuilder seznam = new StringBuilder();
        for (String slovoPrikazu : commandMap.keySet()) {
            seznam.append(slovoPrikazu).append(", ");
        }
        return seznam.toString();
    }
}