package br.edu.cleardrive.command;

import br.edu.cleardrive.thread.FilesRecoverySubject;
import br.edu.cleardrive.view.component.ComponentManager;

/**
 * Command to stop all threads that are recovering files.
 *
 * @author Clederson Cruz
 *
 * @since 2.0.0
 *
 */

public class StopRecoverCommand implements Command {

	/**
	 * Executes the command to stop the recovery of files if exists a recovery
	 * thread executing.
	 */
	@Override
	public void execute() {
		ComponentManager.enableConflictingButtons(true);

		if (FilesRecoverySubject.activeCount() > 0) {
			FilesRecoverySubject.stopRecovery();
		}
	}
}
