package br.edu.cleardrive.util;

import java.util.Objects;

/**
 * This class is a utility for string operations.
 *
 * @author Clederson Cruz
 *
 * @since 2.0.0
 *
 */

public final class StringUtils {

	/**
	 * Private constructor.
	 *
	 * @throws AssertionError for convenience (this class is a utility, we don't
	 *                        need to instantiate it)
	 */
	private StringUtils() {
		throw new AssertionError("You can not instantiate " + this.getClass().getName());
	}

	/**
	 * This method gets a string and verify if the string is null or empty.
	 *
	 * @param str is a string to be compared
	 *
	 * @return true the string is null or empty
	 */
	public static boolean isNullOrEmpty(String str) {
		return Objects.isNull(str) || str.equals("");
	}
}
