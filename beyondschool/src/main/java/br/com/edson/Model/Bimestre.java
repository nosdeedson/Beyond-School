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

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table( name = "bimestre")
public class Bimestre implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long idBimestre;
	private boolean atual;
	private Date dataInicio;
	private Date dataFim;
	
	public Bimestre() {	}

	@Id
	@GeneratedValue
	@Column(name = "id_bimestre")
	public Long getIdBimestre() {
		return idBimestre;
	}

	public void setIdBimestre(Long idBimestre) {
		this.idBimestre = idBimestre;
	}
	
	@Column(name = "atual", columnDefinition = "boolean", nullable = false) 
	public boolean isAtual() {
		return atual;
	}

	public void setAtual(boolean atual) {
		this.atual = atual;
	}
	
	@NotEmpty
	@Temporal(TemporalType.DATE)
	@Column(name = "data_inicio", nullable = false)
	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	@NotEmpty
	@Temporal(TemporalType.DATE)
	@Column(name = "data_fim", nullable = false)
	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}
	
}
