package br.edu.cleardrive.view;

import javax.swing.JFrame;

public class Window extends JFrame {
	
	private static final long serialVersionUID = 1L;

	public Window() {
		initWindow();
	}
	
	private void initWindow() {
		short width = 450;
		short height = 400;
		
		this.setTitle("Clear Drive");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setSize(width, height);
		this.setLocationRelativeTo(null);	
		this.setResizable(false);
		this.setContentPane( new MainPanel() );
		this.setVisible(true);
	}
}
