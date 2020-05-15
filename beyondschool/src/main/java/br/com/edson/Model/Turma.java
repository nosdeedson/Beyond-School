package br.com.edson.Model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "turma")
public class Turma implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Aula aula;
	private List<Aluno> alunos;
	private String codigoTurma;
	private String nomeLivro;
	private String nomeTurma;
	private Professor professor;
	private Semestre semestre;
	
	public Turma() {
		super();
	}
	
	@OneToOne
	@JoinColumn(name = "id_aula", nullable = false)
	public Aula getAula() {
		return aula;
	}
	
	public void setAula(Aula aula) {
		this.aula = aula;
	}
	
	@NotNull
	@OneToMany (mappedBy = "matricula" )
	public List<Aluno> getAlunos() {
		return alunos;
	}

	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}

	@Id
	@GeneratedValue
	@Column(name = "codigo_turma", nullable = false)
	public String getCodigoTurma() {
		return codigoTurma;
	}

	public void setCodigoTurma(String codigoTurma) {
		this.codigoTurma = codigoTurma;
	}

	@NotEmpty
	@Column(name = "nome_livro", length = 20, nullable = false)
	public String getNomeLivro() {
		return nomeLivro;
	}

	public void setNomeLivro(String nomeLivro) {
		this.nomeLivro = nomeLivro;
	}
	
	@NotEmpty
	@Column(name = "nome_turma", length = 20, nullable = false)
	public String getNomeTurma() {
		return nomeTurma;
	}

	public void setNomeTurma(String nomeTurma) {
		this.nomeTurma = nomeTurma;
	}
	
	@NotNull
	@OneToOne
	@JoinColumn(name = "prof_registro", nullable = false)
	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}
	
	@OneToOne
	@JoinColumn(name = "id_semestre", nullable = false)
	public Semestre getSemestre() {
		return semestre;
	}

	public void setSemestre(Semestre semestre) {
		this.semestre = semestre;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigoTurma == null) ? 0 : codigoTurma.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Turma other = (Turma) obj;
		if (codigoTurma == null) {
			if (other.codigoTurma != null)
				return false;
		} else if (!codigoTurma.equals(other.codigoTurma))
			return false;
		return true;
	}

	
	
}
