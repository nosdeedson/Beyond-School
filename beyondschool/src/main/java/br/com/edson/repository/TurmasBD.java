package br.com.edson.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.swing.JOptionPane;

import br.com.edson.Model.Turma;

public class TurmasBD implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private EntityManager em;

	@Inject
	public TurmasBD(EntityManager em) {
		this.em = em;
	}
	
	
	/**
	 * salvar turma código gerado pelo sistema, não é autoincremente.
	 * o código é um varchar de oito caracteres
	 * @param turma
	 */
	public void salvarTurma(Turma turma) {
		this.em.merge(turma);
	}
	
	public List<String> buscaCodigos(){
		
		TypedQuery<String> codigos = this.em.createQuery("select t.codigoTurma from Turma t ", String.class);
		return codigos.getResultList();
	}

}
