package br.edu.cleardrive.command;

import javax.swing.JComboBox;

import br.edu.cleardrive.thread.RecoveryFiles;
import br.edu.cleardrive.util.StringUtils;
import br.edu.cleardrive.view.component.ComponentManager;
import br.edu.cleardrive.view.component.ComponentName;

/**
 * Command to recover files.
 *
 * @author Clederson Cruz
 *
 * @since 2.0.0
 *
 */

public class RecoverCommand implements Command {

	/**
	 * Executes a command to recover.
	 */
	@Override
	public void execute() {
		@SuppressWarnings("unchecked") // It's a ComboBox
		JComboBox<String> listedDrives = (JComboBox<String>) ComponentManager
				.get(ComponentName.DRIVE_CHANGED_COMBOBOX.name());

		String drive = (String) listedDrives.getSelectedItem();
		if (StringUtils.isNullOrEmpty(drive)) {
			return;
		}

		startRecover();
	}

	/**
	 * Starts the action to recover.
	 */
	private void startRecover() {
		//ComponentManager.enableConflictingButtons(false); //disable GUI Buttons
		new RecoveryFiles().start();
	}

}
