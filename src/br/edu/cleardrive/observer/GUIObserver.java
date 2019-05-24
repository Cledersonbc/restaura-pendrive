package br.edu.cleardrive.observer;

import br.edu.cleardrive.model.ObservableData;
import br.edu.cleardrive.view.component.ComponentManager;

/**
 * An GUI observer that updates your output according to subject's data.
 *
 * @author Clederson Cruz
 *
 * @since 2.0.0
 *
 */

public class GUIObserver extends Observer {

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

		if (super.hasMoreThan100Files(data)) {
			increment = super.getMinIncrement(data);
		} else {
			increment = super.getMaxIncrement(data);
		}

		ComponentManager.incrementProgressBar(increment);
		ComponentManager.updateViewOutput(data.getLastMessage());

		if (data.hasFinished()) {
			ComponentManager.updateViewOutput(data.getFinishedMessage());
			ComponentManager.enableConflictingButtons(true);
		}
	}

}
