package br.com.edson.repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.swing.JOptionPane;

import br.com.edson.Model.Usuario;
import br.com.edson.service.NegocioException;

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
	

	public int excluirUser(Long id) {
		JOptionPane.showMessageDialog(null, "exluindo user resp");
		Usuario user = this.em.find(Usuario.class, id);
		
		try {
			this.em.remove(user);
			return 1;
		} catch ( PersistenceException e) {
			return 0;
		}
	}
	
	
	
	/**
	 * valida o usuário informado no login
	 * @param nomeUser
	 * @param senha
	 * @return
	 */
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
	
	public Usuario validaUserResetPassWord( String email, String nomeCompleto, Date niver, String tipoAcesso ){
		
		Usuario user = null;
		String sql = "select u from Usuario u where u.email= :email and u.pessoa.nomeCompleto= :nomeCompleto"
				+ " and u.pessoa.dataNascimento= :dataNascimento and u.tipoAcesso= :tipoAcesso";
		try {
			user = this.em.createQuery(sql, Usuario.class).setParameter("email", email).setParameter("nomeCompleto", nomeCompleto)
					.setParameter("nomeCompleto", nomeCompleto).setParameter("dataNascimento", niver)
					.setParameter("tipoAcesso", tipoAcesso).getSingleResult();
							
		} catch (PersistenceException e) {
			e.printStackTrace();
			return null;
		}
		return user;
	}
	
	public List<String> todasSenhas( ){
		
		String sql = "select senha from Usuario";
		try {
			TypedQuery<String>	senhas = this.em.createQuery(sql, String.class);
			return senhas.getResultList();
		} catch (PersistenceException e) {
			e.printStackTrace();
			return new ArrayList<String>();
		}
	
	}
	
	public Usuario porID( Long idPessoa) {
		return this.em.find(Usuario.class, idPessoa);
	}
	

}
