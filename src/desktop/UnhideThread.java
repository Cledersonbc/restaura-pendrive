package desktop;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;

public class UnhideThread extends Thread {

	private JTextArea founds;
	private JButton recovery;
	private File path;
	private JProgressBar progressBar;
	private int maxStep;
	private static int step;
	private boolean running;

	public UnhideThread(JTextArea founds, File path, JProgressBar progressBar, JButton recovery) {
		this.recovery = recovery;
		this.progressBar = progressBar;
		this.path = path;
		this.founds = founds;
	}

	/**
	 * This method show hidden files and add its names to text area
	 * 
	 * @param root
	 *            path (Directory)
	 */
	private void unhide(File root) {
		if (validPath(root)) {
			for (File file : root.listFiles()) {
				try {
					Files.setAttribute(Paths.get(file.getAbsolutePath()), "dos:hidden", false);
					founds.setText(founds.getText() + file.getAbsolutePath() + "\n");
					step++;
					if (step == maxStep) {
						progressBar.setValue(progressBar.getValue() + 1);
						step = 0;
					}
					if (file.isDirectory()) {
						unhide(file);
					}
				} catch (IOException ex) {
					founds.setText(founds.getText() + "[ERRO] " + file.getAbsolutePath() + "\n");
				}
			}
		}
	}

	/**
	 * This method test if a path is valid
	 * 
	 * @param path
	 *            (a Directory)
	 * @return true if a path: exists, can be written and read.
	 */
	private boolean validPath(File path) {
		return path.canRead() && path.canWrite() && path.exists();
	}

	private int countFiles(File root) {
		int sum = 0;

		if (validPath(root)) {
			for (File file : root.listFiles()) {
				try {
					if (file.isDirectory()) {
						sum = sum + countFiles(file) + 1;
					} else {
						sum++;
					}
				} catch (Exception e) {
					// Exceptions
				}
			}
		}
		return sum;
	}

	@SuppressWarnings("deprecation")
	public void kill() {
		try {
			if (running) {
				stop();
				recovery.setEnabled(true);
				founds.setText(founds.getText() + "\n\n[Recuperação de arquivos interrompida.]");
				running = false;
			}
		} catch (Exception exp) {

		}
	}

	@Override
	public void run() {
		running = true;
		recovery.setEnabled(false);
		progressBar.setValue(0);
		int allFiles = countFiles(path);
		step = 0;
		maxStep = allFiles / 100;
		founds.setText("Restaurando arquivos da unidade \"" + path.getPath() + "\"\n\n");
		unhide(path);
		JOptionPane.showMessageDialog(null,
				"Os arquivos encontrados foram desocultados.\n"
						+ "Nota: o vírus ainda pode estar no pendrive e os arquivos que foram\n"
						+ "deletados não foram recuperados.");
		recovery.setEnabled(true);
	}
}
