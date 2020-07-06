package br.com.edson.manageBean;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.PersistenceException;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

import br.com.edson.Model.Usuario;
import br.com.edson.repository.UsuariosBD;
import br.com.edson.service.NegocioException;

@Named
@javax.faces.view.ViewScoped
public class LoginMBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Usuario user;
	
	@Inject
	private UsuariosBD usersBD;
	
	private String nomeUser = "joao.silva4";
	
	private String senha = "123123123";

	public String login() throws NegocioException {
		

		FacesContext context = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);

		try {
			user = usersBD.ValidaUsuarioLogin( nomeUser, senha);
			
			if(user == null)
				throw new NegocioException("Nome de usuário/senha inválido.");
			
			switch (user.getTipoAcesso()) {
			case "admin":
				session.setAttribute("usuario", user);
				return "/APP/telaAdmin?faces-redirect=true";
			case "aluno":
				session.setAttribute("usuario", user);
				return "/APP/telaAluno?faces-redirect=true";
			case "professor":
				session.setAttribute("usuario", user);
				return "/APP/listaTurmas?faces-redirect=true";
			case "responsavel":
				session.setAttribute("usuario", user);
				return "/APP/telaPai?faces-redirect=true";
			}
			
			
		} catch (NegocioException | PersistenceException e) {
			e.printStackTrace();
			FacesMessage msg = new FacesMessage( "Usuário/senha Inválido");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, msg);
		}
		
		return null;
		
	}
	
	public String logOut() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "/public/index?faces-redirect=true";
	}
	
	//getters and setters
	public String getNomeUser() {
		return nomeUser;
	}

	public void setNomeUser(String nomeUser) {
		this.nomeUser = nomeUser;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	
	
	

}
