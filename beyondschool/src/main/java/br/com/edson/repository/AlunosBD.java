package br.com.edson.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.edson.Model.Aluno;

public class AlunosBD implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager em;
	
	@Inject
	public AlunosBD() {}
	
	
	public List<Aluno> buscaAlunos(){
		TypedQuery<Aluno> alunos = this.em.createQuery("from Aluno", Aluno.class);
		return alunos.getResultList();
	}
	
	/**
	 * método para salvar aluno quando o responsável faz o cadastro primeiro apenas o nome é necessário
	 * @param aluno
	 */
	public void salvarAlunosRespCadPrimerio( Aluno aluno) {
		this.em.merge(aluno);
	}
}
