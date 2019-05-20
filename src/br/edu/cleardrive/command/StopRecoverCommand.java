package br.edu.cleardrive.command;

import br.edu.cleardrive.thread.RecoveryFiles;
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
	 * Executes the command to stop the recovery of files.
	 */
	@Override
	public void execute() {
		ComponentManager.enableConflictingButtons(true);

		if (RecoveryFiles.activeCount() > 0) {
			RecoveryFiles.killAll();
		}
	}
}
