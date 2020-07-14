package br.com.edson.service;

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.edson.Model.AlunoResponsavel;
import br.com.edson.Model.Responsavel;

public class AcoesAlunoResponsavel implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager em;
	
	
	public AcoesAlunoResponsavel() {	}


	public void excluiResponsavel( AlunoResponsavel responsavel) {
		
	}

}
