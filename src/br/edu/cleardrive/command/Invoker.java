package br.edu.cleardrive.command;

import java.util.HashMap;
import java.util.Map;

/**
 * Invoker of all commands.
 *
 * @author Clederson Cruz
 *
 * @since 2.0.0
 *
 */

public class Invoker {

	private final Map<String, Command> commands;

	/**
	 * Constructor that initializes all available commands.
	 */
	public Invoker() {
		commands = new HashMap<String, Command>();
		commands.put(CommandName.ABOUT.toString(), new AboutCommand());
		commands.put(CommandName.DRIVE_CHANGED_COMBOBOX.toString(), new DriveChangeCommand());
		commands.put(CommandName.HELP.toString(), new HelpCommand());
		commands.put(CommandName.RECOVER.toString(), new RecoverCommand());
		commands.put(CommandName.STOP.toString(), new StopRecoverCommand());
		commands.put(CommandName.PROTECT.toString(), new ProtectCommand());
		commands.put(CommandName.VERSION.toString(), new VersionCommand());
	}

	/**
	 * Executes an action.
	 *
	 * @param action to be executed.
	 */
	public void executeAction(String action) {
		commands.get(action).execute();
	}

}
