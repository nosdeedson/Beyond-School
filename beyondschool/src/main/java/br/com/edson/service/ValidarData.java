package br.com.edson.service;

import java.io.Serializable;
import java.text.ParseException;
import java.time.LocalDate;

public class ValidarData implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public static boolean validarData( String nascimento) throws ParseException, NegocioException {
		
		String dia = nascimento.substring(0, 2);
		String mes = nascimento.substring(3, 5);
		String ano = nascimento.substring(6, 10);
		
		
		int d = Integer.parseInt(dia);
		int m = Integer.parseInt(mes);
		int a = Integer.parseInt(ano);
		
		LocalDate niver = LocalDate.of(a, m, d);
		LocalDate agora = LocalDate.now();
		agora = agora.minusYears(7);
		if(niver.isAfter(agora)) {
			throw new NegocioException("Você tem que ter mais de sete anos para se cadastrar");
		}

		return true;
	}

}
