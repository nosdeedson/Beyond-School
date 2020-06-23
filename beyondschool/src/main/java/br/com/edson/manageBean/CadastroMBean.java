package br.com.edson.manageBean;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.enterprise.inject.InjectionException;
import javax.faces.FacesException;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.swing.JOptionPane;

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
	
	private String nomeAluno1;
	
	private String nomeAluno2;
	
	private String nascimento;

	private String codigoTurma;/// = "bs9op84o";
	
	private String confirmeSenha;
	
	private String tipoAcesso;
	
	private String nomeResponsavel1;
	
	private String nomeResponsavel2;
	
	@Inject
	private Usuario user;
	
	@Inject
	private UsuariosBD userBD;
	
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
	private AlunosBD alunosBD;
	
	@Inject
	private Responsavel responsavel1;
	
	@Inject
	private Responsavel responsavel2;
	
	private boolean mostraInputParaAlunos = false;
	
	private boolean mostraInputResponsaveis = false;
	
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
				
				Long idAdmin = adminBD.salvarFuncionario(admin);
				admin.setIdPessoa(idAdmin);

				user.setPessoa(admin);
				userBD.salvarUser(user);
				
				et.commit();
//			return "consultaCampeonato?faces-redirect=true";
				
				break;
			case "Aluno":
				
				JOptionPane.showMessageDialog(null, "aluno");
				
				validaDados.validarCodigo(codigoTurma);
								
				Aluno student = alunosBD.buscaAlunoPeloNome(nomeCompleto);
				if(student == null) {
					student = new Aluno();
				}
					
				Turma t = turmasBD.buscaTurma(codigoTurma);
				
				Integer mat = alunosBD.buscaMatricula();
				
				if( mat == null)
					mat = 100;
				else
					mat++;
				//JOptionPane.showMessageDialog(null, mat);
				
				// ok acima 
				et.begin();
				student.setDataNascimento(new SimpleDateFormat("dd/MM/yyyy").parse(nascimento));
				student.setNomeCompleto(nomeCompleto);
				student.setMatricula(mat);
				student.setTurma(t);
				Long idStudent = alunosBD.salvarAluno(student);
				student.setIdPessoa(idStudent);
				
				
				user.setPessoa(student);
				userBD.salvarUser(user);
				
				if( !getNomeResponsavel1().equals("")) {
					responsavel1 = responsaveisBD.buscaResponsavelPeloNome(nomeResponsavel1);
					
					if(responsavel1 == null) {
						responsavel1 = new Responsavel();
						responsavel1.setNomeCompleto(nomeResponsavel1);
						Long idResponsa = responsaveisBD.salvarResponsavel(responsavel1);
						responsavel1.setIdPessoa(idResponsa);
					}
					
					AlunoResponsavel ar = new AlunoResponsavel();
					
					ar.setAluno(student);
					ar.setResponsavel(responsavel1);
					alunosResponsaveisBD.salvarAlunoResponsavel(ar);
				}
				
				if( !getNomeResponsavel2().equals("")) {
					
					responsavel2 = responsaveisBD.buscaResponsavelPeloNome(nomeResponsavel2);
					
					if( responsavel2 == null) {
						responsavel2 = new Responsavel();
						responsavel2.setNomeCompleto(nomeResponsavel2);
						Long idResponsa = responsaveisBD.salvarResponsavel(responsavel2);
						responsavel2.setIdPessoa(idResponsa);
					}
					
					AlunoResponsavel ar2 = new AlunoResponsavel();
					ar2.setAluno(student);
					ar2.setResponsavel(responsavel2);
					alunosResponsaveisBD.salvarAlunoResponsavel(ar2);
				}
				et.commit();
				break;
			case "Professor":
				
				et.begin();
					Funcionario f = new Funcionario();
					f = cadProf.salvarProfessor(codigoTurma, nomeCompleto, nascimento);
					JOptionPane.showMessageDialog(null, f.getIdPessoa() + f.getNomeCompleto());
					user.setPessoa(f);
					userBD.salvarUser(user);
				et.commit();
				break;
				
			case "Responsável":
				// ok não mexer mais, até agora coloquei resp que não exitia
				 Responsavel existe = verificaResp.buscaResponsavel(nomeCompleto);			 
				// verifica se o resp já existe
				
				et.begin();
				
				if (existe == null) {
					existe = new Responsavel();
				}
				
				existe.setDataNascimento(new SimpleDateFormat("dd/MM/yyyy").parse(nascimento));
				existe.setNomeCompleto(nomeCompleto);
				
				Long idResp = responsaveisBD.salvarResponsavel(existe);
				existe.setIdPessoa(idResp);
				
				user.setPessoa(existe);
				userBD.salvarUser(user);
				
				if( !getNomeAluno1().equals("") ) {
					
					aluno1 = alunosBD.buscaAlunoPeloNome(nomeAluno1);
				
					if( aluno1 ==  null) {
						aluno1 = new Aluno();
						aluno1.setNomeCompleto(nomeAluno1);
						Long idAluno1 = alunosBD.salvarAluno(aluno1);
						aluno1.setIdPessoa(idAluno1);
					}
					
					//salva alunoResponsavel
					AlunoResponsavel alunoResp = new AlunoResponsavel();
					alunoResp.setAluno(aluno1);
					alunoResp.setResponsavel(existe);
					alunosResponsaveisBD.salvarAlunoResponsavel(alunoResp);
				}
				if( !getNomeAluno2().equals("") ) {
					
					aluno2 = alunosBD.buscaAlunoPeloNome(nomeAluno2);
					
					if(aluno2 == null) {
						aluno2 = new Aluno();
						aluno2.setNomeCompleto(nomeAluno2);
						Long idAluno2 = alunosBD.salvarAluno(aluno2);
						aluno2.setIdPessoa(idAluno2);
					}
					
					//salva alunoResponsavel
					AlunoResponsavel alunoResp = new AlunoResponsavel();
					alunoResp.setAluno(aluno2);
					alunoResp.setResponsavel(existe);
					alunosResponsaveisBD.salvarAlunoResponsavel(alunoResp);
				}
				
				 et.commit();
				break;
			}

			
			
			context.addMessage(null, new FacesMessage("Cadastrado com sucesso!!\n Seu nome de usuario: "+nomeUsuario));
			
		} catch ( PersistenceException | NegocioException | ParseException | NullPointerException | FacesException | InjectionException e) {
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
			setNomeAluno1("");
			setNomeAluno2("");
			setNomeResponsavel1("");
			setNomeResponsavel2("");
		}
		
	}
	
	public void showAluno() {
		setTipoAcesso("Aluno");
		mostraInputResponsaveis = true;
		setMostraPanel2(true);
		setMostraPanel1(false);
	}
	
	public void showResponsaveis() {
		setTipoAcesso("Responsável");
		mostraInputParaAlunos = true;
		setMostraPanel2(true);
		setMostraPanel1(false);
	}
	
	public void showProfessor() {
		setTipoAcesso("Professor");
		setMostraPanel2(true);
		setMostraPanel1(false);
	}
	
	public void showAdmin() {
		setTipoAcesso("Admin");
		setMostraPanel2(true);
		setMostraPanel1(false);
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

	public String getNomeAluno1() {
		return nomeAluno1;
	}

	public void setNomeAluno1(String nomeAluno1) {
		this.nomeAluno1 = nomeAluno1;
	}

	public String getNomeAluno2() {
		return nomeAluno2;
	}

	public void setNomeAluno2(String nomeAluno2) {
		this.nomeAluno2 = nomeAluno2;
	}

	public String getNomeResponsavel1() {
		return nomeResponsavel1;
	}

	public void setNomeResponsavel1(String nomeResponsavel1) {
		this.nomeResponsavel1 = nomeResponsavel1;
	}

	public String getNomeResponsavel2() {
		return nomeResponsavel2;
	}

	public void setNomeResponsavel2(String nomeResponsavel2) {
		this.nomeResponsavel2 = nomeResponsavel2;
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

	
	
	
	
	

	
}
