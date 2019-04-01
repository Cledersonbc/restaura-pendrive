package br.edu.cleardrive.command;

import java.util.HashMap;
import java.util.Map;

import br.edu.cleardrive.view.component.ComponentName;

public class Invoker {

	private final Map<String, Command> commands;

	public Invoker() {
		//XXX this is not a good implementation because I need to instantiate all commands ever time
		// Find the best way to do it
		commands = new HashMap<String, Command>();
		commands.put(ComponentName.ABOUT_BUTTON.toString(), new AboutCommand());
		commands.put(ComponentName.DRIVE_CHANGED_COMBOBOX.toString(), new DriveChangeCommand());
		commands.put(ComponentName.RECOVER_BUTTON.toString(), new RecoverCommand());
		commands.put(ComponentName.STOP_BUTTON.toString(), new StopRecoverCommand());
		commands.put(ComponentName.PROTECT_BUTTON.toString(), new ProtectCommand());
	}

	public void executeAction(String action) {
		commands.get(action).execute();
	}

}
