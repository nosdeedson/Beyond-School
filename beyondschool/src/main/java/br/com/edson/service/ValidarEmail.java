package br.com.edson.service;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidarEmail implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public static boolean isValidEmail(String email) throws NegocioException {
		
		String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
		Pattern padrao = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
		Matcher match = padrao.matcher(email);
		if(!match.matches()) {
			throw new NegocioException("Email inválido");
		}
		return true;
	}

}
