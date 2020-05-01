package br.com.edson.Model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table (name = "pessoa")
public abstract class Pessoa implements Serializable {

	private static final long serialVersionUID = 1L;
	private Date dataNascimento;
	private int idPessoa;
	private String nomeCompleto;
	private String tipoAcesso;
	
	public Pessoa() {
		super();
	}

	public Pessoa(Date dataNascimento, int idPessoa, String nomeCompleto, String tipoAcesso) {
		super();
		this.dataNascimento = dataNascimento;
		this.idPessoa = idPessoa;
		this.nomeCompleto = nomeCompleto;
		this.tipoAcesso = tipoAcesso;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public int getId() {
		return idPessoa;
	}

	public void setId(int idPessoa) {
		this.idPessoa = idPessoa;
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public String getTipoAcesso() {
		return tipoAcesso;
	}

	public void setTipoAcesso(String tipoAcesso) {
		this.tipoAcesso = tipoAcesso;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idPessoa;
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
		Pessoa other = (Pessoa) obj;
		if (idPessoa != other.idPessoa)
			return false;
		return true;
	}
	
	
	
}
