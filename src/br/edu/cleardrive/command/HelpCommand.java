package br.edu.cleardrive.command;

import br.edu.cleardrive.App;
import br.edu.cleardrive.ExecutionMode;

/**
 * HelpCommand shows all available commands on CLI.
 *
 * @author Clederson Cruz
 *
 * @since 2.0.0
 *
 */

public class HelpCommand implements Command {

	/**
	 * Executes HelpCommand, available only in CLI usage.
	 */
	@Override
	public void execute() {
		if (App.getExecutionMode().equals(ExecutionMode.CLI)) {
			cliCommand();
		}

	}

	/**
	 * Shows all available CLI commands.
	 */
	private void cliCommand() {
		String args[] = App.getLatestArgs();
		String command = args[0];
		StringBuilder builder = new StringBuilder();

		builder.append("Exibindo opções de \"" + command + "\".");
		builder.append(System.lineSeparator() + System.lineSeparator());
		builder.append("--about:\texibe informações sobre esse software.");
		builder.append(System.lineSeparator());
		builder.append("--help:\texibe essa mensagem.");
		builder.append(System.lineSeparator());
		builder.append("--recover <letter>:\trecupera arquivos no disco <letter>, ex.: D, E, H etc.");
		builder.append(System.lineSeparator());
		builder.append("--version, --version <arg>:\texibe a versão desse software. Args:");
		builder.append(System.lineSeparator());
		builder.append("\tn:\tversão numérica");
		builder.append(System.lineSeparator());
		builder.append("\te:\tversão estendida");

		System.out.println(builder.toString());
	}

}
