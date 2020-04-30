package br.com.edson.Model;

import java.io.Serializable;
import java.util.Date;

public abstract class Pessoa implements Serializable {

	private static final long serialVersionUID = 1L;
	private Date dataNascimento;
	private int id;
	private String nomeCompleto;
	private String tipoAcesso;
	
	public Pessoa() {
		super();
	}

	public Pessoa(Date dataNascimento, int id, String nomeCompleto, String tipoAcesso) {
		super();
		this.dataNascimento = dataNascimento;
		this.id = id;
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
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
		result = prime * result + id;
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
		if (id != other.id)
			return false;
		return true;
	}
	
	
	
}
