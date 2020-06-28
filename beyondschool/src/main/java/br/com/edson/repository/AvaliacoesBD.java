package br.com.edson.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.swing.JOptionPane;

import br.com.edson.Model.Avaliacao;
import br.com.edson.service.NegocioException;

public class AvaliacoesBD implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager em;

	@Inject
	public AvaliacoesBD() {	}
	
	public void salvarAvaliacao( Avaliacao avaliacao) throws NegocioException {
		JOptionPane.showMessageDialog(null, avaliacao);
		try {
			this.em.merge(avaliacao);
		} catch (PersistenceException e) {
			throw new NegocioException("Falha ao salvar o aluno. Tente novamente.");
		}
		
	}

	public List<Avaliacao> buscarAvaliacoes() {
		TypedQuery<Avaliacao> avaliacoes = this.em.createQuery("select a from Avaliacao a "
				+ ", Bimestre b where a.bimestre= b.idBimestre and b.atual=true", Avaliacao.class);
		return avaliacoes.getResultList();
	}
	
	

}
