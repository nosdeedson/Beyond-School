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

//
//@Entity
//@Table(name = "responsavel")
//@PrimaryKeyJoinColumn(name = "id_pessoa")
public class Responsavel extends Pessoa implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Usuario usuarioResponsavel;
	
	public Responsavel() {
		super();
	}

//	@OneToOne
//	@JoinColumn (name = "id_responsavel_usuario")
	public Usuario getUsuarioResponsavel() {
		return usuarioResponsavel;
	}

	public void setUsuarioResponsavel(Usuario usuarioResponsavel) {
		this.usuarioResponsavel = usuarioResponsavel;
	}

	
	
}
