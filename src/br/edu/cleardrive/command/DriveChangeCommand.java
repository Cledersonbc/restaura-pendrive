package br.edu.cleardrive.command;

import java.io.File;

import javax.swing.JComboBox;

import br.edu.cleardrive.App;
import br.edu.cleardrive.ExecutionMode;
import br.edu.cleardrive.util.DriveUtils;
import br.edu.cleardrive.view.component.ComponentManager;

/**
 * Command to change listed drives.
 *
 * @author Clederson Cruz
 *
 * @since 2.0.0
 *
 */

public class DriveChangeCommand implements Command {

	/**
	 * Executes the change in the ComboBox. This operation is unsupported by CLI mode.
	 *
	 * @throws UnsupportedOperationException if this command is called by CLI mode.
	 */
	@Override
	public void execute() {

		if (App.getExecutionMode().equals(ExecutionMode.GUI)) {
			@SuppressWarnings("unchecked") // Ever will be a ComboBox
			JComboBox<String> listedDrives = (JComboBox<String>) ComponentManager
					.get(CommandName.DRIVE_CHANGED_COMBOBOX.name());
			File[] roots = File.listRoots();
			listedDrives.removeAllItems();

			for (File root : roots) {
				if (DriveUtils.isValidPath(root) && isNonCDisk(root)) {
					listedDrives.addItem(root.toString());
				}
			}
		} else if (App.getExecutionMode().equals(ExecutionMode.CLI)) {
			throw new UnsupportedOperationException("Operação não suportada via linha de comando.");
		}

	}

	/**
	 * Verifies if the disk is C://.
	 *
	 * @param root disk
	 *
	 * @return true if the disk is not C://
	 */
	private boolean isNonCDisk(File root) {
		String cDisk = "C:\\";
		return !root.toString().equals(cDisk);
	}

}
