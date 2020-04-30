package br.com.edson.Model;

import java.io.Serializable;

public class Semestre implements Serializable {

	private static final long serialVersionUID = 1L;
	private Bimestre primeiroBimestre;
	private Bimestre segundoBimestre;
	private int id;
	
	public Semestre() {
		super();
	}

	public Semestre(Bimestre primeiroBimestre, Bimestre segundoBimestre, int id) {
		super();
		this.primeiroBimestre = primeiroBimestre;
		this.segundoBimestre = segundoBimestre;
		this.id = id;
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		if (id != other.id)
			return false;
		return true;
	}
	
	

}
