package br.com.edson.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

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
	public List<Avaliacao> buscarAvaliacoes( ) {
		TypedQuery<Avaliacao> avaliacoes = this.em.createQuery("select a from Avaliacao a "
				+ ", Bimestre b where a.bimestre= b.idBimestre and b.atual=true", Avaliacao.class);
		return avaliacoes.getResultList();
	}
	
//	faça uma busca cruzando com o bimestre atual
	
	public Avaliacao buscarAvaliacoesSemestreCorrente( Long idBimestre, Long idAluno){
		
		Avaliacao ava = null;
		String sql = "select a from Avaliacao a where a.aluno.idPessoa= :idPessoa"
				+ " and a.bimestre.idBimestre= :idBimestre";
		try {
			ava = this.em.createQuery(sql, Avaliacao.class).setParameter("idPessoa", idAluno)
					.setParameter("idBimestre", idBimestre).getSingleResult();
			return ava;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
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

	public List<Avaliacao> todasAvaliacoesAluno(Long idAluno) {
		String sql = "select a from Avaliacao a where a.aluno.idPessoa= :idPessoa order by a.idAvaliacao asc";
	
		try {
			TypedQuery<Avaliacao> avaliacoes = this.em.createQuery(sql, Avaliacao.class).setParameter("idPessoa", idAluno);
			return avaliacoes.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}//fim classe
