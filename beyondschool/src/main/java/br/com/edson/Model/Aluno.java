package br.com.edson.Model;

import java.io.Serializable;

public class Aluno extends Pessoa implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int matricula;
	private Usuario usuarioAluno;

	public Aluno() {
		super();
	}

	public Aluno(int matricula, Usuario usuarioAluno) {
		super();
		this.matricula = matricula;
		this.usuarioAluno = usuarioAluno;
	}

	
	public Usuario getUsuarioAluno() {
		return usuarioAluno;
	}

	public void setUsuarioAluno(Usuario usuarioAluno) {
		this.usuarioAluno = usuarioAluno;
	}

	public int getMatricula() {
		return matricula;
	}

	public void setMatricula(int matricula) {
		this.matricula = matricula;
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
