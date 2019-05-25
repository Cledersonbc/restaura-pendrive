package br.edu.cleardrive.observer;

import br.edu.cleardrive.model.ObservableData;

/**
 * An CLI observer that updates your output according to subject's data.
 *
 * @author Clederson Cruz
 *
 * @since 2.0.0
 *
 */

public class CLIObserver extends Observer {

	/**
	 * This field store the progress of recovery.
	 */
	private int statusProgress = 0;

	/**
	 * <p>
	 * When receives an ObservableData, it updates your CLI. This process consists
	 * in increase the value to be outputted in one of two ways: maxIncrement, if
	 * the total of files to be recovered is less or equal than 100; minIncrement,
	 * otherwise.
	 *
	 * <p>
	 * If the recovery process has finished, an output message will be sent.
	 *
	 * @param data for update output.
	 */
	@Override
	public void update(ObservableData data) {
		int increment = super.getIncrement(data);

		this.statusProgress += increment;

		System.out.println(String.format("[%d%%] %s", this.statusProgress, data.getLastMessage()));

		if (data.hasFinished()) {
			System.out.println(data.getFinishedMessage());
		}
	}

}
