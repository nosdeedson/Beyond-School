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

import org.primefaces.event.DragDropEvent;

import br.com.edson.Model.Aluno;
import br.com.edson.Model.Turma;
import br.com.edson.Model.Usuario;
import br.com.edson.repository.AlunosBD;
import br.com.edson.repository.TurmasBD;
import br.com.edson.service.NegocioException;

@Named
@javax.faces.view.ViewScoped
public class TransferirAlunoTurmaMBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private List<Aluno> alunos = new ArrayList<Aluno>();
	
	private List<Aluno> dropAlunos= new ArrayList<Aluno>();
	
	@Inject
	private Aluno aluno;
	
	@Inject
	private AlunosBD alunosBD;
	
	private List<Turma> turmas = new ArrayList<Turma>();
	

	@Inject
	private TurmasBD turmasBD;
	
	@Inject
	private Turma turmaDestino;
	
	private String codigoTurma;
	
	@Inject
	private EntityManager em;
	
	HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
	
	
	@Inject
	private Usuario user;

	public void buscarAlunos() {
		user = (Usuario) session.getAttribute("usuario");
		if( aluno.getNomeCompleto() == null) {
			alunos = alunosBD.buscaAlunos();
		}
		else {
			alunos.add(aluno);
		}
	}
	
	
	public void buscarTurmas() {
		turmas = turmasBD.todasTurmas();
	}
	
	public void onAlunosDrop( DragDropEvent ddEvento) {
		
		aluno = (Aluno) ddEvento.getData();
		dropAlunos.add(aluno);
		alunos.remove(aluno);
		
	}
	
	
	public void transferir( Aluno aluno ) {
		
		FacesContext context = FacesContext.getCurrentInstance();
		EntityTransaction et = this.em.getTransaction();
		
		try {
			et.begin();
			alunosBD.tranferirAluno(aluno, codigoTurma);
			et.commit();
			dropAlunos.remove(aluno);
			context.addMessage(null, new FacesMessage("Aluno Transferido."));
			
		} catch (PersistenceException | NegocioException e) {
			et.rollback();
			FacesMessage msg = new FacesMessage(e.getMessage());
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, msg);
		}finally {
			codigoTurma ="";
		}
	}

	
	// getters and setters
	public List<Aluno> getAlunos() {
		return alunos;
	}

	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}

	public List<Turma> getTurmas() {
		return turmas;
	}

	public void setTurmas(List<Turma> turmas) {
		this.turmas = turmas;
	}

	public List<Aluno> getDropAlunos() {
		return dropAlunos;
	}

	public void setDropAlunos(List<Aluno> dropAlunos) {
		this.dropAlunos = dropAlunos;
	}


	public Turma getTurmaDestino() {
		return turmaDestino;
	}

	public void setTurmaDestino(Turma turmaDestino) {
		this.turmaDestino = turmaDestino;
	}

	public String getCodigoTurma() {
		return codigoTurma;
	}

	public void setCodigoTurma(String codigoTurma) {
		this.codigoTurma = codigoTurma;
	}


	public Aluno getAluno() {
		return aluno;
	}


	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}
	
	
	
}
