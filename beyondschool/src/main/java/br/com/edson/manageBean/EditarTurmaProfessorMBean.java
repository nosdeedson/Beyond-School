package br.com.edson.manageBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;

import br.com.edson.Model.Aluno;
import br.com.edson.Model.Bimestre;
import br.com.edson.Model.Turma;
import br.com.edson.repository.AlunosBD;
import br.com.edson.repository.BimestresBD;
import br.com.edson.repository.TurmasBD;
import br.com.edson.service.AtualizaBimestre;
import br.com.edson.service.NegocioException;
import br.com.edson.service.RegistrarAvaliacao;

@Named
@javax.faces.view.ViewScoped
public class EditarTurmaProfessorMBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Turma turma;
	
	@Inject
	private Bimestre bimestreAtual;
	
	@Inject
	private BimestresBD bimestresBD;
	
	private List<Aluno> alunos = new ArrayList<Aluno>();
	
	@Inject
	private AlunosBD alunosBD;
	
	@Inject
	private AtualizaBimestre atualizaBimestre;
	
	@Inject
	private EntityManager em;
	
	@Inject
	private RegistrarAvaliacao r;

	//métodos bs9op84o
	public void buscarAlunos() {
		alunos = alunosBD.buscaAlunosTurma(turma.getCodigoTurma());
	}
	
	public void atualizaBimestre() throws NegocioException {
		EntityTransaction et = this.em.getTransaction();

		try {
			et.begin();
			atualizaBimestre.atualizaBimestre();
			et.commit();
		} catch (PersistenceException |NegocioException e) {
			et.rollback();
			throw new NegocioException("Falha ao atualizar o bimestre");
		}
		
	}
	
	public void buscarBimestre() {
		bimestreAtual = bimestresBD.buscarBimestreAtual();
	}
		
	//getters and setters
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
	
	
	
	

}
