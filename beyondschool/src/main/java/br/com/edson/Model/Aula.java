package br.com.edson.Model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "aula")
public class Aula implements Serializable {

	private static final long serialVersionUID = 1L;
	private Date primeiroDia;
	private  Date segundoDia;
	private Date horaInicio;
	private Date horaFim;
	private int idAula;
	
	
	public Aula() {
		super();
	}
	
	public Aula(Date primeiroDia, Date segundoDia, Date horaInicio, Date horaFim, int idAula) {
		super();
		this.primeiroDia = primeiroDia;
		this.segundoDia = segundoDia;
		this.horaInicio = horaInicio;
		this.horaFim = horaFim;
		this.idAula = idAula;
	}

	@NotNull
	@Temporal(TemporalType.TIME)
	@Column(name = "hora_inicio", nullable = false)
	public Date getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(Date horaInicio) {
		this.horaInicio = horaInicio;
	}
	@NotNull
	@Temporal(TemporalType.TIME)
	@Column(name = "hora_fim", nullable = false)
	public Date getHoraFim() {
		return horaFim;
	}

	public void setHoraFim(Date horaFim) {
		this.horaFim = horaFim;
	}

	@Id
	@GeneratedValue
	public int getIdAula() {
		return idAula;
	}

	public void setIdAula(int idAula) {
		this.idAula = idAula;
	}
	
	@NotNull
	@Temporal(TemporalType.DATE)
	@Column(name = "primeiro_dia")
	public Date getPrimeiroDia() {
		return primeiroDia;
	}

	public void setPrimeiroDia(Date primeiroDia) {
		this.primeiroDia = primeiroDia;
	}
	
	@NotNull
	@Temporal(TemporalType.DATE)
	@Column(name = "segundo_dia")
	public Date getSegundoDia() {
		return segundoDia;
	}

	public void setSegundoDia(Date segundoDia) {
		this.segundoDia = segundoDia;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idAula;
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
		Aula other = (Aula) obj;
		if (idAula != other.idAula)
			return false;
		return true;
	}
	

	
	
	
}
