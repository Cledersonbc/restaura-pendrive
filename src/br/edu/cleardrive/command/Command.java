package br.edu.cleardrive.command;

/**
 * Command itself. This interface represents each command that can be executed
 * by the application.
 *
 * @author Clederson Cruz
 *
 * @since 2.0.0
 *
 */

public interface Command {

	/**
	 * Unique method is "execute", that should be implemented
	 * to execute a command.
	 */
	public void execute();
}
