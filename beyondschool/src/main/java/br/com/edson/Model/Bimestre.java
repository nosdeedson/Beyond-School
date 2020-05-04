package br.com.edson.Model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "bimestre")
public class Bimestre implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Date dataInicio;
	private Date dataFim;
	private int idBimestre;
	private Semestre semestre;
	
	public Bimestre() {
		super();
	}

	public Bimestre(Date dataInicio, Date dataFim, int idBimestre, Semestre semestre) {
		super();
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.idBimestre = idBimestre;
		this.semestre = semestre;
	}
	
	@NotNull
	@Temporal(TemporalType.DATE)
	@Column(name = "data_inicio")
	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}
	
	@NotNull
	@Temporal(TemporalType.DATE)
	@Column(name = "data_fim")
	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	@Id
	@GeneratedValue
	@Column(name = "id_bimestre")
	public int getIdBimestre() {
		return idBimestre;
	}

	public void setIdBimestre(int idBimestre) {
		this.idBimestre = idBimestre;
	}
	
	@OneToOne
	@JoinColumn(name = "id_semestre")
	public Semestre getSemestre() {
		return semestre;
	}

	public void setSemestre(Semestre semestre) {
		this.semestre = semestre;
	}




}
