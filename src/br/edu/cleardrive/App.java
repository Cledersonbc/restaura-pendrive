package br.edu.cleardrive;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

import br.edu.cleardrive.view.Window;

/**
 * Cleardrive is a software to recover hidden files from disks like pendrive on
 * Windows operating system.
 *
 * @version 2.0.0
 *
 * @author Clederson Cruz
 *
 */

public class App {
	final public static String VERSION = "2.0.0";
	final public static String AUTHOR = "Clederson Cruz";

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}

		String osname = System.getProperty("os.name").toLowerCase();
		if (!osname.contains("windows")) {
			String message = "Essa aplicação não funcionará bem em uma plataforma diferente do Windows.";
			String title = "Alerta";

			JOptionPane.showMessageDialog(null, message, title, JOptionPane.WARNING_MESSAGE);
		}
		new Window();

	}

}
