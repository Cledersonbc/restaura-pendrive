package br.edu.cleardrive.view;

import javax.swing.JFrame;

import br.edu.cleardrive.App;

/**
 * The window shows all components inserted in main panel, like buttons, fields etc.
 * This class is a JFrame that supports all window's configurations.
 *
 * @author Clederson Cruz
 *
 * @since 1.0.0
 *
 */

public class Window extends JFrame {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor initializes all window's configurations.
	 */
	public Window() {
		initWindow();
	}

	/**
	 * All window's configurations are setting here.
	 */
	private void initWindow() {
		short width = 450;
		short height = 400;

		this.setTitle(App.NAME);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setSize(width, height);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setContentPane( new MainPanel() );
		this.setVisible(true);
	}
}
