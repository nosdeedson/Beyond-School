package br.com.edson.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import br.com.edson.Model.Aluno;

public class AlunosBD implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager em;
	
	@Inject
	public AlunosBD() {}
	
	public void atualizaAluno( Aluno aluno) {
		this.em.merge(aluno);
	}
	
	public List<Aluno> buscaAlunos(){
		try {
			TypedQuery<Aluno> alunos = this.em.createQuery("from Aluno", Aluno.class);
			return alunos.getResultList();
		} catch (PersistenceException e) {
			return null;
		}

	}
	
	public Aluno buscaAlunoPeloNome( String nome) {
		Aluno aluno;
		try {
			String sql = "select a from Aluno a where nomeCompleto = :nomeCompleto";
			aluno = this.em.createQuery(sql, Aluno.class).setParameter("nomeCompleto", nome).getSingleResult();
		} catch (PersistenceException e) {
			return null;
		}
		return aluno;
	}
	
	public Long salvarAluno(Aluno aluno) {
		this.em.merge(aluno);
		
		String sql = "select idPessoa from Aluno where nomeCompleto = :nomeCompleto";
		
		Long idAluno = this.em.createQuery(sql, Long.class).setParameter("nomeCompleto", aluno.getNomeCompleto()).getSingleResult();
		
		return idAluno;
	}
	
	/**
	 * método para salvar aluno quando o responsável faz o cadastro primeiro apenas o nome é necessário
	 * @param aluno
	 */
	public void salvarAlunosRespCadPrimerio( Aluno aluno) {
		this.em.merge(aluno);
	}
}
