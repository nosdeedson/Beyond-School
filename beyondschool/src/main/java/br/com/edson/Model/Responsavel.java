package br.com.edson.Model;

import java.io.Serializable;

public class Responsavel extends Pessoa implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int codigoResponsavel;
	private Usuario usuarioResponsavel;
	
	public Responsavel() {
		super();
	}

	public Responsavel(int codigoResponsavel, Usuario usuarioResponsavel) {
		super();
		this.codigoResponsavel = codigoResponsavel;
		this.usuarioResponsavel = usuarioResponsavel;
	}

	public int getCodigoResponsavel() {
		return codigoResponsavel;
	}

	public void setCodigoResponsavel(int codigoResponsavel) {
		this.codigoResponsavel = codigoResponsavel;
	}

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
