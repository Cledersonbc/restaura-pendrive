package br.edu.cleardrive.command;

import java.io.File;

import javax.swing.JComboBox;

import br.edu.cleardrive.util.DriveUtils;
import br.edu.cleardrive.view.component.ComponentManager;
import br.edu.cleardrive.view.component.ComponentName;

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
	 * Executes the change in the ComboBox.
	 */
	@Override
	public void execute() {
		@SuppressWarnings("unchecked") // Ever will be a ComboBox
		JComboBox<String> listedDrives = (JComboBox<String>) ComponentManager.get(ComponentName.DRIVE_CHANGED_COMBOBOX.name());
		File[] roots = File.listRoots();
		listedDrives.removeAllItems();

		for (File root : roots) {
			if (DriveUtils.isValidPath(root) && isNonCDisk(root)) {
				listedDrives.addItem(root.toString());
			}
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
