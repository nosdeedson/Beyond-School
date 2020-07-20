package br.com.edson.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

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
	
	public List<Comentario> porIdAvaliacao( Long idAvaliacao){
		String sql = "select c from Comentario c where c.avaliacao.idAvaliacao= :idAvaliacao order by c.avaliacao.idAvaliacao asc";
		try {
			TypedQuery<Comentario> comentarios = this.em.createQuery(sql, Comentario.class)
					.setParameter("idAvaliacao", idAvaliacao);
			return comentarios.getResultList();
		} catch (PersistenceException e) {
			e.printStackTrace();
			return null;
		}
	}

}
