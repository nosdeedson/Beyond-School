package br.com.edson.manageBean;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.application.ViewHandler;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

import br.com.edson.Model.Aluno;
import br.com.edson.Model.Funcionario;
import br.com.edson.Model.Pessoa;
import br.com.edson.Model.Responsavel;
import br.com.edson.Model.Usuario;
import br.com.edson.repository.AlunosBD;
import br.com.edson.repository.FuncionariosBD;
import br.com.edson.repository.ResponsaveisBD;
import br.com.edson.repository.UsuariosBD;
import br.com.edson.service.NegocioException;
import br.com.edson.service.ValidarEmail;

@Named
@javax.faces.view.ViewScoped
public class EditarPerfil implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Usuario user;
	
	@Inject
	private UsuariosBD usersBD;
	
	@Inject
	private Aluno aluno;
	
	@Inject
	private AlunosBD alunosBD;
	
	@Inject
	private Funcionario func;
	
	@Inject
	private FuncionariosBD funcionariosBD;
	
	@Inject
	private Responsavel responsavel;
	
	@Inject
	private ResponsaveisBD responsaveisBD;
	
	@Inject
	private EntityManager em;
	
	private String name;
	private String email;
	private String nomeUser;
	private String nascimento;
	

	
	HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
	
	public void setaUser() throws NullPointerException, ParseException {
		user = (Usuario) session.getAttribute("usuario");
		switch (user.getTipoAcesso()) {
		case "admin":
			func = funcionariosBD.porId(user.getPessoa().getIdPessoa());
			setaAtributos(user, func);
			break;
		case "aluno":
			aluno = alunosBD.porId(user.getPessoa().getIdPessoa());
			setaAtributos(user, aluno);
			break;
		case "professor":
			func = funcionariosBD.porId(user.getPessoa().getIdPessoa());
			setaAtributos(user, func);
			break;
		case "responsavel":
			responsavel = responsaveisBD.porId(user.getPessoa().getIdPessoa());
			setaAtributos(user, responsavel);
			break;

		}
		
	}
	
	private void setaAtributos(Usuario user, Pessoa p) throws ParseException {
		
		name = p.getNomeCompleto();
		DateFormat data = new SimpleDateFormat("dd/MM/yyyy");
		nascimento = data.format(p.getDataNascimento());
		nomeUser = user.getNomeUsuario();
		email = user.getEmail();
	}
	
	public void editar() throws Exception {
		FacesContext context = FacesContext.getCurrentInstance();
		EntityTransaction et = this.em.getTransaction();
		try {
		switch (user.getTipoAcesso()) {
		case "admin":{
			boolean emailValido = ValidarEmail.isValidEmail(email);
			if(!emailValido)
				throw new NegocioException("Email inválido");
			boolean retorno  = preparaPessoa(func);
			boolean retorno1 = preparauser(user);
			et.begin();
			if( retorno)
				funcionariosBD.atualizaFuncionario(func);
			if( retorno1)
				usersBD.salvarUser(user);
			et.commit();
			break;
		}
		case "aluno":{
			boolean emailValido = ValidarEmail.isValidEmail(email);
			if(!emailValido)
				throw new NegocioException("Email inválido");
			boolean retorno  = preparaPessoa(aluno);
			boolean retorno1 = preparauser(user);
			et.begin();
			if( retorno)
				alunosBD.atualizaAluno(aluno);
			if( retorno1)
				usersBD.salvarUser(user);
			et.commit();
			break;
			}
		case "professor":{
			boolean emailValido = ValidarEmail.isValidEmail(email);
			if(!emailValido)
				throw new NegocioException("Email inválido");
			boolean retorno  = preparaPessoa(func);
			boolean retorno1 = preparauser(user);
			et.begin();
			if( retorno)
				funcionariosBD.atualizaFuncionario(func);
			if( retorno1)
				usersBD.salvarUser(user);
			et.commit();
			break;
		}
		case "responsavel":
			boolean emailValido = ValidarEmail.isValidEmail(email);
			if(!emailValido)
				throw new NegocioException("Email inválido");
			boolean retorno  = preparaPessoa(responsavel);
			boolean retorno1 = preparauser(user);
			et.begin();
			if( retorno)
				responsaveisBD.atualizaResponsavel(responsavel);
			if( retorno1)
				usersBD.salvarUser(user);
			et.commit();
			break;

		}
			context.addMessage(null, new FacesMessage("Informações editadas com sucesso."));
		} catch ( Exception e) {
			et.rollback();
			e.printStackTrace();
			FacesMessage msg = new FacesMessage(e.getMessage());
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, msg);
		}finally {
			name = "";
			email = "";
			nascimento ="";
			nomeUser = "";
		}
		
	}
	
	private boolean preparaPessoa( Pessoa p) throws ParseException {
		boolean flagPessoa = false;
		if( !name.equals(p.getNomeCompleto())) {
			p.setNomeCompleto(name);
			flagPessoa = true;
		}
		DateFormat data = new SimpleDateFormat("dd/MM/yyyy");
		String niver = data.format(p.getDataNascimento());
		if(!nascimento.equals(niver)) {
			p.setDataNascimento(new SimpleDateFormat("dd/MM/yyyy").parse(nascimento));
			flagPessoa = true;
		}
		return flagPessoa;
	}
	
	private boolean preparauser( Usuario user) {
		boolean flagUser = false;
		if(!nomeUser.equals(user.getNomeUsuario())) {
			user.setNomeUsuario(nomeUser);
			flagUser= true;
		}
		if(!email.equals(user.getEmail())) {
			user.setEmail(email);
			flagUser = true;
		}
		return flagUser;
	}

	
	//getters and setters
	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public Funcionario getFunc() {
		return func;
	}

	public void setFunc(Funcionario func) {
		this.func = func;
	}

	public Responsavel getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(Responsavel responsavel) {
		this.responsavel = responsavel;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNomeUser() {
		return nomeUser;
	}

	public void setNomeUser(String nomeUser) {
		this.nomeUser = nomeUser;
	}

	public String getNascimento() {
		return nascimento;
	}

	public void setNascimento(String nascimento) {
		this.nascimento = nascimento;
	}	
	
}
