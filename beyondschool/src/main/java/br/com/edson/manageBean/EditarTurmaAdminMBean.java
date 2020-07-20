package br.com.edson.manageBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

import br.com.edson.Model.Aluno;
import br.com.edson.Model.Bimestre;
import br.com.edson.Model.Turma;
import br.com.edson.Model.Usuario;
import br.com.edson.repository.AlunosBD;
import br.com.edson.repository.BimestresBD;
import br.com.edson.repository.TurmasBD;
import br.com.edson.service.AtualizaBimestre;
import br.com.edson.service.NegocioException;

@Named
@javax.faces.view.ViewScoped
public class EditarTurmaAdminMBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Turma turma;
	
	@Inject
	private TurmasBD turmasBD;
	
	@Inject
	private Turma turmaSerExcluida;
	
	private List<Aluno> alunos = new ArrayList<Aluno>();
	
	@Inject
	private Aluno alunoSerExcluido;
	
	@Inject
	private AlunosBD alunosBD;

	@Inject
	private Bimestre bimestreAtual;
	
	@Inject
	private BimestresBD bimestesBD;
	
	@Inject
	private EntityManager em;
	
	@Inject
	private AtualizaBimestre atualizaBimeste;
	
	@Inject
	private Usuario user;
	
	HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
	
	// metodos
	public void buscarBimestre() {
		user = (Usuario) session.getAttribute("usuario");
		bimestreAtual = bimestesBD.buscarBimestreAtual();
	}
	
	public void buscarAlunos() {
		alunos = alunosBD.buscaAlunosTurma(turma.getCodigoTurma());
	}
	
	public void atualizaBimestre() throws NegocioException {
		FacesContext context = FacesContext.getCurrentInstance();
		EntityTransaction et = this.em.getTransaction();
		try {
			et.begin();
			atualizaBimeste.atualizaBimestre();
			et.commit();
		} catch (PersistenceException | NegocioException e) {
			et.rollback();
			FacesMessage msg= new FacesMessage(e.getMessage());
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, msg);
		}
		
	}
	
	public void excluirAluno() {
		FacesContext context = FacesContext.getCurrentInstance();
		EntityTransaction et = this.em.getTransaction();
		
		try {
			et.begin();
				alunosBD.excluirAluno(getAlunoSerExcluido());
			et.commit();
			context.addMessage(null, new FacesMessage("Aluno Excluido."));
			buscarAlunos();
		} catch (PersistenceException | NegocioException e) {
			et.rollback();
			FacesMessage msg= new FacesMessage(e.getMessage());
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, msg);
		}
	}
	
	public String excluirTurma() throws NegocioException {
		
		FacesContext context = FacesContext.getCurrentInstance();
		EntityTransaction et = this.em.getTransaction();
		try {
			
			if( alunos.size() > 0) {
				throw new NegocioException(" Transfira o(s) aluno(s) primeiro!!");
			}
			 
			et.begin();
			
			turmasBD.excluir(turmaSerExcluida);
			
			et.commit();
			
			
			context.addMessage(null, new FacesMessage("Turma Excluida."));
		} catch ( PersistenceException | NegocioException e) {
			et.rollback();
			FacesMessage msg = new FacesMessage(e.getMessage());
			msg.setSeverity(FacesMessage.SEVERITY_INFO);
			context.addMessage(null, msg);
		}
		if(alunos.size() == 0)
			return "/APP/telaAdmin?faces-redirect=true";
		return "";
	}
	
	// getters and setters
	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

	public List<Aluno> getAlunos() {
		return alunos;
	}

	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}

	public Bimestre getBimestreAtual() {
		return bimestreAtual;
	}

	public void setBimestreAtual(Bimestre bimestreAtual) {
		this.bimestreAtual = bimestreAtual;
	}

	public Aluno getAlunoSerExcluido() {
		return alunoSerExcluido;
	}

	public void setAlunoSerExcluido(Aluno alunoSerExcluido) {
		this.alunoSerExcluido = alunoSerExcluido;
	}

	public Turma getTurmaSerExcluida() {
		return turmaSerExcluida;
	}

	public void setTurmaSerExcluida(Turma turmaSerExcluida) {
		this.turmaSerExcluida = turmaSerExcluida;
	}
	
	

}
