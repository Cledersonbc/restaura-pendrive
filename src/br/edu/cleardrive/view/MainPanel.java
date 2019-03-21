package br.edu.cleardrive.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import br.edu.cleardrive.view.event.ButtonEventHandler;

public class MainPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private final short PADDING = 4;
	private ButtonEventHandler buttonEventHandler;


	public MainPanel() {
		this.setLayout(new BorderLayout());
		buttonEventHandler = new ButtonEventHandler();

		buildHeaderPanel();
		buildBodyPanel();
		buildFooterPanel();
	}


	private void buildHeaderPanel() {
		JPanel header = new JPanel(new GridLayout(2, 2, PADDING, PADDING));
		JComboBox<String> listedDrives = new JComboBox<String>();
		JProgressBar progressBar = new JProgressBar();

		ComponentManager.register(ComponentName.DRIVE_CHANGED_COMBOBOX.toString(), listedDrives);
		ComponentManager.register(ComponentName.STATUS_PROGRESS_BAR.toString(), progressBar);

		listedDrives.setActionCommand(ComponentName.DRIVE_CHANGED_COMBOBOX.toString());
		listedDrives.addActionListener(buttonEventHandler);

		header.add(new JLabel("Progeresso:"));
		header.add(progressBar);
		header.add(new JLabel("Unidade:"));
		header.add(listedDrives);

		this.add(header, BorderLayout.NORTH);
	}


	private void buildBodyPanel() {
		JPanel body = new JPanel(new GridLayout(1, 1, PADDING, PADDING));
		JTextArea outputView = new JTextArea();

		ComponentManager.register(ComponentName.OUTPUT_VIEW_TEXT_AREA.toString(), outputView);

		outputView.setEditable(false);
		body.add(new JScrollPane(outputView));

		this.add(body, BorderLayout.CENTER);
	}

	private void buildFooterPanel() {
		JPanel footer = new JPanel(new GridLayout(1, 1, PADDING, PADDING));
		JButton btnAbout = new JButton("Sobre");
		JButton btnProtect = new JButton("Proteger");
		JButton btnStop = new JButton("Parar");
		JButton btnRecovery = new JButton("Recuperar");

		ComponentManager.register(ComponentName.ABOUT_BUTTON.toString(), btnAbout);
		ComponentManager.register(ComponentName.PROTECT_BUTTON.toString(), btnProtect);
		ComponentManager.register(ComponentName.STOP_BUTTON.toString(), btnStop);
		ComponentManager.register(ComponentName.RECOVER_BUTTON.toString(), btnRecovery);

		btnAbout.addActionListener(buttonEventHandler);
		btnAbout.setActionCommand(ComponentName.ABOUT_BUTTON.toString());

		btnProtect.addActionListener(buttonEventHandler);
		btnProtect.setActionCommand(ComponentName.PROTECT_BUTTON.toString());

		btnStop.addActionListener(buttonEventHandler);
		btnStop.setActionCommand(ComponentName.STOP_BUTTON.toString());

		btnRecovery.addActionListener(buttonEventHandler);
		btnRecovery.setActionCommand(ComponentName.RECOVER_BUTTON.toString());

		footer.add(btnAbout);
		footer.add(btnProtect);
		footer.add(btnStop);
		footer.add(btnRecovery);

		this.add(footer, BorderLayout.SOUTH);

	}

}
