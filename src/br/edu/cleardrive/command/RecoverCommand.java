package br.edu.cleardrive.command;

import javax.swing.JComboBox;

import br.edu.cleardrive.thread.RecoveryFiles;
import br.edu.cleardrive.util.StringUtils;
import br.edu.cleardrive.view.ComponentManager;
import br.edu.cleardrive.view.ComponentName;

public class RecoverCommand implements Command {

	@Override
	public void execute() {
		@SuppressWarnings("unchecked") // Ever will be a ComboBox
		JComboBox<String> listedDrives = (JComboBox<String>) ComponentManager
				.get(ComponentName.DRIVE_CHANGED_COMBOBOX.name());

		String drive = (String) listedDrives.getSelectedItem();
		if (StringUtils.isNullOrEmpty(drive)) {
			return;
		}

		ComponentManager.enableConflictingButtons(false);
		new RecoveryFiles().start();
	}

}
