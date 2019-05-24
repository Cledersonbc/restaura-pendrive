package br.edu.cleardrive.event;

import br.edu.cleardrive.App;
import br.edu.cleardrive.command.CommandName;
import br.edu.cleardrive.command.Invoker;

/**
 * Client to invoke action commands for each command name that are registered on
 * ComponentName (Enum) that are passed by CLI args.
 * 
 * @author Clederson Cruz
 *
 * @since 2.0.0
 */

public class CommandLineEnventHandler {

	/**
	 * Default Constructor.
	 */
	public CommandLineEnventHandler() {

	}

	/**
	 * Initializes command line event and executes the args commands (if possible).
	 */
	public void init() {
		Invoker invoker = new Invoker();
		String args[] = App.getLatestArgs();
		String commandAction = args[0].replace("--", "");
		Boolean validated = false;

		for (CommandName commandName : CommandName.values()) {
			if (commandAction.equals(commandName.toString())) {
				validated = true;
				break;
			}
		}

		if (validated.equals(Boolean.FALSE)) {
			System.err.println("Command \"" + commandAction + "\" not found.");
			return;
		}

		try {
			invoker.executeAction(commandAction);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
