package br.edu.cleardrive.command;

/**
 * All components names are registered here as an Enum. These names are commands
 * too.
 *
 * @author Clederson Cruz
 *
 * @since 2.0.0
 */
public enum CommandName {

	/**
	 * About command.
	 */
	ABOUT() {
		@Override
		public String toString() {
			return super.toString().toLowerCase();
		}
	},

	/**
	 * Help command.
	 */
	HELP() {
		@Override
		public String toString() {
			return super.toString().toLowerCase();
		}
	},

	/**
	 * Recover command.
	 */
	RECOVER() {
		@Override
		public String toString() {
			return super.toString().toLowerCase();
		}
	},

	/**
	 * Protect command.
	 */
	PROTECT() {
		@Override
		public String toString() {
			return super.toString().toLowerCase();
		}
	},

	/**
	 * Stop command.
	 */
	STOP() {
		@Override
		public String toString() {
			return super.toString().toLowerCase();
		}
	},

	/**
	 * Version command.
	 */
	VERSION() {
		@Override
		public String toString() {
			return super.toString().toLowerCase();
		}
	},

	/**
	 * Drive changed combobox command, it's the name of the combobox GUI with drives.
	 */
	DRIVE_CHANGED_COMBOBOX() {
		@Override
		public String toString() {
			return super.toString().toLowerCase();
		}
	},

	/**
	 * Output view text area command, it's the name of output GUI.
	 */
	OUTPUT_VIEW_TEXT_AREA() {
		@Override
		public String toString() {
			return super.toString().toLowerCase();
		}
	},

	/**
	 * Status progress bar command, it's the name of progress bar component itself.
	 */
	STATUS_PROGRESS_BAR() {
		@Override
		public String toString() {
			return super.toString().toLowerCase();
		}
	};
}
