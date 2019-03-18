package br.edu.cleardrive.util;

import java.util.Objects;

public class StringUtils {
	public static boolean isNullOrEmpty(String str) {
		return Objects.isNull(str) || str.equals("");
	}
}
