package br.com.edson.manageBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.swing.JOptionPane;

import br.com.edson.Model.Usuario;
import br.com.edson.service.NegocioException;
import br.com.edson.service.ValidaDadosCadastro;

@Named
@javax.faces.view.ViewScoped
public class CadastroMBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
		
	private String nomeCompleto;
	
	private String nascimento;

	private String codigoTurma;
	
	@Inject
	private Usuario user;
	
	private String confirmeSenha;
	
	private String tipoAcesso;
	
	@Inject
	private ValidaDadosCadastro validaDados;
	
	@Inject
	private EntityManager em;
	
	
	public void salvar(){
		
		FacesContext context = FacesContext.getCurrentInstance();
		
		try {
			JOptionPane.showMessageDialog(null, "bean");
			
			validaDados.validarCodigo(getCodigoTurma());
			
			List<String> senhas = new ArrayList<String>();
			senhas.add(user.getSenha());
			senhas.add(confirmeSenha);
			
			validaDados.verificaSenha(senhas);
			
//			JOptionPane.showMessageDialog(null, nomeCompleto);
//			JOptionPane.showMessageDialog(null, nascimento);
//			JOptionPane.showMessageDialog(null, senha);
//			JOptionPane.showMessageDialog(null, confirmeSenha);
//			JOptionPane.showMessageDialog(null, tipoAcesso);
			
			
			context.addMessage(null, new FacesMessage("Cadastrado com sucesso!!"));
			
		} catch ( NegocioException e) {
			FacesMessage msg = new FacesMessage(e.getMessage());
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, msg);
		} finally {
			setCodigoTurma("");
			setNascimento("");
			setConfirmeSenha("");
			setCodigoTurma("");
			setConfirmeSenha("");
			setTipoAcesso("");
			setNomeCompleto("");
		}
		
	}
	
	
	
	// getters and setters
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

	public String getCodigoTurma() {
		return codigoTurma;
	}

	public void setCodigoTurma(String codigoTurma) {
		this.codigoTurma = codigoTurma;
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

	public String getTipoAcesso() {
		return tipoAcesso;
	}

	public void setTipoAcesso(String tipoAcesso) {
		this.tipoAcesso = tipoAcesso;
	}
	
	
	
}
