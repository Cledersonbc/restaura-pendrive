package br.edu.cleardrive.command;

import java.util.HashMap;
import java.util.Map;

import br.edu.cleardrive.view.component.ComponentName;

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
		commands.put(ComponentName.ABOUT_BUTTON.toString(), new AboutCommand());
		commands.put(ComponentName.DRIVE_CHANGED_COMBOBOX.toString(), new DriveChangeCommand());
		commands.put(ComponentName.RECOVER_BUTTON.toString(), new RecoverCommand());
		commands.put(ComponentName.STOP_BUTTON.toString(), new StopRecoverCommand());
		commands.put(ComponentName.PROTECT_BUTTON.toString(), new ProtectCommand());
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
