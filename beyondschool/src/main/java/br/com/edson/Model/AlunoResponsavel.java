package br.com.edson.Model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "aluno_responsavel")
public class AlunoResponsavel implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id_aluno_responsavel;
	private Aluno aluno;
	private Responsavel responsavel;
	
	public AlunoResponsavel() {	}
	
	@Id
	@GeneratedValue
	@Column(name = "id_aluno_responsavel")
	public Long getId_aluno_responsavel() {
		return id_aluno_responsavel;
	}

	public void setId_aluno_responsavel(Long id_aluno_responsavel) {
		this.id_aluno_responsavel = id_aluno_responsavel;
	}

	@NotNull
	@OneToOne
	@JoinColumn(name = "id_aluno")
	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	@NotNull
	@OneToOne
	@JoinColumn(name = "id_responsavel")
	public Responsavel getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(Responsavel responsavel) {
		this.responsavel = responsavel;
	}
		
	

}
