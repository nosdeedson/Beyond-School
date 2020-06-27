package br.com.edson.repository;

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.swing.JOptionPane;

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
	
	public Long buncaUltimoUser() {
		
		String sql = "select max(idUsuario) from Usuario";
		
		Long id = this.em.createQuery(sql, Long.class).getSingleResult();
		
		return id;
	}


	public  Usuario ValidaUsuarioLogin(String nomeUser, String senha) {
		Usuario user = null;
		String sql = "select u from Usuario u where u.nomeUsuario= :nomeUsuario and u.senha= :senha";
		
		try {
			user = this.em.createQuery(sql, Usuario.class).setParameter("nomeUsuario", nomeUser)
					.setParameter("senha", senha).getSingleResult();
			return user;
		} catch (PersistenceException e) {
			return user;
		}
		
	}

}
