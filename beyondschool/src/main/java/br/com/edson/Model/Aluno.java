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

//@Entity
//@Table(name = "aluno")
//@PrimaryKeyJoinColumn(name = "id_pessoa")
public class Aluno extends Pessoa implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Usuario usuarioAluno;

	public Aluno() {
		super();
	}

//	@OneToOne
//	@JoinColumn (name = "id_aluno_usuario")
	public Usuario getUsuarioAluno() {
		return usuarioAluno;
	}

	public void setUsuarioAluno(Usuario usuarioAluno) {
		this.usuarioAluno = usuarioAluno;
	}
	
}
