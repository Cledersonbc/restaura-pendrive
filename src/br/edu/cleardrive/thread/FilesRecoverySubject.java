package br.edu.cleardrive.thread;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import br.edu.cleardrive.model.ObservableData;
import br.edu.cleardrive.observer.Observer;
import br.edu.cleardrive.util.DriveUtils;

/**
 * FilesRecoverySubject is responsable to recover every file (if possible) of a drive.
 *
 * @author Clederson Cruz
 *
 * @since 1.0.0
 *
 */

public class FilesRecoverySubject extends Thread {

	/**
	 * Root path is a drive to be scanned for recovery process.
	 */
	private File root;

	/**
	 * Indicate that this thread is already running.
	 */
	private static boolean alive;

	/**
	 * State of the recovery are log in an ObservableData object.
	 */
	private ObservableData data = new ObservableData();

	/**
	 * Set of observers to be notified that ObservableData was changed.
	 */
	private Set<Observer> observers = new HashSet<>();

	/**
	 * Default constructor that initializes private fields.
	 *
	 * @throws NullPointerException if root or observer are null.
	 */
	public FilesRecoverySubject(File root, Observer observer) {
		if (Objects.isNull(root)) {
			throw new NullPointerException("Root can't be null");
		}
		if (Objects.isNull(observer)) {
			throw new NullPointerException("Observer can't be null");
		}

		this.registerObserver(observer);
		this.root = root;
	}

	/**
	 * Start this thread.
	 */
	@Override
	public void run() {
		long total = DriveUtils.countRecoverableFiles(root);
		this.data.setTotal(total);
		alive = true;
		walkAndRecover(root);
	}

	/**
	 * For each file in the root (that includes dirs or files) this method will be
	 * called. When this method is called, a file (or dir) is recovered.
	 *
	 * @param root of the files like C:, D:, E:...
	 */
	private void walkAndRecover(File rootFile) {
		File[] files = rootFile.listFiles();

		for (File file : files) {
			if (!alive) {
				break;
			}
			if (file.isFile()) {
				recover(file);
			} else if (file.isDirectory()) {
				recover(file);
				walkAndRecover(file);
			}
		}
	}

	/**
	 * Register an observer to be notified when a change in the state of the
	 * ObservableData occur.
	 *
	 * @param observer to be registered.
	 */
	public void registerObserver(Observer observer) {
		this.observers.add(observer);
	}

	/**
	 * Unregister from notifications of changes in the state of the ObservableData.
	 *
	 * @param observer to be unregistered.
	 */
	public void unregisterObserver(Observer observer) {
		this.observers.remove(observer);
	}

	/**
	 * This method stop all recovery actions.
	 */
	public static void stopRecovery() {
		alive = false;
	}

	/**
	 * The recovery processes consists in unhide a file, log changes in an
	 * ObservableData and notify observers.
	 *
	 * @param file to be unhide (file itself or directory).
	 */
	private void recover(File file) {
		Path fullPath = Paths.get(file.getAbsolutePath());

		try {
			Files.setAttribute(fullPath, "dos:hidden", false);
			this.data.incrementRecoveredFiles();
			this.data.setLastMessage(file.getAbsolutePath());
		} catch (IOException e) {
			this.data.incrementIgnoredFiles();
			this.data.setLastMessage("[IGN]: " + file.getAbsolutePath());
		}

		notifyObservers();
	}

	/**
	 * Notify observers to update their output representation of ObservableData.
	 */
	private void notifyObservers() {
		for (Observer observer : observers) {
			observer.update(this.data);
		}
	}
}
