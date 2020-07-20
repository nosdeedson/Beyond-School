package br.com.edson.manageBean;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.enterprise.inject.InjectionException;
import javax.faces.FacesException;
import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIViewRoot;
import javax.faces.component.html.HtmlCommandButton;
import javax.faces.component.html.HtmlInputText;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.swing.JOptionPane;

import org.junit.jupiter.params.shadow.com.univocity.parsers.conversions.CalendarConversion;

import javax.faces.application.ViewHandler;


import br.com.edson.Model.Aluno;
import br.com.edson.Model.AlunoResponsavel;
import br.com.edson.Model.Funcionario;
import br.com.edson.Model.PapelEnum;
import br.com.edson.Model.Responsavel;
import br.com.edson.Model.Turma;
import br.com.edson.Model.Usuario;
import br.com.edson.repository.AlunosBD;
import br.com.edson.repository.AlunosResponsaveisBD;
import br.com.edson.repository.FuncionariosBD;
import br.com.edson.repository.ResponsaveisBD;
import br.com.edson.repository.TurmasBD;
import br.com.edson.repository.UsuariosBD;
import br.com.edson.service.CadastraProfessor;
import br.com.edson.service.GeradorHashSenha;
import br.com.edson.service.NegocioException;
import br.com.edson.service.ValidaDadosCadastro;
import br.com.edson.service.ValidarEmail;
import br.com.edson.service.VerificaExisteResponsavel;

