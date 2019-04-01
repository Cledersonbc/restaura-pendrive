package br.edu.cleardrive.command;

import br.edu.cleardrive.thread.RecoveryFiles;
import br.edu.cleardrive.view.component.ComponentManager;

public class StopRecoverCommand implements Command {

	@Override
	public void execute() {
		ComponentManager.enableConflictingButtons(true);

		if (RecoveryFiles.activeCount() > 0) {
			RecoveryFiles.killAll();
		}
	}
}
