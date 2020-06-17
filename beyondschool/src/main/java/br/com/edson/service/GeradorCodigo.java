package br.com.edson.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.inject.Inject;

import br.com.edson.repository.TurmasBD;

public class GeradorCodigo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private TurmasBD turmasBD;
	
	private List<String> codigos = new ArrayList<String>();

	public GeradorCodigo() { }
	
	public String gerarCodigoTurma() {
		String codigo = "";
		int sorteio = 0;
		int cont = 0;
		
		while ( cont < 8 ) {
			
			Random r = new Random();
			sorteio = r.nextInt(26) + 65 ;
			codigo +=  new Character((char)sorteio).toString();
			cont++;
			sorteio = r.nextInt(26) + 97;
			codigo +=  new Character((char)sorteio).toString();
			cont++;
			if( cont == 2 || cont == 5 ) {
				sorteio = r.nextInt(10) + 48;
				codigo +=  new Character((char)sorteio).toString();
				cont++;
			}
			
		}
		
		codigos = turmasBD.buscaCodigos();
		
		for (String codTurma : codigos) {
			if(codTurma.equals(codigo)) {
				this.gerarCodigoTurma();
			}
		}
		
		
		return codigo;
	}
	


}
