package br.edu.cleardrive.command;

import java.io.File;

import javax.swing.JComboBox;

import br.edu.cleardrive.util.DriveUtils;
import br.edu.cleardrive.view.component.ComponentManager;
import br.edu.cleardrive.view.component.ComponentName;

public class DriveChangeCommand implements Command {

	@Override
	public void execute() {
		@SuppressWarnings("unchecked") // Ever will be a ComboBox
		JComboBox<String> listedDrives = (JComboBox<String>) ComponentManager.get(ComponentName.DRIVE_CHANGED_COMBOBOX.name());
		File[] roots = File.listRoots();

		listedDrives.removeAllItems();
		for (File root : roots) {
			String cDisk = "C:\\";

			if (DriveUtils.isValidPath(root) && !root.toString().equals(cDisk)) {
				listedDrives.addItem(root.toString());
			}
		}
	}

}
