package br.edu.cleardrive.command;

import br.edu.cleardrive.App;
import br.edu.cleardrive.ExecutionMode;
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

		if (FilesRecoverySubject.activeCount() > 0) {
			FilesRecoverySubject.stopRecovery();
		}

		if (App.getExecutionMode().equals(ExecutionMode.GUI)) {
			ComponentManager.enableConflictingButtons(true);
		} else if (App.getExecutionMode().equals(ExecutionMode.CLI)) {
			throw new UnsupportedOperationException("Operação não suportada via linha de comando.");
		}
	}
}