@Named
@javax.faces.view.ViewScoped
public class CadastroMBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
		
	private String nomeCompleto;
	
	private String[] tutelado = new String[5];
	
	private String nascimento;

	private String codigoTurma;
	
	private String passWord;
	
	private String confirmeSenha;
	
	private String tipoAcesso;
	
	private String[] nomeResponsavel = new String[2];
	
	@Inject
	private Usuario user;
	
	@Inject
	private UsuariosBD userBD;
	
	@Inject
	private EntityManager em;
	
	@Inject
	private CadastraProfessor cadProf;
	
	
	/* usado para cadastrar o admin apenas*/
	@Inject
	private FuncionariosBD adminBD;
	
	@Inject
	private Aluno aluno;
	
	@Inject
	private AlunosBD alunosBD;
	
	@Inject
	private Responsavel responsavel;
	
	private boolean mostraInputParaAlunos[] = new boolean[5];
	
	private int qtdAlunosTutelados;
	
	private boolean flagResponsavel[] = new boolean[2];
	
	private int qtdResponsaveis;
	
	private boolean mostraPanel1 = true;
	
	private boolean mostraPanel2 = false;
	
	@Inject
	private ResponsaveisBD responsaveisBD;
	
	@Inject
	private VerificaExisteResponsavel verificaResp;
		
	@Inject
	private AlunosResponsaveisBD alunosResponsaveisBD;

	@Inject
	private TurmasBD turmasBD;
	
	private boolean flagCadastrado = true;
	
	@Inject
	private ValidaDadosCadastro validaDados;
	
	private boolean forget = false;
	
	private String email;
	
	private String qtdRep;
	
	private String qtdAluno;
		
	
	
	// métodos
	public void salvar() throws Exception{
		FacesContext context = FacesContext.getCurrentInstance();
		EntityTransaction et = em.getTransaction();	
		int cont = 0;
		try {
			
			String nome[] = {nomeCompleto};
			verificaNomesDigitados(nome, 1);
			
			if( !tipoAcesso.equals("Admin"))
				 validaDados.validarCodigo(getCodigoTurma());
			if(passWord.length()< 8)
				throw new NegocioException("Senha deve ter ao menos 8 caracteres.");
			if(!passWord.equals(confirmeSenha)) {
				throw new NegocioException("Senhas não conferem!! Digite novamente.");
			}
			
			// muda a senha digitada para um hash
			
			passWord = GeradorHashSenha.geradorHashPassWord(passWord);
			
			if (validaDados.verificaSenha(passWord)) 
				user.setSenha(passWord);
			
			
			String nomeUsuario = validaDados.criaNomeUsuario(nomeCompleto);
			
			user.setNomeUsuario(nomeUsuario);
			
			if( !email.isEmpty()  )
				if(ValidarEmail.isValidEmail( email))
					user.setEmail(email);
			
			
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
				cont++;
				flagCadastrado = false;
				break;
				
				
			case "Aluno":
				
				//cont instanciado antes do try
				if(nomeResponsavel[0].isEmpty())
					throw new NegocioException("Ao menos um responsável deve ser informado.");
				
				verificaNomesDigitados(nomeResponsavel, qtdResponsaveis);
				
				validaDados.validarCodigo(codigoTurma);
				boolean flagAluno = true;
				
				Aluno student = alunosBD.buscaAlunoPeloNome(nomeCompleto);
				if(student == null) {
					student = new Aluno();
					flagAluno = false;
				}
				else if(student.isDeletado()) {
					student.setDeletado(false);
				}
					
				Turma t = turmasBD.buscaTurma(codigoTurma);
								
				Integer mat = alunosBD.buscaMatricula();

				
				if( mat == null)
					mat = new Integer(100);
				else
					mat++;
			
				// ok acima 
				et.begin();	
				
				student.setDataNascimento(new SimpleDateFormat("dd/MM/yyyy").parse(nascimento));
				student.setNomeCompleto(nomeCompleto);
				student.setMatricula(mat);
				student.setTurma(t);
		
				alunosBD.salvarAluno(student);				
				
				user.setPessoa(student);
				
				userBD.salvarUser(user);
				
				cont = 0;  //cont instanciado antes do try
				do {

					boolean flagResponsavel = true;
					responsavel = responsaveisBD.buscaResponsavelPeloNome(nomeResponsavel[cont]);
					
					if(responsavel == null) {
						
						flagResponsavel = false;
						responsavel = new Responsavel();
						responsavel.setNomeCompleto(nomeResponsavel[cont]);
						
						responsaveisBD.salvarResponsavelCadastro(responsavel);
						
					}
					
					if( !flagAluno || !flagResponsavel) {
						
						AlunoResponsavel ar = new AlunoResponsavel();
						ar.setAluno(student);
						ar.setResponsavel(responsavel);
						alunosResponsaveisBD.salvarAlunoResponsavel(ar);				
					}
					
					responsavel = new Responsavel();
					
					cont++;
				} while (cont < qtdResponsaveis);

				et.commit();
				flagCadastrado = false;
				break;
				
				
			case "Professor":
				cont++;
				et.begin();
					Funcionario f = new Funcionario();
					f = cadProf.salvarProfessor(codigoTurma, nomeCompleto, nascimento);
					user.setPessoa(f);
					userBD.salvarUser(user);
				et.commit();
				flagCadastrado = false;
				break;
			
			case "Responsável":
	
				verificaNomesDigitados(tutelado, qtdAlunosTutelados);
				
				Responsavel existeResp = verificaResp.buscaResponsavel(nomeCompleto);			 
				
				et.begin();
				boolean flagResponsavel = true;
				// verifica se o resp já existe
				 if (existeResp == null) {
					 existeResp = new Responsavel();
					 flagResponsavel = false;
				}
				 
				existeResp.setDataNascimento(new SimpleDateFormat("dd/MM/yyyy").parse(nascimento));
				existeResp.setNomeCompleto(nomeCompleto);
				
				responsaveisBD.salvarResponsavelCadastro(existeResp);
				
				user.setPessoa(existeResp);
				userBD.salvarUser(user);
				
				cont = 0;
				do {
					
					aluno = alunosBD.buscaAlunoPeloNome(tutelado[cont]);
					if(aluno != null) {
						if(aluno.isDeletado())
							aluno.setDeletado(false);
					}
						
					boolean flagStudent = true;
					
					if( aluno ==  null) {
						aluno = new Aluno();
						aluno.setNomeCompleto(tutelado[cont]);
						alunosBD.salvarAluno(aluno);
						flagStudent = false;
					}
					
					//salva alunoResponsavel
					
					if( !flagStudent || !flagResponsavel) {
						AlunoResponsavel alunoResp = new AlunoResponsavel();
	
						alunoResp = new AlunoResponsavel();
						alunoResp.setAluno(aluno);
						alunoResp.setResponsavel(existeResp);
						alunosResponsaveisBD.salvarAlunoResponsavel(alunoResp);
					}
					
					
					aluno = new Aluno();
					
					cont++;
				} while (cont < qtdAlunosTutelados);
				
				
				 et.commit();
				break;
			}

			
			context.addMessage(null, new FacesMessage("Cadastrado com sucesso!! Seu nome de usuario: "+nomeUsuario));
			refreshPage(context);
		} catch ( PersistenceException | ParseException | NullPointerException | NegocioException | FacesException | InjectionException e) {
			et.rollback();
			e.printStackTrace();
			FacesMessage msg = new FacesMessage(e.getMessage());
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, msg);
			refreshPage(context);
		} finally {
			setCodigoTurma("");
			setNascimento("");
			setConfirmeSenha("");
			setCodigoTurma("");
			setConfirmeSenha("");
			setTipoAcesso("");
			setNomeCompleto("");
			String[] semNome = { "","","","","" };
			setTutelado(semNome);
			String[] respVazio = {" ", " "};
			setNomeResponsavel(respVazio);
			setEmail("");
		}
		
	}
	
	private void verificaNomesDigitados(String nomes[], int tm) throws NegocioException {
		int cont = 0;
		do {
			
			if(nomes[cont].endsWith(" "))
				throw new NegocioException("Por favor não coloque espaços depois do sobrenome.");
			int primeiroEspaco = nomes[cont].indexOf(" ");
			if( primeiroEspaco == 1)
				throw new NegocioException("Retire o espaço antes do nome.");
			if( primeiroEspaco == -1 || nomes[cont].isEmpty())
				throw new NegocioException("Informa o nome completo dos alunos.");
				
			cont++;			
		} while (cont < tm);
	}
	
	public void refreshPage(FacesContext context ) {
		//does the refresh of the page
		Application application = context.getApplication();
		ViewHandler view = application.getViewHandler();
		UIViewRoot root = view.createView(context, context.getViewRoot().getViewId());
		context.setViewRoot(root);
		context.getRenderResponse();
		
	}
	
	public void showAdmin() {
		setTipoAcesso("Admin");
		setMostraPanel2(true);
		setMostraPanel1(false);
		user.setTipoAcesso("admin");
	}
	
	public void showAluno() {
		FacesContext context = FacesContext.getCurrentInstance();
		if(qtdRep.matches("[1,2]*")) {
			qtdResponsaveis = Integer.parseInt(qtdRep);
		}
		else {
			context.addMessage(null, new FacesMessage("Você só pode informar 1 ou 2 responsáveis."));
			refreshPage(context);
		}

		setTipoAcesso("Aluno");
		for (int i = 0; i < qtdResponsaveis; i++) {
			flagResponsavel[i] = true;
		}
		setMostraPanel2(true);
		setMostraPanel1(false);
		user.setTipoAcesso("aluno");
	}
	
	
	public void showProfessor() {
		setTipoAcesso("Professor");
		setMostraPanel2(true);
		setMostraPanel1(false);
		user.setTipoAcesso("professor");
	}
	
	public void showResponsaveis() {
		FacesContext context = FacesContext.getCurrentInstance();
		if(qtdAluno.matches("[1,5]*")) {
			qtdAlunosTutelados = Integer.parseInt(qtdAluno);
		}
		else {
			context.addMessage(null, new FacesMessage("Você só pode informar 5 alunos."));
			refreshPage(context);
		}

		setTipoAcesso("Responsável");
		for (int i = 0; i < qtdAlunosTutelados; i++) {
			mostraInputParaAlunos[i] = true;
		}
		
		setMostraPanel2(true);
		setMostraPanel1(false);
		user.setTipoAcesso("responsavel");
	}

	
	
	// getters and setters
	
	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
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

	public String getTipoAcesso() {
		return tipoAcesso;
	}

	public void setTipoAcesso(String tipoAcesso) {
		this.tipoAcesso = tipoAcesso;
	}

	public boolean isMostraPanel1() {
		return mostraPanel1;
	}

	public void setMostraPanel1(boolean mostraPanel1) {
		this.mostraPanel1 = mostraPanel1;
	}

	public boolean isMostraPanel2() {
		return mostraPanel2;
	}

	public void setMostraPanel2(boolean mostraPanel2) {
		this.mostraPanel2 = mostraPanel2;
	}

	public int getQtdAlunosTutelados() {
		return qtdAlunosTutelados;
	}

	public void setQtdAlunosTutelados(int qtdAlunosTutelados) {
		this.qtdAlunosTutelados = qtdAlunosTutelados;
	}

	public boolean[] getMostraInputParaAlunos() {
		return mostraInputParaAlunos;
	}

	public void setMostraInputParaAlunos(boolean[] mostraInputParaAlunos) {
		this.mostraInputParaAlunos = mostraInputParaAlunos;
	}

	public String[] getTutelado() {
		return tutelado;
	}

	public void setTutelado(String[] tutelado) {
		this.tutelado = tutelado;
	}

	public String[] getNomeResponsavel() {
		return nomeResponsavel;
	}

	public void setNomeResponsavel(String[] nomeResponsavel) {
		this.nomeResponsavel = nomeResponsavel;
	}

	public Responsavel getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(Responsavel responsavel) {
		this.responsavel = responsavel;
	}

	public boolean isFlagCadastrado() {
		return flagCadastrado;
	}

	public void setFlagCadastrado(boolean flagCadastrado) {
		this.flagCadastrado = flagCadastrado;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getConfirmeSenha() {
		return confirmeSenha;
	}

	public void setConfirmeSenha(String confirmeSenha) {
		this.confirmeSenha = confirmeSenha;
	}

	public boolean isForget() {
		return forget;
	}

	public void setForget(boolean forget) {
		this.forget = forget;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	

	public boolean[] getFlagResponsavel() {
		return flagResponsavel;
	}

	public void setFlagResponsavel(boolean[] flagResponsavel) {
		this.flagResponsavel = flagResponsavel;
	}

	public int getQtdResponsaveis() {
		return qtdResponsaveis;
	}

	public void setQtdResponsaveis(int qtdResponsaveis) {
		this.qtdResponsaveis = qtdResponsaveis;
	}

	public String getQtdRep() {
		return qtdRep;
	}

	public void setQtdRep(String qtdRep) {
		this.qtdRep = qtdRep;
	}

	public String getQtdAluno() {
		return qtdAluno;
	}

	public void setQtdAluno(String qtdAluno) {
		this.qtdAluno = qtdAluno;
	}
	
	


	
	
	
}
