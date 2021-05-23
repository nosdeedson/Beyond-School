package br.com.edson.repository;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

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
	
	public Bimestre nextBimestre(Date date) {
		
		LocalDateTime fimAno = Instant.ofEpochMilli(date.getTime())
				.atZone(ZoneId.systemDefault())
				.toLocalDateTime().plusMonths(4L);
		
		Date fimSemestre = java.sql.Timestamp.valueOf(fimAno);
		
		String sql = "select b from Bimestre b where b.dataInicio between :data and :fimSemestre";
		Bimestre b = null;
		
		try {
			b = this.em.createQuery(sql, Bimestre.class)
					.setParameter("data", date)
					.setParameter("fimSemestre", fimSemestre)
					.setMaxResults(1)
					.getSingleResult();
			return b;
		}catch (PersistenceException e) {
			e.printStackTrace();
			return b;
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
