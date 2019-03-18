package br.edu.cleardrive;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

import br.edu.cleardrive.view.Window;

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
			JOptionPane.showMessageDialog(null,
					"Essa aplicação não funcionará bem em uma plataforma diferente do Windows.",
					"Alerta",
					JOptionPane.WARNING_MESSAGE);
		}
		new Window();

	}

}
