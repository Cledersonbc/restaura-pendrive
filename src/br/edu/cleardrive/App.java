package br.edu.cleardrive;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

import br.edu.cleardrive.event.CommandLineEnventHandler;
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
	final public static String NAME = "Cleardrive";
	final public static String VERSION = "2.0.0";
	final public static String AUTHOR = "Clederson Cruz";
	private static ExecutionMode mode = ExecutionMode.NONE;
	private static String[] latestArgs;

	public static void main(String[] args) {

		if (args.length > 0) {
			App.mode = ExecutionMode.CLI;
			App.latestArgs = args;
			loadCLI();
		} else {
			App.mode = ExecutionMode.GUI;
			loadGUI();
		}
	}

	private static void loadCLI() {
		new CommandLineEnventHandler();
	}

	public static ExecutionMode getExecutionMode() {
		return App.mode;
	}

	public static String[] getLatestArgs() {
		return App.latestArgs;
	}

	private static void loadGUI() {
		String osname = System.getProperty("os.name").toLowerCase();
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (!osname.contains("windows")) {
			String message = "Essa aplicação não funcionará bem em uma plataforma diferente do Windows.";
			String title = "Alerta";

			JOptionPane.showMessageDialog(null, message, title, JOptionPane.WARNING_MESSAGE);
		}

		new Window();
	}

}
