package br.edu.cleardrive.observer;

import br.edu.cleardrive.view.component.ComponentManager;

/**
 * An GUI observer that updates your output according to subject's data.
 *
 * @author Clederson Cruz
 *
 * @since 2.0.0
 *
 */

public class GUIObserver implements Observer {

	/**
	 * <p>
	 * When receives an ObservableData, it updates your GUI. This process consists
	 * in increase the progress bar in one of two ways: maxIncrement, if the total
	 * of files to be recovered is less or equal than 100; minIncrement, otherwise.
	 *
	 * <p>
	 * After it increases progress bar, it updates output view and verify if the
	 * subject has finished the recovery to (re)enable GUI buttons.
	 *
	 * <p>
	 * Note: it's possible it sets maximumValue() in progressBar == totalOfFiles to
	 * be recovered and the increment will be 1 without be needed getMaxIncrement or
	 * getMinIncrement. But that method expects an int value and if the number of
	 * files to be recovered is bigger than Integer.MAX_VALUE, then an exception
	 * will occur. But with this implementation, progressBar will always be set with
	 * values 0 <= n <= 100 and an exception in increment will not occur.
	 *
	 * @param data for update output.
	 */
	@Override
	public void update(ObservableData data) {
		int increment;

		if (hasMoreThan100Files(data)) {
			increment = getMinIncrement(data);
		} else {
			increment = getMaxIncrement(data);
		}

		ComponentManager.incrementProgressBar(increment);
		ComponentManager.updateViewOutput(data.getLastMessage());

		if (data.hasFinished()) {
			ComponentManager.updateViewOutput(data.getFinishedMessage());
			ComponentManager.enableConflictingButtons(true);
		}
	}

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
	private int getMinIncrement(ObservableData data) {
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
	private int getMaxIncrement(ObservableData data) {
		return (int) Math.ceil(100.0 / data.getTotal());
	}

	/**
	 * Verify if the total of files are bigger than 100.
	 *
	 * @param data from subject.
	 *
	 * @return true if the total of files are bigger than 100.
	 */
	private boolean hasMoreThan100Files(ObservableData data) {
		return data.getTotal() > 100;
	}

}
