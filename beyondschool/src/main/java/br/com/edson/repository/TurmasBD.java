package br.com.edson.repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import br.com.edson.Model.Turma;
import br.com.edson.service.NegocioException;

public class TurmasBD implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager em;

	@Inject
	public TurmasBD() { }
	
	
	/**
	 * retorna o id do professor da turma ao qual o codigo recebido se refere
	 * @param codigo
	 * @return
	 */
	public Long buscaIdProfessor( String codigo) {
		String sql = "select t.professor.idPessoa from Turma t where t.codigoTurma= :codigoTurma";
		try {
			Long codigoProf = this.em.createQuery(sql, Long.class)
					.setParameter("codigoTurma", codigo).getSingleResult();
			return codigoProf;
		} catch ( PersistenceException e) {
			return null;
		}
		
	}
	
	/**
	 * verifica se o codigo gerado pela classe gerador de código no pacote service já existe
	 * @param codigo
	 * @return
	 */
	public boolean verificaCodigoGerado( String codigo){
		
		try {
			String cod = this.em.createQuery("select t.codigoTurma from Turma t where t.codigoTurma = :codigoTurma", String.class)
					.setParameter("codigoTurma", codigo).getSingleResult();
			if(!cod.equals(""))
				return true;
		} catch (PersistenceException e) {
			return false;
		}
		
		return false;
	}
	
	/**
	 * busca a turma pelo códgigo, não existe id do tipo long
	 * @param codigo
	 * @return
	 */
	public Turma buscaTurma( String codigo){
		Turma turma;
		try {
			String sql = "select t from Turma t where t.codigoTurma = :codigoTurma";
			turma = this.em.createQuery(sql, Turma.class).setParameter("codigoTurma", codigo).getSingleResult();
			
		} catch ( PersistenceException e) {
			return null;
		}
		
		
		return turma;
	}
	
	public List<Turma> todasTurmas(){
		try {			
			TypedQuery<Turma> turmas = this.em.createQuery("from Turma", Turma.class);
			return turmas.getResultList();
		} catch (PersistenceException e) {
			return new ArrayList<Turma>();
		}
	}
	
	/**
	 * salvar turma código gerado pelo sistema, não é autoincremente.
	 * o código é um varchar de oito caracteres
	 * @param turma
	 */
	public void salvarTurma(Turma turma) {
		this.em.merge(turma);
	}

	/**
	 * busca as turmas do professor dono do id
	 * @param idProfessor
	 * @return
	 */
	public List<Turma> turmasProfessor( Long idProfessor) {
		
		String sql = " select t from Turma t where t.professor.idPessoa= :idPessoa";
		
		try {
			TypedQuery<Turma> turmas = this.em.createQuery(sql, Turma.class)
					.setParameter("idPessoa", idProfessor);
			return  turmas.getResultList();
			
		} catch ( PersistenceException e) {
			return null;
		}

	}


	public void excluir(Turma turmaSerExcluida) throws NegocioException {
		
		try {
			this.em.remove(turmaSerExcluida);
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new NegocioException("Falha ao excluir turma.");
		}
		
	}

	/**
	 * 
	 * @param idPessoa
	 * @return
	 */

	public Turma buscaTurma(Long idPessoa) {
		Turma t = null;
		
		String sql = "select t from Turma t, Aluno a where "
				+ " a.idPessoa= :idPessoa and t.codigoTurma=a.turma.codigoTurma";
		
		try {
			t = this.em.createQuery(sql, Turma.class).setParameter("idPessoa", idPessoa)
					.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return t;
	}

}
