package br.edu.cleardrive.command;

/**
 * All components names are registered here as an Enum.
 *
 * @author Clederson Cruz
 *
 * @since 2.0.0
 */
public enum CommandName {
	ABOUT() {
		@Override
		public String toString() {
			return super.toString().toLowerCase();
		}
	},
	HELP() {
		@Override
		public String toString() {
			return super.toString().toLowerCase();
		}
	},
	RECOVER() {
		@Override
		public String toString() {
			return super.toString().toLowerCase();
		}
	},
	PROTECT() {
		@Override
		public String toString() {
			return super.toString().toLowerCase();
		}
	},
	STOP() {
		@Override
		public String toString() {
			return super.toString().toLowerCase();
		}
	},
	VERSION() {
		@Override
		public String toString() {
			return super.toString().toLowerCase();
		}
	},
	DRIVE_CHANGED_COMBOBOX() {
		@Override
		public String toString() {
			return super.toString().toLowerCase();
		}
	},
	OUTPUT_VIEW_TEXT_AREA() {
		@Override
		public String toString() {
			return super.toString().toLowerCase();
		}
	},
	STATUS_PROGRESS_BAR() {
		@Override
		public String toString() {
			return super.toString().toLowerCase();
		}
	};
}
