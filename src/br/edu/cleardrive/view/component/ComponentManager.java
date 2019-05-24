package br.edu.cleardrive.view.component;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JProgressBar;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JViewport;

import br.edu.cleardrive.command.CommandName;

/**
 * ComponentManager is a way to manage all GUI components, like buttons, fields, textarea etc.
 * This abstraction contains methods that allows you to put, get and remove GUI components to be
 * accessed by others classes.
 *
 * @author Clederson Cruz
 *
 * @since 2.0.0
 */
public final class ComponentManager {
	private static Map<String, JComponent> components = new HashMap<String, JComponent>();

	/**
	 * No instances are required, private constructor.
	 *
	 * @throws AssertionError for convenience (all methods are statics and the component
	 *                         manager are unique)
	 */
	private ComponentManager() {
		throw new AssertionError("You can not instantiate " + this.getClass().getName());
	}

	/**
	 * To register a JComponent into ComponentManager, a name and itself are needed.
	 *
	 * @param name of component (must be unique)
	 * @param component itself
	 */
	public static void register(String name, JComponent component) {
		components.put(name, component);
	}

	/**
	 * Removes a JComponent from ComponentManager.
	 *
	 * @param name of the component
	 * @return the component removed
	 */
	public static JComponent remove(String name) {
		return components.remove(name);
	}

	/**
	 * Returns a JComponent from ComponentManager.
	 *
	 * @param name of the component
	 * @return the component itself
	 */
	public static JComponent get(String name) {
		return components.get(name);
	}

	/**
	 * If false, all components in the GUI that contains actions (and that actions are conflicting)
	 * will be disabled. If true, all components are true again;.
	 *
	 * @param value true or false to enable or disable
	 */
	public static void enableConflictingButtons(boolean value) {
		JButton protecBtn = (JButton) get(CommandName.PROTECT.toString());
		JButton recoverBtn = (JButton) get(CommandName.RECOVER.toString());
		@SuppressWarnings("unchecked") // Ever will be a ComboBox
		JComboBox<String> listedDrives = (JComboBox<String>) get(
				CommandName.DRIVE_CHANGED_COMBOBOX.toString()
		);

		protecBtn.setEnabled(value);
		recoverBtn.setEnabled(value);
		listedDrives.setEnabled(value);
	}

	/**
	 * This method appends new messages in output view.
	 *
	 * @param text to be inserted
	 */
	public static void updateViewOutput(String text) {
		JTextArea outputView = (JTextArea) get(CommandName.OUTPUT_VIEW_TEXT_AREA.toString());
		JViewport viewPort = (JViewport) outputView.getParent();
		JScrollPane panel = (JScrollPane) viewPort.getParent();
		JScrollBar scrollBar = panel.getVerticalScrollBar();

		outputView.setText(outputView.getText() + text + "\n");
		scrollBar.setValue(scrollBar.getMaximum());
	}

	/**
	 * Clears the output view.
	 */
	public static void clearViewOutput() {
		JTextArea outputView = (JTextArea) get(CommandName.OUTPUT_VIEW_TEXT_AREA.toString());
		outputView.setText("");
	}

	/**
	 * Clears the progress bar.
	 */
	public static void clearProgressBar() {
		JProgressBar progressBar = (JProgressBar) get(CommandName.STATUS_PROGRESS_BAR.toString());
		progressBar.setValue(0);
	}

	/**
	 * Receives a increment value and increases the progress bar.
	 *
	 * @param increment
	 */
	public static void incrementProgressBar(int increment) {
		JProgressBar progressBar = (JProgressBar) ComponentManager.get(
				CommandName.STATUS_PROGRESS_BAR.toString()
		);
		progressBar.setValue(progressBar.getValue() + increment);
	}
}
