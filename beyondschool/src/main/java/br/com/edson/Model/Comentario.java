package br.com.edson.Model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "comentario")
public class Comentario implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long idComentario;
	private String comentario;
	private String quemFez;
	
	public Comentario() {
	}

	@Id
	@GeneratedValue
	@Column(name = "id_comentario")
	public Long getIdComentario() {
		return idComentario;
	}

	public void setIdComentario(Long idComentario) {
		this.idComentario = idComentario;
	}

	@Column(name = "comentario", nullable = true, length = 400)
	public String getComentaio() {
		return comentario;
	}

	public void setComentaio(String comentario) {
		this.comentario = comentario;
	}

	@Column(name = "quem_fez", length = 12, nullable = true)
	public String getQuemFez() {
		return quemFez;
	}

	public void setQuemFez(String quemFez) {
		this.quemFez = quemFez;
	}

}
