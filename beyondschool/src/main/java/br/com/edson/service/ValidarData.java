package br.com.edson.service;

import java.io.Serializable;
import java.text.ParseException;
import java.time.LocalDate;

import javax.swing.JOptionPane;

public class ValidarData implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public static void validarMaiorDeSeteAnos( String nascimento) throws ParseException, NegocioException {
		int data[] = stringToInt(nascimento);
		LocalDate niver = LocalDate.of(data[0], data[1], data[2]);
		LocalDate agora = LocalDate.now();
		agora = agora.minusYears(7);
		if(niver.isAfter(agora)) {
			throw new NegocioException("Você tem que ter mais de sete anos para se cadastrar");
		}
	
	}
	
	public static boolean validarMaiorIdade(String nascimento) throws NegocioException {
		int data[] = stringToInt(nascimento);
		
		LocalDate niver = LocalDate.of(data[0], data[1], data[2]);
		LocalDate agora = LocalDate.now();
		agora = agora.minusYears(18);
		if(niver.isAfter(agora)) {
			throw new NegocioException("Você tem que ter mais de 18 anos para se cadastrar.");
		}
		return true;
	}
	
	private static int[] stringToInt( String niver) throws NegocioException {
		
		String dia = niver.substring(0, 2);
		String mes = niver.substring(3, 5);
		String ano = niver.substring(6, 10);
		
		
		int d = Integer.parseInt(dia);
		int m = Integer.parseInt(mes);
		if( m <= 0 || m > 12)
			throw new NegocioException("O mês informado é inválido. Por favor informe o valor correto.");
		int a = Integer.parseInt(ano);
		
		confereDia(m, d, a);
		
		int data[] = {a, m, d};
		return data;
	}
	private static void confereDia(int mes, int dia, int a) throws NegocioException {
		switch (mes) {
		case 1:
			if(dia <= 0 || dia > 31)
				throw new NegocioException("O dia é inválido. Por favor informe o valor correto.");
			break;
		case 2:
			if( a % 4 == 0 ) {
				if( dia <= 0 || dia > 29 )
					throw new NegocioException("O dia é inválido. Por favor informe o valor correto.");
				if( dia <= 0 || dia > 28 )
					throw new NegocioException("O dia é inválido. Por favor informe o valor correto.");
					break;
			}	
		case 3:
			if( dia <= 0 || dia > 31 )
				throw new NegocioException("O dia é inválido. Por favor informe o valor correto.");
			break;
		case 4:
			if( dia <= 0 || dia > 30 )
				throw new NegocioException("O dia é inválido. Por favor informe o valor correto.");
			break;
		case 5:
			if( dia <= 0 || dia > 31 )
				throw new NegocioException("O dia é inválido. Por favor informe o valor correto.");
			break;
		case 6:
			if( dia <= 0 || dia > 30 )
				throw new NegocioException("O dia é inválido. Por favor informe o valor correto.");
			break;
		case 7:
			if( dia <= 0 || dia > 31 )
				throw new NegocioException("O dia é inválido. Por favor informe o valor correto.");
			break;
		case 8:
			if( dia <= 0 || dia > 31 )
				throw new NegocioException("O dia é inválido. Por favor informe o valor correto.");
			break;
		case 9:
			if( dia <= 0 || dia > 30 )
				throw new NegocioException("O dia é inválido. Por favor informe o valor correto.");
			break;
		case 10:
			if( dia <= 0 || dia > 31 )
				throw new NegocioException("O dia é inválido. Por favor informe o valor correto.");
			break;
		case 11:
			if( dia <= 0 || dia > 30 )
				throw new NegocioException("O dia é inválido. Por favor informe o valor correto.");
			break;
		case 12:
			if( dia <= 0 || dia > 31 )
				throw new NegocioException("O dia é inválido. Por favor informe o valor correto.");
			break;
		}
		
	}

}
