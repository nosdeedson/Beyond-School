package br.com.edson.Model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

public class Admin extends Pessoa implements Serializable {

	private static final long serialVersionUID = 1L;
	private int codigoAdmin;
	private Usuario usuarioAdmin;
	
	public Admin() {
		super();
	}

	public Admin(int codigoAdmin, Usuario usuarioAdmin) {
		super();
		this.codigoAdmin = codigoAdmin;
		this.usuarioAdmin = usuarioAdmin;
	}

	@Id
	@GeneratedValue
	@Column (name = "codigo_admin")
	public int getCodigoAdmin() {
		return codigoAdmin;
	}

	public void setCodigoAdmin(int codigoAdmin) {
		this.codigoAdmin = codigoAdmin;
	}
	
	@OneToOne
	@JoinColumn (name = "id_usuario")
	public Usuario getUsuarioAdmin() {
		return usuarioAdmin;
	}

	public void setUsuarioAdmin(Usuario usuarioAdmin) {
		this.usuarioAdmin = usuarioAdmin;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + codigoAdmin;
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
		Admin other = (Admin) obj;
		if (codigoAdmin != other.codigoAdmin)
			return false;
		return true;
	}
	
	
	
	

}
