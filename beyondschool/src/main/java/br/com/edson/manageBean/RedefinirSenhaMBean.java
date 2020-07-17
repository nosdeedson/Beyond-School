package br.com.edson.manageBean;

import java.io.Serializable;

import javax.enterprise.context.spi.Context;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

import br.com.edson.Model.Usuario;
import br.com.edson.repository.UsuariosBD;
import br.com.edson.service.GeradorHashSenha;
import br.com.edson.service.NegocioException;

@Named
@javax.faces.view.ViewScoped
public class RedefinirSenhaMBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Usuario user;
	
	@Inject
	private UsuariosBD usersBD;
	
	private String confirmeSenha;
	
	@Inject
	private EntityManager em;
	
	HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
	
	public void setaUser() {
		user = (Usuario) session.getAttribute("usuario");
	}

	public void redefinirSenha() throws NegocioException {
		EntityTransaction et = this.em.getTransaction();
		FacesContext context = FacesContext.getCurrentInstance();
		
		if(!confirmeSenha.equals(user.getSenha()))
			throw new NegocioException("Os valores não conferem.");
		
		user.setSenha(GeradorHashSenha.geradorHashPassWord(user.getSenha()));
		
		try {
			et.begin();
				usersBD.salvarUser(user);
			et.commit();
			context.addMessage(null, new FacesMessage("Senha alterada com sucesso!!"));
		} catch (Exception e) {
			et.rollback();
			e.printStackTrace();
			FacesMessage msg = new FacesMessage(e.getMessage());
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, msg);
		}
		
	}
	
	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}

	public String getConfirmeSenha() {
		return confirmeSenha;
	}

	public void setConfirmeSenha(String confirmeSenha) {
		this.confirmeSenha = confirmeSenha;
	}

	
	
	
}
