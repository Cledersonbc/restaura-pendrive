package br.edu.cleardrive.view.event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import br.edu.cleardrive.command.Invoker;

public class ButtonEventHandler implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		Invoker invoker = new Invoker();
		String action = e.getActionCommand();
		invoker.executeAction(action);
	}

}
