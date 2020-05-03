package br.com.edson.Model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

//@Entity
//@Table(name = "turma")
public class Turma implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String codigoTurma;
	private String nomeLivro;
	private String nomeTurma;
	
	public Turma() {
		super();
	}

//	@Id
//	@GeneratedValue
//	@Column(name = "codigo_turma")
	public String getCodigoTurma() {
		return codigoTurma;
	}

	public void setCodigoTurma(String codigoTurma) {
		this.codigoTurma = codigoTurma;
	}

//	@NotEmpty
//	@Column(name = "nome_livro", length = 20, nullable = false)
	public String getNomeLivro() {
		return nomeLivro;
	}

	public void setNomeLivro(String nomeLivro) {
		this.nomeLivro = nomeLivro;
	}
	
//	@NotEmpty
//	@Column(name = "nome_turma", length = 20, nullable = false)
	public String getNomeTurma() {
		return nomeTurma;
	}

	public void setNomeTurma(String nomeTurma) {
		this.nomeTurma = nomeTurma;
	}
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigoTurma == null) ? 0 : codigoTurma.hashCode());
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
		Turma other = (Turma) obj;
		if (codigoTurma == null) {
			if (other.codigoTurma != null)
				return false;
		} else if (!codigoTurma.equals(other.codigoTurma))
			return false;
		return true;
	}

	
	
}
