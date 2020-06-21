package br.com.edson.repository;

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.edson.Model.AlunoResponsavel;

public class AlunosResponsaveisBD implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager em;

	@Inject
	public AlunosResponsaveisBD() {	}
	
	public void salvarAlunoResponsavel( AlunoResponsavel alunoResponsavel) {
		this.em.merge(alunoResponsavel);
	}
	
	

}
