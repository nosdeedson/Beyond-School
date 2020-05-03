package br.com.edson.Model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;


public class Admin extends Pessoa implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Usuario usuarioAdmin;
	
	public Admin() {
		super();
	}


	public Usuario getUsuarioAdmin() {
		return usuarioAdmin;
	}

	public void setUsuarioAdmin(Usuario usuarioAdmin) {
		this.usuarioAdmin = usuarioAdmin;
	}


}
