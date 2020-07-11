package br.com.edson.manageBean;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.swing.JOptionPane;

import br.com.edson.service.NegocioException;
import br.com.edson.service.ValidarEmail;

@Named
@javax.faces.view.ViewScoped
public class EsqueceuSenhaMBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
    private String email;

    //métodos
    public void enviarEmail() throws NegocioException {
    	FacesContext context = FacesContext.getCurrentInstance();
    	JOptionPane.showMessageDialog(null, "teste enviado");
    	if(!ValidarEmail.isValidEmail(email)) {
    		context.addMessage(null, new FacesMessage("Cadastrado com sucesso!!\n Seu nome de usuario: "));
    		throw new NegocioException("Email inválido");
    	}
    	context.addMessage(null, new FacesMessage("Cadastrado com sucesso!!\n Seu nome de usuario: "));
    }
    
    
    // getters and setters
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
    
    
    
 
}
