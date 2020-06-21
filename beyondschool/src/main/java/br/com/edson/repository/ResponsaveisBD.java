package br.com.edson.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.edson.Model.Responsavel;

public class ResponsaveisBD implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager em;

	@Inject
	public ResponsaveisBD() {
	}
	
	public List<Responsavel> buscaTodos(){
		TypedQuery<Responsavel> responsaveis = em.createQuery("from Responsavel ", Responsavel.class);
		return responsaveis.getResultList();
	}
	
	public void salvarResponsavelCadastro(Responsavel responsavel) {
		
		this.em.merge(responsavel);		
		
	}
	
	

}
