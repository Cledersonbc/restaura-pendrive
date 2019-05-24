package br.edu.cleardrive.event;

import br.edu.cleardrive.App;
import br.edu.cleardrive.command.CommandName;
import br.edu.cleardrive.command.Invoker;

public class CommandLineEnventHandler {

	public CommandLineEnventHandler() {
		init();
	}

	private void init() {
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
			System.out.println("Command \"" + commandAction + "\" not found.");
			return;
		}

		try {
			invoker.executeAction(commandAction);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
