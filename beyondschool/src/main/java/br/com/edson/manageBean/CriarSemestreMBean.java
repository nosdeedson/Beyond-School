package br.com.edson.manageBean;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.enterprise.inject.InjectionException;
import javax.faces.FacesException;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

import br.com.edson.Model.Bimestre;
import br.com.edson.Model.Usuario;
import br.com.edson.repository.BimestresBD;

@Named
@javax.faces.view.ViewScoped
public class CriarSemestreMBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Bimestre primeiro;
	
	@Inject
	private Bimestre segundo;
	
	@Inject
	private BimestresBD bimestresBD;
	
	@Inject
	private EntityManager em;
	
	HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
	
	@Inject
	private Usuario user;
	
	//métodos
	
	public void setaUser() {
		user = (Usuario) session.getAttribute("usuario");	
	}
	
	public void salvar() {
		EntityTransaction et = em.getTransaction();
		FacesContext context = FacesContext.getCurrentInstance();
		
		Bimestre existeBimestreAtual = bimestresBD.buscarBimestreAtual();
		
		if(existeBimestreAtual == null)
			primeiro.setAtual(true);
		try {
			
			et.begin();
			bimestresBD.salvarBimestre(primeiro);
			bimestresBD.salvarBimestre(segundo);
			et.commit();
			context.addMessage( null, new FacesMessage("Semestre salvo com sucesso!!"));
		} catch(PersistenceException | NullPointerException | FacesException | InjectionException e) {
			et.rollback();
			FacesMessage msg = new FacesMessage(e.getMessage());
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, msg);
		}finally {
			primeiro = new Bimestre();
			segundo = new Bimestre();
		}
	}



	// getters and setters
	public Bimestre getPrimeiro() {
		return primeiro;
	}



	public void setPrimeiro(Bimestre primeiro) {
		this.primeiro = primeiro;
	}



	public Bimestre getSegundo() {
		return segundo;
	}



	public void setSegundo(Bimestre segundo) {
		this.segundo = segundo;
	}

	
	

	
	

}
