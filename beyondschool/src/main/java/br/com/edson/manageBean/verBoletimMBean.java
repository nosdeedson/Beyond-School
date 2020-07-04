package br.com.edson.manageBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.swing.JOptionPane;

import br.com.edson.Model.Aluno;
import br.com.edson.Model.Avaliacao;
import br.com.edson.Model.Turma;
import br.com.edson.repository.AlunosBD;
import br.com.edson.repository.AvaliacoesBD;
import br.com.edson.repository.TurmasBD;

@Named
@javax.faces.view.ViewScoped
public class verBoletimMBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Aluno aluno;
	
	@Inject
	private AlunosBD alunnosBD;
	
	@Inject
	private Avaliacao avaliacao;
	
	@Inject
	private AvaliacoesBD avaliacoesBD;

	@Inject
	private Turma turma;
	
	@Inject
	private TurmasBD turmasBD;
	
	private List<String> comentarios = new ArrayList<String>();
	
	private String[] comentario = new String[3];
	
	private boolean flag1 = false;
	
	private boolean flag2 = false;
	
	
	//métodos
	
	public void buscarAvaliacao() {
		avaliacao= avaliacoesBD.buscaPorIdAluno(aluno.getIdPessoa());
	}
	
	public void buscarComentarios() {
		
		comentarios = avaliacoesBD.buscaComentarios(avaliacao.getIdAvaliacao());
		if(comentarios.size() == 0)
			comentarios.add("Sem comentarios");
		for ( int i = 0; i < comentarios.size(); i++) {
			comentario[i] = comentarios.get(i);
			if( i ==  1) {
				flag1 = true;
			}
			if( i ==  2) {
				flag2 = true;
			}
		}
		
	}
	
	//getters and setters
	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public Avaliacao getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(Avaliacao avaliacao) {
		this.avaliacao = avaliacao;
	}

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

	public boolean isFlag1() {
		return flag1;
	}

	public void setFlag1(boolean flag1) {
		this.flag1 = flag1;
	}

	public boolean isFlag2() {
		return flag2;
	}

	public void setFlag2(boolean flag2) {
		this.flag2 = flag2;
	}

	public String[] getComentario() {
		return comentario;
	}

	public void setComentario(String[] comentario) {
		this.comentario = comentario;
	}

	

	
	
	
}
