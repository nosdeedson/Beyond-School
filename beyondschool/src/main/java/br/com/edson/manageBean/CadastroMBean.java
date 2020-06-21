package br.com.edson.manageBean;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.faces.FacesException;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.swing.JOptionPane;

import br.com.edson.Model.Aluno;
import br.com.edson.Model.AlunoResponsavel;
import br.com.edson.Model.Funcionario;
import br.com.edson.Model.PapelEnum;
import br.com.edson.Model.Responsavel;
import br.com.edson.Model.Usuario;
import br.com.edson.repository.AlunosBD;
import br.com.edson.repository.AlunosResponsaveisBD;
import br.com.edson.repository.FuncionariosBD;
import br.com.edson.repository.ResponsaveisBD;
import br.com.edson.repository.UsuariosBD;
import br.com.edson.service.CadastraAluno;
import br.com.edson.service.CadastraProfessor;
import br.com.edson.service.NegocioException;
import br.com.edson.service.ValidaDadosCadastro;
import br.com.edson.service.VerificaExisteResponsavel;

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
	
	@Inject
	private Responsavel responsavel1;
	
	@Inject
	private Responsavel responsavel2;
	
	@Inject
	private AlunosBD alunosBD;
	
	private boolean mostraInputParaAlunos = false;
	
	private boolean mostraInputResponsaveis = false;
	
	@Inject
	private ResponsaveisBD responsaveisBD;
	
	@Inject
	private VerificaExisteResponsavel verificaResp;
	
	@Inject
	private CadastraAluno cadAluno;
	
	@Inject
	private AlunosResponsaveisBD alunosResponsaveisBD;
	
	
	
	
	// métodos
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
				
				if(!codigoTurma.equals("semcodigo"))
					throw new NegocioException("Código Inválido");
				et.begin();
				
				Funcionario admin = new Funcionario();
				admin.setDataNascimento( new SimpleDateFormat("dd/MM/yyyy").parse(nascimento));
				admin.setNomeCompleto(nomeCompleto);
				admin.setTipoAcesso(PapelEnum.ADMIN);
				
				adminBD.salvarFuncionario(admin);

				user.setPessoa(admin);
				userBD.salvarUser(user);
				
				et.commit();
//			return "consultaCampeonato?faces-redirect=true";
				break;
			case "Aluno":
				JOptionPane.showMessageDialog(null, "aluno");
				
				et.begin();
				Aluno student = cadAluno.salvarAluno(codigoTurma, nomeCompleto, nascimento);
				JOptionPane.showMessageDialog(null, student);
				et.commit();
				et.begin();
				user.setPessoa(student);
				userBD.salvarUser(user);
				
				
				if( !responsavel1.getNomeCompleto().equals(" ")) {
					
					responsaveisBD.salvarResponsavelCadastro(responsavel1);
					
					AlunoResponsavel alunoResponsa = new AlunoResponsavel();
					
					alunoResponsa.setAluno(student);
					alunoResponsa.setResponsavel(responsavel1);
					
					alunosResponsaveisBD.salvarAlunoResponsavel(alunoResponsa);
				}
				
				if( !responsavel2.getNomeCompleto().equals(" ")) {
					responsaveisBD.salvarResponsavelCadastro(responsavel2);
					
					AlunoResponsavel alunoResponsa = new AlunoResponsavel();
					
					alunoResponsa.setAluno(student);
					alunoResponsa.setResponsavel(responsavel2);
					alunosResponsaveisBD.salvarAlunoResponsavel(alunoResponsa);
				}
				
				et.commit();
				
				break;
			case "Professor":
				
				et.begin();
					Funcionario f = new Funcionario();
					f = cadProf.salvarProfessor(codigoTurma, nomeCompleto, nascimento);
					user.setPessoa(f);
					userBD.salvarUser(user);
				et.commit();
				break;
			case "Responsável":
				
				 Responsavel existe = verificaResp.buscaResponsavel(nomeCompleto);			 
				// verifica se o resp já existe
				
				et.begin();
				
				if (existe == null) {
					existe = new Responsavel();
				}
				
				existe.setDataNascimento(new SimpleDateFormat("dd/MM/yyyy").parse(nascimento));
				existe.setNomeCompleto(nomeCompleto);
				
				responsaveisBD.salvarResponsavelCadastro(existe);
				
				user.setPessoa(existe);
				userBD.salvarUser(user);
				// tente passar apenas o id da turma
				if( !aluno1.getNomeCompleto().equals(" ") ) {
					
					alunosBD.salvarAlunosRespCadPrimerio(aluno1);

					//salva alunoResponsavel
					AlunoResponsavel alunoResp = new AlunoResponsavel();
					alunoResp.setAluno(aluno1);
					alunoResp.setResponsavel(existe);
					alunosResponsaveisBD.salvarAlunoResponsavel(alunoResp);
				}
				if( !aluno2.getNomeCompleto().equals(" ") ) {
					alunosBD.salvarAlunosRespCadPrimerio(aluno2);
					
					//salva alunoResponsavel
					AlunoResponsavel alunoResp = new AlunoResponsavel();
					alunoResp.setAluno(aluno2);
					alunoResp.setResponsavel(existe);
					alunosResponsaveisBD.salvarAlunoResponsavel(alunoResp);
				}
				
				 et.commit();
				break;
			}
//			JOptionPane.showMessageDialog(null, nomeCompleto);
//			JOptionPane.showMessageDialog(null, nascimento);
//			JOptionPane.showMessageDialog(null, senha);
//			JOptionPane.showMessageDialog(null, confirmeSenha);
//			JOptionPane.showMessageDialog(null, tipoAcesso);
			
			
			context.addMessage(null, new FacesMessage("Cadastrado com sucesso!!\n Seu nome de usuario: "+nomeUsuario));
			
		} catch ( NegocioException | ParseException | NullPointerException | FacesException e) {
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
			aluno1 = new Aluno();
			aluno2 = new Aluno();
			responsavel1 = new Responsavel();
			responsavel2 = new Responsavel();
		}
		
	}
	
	public void showAlunos() {
		mostraInputParaAlunos = true;
	}
	
	public void showResponsaveis() {
		mostraInputResponsaveis = true;
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

	public boolean isMostraInputParaAlunos() {
		return mostraInputParaAlunos;
	}

	public void setMostraInputParaAlunos(boolean mostraInputParaAlunos) {
		this.mostraInputParaAlunos = mostraInputParaAlunos;
	}

	public boolean isMostraInputResponsaveis() {
		return mostraInputResponsaveis;
	}

	public void setMostraInputResponsaveis(boolean mostraInputResponsaveis) {
		this.mostraInputResponsaveis = mostraInputResponsaveis;
	}

	public Responsavel getResponsavel1() {
		return responsavel1;
	}

	public void setResponsavel1(Responsavel responsavel1) {
		this.responsavel1 = responsavel1;
	}

	public Responsavel getResponsavel2() {
		return responsavel2;
	}

	public void setResponsavel2(Responsavel responsavel2) {
		this.responsavel2 = responsavel2;
	}

	

	
}
