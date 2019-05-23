package br.edu.cleardrive.command;

import java.io.File;

import javax.swing.JComboBox;

import br.edu.cleardrive.observer.GUIObserver;
import br.edu.cleardrive.observer.Observer;
import br.edu.cleardrive.thread.FilesRecoverySubject;
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
		JComboBox<String> listedDrives = (JComboBox<String>) ComponentManager.get(
				ComponentName.DRIVE_CHANGED_COMBOBOX.name()
		);

		String drive = (String) listedDrives.getSelectedItem();
		if (StringUtils.isNullOrEmpty(drive)) {
			return;
		}

		startRecover(drive);
	}

	/**
	 * The process to start the action to recover consists in disable some GUI
	 * buttons, clear screen and progress bar, than create a thread that starts the
	 * recovery.
	 *
	 * @param drive is the root path to be recovered.
	 */
	private void startRecover(String drive) {
		File root = new File(drive);
		Observer observer = new GUIObserver();

		ComponentManager.enableConflictingButtons(false);
		ComponentManager.clearViewOutput();
		ComponentManager.clearProgressBar();

		new FilesRecoverySubject(root, observer).start();
	}

}
