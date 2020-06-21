package br.com.edson.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.edson.Model.Responsavel;
import br.com.edson.repository.ResponsaveisBD;

public class VerificaExisteResponsavel implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager em;
	
	@Inject
	private ResponsaveisBD responsaveisBD;

	public VerificaExisteResponsavel() {	}
	
	
	public Responsavel buscaResponsavel( String nomecompleto) {
		
		List< Responsavel> responsaveis = responsaveisBD.buscaTodos();
		
		for (Responsavel responsavel : responsaveis) {
			if(nomecompleto.equals(responsavel.getNomeCompleto())) {
				return responsavel;
			}
		}
		return null;
	}
	

}
