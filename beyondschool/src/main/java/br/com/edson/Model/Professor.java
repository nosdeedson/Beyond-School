package br.com.edson.Model;

import java.io.Serializable;

public class Professor extends Pessoa implements Serializable {

	private static final long serialVersionUID = 1L;
	private int registroProfessor;
	private Usuario usuario;

	public Professor() {
		super();
	}

	public Professor(int registroProfessor) {
		super();
		this.registroProfessor = registroProfessor;
	}

	public int getRegistro() {
		return registroProfessor;
	}

	public void setRegistro(int registroProfessor) {
		this.registroProfessor = registroProfessor;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + registroProfessor;
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
		Professor other = (Professor) obj;
		if (registroProfessor != other.registroProfessor)
			return false;
		return true;
	}
	
	


}
