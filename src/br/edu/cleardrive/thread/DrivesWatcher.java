package br.edu.cleardrive.thread;

import java.io.File;

import javax.swing.JComboBox;

import br.edu.cleardrive.command.CommandName;
import br.edu.cleardrive.util.DriveUtils;
import br.edu.cleardrive.view.component.ComponentManager;

/**
 * DrivesWatcher is responsable to watch available roots and update listed drives.
 *
 * @author Clederson Cruz
 *
 * @since 2.0.0
 *
 */

public class DrivesWatcher extends Thread {

	/**
	 * List of listed drives on menu.
	 */
	private JComboBox<String> listedDrives;

	/**
	 * Interval to watch and update menu options in ms.
	 */
	final private long INTERVAL = 500;

	/**
	 * Indicate that this thread is already running.
	 */
	private static boolean alive;

	/**
	 * Default constructor that initializes private fields.
	 */
	@SuppressWarnings("unchecked") // Ever will be a ComboBox
	public DrivesWatcher() {
		alive = true;
		listedDrives = (JComboBox<String>) ComponentManager.get(CommandName.DRIVE_CHANGED_COMBOBOX.toString());
	}

	/**
	 * Starts this thread.
	 */
	@Override
	public void run() {
		while (alive) {
			watchAndUpdate();
			try {
				Thread.sleep(INTERVAL);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Updates menu options when a new drive is connected or a existing drive is removed, accord to interval.
	 */
	private void watchAndUpdate() {
		int rootsLength = File.listRoots().length;
		int listedDrivesLength = listedDrives.getItemCount() + 1; // options + cDisk

		if (rootsLength != listedDrivesLength) {
			listedDrives.removeAllItems();

			for (File root : File.listRoots()) {
				if (DriveUtils.isValidPath(root) && DriveUtils.isNonCDisk(root)) {
					listedDrives.addItem(root.toString());
				}
			}
		}
	}
}