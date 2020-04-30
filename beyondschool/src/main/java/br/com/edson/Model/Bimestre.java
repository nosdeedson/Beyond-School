package br.com.edson.Model;

import java.io.Serializable;
import java.util.Date;

public class Bimestre implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private Date dataInicio;
	private Date dataFim;
	private Semestre semestre;
	
	public Bimestre() {
		super();
	}

	public Bimestre(int id, Date dataInicio, Date dataFim, Semestre semestre) {
		super();
		this.id = id;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.semestre = semestre;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
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
		Bimestre other = (Bimestre) obj;
		if (id != other.id)
			return false;
		return true;
	}



}
