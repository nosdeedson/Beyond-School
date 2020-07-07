package br.com.edson.Model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "comentario")
public class Comentario implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long idComentario;
	private String comentario;
	private Long idPessoaQueFez;
	private Date dataComentario;
	private Avaliacao avaliacao;
	
	public Comentario() {}

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
	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name = "data_comentario", nullable = false)
	public Date getDataComentario() {
		return dataComentario;
	}

	public void setDataComentario(Date dataComentario) {
		this.dataComentario = dataComentario;
	}
	
	@Column(name = "id_pessoa_pessoa_que_fez")
	public Long getIdPessoaQueFez() {
		return idPessoaQueFez;
	}

	public void setIdPessoaQueFez(Long idPessoaQueFez) {
		this.idPessoaQueFez = idPessoaQueFez;
	}

	@ManyToOne
	@JoinColumn(name = "id_avaliacao")
	public Avaliacao getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(Avaliacao avaliacao) {
		this.avaliacao = avaliacao;
	}
	
	

}
