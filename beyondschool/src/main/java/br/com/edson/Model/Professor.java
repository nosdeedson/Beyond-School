package br.com.edson.Model;

import java.io.Serializable;

public class Professor extends Pessoa implements Serializable {
	
	private int registro;

	public Professor() {
		super();
	}

	public Professor(int registro) {
		super();
		this.registro = registro;
	}

	public int getRegistro() {
		return registro;
	}

	public void setRegistro(int registro) {
		this.registro = registro;
	}
	
	

}
