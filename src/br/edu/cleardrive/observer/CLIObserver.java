package br.edu.cleardrive.observer;

import br.edu.cleardrive.model.ObservableData;

public class CLIObserver extends Observer {

	@Override
	public void update(ObservableData data) {
		int increment;

		if (super.hasMoreThan100Files(data)) {
			increment = super.getMinIncrement(data);
		} else {
			increment = super.getMaxIncrement(data);
		}

		System.out.println(String.format("[%d%%] %s%s", increment, data.getLastMessage(), System.lineSeparator()));

		if (data.hasFinished()) {
			System.out.println(data.getFinishedMessage());
		}
	}

}
