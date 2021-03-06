package br.edu.cleardrive.command;

import javax.swing.JOptionPane;

import br.edu.cleardrive.App;
import br.edu.cleardrive.ExecutionMode;

/**
 * Command to show "About the software".
 *
 * @author Clederson Cruz
 *
 * @since 2.0.0
 *
 */

public class AboutCommand implements Command {

	/**
	 * Executes the command, that it shows "about the software".
	 */
	@Override
	public void execute() {
		StringBuilder message = new StringBuilder();

		message.append("Software desenvolvido por " + App.AUTHOR);
		message.append(System.lineSeparator());
		message.append("para recuperação de arquivos ocultados em");
		message.append(System.lineSeparator());
		message.append("pendrives ou unidades de disco.");
		message.append(System.lineSeparator() + System.lineSeparator());
		message.append("Versão: " + App.VERSION);

		if (App.getExecutionMode().equals(ExecutionMode.CLI)) {
			cliCommand(message.toString());
		} else if (App.getExecutionMode().equals(ExecutionMode.GUI)) {
			guiCommand(message.toString());
		}
	}

	/**
	 * Shows information about this software in CLI.
	 *
	 * @param message to be sent about this software.
	 *
	 * @throws IllegalArgumentException if the args or syntax are incorrectly.
	 */
	private void cliCommand(String message) {
		String args[] = App.getLatestArgs();
		String command = args[0];

		if (args.length > 1) {
			throw new IllegalArgumentException("Sintáxe inválida para \"" + command + "\".");
		}
		System.out.println(message);
	}

	/**
	 * Shows information about this software in GUI.
	 *
	 * @param message to be sent about this software.
	 */
	private void guiCommand(String message) {
		String title = "Sobre";
		JOptionPane.showMessageDialog(null, message, title, JOptionPane.PLAIN_MESSAGE);
	}

}
