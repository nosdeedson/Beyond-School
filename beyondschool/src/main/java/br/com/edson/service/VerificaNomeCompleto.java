package br.com.edson.service;

import java.io.Serializable;

public class VerificaNomeCompleto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public VerificaNomeCompleto() {}
	
	public static String verficaNome( String nome) {
		String[] partes;
		String nomeCorreto = "";
		partes = nome.split(" ");
		
		for (int i = 0; i < partes.length; i++) {
			
			char transformar = partes[i].charAt(0);
			char first = Character.toUpperCase(transformar);
			
			String t = Character.toString(transformar);
			String f = Character.toString(first);
			partes[i].replaceFirst(t, f);
			partes[i] = partes[i].replaceFirst(t, f);
			
			if( i < (partes.length - 1))
				nomeCorreto += partes[i]+" ";
			else
				nomeCorreto += partes[i];
		}
		
		return nomeCorreto;
	}

}
