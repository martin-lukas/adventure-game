package com.lukas.adventuregame.commands;

/**
 * Interface for specifying the responsibilities of each command.
 *
 * @author Martin Lukáš
 * @version 1.0
 */
public interface ICommand {

    /**
     * Method for executing the command. Some commands will have no parameters,
     * some might have even more than one.
     *
     * @param parameters parameters of the command
     * @return success or error message
     */
    String execute(String... parameters);

    String getName();
}
