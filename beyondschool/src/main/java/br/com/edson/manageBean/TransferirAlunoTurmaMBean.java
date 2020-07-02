package br.com.edson.manageBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import br.com.edson.Model.Aluno;
import br.com.edson.Model.Turma;
import br.com.edson.repository.AlunosBD;
import br.com.edson.repository.TurmasBD;

@Named
@javax.faces.view.ViewScoped
public class TransferirAlunoTurmaMBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private List<Aluno> alunos = new ArrayList<Aluno>();
	
	@Inject
	private AlunosBD alunosBD;
	
	private List<Turma> turmas = new ArrayList<Turma>();

	@Inject
	private TurmasBD turmasBD;

	public void buscarAlunos() {
		alunos = alunosBD.buscaAlunos();
	}
	
	public void buscarTurmas() {
		turmas = turmasBD.todasTurmas();
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
	
	
}
