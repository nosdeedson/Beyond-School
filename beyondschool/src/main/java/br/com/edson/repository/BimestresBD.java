package br.com.edson.repository;

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.EntityManager;

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
	
	
}
