package br.com.edson.manageBean;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.swing.JOptionPane;

import br.com.edson.repository.UsuariosBD;
import br.com.edson.service.NegocioException;
import br.com.edson.service.ResetarSenha;
import br.com.edson.service.ValidarEmail;

@Named
@javax.faces.view.ViewScoped
public class EsqueceuSenhaMBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
    private String email;
    
    private String nomeCompleto;
    
    private String nascimento;
    
    private String tipoAcesso;
    
    private Date date = new Date();
    
    @Inject
    private UsuariosBD usersBD;
    
    @Inject
    private ResetarSenha resetSenha;
    
    
    @Inject
    private EntityManager em;
    
    private Integer progresso = 0;
    
    //métodos
 
    
    public void enviarEmail() throws NegocioException {
    	FacesContext context = FacesContext.getCurrentInstance();
    	EntityTransaction et = this.em.getTransaction();
    	
    	try {
    		if(!ValidarEmail.isValidEmail(email)) {
        		throw new NegocioException("Email inválido");
        	}
    	
    		setDate(new SimpleDateFormat("dd/MM/yyyy").parse(nascimento));
    		// verifica o usuario
    		et.begin();
    			resetSenha.resetPassWord(email, nomeCompleto, date, tipoAcesso); 
    		et.commit();
    		context.addMessage(null, new FacesMessage("Mandamos uma nova senha no seu email."));
    		CadastroMBean cad = new CadastroMBean();
    		cad.refreshPage(context);
		} catch (PersistenceException | ParseException | NegocioException e) {
			et.rollback();
			FacesMessage msg = new FacesMessage(e.getMessage());
			e.printStackTrace();
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, msg);
			CadastroMBean cad = new CadastroMBean();
    		cad.refreshPage(context);
		}
    	finally {
    		setEmail("");
    		setNascimento("");
    		setNomeCompleto("");
    		date = null;
    		setTipoAcesso("");	
		}
    	
    	
    }
    
    
    // getters and setters
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public String getNomeCompleto() {
		return nomeCompleto;
	}


	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}


	public String getNascimento() {
		return nascimento;
	}


	public void setNascimento(String nascimento) {
		this.nascimento = nascimento;
	}

	public String getTipoAcesso() {
		return tipoAcesso;
	}


	public void setTipoAcesso(String tipoAcesso) {
		this.tipoAcesso = tipoAcesso;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public Integer getProgresso() {
		return progresso;
	}


	public void setProgresso(Integer progresso) {
		this.progresso = progresso;
	}

    
    
    
 
}
