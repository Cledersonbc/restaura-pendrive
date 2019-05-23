package br.edu.cleardrive.observer;

/**
 * An observer that updates your output according to subject's data.
 *
 * @author Clederson Cruz
 *
 * @since 2.0.0
 *
 */

public interface Observer {

	/**
	 * When receives a ObservableData, it updates your output that can be a GUI
	 * (Graphical User Interface) or CLI (Command Line Interface).
	 *
	 * @param data for update output.
	 */
	public void update(ObservableData data);
}
