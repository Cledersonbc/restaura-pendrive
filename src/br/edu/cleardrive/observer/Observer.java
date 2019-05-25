package br.edu.cleardrive.observer;

import br.edu.cleardrive.model.ObservableData;

/**
 * An observer that updates your output according to subject's data.
 *
 * @author Clederson Cruz
 *
 * @since 2.0.0
 *
 */

public abstract class Observer {

	/**
	 * When receives a ObservableData, it updates your output that can be a GUI
	 * (Graphical User Interface) or CLI (Command Line Interface).
	 *
	 * @param data for update output.
	 */
	public abstract void update(ObservableData data);

	/**
	 * <p>
	 * Gets a min increment, that can be one or zero. This approach is good if the
	 * number of total files is greater than or equal to 100. Else, limit will
	 * always be 0 and a division by zero occur.
	 *
	 * <p>
	 * Logic behind: 1 increment after X files. These "X files" are named "limit".
	 *
	 * @param data from subject.
	 *
	 * @return min increment.
	 */
	protected int getMinIncrement(ObservableData data) {
		long recovered = data.getRecoveredFiles();
		long ignored = data.getIgnoredFiles();
		long limit = (long) (data.getTotal() / 100.0);
		int increment = (int) ((recovered + ignored) % limit) == 0 ? 1 : 0;

		return increment;

	}

	/**
	 * <p>
	 * Gets a max increment, that can be 1 <= n <= 100. This approach is good if the
	 * number of total files is less than or equal to 100. Else, increment will
	 * always be 1 and the progress bar will be filled without before recover action
	 * and output messages has been finished.
	 *
	 * <p>
	 * Logic behind: X increment for each file. This "X" increment is a "limit" in
	 * getMinIncrement.
	 *
	 * @param data from subject.
	 *
	 * @return max increment.
	 */
	protected int getMaxIncrement(ObservableData data) {
		return (int) Math.ceil(100.0 / data.getTotal());
	}

	/**
	 * Verify if the total of files are bigger than 100.
	 *
	 * @param data from subject.
	 *
	 * @return true if the total of files are bigger than 100.
	 */
	protected boolean hasMoreThan100Files(ObservableData data) {
		return data.getTotal() > 100;
	}

	/**
	 * Returns an increment based on progress status of the ObservableData.
	 *
	 * @param data from subject.
	 *
	 * @return an increment.
	 */
	protected int getIncrement(ObservableData data) {
		if (hasMoreThan100Files(data)) {
			return this.getMinIncrement(data);
		} else {
			return this.getMaxIncrement(data);
		}
	}
}
