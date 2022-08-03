package com.myprojects.cadclients.utils;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.Normalizer;

public abstract class StringUtils {

	public static String capitalise(String str) {
		return str.substring(0, 1).toUpperCase().concat(str.substring(1));
	}

	public static String unaccent(String str) {
		return Normalizer.normalize(str, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
	}

	public static String convertStackTraceToString(Throwable t) {
		StringWriter sw = new StringWriter();
		t.printStackTrace(new PrintWriter(sw));
		return sw.toString();
	}

	public static String clearCPF(String cpf) {
		return cpf.replaceAll("\\D", "");
	}

	public static String formatCPF(String cpf) {
		cpf = cpf.substring(0, 3)
				+ "." + cpf.substring(3, 6)
				+ "." + cpf.substring(6, 9)
				+ "-" + cpf.substring(9, 11);
		return cpf;
	}
}
