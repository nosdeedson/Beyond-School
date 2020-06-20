package br.com.edson.repository;

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.edson.Model.Usuario;

public class UsuariosBD implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager em;
	
	@Inject
	public UsuariosBD() {	}
	
	
	/**
	 * salva usuario
	 * @param user
	 */
	public void salvarUser( Usuario user) {
		this.em.merge(user);
	}

}
