package br.edu.cleardrive.model;

/**
 * This class represents the data produced by a subject to be sent to observers.
 *
 * @author Clederson Cruz
 *
 * @since 2.0.0
 *
 */

public class ObservableData {

	/**
	 * Total of files to be recovered if possible.
	 */
	private long total;

	/**
	 * Number of recovered files.
	 */
	private long recoveredFiles;

	/**
	 * Number of ignoredFiles.
	 */
	private long ignoredFiles;

	/**
	 * Last message about recovery state. Generally a path name of the last
	 * recovered file.
	 */
	private String lastMessage;

	/**
	 * Gets the total of files.
	 *
	 * @return total of files.
	 */
	public long getTotal() {
		return total;
	}

	/**
	 * Sets the total of files.
	 *
	 * @param total of files.
	 */
	public void setTotal(long total) {
		this.total = total;
	}

	/**
	 * Gets the total of recovered files.
	 *
	 * @return total of recovered files.
	 */
	public long getRecoveredFiles() {
		return recoveredFiles;
	}

	/**
	 * Increments for each recovered file by the subject.
	 */
	public void incrementRecoveredFiles() {
		this.recoveredFiles = this.recoveredFiles + 1;
	}

	/**
	 * Gets the total of ignored files.
	 *
	 * @return total of ignored files.
	 */
	public long getIgnoredFiles() {
		return ignoredFiles;
	}

	/**
	 * Increments for each ignored file by the subject.
	 */
	public void incrementIgnoredFiles() {
		this.ignoredFiles = this.ignoredFiles + 1;
	}

	/**
	 * Gets the last message sent by the subject. Basically a path name of the file,
	 * if recovered, or "IGN" + path name of the file, if ignored.
	 *
	 * @return the last message sent by subject.
	 */
	public String getLastMessage() {
		return lastMessage;
	}

	/**
	 * Sets the last message sent by the subject. Basically a path name of the file,
	 * if recovered, or "IGN" + path name of the file, if ignored.
	 *
	 * @param lastMessage from subject.
	 */
	public void setLastMessage(String lastMessage) {
		this.lastMessage = lastMessage;
	}

	/**
	 * Verifies if the recovery by the subject has finished looking the total of
	 * recoveredFiles + ignoredFiles.
	 *
	 * @return true if the recovery by the subject has finished.
	 */
	public boolean hasFinished() {
		return this.recoveredFiles + this.ignoredFiles == total;
	}

	/**
	 * Generates a finished message with status of the recovery.
	 *
	 * @return a finished message.
	 */
	public String getFinishedMessage() {
		StringBuilder builder = new StringBuilder();

		builder.append(System.lineSeparator() + System.lineSeparator());
		builder.append("Total de Arquivos: ");
		builder.append(this.recoveredFiles);
		builder.append(System.lineSeparator());
		builder.append("Total de arquivos ignorados: ");
		builder.append(this.ignoredFiles);

		return builder.toString();
	}

}
