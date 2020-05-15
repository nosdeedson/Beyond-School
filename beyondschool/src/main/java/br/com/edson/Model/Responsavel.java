package br.com.edson.Model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "responsavel", uniqueConstraints = @UniqueConstraint( columnNames = { "codigo_responsavel"}))
@PrimaryKeyJoinColumn(name = "id_pessoa")
public class Responsavel extends Pessoa implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int codigoResponsavel;
	private List<Aluno> tutelados;
	private Usuario usuarioResponsavel;
	
	public Responsavel() {
		super();
	}
	
	@NotNull
	@Column(name = "codigo_responsavel")
	public int getCodigoResponsavel() {
		return codigoResponsavel;
	}

	public void setCodigoResponsavel(int codigoResponsavel) {
		this.codigoResponsavel = codigoResponsavel;
	}
	
	@NotNull
	@ManyToMany(mappedBy = "matricula")
	public List<Aluno> getTutelados() {
		return tutelados;
	}

	public void setTutelados(List<Aluno> tutelados) {
		this.tutelados = tutelados;
	}

	@OneToOne
	@JoinColumn (name = "id_responsavel_usuario")
	public Usuario getUsuarioResponsavel() {
		return usuarioResponsavel;
	}

	public void setUsuarioResponsavel(Usuario usuarioResponsavel) {
		this.usuarioResponsavel = usuarioResponsavel;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + codigoResponsavel;
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
		Responsavel other = (Responsavel) obj;
		if (codigoResponsavel != other.codigoResponsavel)
			return false;
		return true;
	}

	
	
}
