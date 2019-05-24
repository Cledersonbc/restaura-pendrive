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

import br.edu.cleardrive.command.CommandName;
import br.edu.cleardrive.event.ButtonEventHandler;
import br.edu.cleardrive.view.component.ComponentManager;

/**
 * Main panel contains three parts: head, body and footer. Each part has
 * your owner components like buttons, labels, fields etc. MainPanel extends
 * JPanel by default.
 *
 * @author Clederson Cruz
 *
 * @since 2.0.0
 */

public class MainPanel extends JPanel {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Padding of GUI components.
	 */
	private final short PADDING = 4;

	/**
	 * Event handler for GUI components.
	 */
	private ButtonEventHandler buttonEventHandler;


	/**
	 * <p>
	 * When a instance of MainPanel are called, all inner panels are
	 * initialized.
	 *
	 * <p>
	 * This class is only instantiable in view package or
	 * by inheritance. The constructor are protected as default.
	 */
	protected MainPanel() {
		this.setLayout(new BorderLayout());
		buttonEventHandler = new ButtonEventHandler();

		buildHeaderPanel();
		buildBodyPanel();
		buildFooterPanel();
	}


	/**
	 * <p>
	 * Builds the header panel, initializing each component.
	 *
	 * <p>
	 * All components are registered on ComponentManager and your actions
	 * are setting by a action command name.
	 */
	private void buildHeaderPanel() {
		JPanel header = new JPanel(new GridLayout(2, 2, PADDING, PADDING));
		JComboBox<String> listedDrives = new JComboBox<String>();
		JProgressBar progressBar = new JProgressBar();

		ComponentManager.register(CommandName.DRIVE_CHANGED_COMBOBOX.toString(), listedDrives);
		ComponentManager.register(CommandName.STATUS_PROGRESS_BAR.toString(), progressBar);

		listedDrives.setActionCommand(CommandName.DRIVE_CHANGED_COMBOBOX.toString());
		listedDrives.addActionListener(buttonEventHandler);
		listedDrives.addItem("vazio");

		header.add(new JLabel("Progeresso:"));
		header.add(progressBar);
		header.add(new JLabel("Unidade:"));
		header.add(listedDrives);

		this.add(header, BorderLayout.NORTH);
	}


	/**
	 * <p>
	 * Builds the body panel, initializing each component.
	 *
	 * <p>
	 * All components are registered on ComponentManager and your actions
	 * are setting by a action command name.
	 */
	private void buildBodyPanel() {
		JPanel body = new JPanel(new GridLayout(1, 1, PADDING, PADDING));
		JTextArea outputView = new JTextArea();

		ComponentManager.register(CommandName.OUTPUT_VIEW_TEXT_AREA.toString(), outputView);

		outputView.setEditable(false);
		body.add(new JScrollPane(outputView));

		this.add(body, BorderLayout.CENTER);
	}

	/**
	 * <p>
	 * Builds the footer panel, initializing each component.
	 *
	 * <p>
	 * All components are registered on ComponentManager and your actions are
	 * setting by a action command name.
	 */
	private void buildFooterPanel() {
		JPanel footer = new JPanel(new GridLayout(1, 1, PADDING, PADDING));
		JButton btnAbout = new JButton("Sobre");
		JButton btnProtect = new JButton("Proteger");
		JButton btnStop = new JButton("Parar");
		JButton btnRecovery = new JButton("Recuperar");

		ComponentManager.register(CommandName.ABOUT.toString(), btnAbout);
		ComponentManager.register(CommandName.PROTECT.toString(), btnProtect);
		ComponentManager.register(CommandName.STOP.toString(), btnStop);
		ComponentManager.register(CommandName.RECOVER.toString(), btnRecovery);

		btnAbout.addActionListener(buttonEventHandler);
		btnAbout.setActionCommand(CommandName.ABOUT.toString());

		btnProtect.addActionListener(buttonEventHandler);
		btnProtect.setActionCommand(CommandName.PROTECT.toString());

		btnStop.addActionListener(buttonEventHandler);
		btnStop.setActionCommand(CommandName.STOP.toString());

		btnRecovery.addActionListener(buttonEventHandler);
		btnRecovery.setActionCommand(CommandName.RECOVER.toString());

		footer.add(btnAbout);
		footer.add(btnProtect);
		footer.add(btnStop);
		footer.add(btnRecovery);

		this.add(footer, BorderLayout.SOUTH);

	}

}
