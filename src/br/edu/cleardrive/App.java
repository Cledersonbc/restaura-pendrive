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

	/**
	 * This field is used to store the name of this software.
	 */
	final public static String NAME = "Cleardrive";

	/**
	 * This field is used to store the version of this software.
	 */
	final public static String VERSION = "2.0.0";

	/**
	 * This field is used to store the author's name of this software.
	 */
	final public static String AUTHOR = "Clederson Cruz";

	/**
	 * This field is used to store the execution mode of this software. The
	 * execution mode of this software can be NONE (by default), CLI (if args > 0)
	 * or GUI (if args == 0).
	 */
	private static ExecutionMode mode = ExecutionMode.NONE;

	/**
	 * This field is used to store the latest args passed by parameters for this software.
	 */
	private static String[] latestArgs;

	/**
	 * Default constructor.
	 */
	public App() {

	}

	/**
	 * Start point.
	 *
	 * @param args received by application.
	 */
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

	/**
	 * A way to get the execution mode. Execution mode can be NONE, CLI or GUI.
	 *
	 * @return execution mode of this software.
	 */
	public static ExecutionMode getExecutionMode() {
		return App.mode;
	}

	/**
	 * Gets the latest args received by this software.
	 *
	 * @return latest args received by this software.
	 */
	public static String[] getLatestArgs() {
		return App.latestArgs;
	}

	/**
	 * Loads CLI of this software.
	 */
	private static void loadCLI() {
		String osname = System.getProperty("os.name").toLowerCase();
		if (!osname.contains("windows")) {
			String message = "Essa aplicação não funcionará bem em uma plataforma diferente do Windows.";
			System.out.println("[WARN] " + message);
		}
		new CommandLineEnventHandler().init();
	}

	/**
	 * Loads GUI of this software.
	 */
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
