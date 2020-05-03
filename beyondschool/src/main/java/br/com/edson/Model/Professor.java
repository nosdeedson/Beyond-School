package br.com.edson.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

//@Entity
//@Table(name = "professor")
//@PrimaryKeyJoinColumn(name = "id_pessoa")
public class Professor extends Pessoa implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int registro;
	private List<Turma> turmas;
	private Usuario usuario;
	
	
	public Professor() {
		super();
	}
	
//	@Id
//	@GeneratedValue
	public int getRegistro() {
		return registro;
	}

	public void setRegistro(int registro) {
		this.registro = registro;
	}

//	@OneToMany
//	@JoinColumn(name = "id_turma")
	public List<Turma> getTurmas() {
		return turmas;
	}

	public void setTurmas(List<Turma> turmas) {
		this.turmas = turmas;
	}

//	@OneToOne
//	@JoinColumn(name = "id_usuario")
	public Usuario getUsuario() {
		return usuario;
	}


	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + registro;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Professor other = (Professor) obj;
		if (registro != other.registro)
			return false;
		return true;
	}
	

			
}
