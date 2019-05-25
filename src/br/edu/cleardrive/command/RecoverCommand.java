package br.edu.cleardrive.command;

import java.io.File;

import javax.swing.JComboBox;

import br.edu.cleardrive.App;
import br.edu.cleardrive.ExecutionMode;
import br.edu.cleardrive.observer.CLIObserver;
import br.edu.cleardrive.observer.GUIObserver;
import br.edu.cleardrive.observer.Observer;
import br.edu.cleardrive.thread.FilesRecoverySubject;
import br.edu.cleardrive.util.DriveUtils;
import br.edu.cleardrive.util.StringUtils;
import br.edu.cleardrive.view.component.ComponentManager;

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

		if (App.getExecutionMode().equals(ExecutionMode.CLI)) {
			cliCommand();
		} else if (App.getExecutionMode().equals(ExecutionMode.GUI)) {
			guiCommand();
		}
	}

	/**
	 * Recovers through CLI mode.
	 *
	 * @throws IllegalArgumentException if the drive or syntax are incorrectly.
	 */
	private void cliCommand() {
		String args[] = App.getLatestArgs();
		String command = args[0];

		if (args.length > 2 || args.length == 1) {
			throw new IllegalArgumentException("Sintáxe inválida para \"" + command + "\".");
		}

		String letter = args[1];
		String drive = String.format("%s:\\", letter.toUpperCase());
		if (DriveUtils.nonValidPath(new File(drive))) {
			throw new IllegalArgumentException("Unidade \"" + drive + "\" não encontrada.");
		}

		Observer observer = new CLIObserver();
		startRecover(drive, observer);
	}

	/**
	 * Recovers through GUI mode.
	 */
	private void guiCommand() {
		@SuppressWarnings("unchecked") // It's a ComboBox
		JComboBox<String> listedDrives = (JComboBox<String>) ComponentManager
				.get(CommandName.DRIVE_CHANGED_COMBOBOX.toString());

		String drive = (String) listedDrives.getSelectedItem();
		if (StringUtils.isNullOrEmpty(drive)) {
			return;
		}

		ComponentManager.enableConflictingButtons(false);
		ComponentManager.clearViewOutput();
		ComponentManager.clearProgressBar();

		Observer observer = new GUIObserver();
		startRecover(drive, observer);
	}

	/**
	 * The process to start the action to recover consists in create a thread that
	 * starts the recovery itself.
	 *
	 * @param drive is the root path to be recovered.
	 *
	 * @param observer to be notified about the recovery status.
	 */
	private void startRecover(String drive, Observer observer) {
		File root = new File(drive);

		new FilesRecoverySubject(root, observer).start();
	}

}
