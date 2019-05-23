package br.edu.cleardrive.util;

import java.io.File;

/**
 * This class is a utility for main operations involving disks for this
 * application. As a high abstraction, it make easy visit, count and validate
 * disks files and directories.
 *
 * @author Clederson Cruz
 *
 * @since 2.0.0
 *
 */

public final class DriveUtils {

	/**
	 * Private constructor.
	 *
	 * @throws AssertionError for convenience (this class is a utility, we don't
	 *                        need to instantiate it)
	 */
	private DriveUtils() {
		throw new AssertionError("You can not instantiate " + this.getClass().getName());
	}

	/**
	 * Checks if a specified path is valid.
	 * 
	 * <p>
	 * If the path specify a file, a valid path for file should contains rw (6)
	 * permissions. But if the path specify a directory, a valid path for a
	 * directory should contains (d)rwx (7) permissions.
	 *
	 * @param path to be validated
	 *
	 * @return true if the path is valid
	 */
	public static boolean isValidPath(File path) {
		if (path.isFile()) {
			return isValidFile(path);
		} else {
			return isValidDirectory(path);
		}
	}

	/**
	 * Checks if a specified path is not valid.
	 *
	 * <p>
	 * This method is a negative form for <code>isValidPath()</code>.
	 *
	 * @param path of file to be verified
	 *
	 * @return true if the path is not valid
	 */
	public static boolean nonValidPath(File path) {
		return !isValidPath(path);
	}

	/**
	 * Count how many files are recoverable.
	 *
	 * <p>
	 * A recoverable file need to contains a valid path. This method counts files as
	 * well as directory. Note: Unix special files like "srwxr-xr-x" are ignored.
	 *
	 * @param root to be inspected
	 *
	 * @return number of recoverable files
	 */
	public static long countRecoverableFiles(File root) {
		long sum = 0;

		if (isValidPath(root)) {
			for (File path : root.listFiles()) {
				if (path.isFile()) {
					sum++;
				} else if (path.isDirectory()) {
					sum = sum + countRecoverableFiles(path) + 1;
				}
			}
		}

		return sum;
	}

	/**
	 * This method validate a file, checking if is readable and writable (rw, 6).
	 *
	 * @param file to be validated
	 *
	 * @return true if the specified file is valid
	 */
	public static boolean isValidFile(File file) {
		return file.canRead() && file.canWrite();
	}

	/**
	 * This method validate a directory, checking if is readable, writable and
	 * executable (rwx, 7).
	 *
	 * @param directory to be validated
	 *
	 * @return true if the specified directory is valid
	 */
	public static boolean isValidDirectory(File directory) {
		return directory.canRead() && directory.canWrite() && directory.canExecute();
	}
}
