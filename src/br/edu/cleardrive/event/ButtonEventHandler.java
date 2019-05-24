package br.edu.cleardrive.event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import br.edu.cleardrive.command.Invoker;

/**
 * Client to invoke action commands for each component
 * that are registered on ComponentManager.
 *
 * @author Clederson Cruz
 *
 * @since 2.0.0
 */

public class ButtonEventHandler implements ActionListener {

	/**
	 * Gets an action command and execute it.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		Invoker invoker = new Invoker();
		String action = e.getActionCommand();
		invoker.executeAction(action);
	}

}
