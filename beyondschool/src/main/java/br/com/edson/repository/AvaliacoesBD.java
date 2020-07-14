package br.com.edson.repository;

import java.io.Serializable;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.swing.JOptionPane;

import br.com.edson.Model.Aluno;
import br.com.edson.Model.Avaliacao;
import br.com.edson.service.NegocioException;

public class AvaliacoesBD implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager em;

	@Inject
	public AvaliacoesBD() {	}

	public void atualiza( Avaliacao ava) {
		this.em.merge(ava);
	}
	
	
	// talvez não esteja mais usando
	public List<Avaliacao> buscarAvaliacoes() {
		TypedQuery<Avaliacao> avaliacoes = this.em.createQuery("select a from Avaliacao a "
				+ ", Bimestre b where a.bimestre= b.idBimestre and b.atual=true", Avaliacao.class);
		return avaliacoes.getResultList();
	}
	
	
	
	public Avaliacao buscaComentarios(Long idAvaliacao){
		Avaliacao avaliacao;
		try {
			avaliacao = this.em.find(Avaliacao.class, idAvaliacao);
		} catch ( PersistenceException e) {
			return null;
		}
		return avaliacao;
	}
	
	public Avaliacao buscaPorIdAluno(Long id) {
		
		Avaliacao ava = null;
		String sql = "select ava from Avaliacao ava, Aluno a where ava.aluno.idPessoa= :idAluno order by ava.idAvaliacao desc";
		try {
			ava =	this.em.createQuery( sql, Avaliacao.class).setParameter("idAluno", id).setMaxResults(1).getSingleResult();
			if(ava != null)
				return ava;
			return null;
		} catch ( PersistenceException | IllegalArgumentException e) {
			e.printStackTrace();
			return null;
		}
	}

	public void salvarAvaliacao( Avaliacao avaliacao) throws NegocioException {
		
		try {
			this.em.persist(avaliacao);
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new NegocioException("Falha ao salvar o comentario");
		}
		
		
	}
}
