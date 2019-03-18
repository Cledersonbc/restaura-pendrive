package br.edu.cleardrive.util;

import java.io.File;

public class DriveUtils {

	public static boolean validPath(File path) {
		if (path.isFile()) {
			return validFile(path);
		} else {
			return validDirectory(path);
		}
	}
	
	
	public static boolean nonValidPath(File path) {
		return !validPath(path);
	}
	
	
	public static int countRecoverableFiles(File root) {
		int sum = 0;
		
		if (validPath(root)) {
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
	
	
	public static boolean validFile(File file) {
		return file.canRead() && file.canWrite();
	}
	
	
	public static boolean validDirectory(File directory) {
		return directory.canRead() && directory.canWrite() && directory.canExecute();
	}
}
