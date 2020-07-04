package br.com.edson.repository;

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.swing.JOptionPane;

import br.com.edson.Model.Bimestre;

public class BimestresBD implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager em;

	@Inject
	public BimestresBD() {}
	
	public void salvarBimestre(Bimestre bimestre) {
		
		this.em.merge(bimestre);
	}

	public Bimestre buscarBimestreAtual() {
		
		String sql = "select b from Bimestre b where b.atual= true ";
		
		try {
			Bimestre b = this.em.createQuery(sql, Bimestre.class)
					.getSingleResult();
			return b;
		} catch (PersistenceException e) {
			return null;
		}
	}
	
	public Bimestre porId( Long id) {
		try {
			return this.em.find(Bimestre.class, id);
		} catch ( PersistenceException e) {
			return null;
		}
		
	}
	
}
