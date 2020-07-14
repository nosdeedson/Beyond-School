package br.com.edson.service;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.swing.JOptionPane;

import br.com.edson.Model.Bimestre;
import br.com.edson.repository.BimestresBD;

public class AtualizaBimestre implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Bimestre bimestre;
	
	@Inject
	private Bimestre next;
	
	@Inject
	private BimestresBD bimestresBD;
	
	@Inject
	public AtualizaBimestre() {}
	
	
	
	public void atualizaBimestre() throws NegocioException {
		
	    Date date = new Date();
	    try {
	    	
	    	bimestre = bimestresBD.buscarBimestreAtual();
	    	next = bimestresBD.nextBimestre(bimestre.getDataInicio());
		    
		    if( bimestre == null || next == null)
		    	throw new NegocioException("Falha ao atualizar o bimestre");
		    
		    if( date.after(bimestre.getDataFim())){
		    	bimestre.setAtual(false);
		    	next.setAtual(true);
		    	bimestresBD.salvarBimestre(bimestre);
		    	bimestresBD.salvarBimestre(next);
		    }
		} catch (Exception e) {
			throw new NegocioException("Falha ao atualizar o bimestre");
		}    
	    
	}
	
}
