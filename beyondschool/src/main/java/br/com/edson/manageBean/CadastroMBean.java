package br.com.edson.manageBean;

import java.awt.HeadlessException;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.swing.JOptionPane;

import br.com.edson.Model.Aluno;
import br.com.edson.Model.Funcionario;
import br.com.edson.Model.PapelEnum;
import br.com.edson.Model.Responsavel;
import br.com.edson.Model.Usuario;
import br.com.edson.repository.FuncionariosBD;
import br.com.edson.repository.UsuariosBD;
import br.com.edson.service.CadastraProfessor;
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
	
	@Inject
	private UsuariosBD userBD;
	
	private String confirmeSenha;
	
	private String tipoAcesso;
	
	@Inject
	private ValidaDadosCadastro validaDados;
	
	@Inject
	private EntityManager em;
	
	@Inject
	private CadastraProfessor cadProf;
	
	
	/* usado para cadastrar o admin apenas*/
	@Inject
	private FuncionariosBD adminBD;
	
	@Inject
	private Aluno aluno1;
	
	@Inject
	private Aluno aluno2;
	
	
	
	public void salvar(){
		
		FacesContext context = FacesContext.getCurrentInstance();
		EntityTransaction et = em.getTransaction();	
		try {
			
			if( !tipoAcesso.equals("Admin"))
				validaDados.validarCodigo(getCodigoTurma());
			
			
			validaDados.verificaSenha(user.getSenha(), confirmeSenha);
			
			
			String nomeUsuario = validaDados.criaNomeUsuario(nomeCompleto);
			user.setNomeUsuario(nomeUsuario);
			
			
			switch (tipoAcesso) {
			case "Admin":
				JOptionPane.showMessageDialog(null, "admin");
				if(!codigoTurma.equals("semcodigo"))
					throw new NegocioException("Código Inválido");
				et.begin();
				
				Funcionario admin = new Funcionario();
				admin.setDataNascimento( new SimpleDateFormat("dd/MM/yyyy").parse(nascimento));
				admin.setNomeCompleto(nomeCompleto);
				admin.setTipoAcesso(PapelEnum.ADMIN);
				
				Long idAdmin = adminBD.salvarFuncionario(admin);
				admin.setIdPessoa(idAdmin);
				user.setPessoa(admin);
				userBD.salvarUser(user);
			et.commit();
				break;
			case "Aluno":
				JOptionPane.showMessageDialog(null, "aluno");
				break;
			case "Professor":
				JOptionPane.showMessageDialog(null, "professor");
				et.begin();
					Funcionario f = new Funcionario();
					f = cadProf.salvarProfessor(codigoTurma, nomeCompleto, nascimento);
					user.setPessoa(f);
					userBD.salvarUser(user);
				et.commit();
				break;
			case "Responsável":
				JOptionPane.showMessageDialog(null, "resp");
				et.begin();
				// aqui 
				Responsavel resp = new Responsavel();
				resp.setDataNascimento(new SimpleDateFormat("dd/MM/yyyy").parse(nascimento));
				resp.setNomeCompleto(nomeCompleto);
				
				break;
			}
//			JOptionPane.showMessageDialog(null, nomeCompleto);
//			JOptionPane.showMessageDialog(null, nascimento);
//			JOptionPane.showMessageDialog(null, senha);
//			JOptionPane.showMessageDialog(null, confirmeSenha);
//			JOptionPane.showMessageDialog(null, tipoAcesso);
			
			
			context.addMessage(null, new FacesMessage("Cadastrado com sucesso!!\n Seu nome de usuario: "+nomeUsuario));
			
		} catch ( NegocioException | ParseException | NullPointerException e) {
			et.rollback();
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
	
	public Aluno getAluno1() {
		return aluno1;
	}

	public void setAluno1(Aluno aluno1) {
		this.aluno1 = aluno1;
	}


	public Aluno getAluno2() {
		return aluno2;
	}


	public void setAluno2(Aluno aluno2) {
		this.aluno2 = aluno2;
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
