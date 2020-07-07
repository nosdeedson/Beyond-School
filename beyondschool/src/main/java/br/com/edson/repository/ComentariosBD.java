package br.com.edson.repository;

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import br.com.edson.Model.Comentario;
import br.com.edson.service.NegocioException;

public class ComentariosBD implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager em;
	
	@Inject
	public ComentariosBD() {	}



	public void salvarComentario( Comentario comentario) throws NegocioException {
		
		try {
			this.em.merge(comentario);
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new NegocioException("Falha ao salvar o comentario");
		}
		
	}

}
