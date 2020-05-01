package br.com.edson.Model;

import java.io.Serializable;

public class Semestre implements Serializable {

	private static final long serialVersionUID = 1L;
	private int idSemestre;
	private Bimestre primeiroBimestre;
	private Bimestre segundoBimestre;
	
	
	public Semestre() {
		super();
	}

	public Semestre(Bimestre primeiroBimestre, Bimestre segundoBimestre, int idSemestre) {
		super();
		this.primeiroBimestre = primeiroBimestre;
		this.segundoBimestre = segundoBimestre;
		this.idSemestre = idSemestre;
	}


	public int getId() {
		return idSemestre;
	}

	public void setId(int idSemestre) {
		this.idSemestre = idSemestre;
	}
	
	public Bimestre getPrimeiroBimestre() {
		return primeiroBimestre;
	}

	public void setPrimeiroBimestre(Bimestre primeiroBimestre) {
		this.primeiroBimestre = primeiroBimestre;
	}

	public Bimestre getSegundoBimestre() {
		return segundoBimestre;
	}

	public void setSegundoBimestre(Bimestre segundoBimestre) {
		this.segundoBimestre = segundoBimestre;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idSemestre;
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
		Semestre other = (Semestre) obj;
		if (idSemestre != other.idSemestre)
			return false;
		return true;
	}
	
	

}
