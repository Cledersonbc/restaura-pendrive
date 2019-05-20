package br.edu.cleardrive.command;

import javax.swing.JOptionPane;

import br.edu.cleardrive.App;

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
	 * Executes the command, that shows "about the software" in a JOptionFrame.
	 */
	@Override
	public void execute() {
		StringBuilder message = new StringBuilder();
		message.append("Software desenvolvido por "+ App.AUTHOR +"\n");
		message.append("para recuperação de arquivos ocultados em\n");
		message.append("pendrives ou unidades de disco.\n");
		message.append("\n");
		message.append("Versão: "+ App.VERSION);

		String title = "Sobre";

		new JOptionPane();
		JOptionPane.showMessageDialog(null, message.toString(), title, JOptionPane.PLAIN_MESSAGE);
	}

}
