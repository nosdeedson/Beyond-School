package br.com.edson.manageBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

import br.com.edson.Model.Aluno;
import br.com.edson.Model.Funcionario;
import br.com.edson.Model.Turma;
import br.com.edson.Model.Usuario;
import br.com.edson.repository.AlunosBD;
import br.com.edson.repository.FuncionariosBD;
import br.com.edson.repository.TurmasBD;
import br.com.edson.repository.UsuariosBD;
import br.com.edson.service.RegistrarAvaliacao;

@Named
@javax.faces.view.ViewScoped
public class listaTurmasMBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Usuario user;
	
	@Inject
	private UsuariosBD usersBD;
	
	@Inject
	private TurmasBD turmasBD;
	
	private List<Turma> turmas = new ArrayList<Turma>();
	
	
	HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
	
	
	public void buscarTurmas() {
		user = usersBD.ValidaUsuarioLogin("maria.silva1", "123123123");
		turmas = turmasBD.turmasProfessor(user.getPessoa().getIdPessoa());
		
	}
	
	public String avaliar() {
		return "/APP/telaAvaliacaoAluno?faces-redirect=true";
	}
	
	// getters and setters
	public List<Turma> getTurmas() {
		return turmas;
	}


	public void setTurmas(List<Turma> turmas) {
		this.turmas = turmas;
	}
	
	

}
