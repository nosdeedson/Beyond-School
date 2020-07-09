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
	
	@Inject
	private Usuario user;
	
	HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
	
	public void setaUsuario() {
		user = (Usuario) session.getAttribute("usuario");
		JOptionPane.showMessageDialog(null, user.getTipoAcesso());
	}
	
	public String voltar() {
		JOptionPane.showMessageDialog(null, user.getTipoAcesso());
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
		
		return "/public/telaPai?faces-redirect=true";
	}

	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}
	
	

}
