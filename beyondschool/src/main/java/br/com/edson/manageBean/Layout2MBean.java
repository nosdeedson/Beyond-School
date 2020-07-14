package br.com.edson.manageBean;

import java.io.Serializable;

import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

import br.com.edson.Model.Usuario;

@Named
@javax.faces.view.ViewScoped
public class Layout2MBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private boolean flagBotaoVoltar = false;

	@Inject
	private Usuario user;
	HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
	
	public void mostraVoltar() {
		
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		user = (Usuario) session.getAttribute("usuario");
		if(user.getTipoAcesso().equals("admin") || user.getTipoAcesso().equals("professor"))
			flagBotaoVoltar =true;
	}
	
	public String voltar() {
		
		switch (user.getTipoAcesso()) {
		case "admin":
			return "/APP/telaAdmin?faces-redirect=true";
		case "aluno":
			return "/APP/telaAluno?faces-redirect=true";
		case "professor":
			return "/APP/listaTurmas?faces-redirect=true";
		case "responsavel":
			return "/APP/telaPai?faces-redirect=true";
		}
		
		return "/public/index?faces-redirect=true";
	}



	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}

	public boolean isFlagBotaoVoltar() {
		return flagBotaoVoltar;
	}

	public void setFlagBotaoVoltar(boolean flagBotaoVoltar) {
		this.flagBotaoVoltar = flagBotaoVoltar;
	}
	
	
	

}
