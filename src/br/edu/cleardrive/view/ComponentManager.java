package br.edu.cleardrive.view;

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

public class ComponentManager {
	private static Map<String, JComponent> components = new HashMap<String, JComponent>();
	
	public static void register(String name, JComponent component) {
		components.put(name, component);
	}
	
	public static JComponent remove(String name) {
		return components.remove(name);
	}
	
	public static JComponent get(String name) {
		return components.get(name);
	}
	
	public static void enableConflictingButtons(boolean value) {
		JButton protecBtn = (JButton) get(ComponentName.PROTECT_BUTTON.toString());
		JButton recoverBtn = (JButton) get(ComponentName.RECOVER_BUTTON.toString());
		@SuppressWarnings("unchecked") // Ever will be a ComboBox
		JComboBox<String> listedDrives = (JComboBox<String>) get(
				ComponentName.DRIVE_CHANGED_COMBOBOX.toString()
		);
		
		protecBtn.setEnabled(value);
		recoverBtn.setEnabled(value);
		listedDrives.setEnabled(value);
	}
	
	public static void updateViewOutput(String text) {
		JTextArea outputView = (JTextArea) get(ComponentName.OUTPUT_VIEW_TEXT_AREA.toString());
		JViewport viewPort = (JViewport) outputView.getParent();
		JScrollPane panel = (JScrollPane) viewPort.getParent();
		JScrollBar scrollBar = panel.getVerticalScrollBar();

		outputView.setText(outputView.getText() + text + "\n");
		scrollBar.setValue(scrollBar.getMaximum());
	}
	
	public static void clearViewOutput() {
		JTextArea outputView = (JTextArea) get(ComponentName.OUTPUT_VIEW_TEXT_AREA.toString());
		outputView.setText("");
	}
	
	public static void fillFullProgressBar() {
		JProgressBar progressBar = (JProgressBar) get(ComponentName.STATUS_PROGRESS_BAR.toString());
		progressBar.setValue(100);
	}
	
	public static void incrementProgressBar(int increment) {
		JProgressBar progressBar = (JProgressBar) ComponentManager.get(
				ComponentName.STATUS_PROGRESS_BAR.toString()
		);
		progressBar.setValue(progressBar.getValue() + increment);
	}
}
