package br.com.edson.repository;

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import br.com.edson.Model.Avaliacao;
import br.com.edson.service.NegocioException;

public class AvaliacoesBD implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private EntityManager em;

	@Inject
	public AvaliacoesBD() {	}
	
	public void salvarAvaliacao( Avaliacao avaliacao) throws NegocioException {
		try {
			this.em.merge(avaliacao);
		} catch (PersistenceException e) {
			throw new NegocioException("Falha ao salvar o aluno. Tente novamente.");
		}
		
	}
	
	

}
