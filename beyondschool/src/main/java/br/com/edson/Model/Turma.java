package br.com.edson.Model;

import java.io.Serializable;

public class Turma implements Serializable {

	private static final long serialVersionUID = 1L;
	private Aula aula;
	private String codigoTurma;
	private String nomeLivro;
	private String nomeTurma;
	private Semestre semestre;
	
	public Turma() {
		super();
	}

	public Turma(Aula aula, String codigoTurma, String nomeLivro, String nomeTurma, Semestre semestre) {
		super();
		this.aula = aula;
		this.codigoTurma = codigoTurma;
		this.nomeLivro = nomeLivro;
		this.nomeTurma = nomeTurma;
		this.semestre = semestre;
	}

	public Aula getAula() {
		return aula;
	}

	public void setAula(Aula aula) {
		this.aula = aula;
	}

	public String getCodigo() {
		return codigoTurma;
	}

	public void setCodigo(String codigoTurma) {
		this.codigoTurma = codigoTurma;
	}

	public String getNomeLivro() {
		return nomeLivro;
	}

	public void setNomeLivro(String nomeLivro) {
		this.nomeLivro = nomeLivro;
	}

	public String getNomeTurma() {
		return nomeTurma;
	}

	public void setNomeTurma(String nomeTurma) {
		this.nomeTurma = nomeTurma;
	}

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
