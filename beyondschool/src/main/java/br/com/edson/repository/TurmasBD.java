package br.com.edson.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.swing.JOptionPane;

import br.com.edson.Model.Funcionario;
import br.com.edson.Model.Turma;
import br.com.edson.service.NegocioException;

public class TurmasBD implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager em;

	@Inject
	public TurmasBD() {
	}
	
	public Long buscaIdProfessor( String codigo) {
		Turma t = new Turma();
		try {
			t = em.createQuery(" from Turma t where t.codigoTurma = :codigoTurma", Turma.class)
					.setParameter("codigoTurma", codigo).getSingleResult();
		} catch ( PersistenceException e) {
			return null;
		}
		
		return t.getProfessor().getIdPessoa();
	}
	
	
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
	
	/**
	 * salvar turma código gerado pelo sistema, não é autoincremente.
	 * o código é um varchar de oito caracteres
	 * @param turma
	 */
	public void salvarTurma(Turma turma) {
		this.em.merge(turma);
	}
	
	


}
