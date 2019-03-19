package br.edu.cleardrive.thread;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.JComboBox;

import br.edu.cleardrive.util.DriveUtils;
import br.edu.cleardrive.view.ComponentManager;
import br.edu.cleardrive.view.ComponentName;

public class RecoveryFiles extends Thread {

	private int recoverableFiles;
	private int fileCounter;
	private final int LIMIT_FILE_COUNTER;
	private final int PROGRESS_INCREMENT_NUMBER;
	private int ignoredFiles;
	private File root;
	private boolean exit = false;

	public RecoveryFiles() {
		super();

		@SuppressWarnings("unchecked") // Ever will be a ComboBox
		JComboBox<String> listedDrives = (JComboBox<String>) ComponentManager
				.get(ComponentName.DRIVE_CHANGED_COMBOBOX.name());

		root = new File((String) listedDrives.getSelectedItem());
		recoverableFiles = DriveUtils.countRecoverableFiles(root);
		fileCounter = 0;
		ignoredFiles = 0;
		LIMIT_FILE_COUNTER = (int) Math.ceil(recoverableFiles / 100.0);
		PROGRESS_INCREMENT_NUMBER = (int) Math.ceil(1.0 / recoverableFiles * 100.0);
	}

	@Override
	public void run() {
		ComponentManager.clearViewOutput();
		tryRecover(root);
		ComponentManager.enableConflictingButtons(true);
	}

	private void tryRecover(File root) {

		if (DriveUtils.isValidPath(root)) {
			for (File path : root.listFiles()) {
				if (exit) {
					return;
				}

				try {
					Path fullPath = Paths.get(path.getAbsolutePath());
					if (DriveUtils.nonValidPath(path)) {
						ignoredFiles++;
						ComponentManager.updateViewOutput("[IGN]: " + fullPath.toString());
						continue;
					}

					Files.setAttribute(fullPath, "dos:hidden", false);
					updateProgress();
					ComponentManager.updateViewOutput(fullPath.toString());
					if (path.isDirectory()) {
						tryRecover(path);
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		ComponentManager.updateViewOutput("\n\nTotal de Arquivos: " + recoverableFiles);
		ComponentManager.updateViewOutput("Total de arquivos ignorados: " + ignoredFiles);
		ComponentManager.fillFullProgressBar();
	}

	public void stopThread() {
		exit = true;
	}

	private void updateProgress() {
		fileCounter++;

		if (fileCounter == LIMIT_FILE_COUNTER) {
			fileCounter = 0;
			ComponentManager.incrementProgressBar(PROGRESS_INCREMENT_NUMBER);
		}
	}

}
