package br.com.edson.Model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "aluno", uniqueConstraints = {@UniqueConstraint (columnNames = "matricula")})
public class Aluno extends Pessoa implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private List<Avaliacao> avaliacoes;
	private String matricula;
	private Turma turma;
	
	public Aluno() { }
	
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	
	@OneToMany(mappedBy = "aluno")
	public List<Avaliacao> getAvaliacoes() {
		return avaliacoes;
	}
	public void setAvaliacoes(List<Avaliacao> avaliacoes) {
		this.avaliacoes = avaliacoes;
	}
	
	@OneToOne
	@JoinColumn(name= "id_turma")
	public Turma getTurma() {
		return turma;
	}
	public void setTurma(Turma turma) {
		this.turma = turma;
	}


	
}
