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
import javax.swing.JOptionPane;

import br.com.edson.Model.Aluno;
import br.com.edson.Model.Bimestre;
import br.com.edson.Model.Turma;
import br.com.edson.repository.AlunosBD;
import br.com.edson.repository.BimestresBD;
import br.com.edson.repository.TurmasBD;
import br.com.edson.service.NegocioException;

@Named
@javax.faces.view.ViewScoped
public class EditarTurmaAdminMBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Turma turma;
	
	@Inject
	private TurmasBD turmasBD;
	
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
	
	// metodos
	public void buscarBimestre() {
		bimestreAtual = bimestesBD.buscarBimestreAtual();
	}
	
	public void buscarAlunos() {
		alunos = alunosBD.buscaAlunosTurma(turma.getCodigoTurma());
	}
	
	public void excluirAluno() {
		FacesContext context = FacesContext.getCurrentInstance();
		EntityTransaction et = this.em.getTransaction();
		
		try {
			et.begin();
				alunosBD.excluirAluno(getAlunoSerExcluido());
			et.commit();
			context.addMessage(null, new FacesMessage("Aluno Excluido."));
		} catch (PersistenceException | NegocioException e) {
			et.rollback();
			FacesMessage msg= new FacesMessage(e.getMessage());
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, msg);
		}
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
	
	

}