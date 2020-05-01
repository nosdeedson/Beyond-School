package br.com.edson.Model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Aula implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<Date> diaSemana;
	private Date horaInicio;
	private Date horaFim;
	private int idAula;
	
	
	public Aula() {
		super();
	}

	public Aula(Date horaInicio, Date horaFim, int idAula, List<Date> diaSemana) {
		super();
		this.horaInicio = horaInicio;
		this.horaFim = horaFim;
		this.idAula= idAula;
		this.diaSemana = diaSemana;
	}

	public Date getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(Date horaInicio) {
		this.horaInicio = horaInicio;
	}

	public Date getHoraFim() {
		return horaFim;
	}

	public void setHoraFim(Date horaFim) {
		this.horaFim = horaFim;
	}

	public int getId() {
		return idAula;
	}

	public void setId(int idAula) {
		this.idAula = idAula;
	}

	public List<Date> getDiaSemana() {
		return diaSemana;
	}

	public void setDiaSemana(List<Date> diaSemana) {
		this.diaSemana = diaSemana;
	}
	
	
	
}
