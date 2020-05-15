package br.com.edson.Model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "aluno", uniqueConstraints = @UniqueConstraint( columnNames = { "matricula"}))
@PrimaryKeyJoinColumn(name = "id_pessoa")
public class Aluno extends Pessoa implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private List<Avaliacao> avaliacoes;
	private int matricula;
	private List<Responsavel> responsaveis;
	private Turma turma;
	private Usuario usuarioAluno;

	public Aluno() {
		super();
	}
	
	@OneToMany
	@JoinColumn(name = "id_avaliacao")
	public List<Avaliacao> getAvaliacoes() {
		return avaliacoes;
	}

	public void setAvaliacoes(List<Avaliacao> avaliacoes) {
		this.avaliacoes = avaliacoes;
	}

	@NotNull
	public int getMatricula() {
		return matricula;
	}

	public void setMatricula(int matricula) {
		this.matricula = matricula;
	}
	
	@NotNull
	@OneToMany
	@JoinColumn(name = "id_codigo_responsavel", nullable = false)
	public List<Responsavel> getResponsaveis() {
		return responsaveis;
	}
	public void setResponsaveis(List<Responsavel> responsaveis) {
		this.responsaveis = responsaveis;
	}
	
	@NotNull
	@OneToOne
	@JoinColumn(name = "codigo_turma")
	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}
	
	@NotNull
	@OneToOne
	@JoinColumn (name = "id_aluno_usuario", nullable = false)
	public Usuario getUsuarioAluno() {
		return usuarioAluno;
	}

	public void setUsuarioAluno(Usuario usuarioAluno) {
		this.usuarioAluno = usuarioAluno;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + matricula;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Aluno other = (Aluno) obj;
		if (matricula != other.matricula)
			return false;
		return true;
	}
	
	
	
}
