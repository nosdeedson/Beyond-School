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
	
	public Bimestre() {
	}

	


}
