package br.com.edson.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.edson.Model.Avaliacao;
import br.com.edson.Model.Bimestre;
import br.com.edson.repository.BimestresBD;

public class RegistrarAvaliacao implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private BimestresBD bimestreBD;
	
	@Inject
	private Bimestre bimestre;
	
	@Inject	
	public RegistrarAvaliacao() { }


	public void salvarAvaliacao(Avaliacao avaliacao) throws NegocioException{
		
		// busque o bimestre vigente
		
		
	}
}
