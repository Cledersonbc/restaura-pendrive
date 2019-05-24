package br.edu.cleardrive.command;

import java.util.HashMap;

import br.edu.cleardrive.App;
import br.edu.cleardrive.ExecutionMode;

/**
 * Command to show the version of this software.
 *
 * @author Clederson Cruz
 *
 * @since 2.0.0
 *
 */

public class VersionCommand implements Command {

	/**
	 * Executes the command to shows the software version. This operation is only
	 * available in CLI mode. In GUI mode, "about" satisfy.
	 */
	@Override
	public void execute() {
		if (App.getExecutionMode().equals(ExecutionMode.CLI)) {
			cliCommand();
		}
	}

	/**
	 * CLI command to show the software version.
	 *
	 * @throws IllegalArgumentException if the args or syntax are incorrectly.
	 */
	private void cliCommand() {
		HashMap<String, String> versionName = new HashMap<>();
		String args[] = App.getLatestArgs();
		String command = args[0];

		versionName.put("n", App.VERSION);
		versionName.put("e", App.NAME + " - " + App.VERSION);

		if (args.length == 1) {
			System.out.println(versionName.get("e"));
		} else if (args.length == 2) {

			if (!versionName.containsKey(args[1])) {
				throw new IllegalArgumentException("Argumento não encontrado para \"" + command + "\".");
			}
			System.out.println(versionName.get(args[1]));

		} else {
			throw new IllegalArgumentException("Sintáxe inválida para \"" + command + "\".");
		}
	}

}
