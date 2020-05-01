package br.com.edson.Model;

import java.io.Serializable;

import org.primefaces.component.password.Password;

public class Usuario implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String codigoTurma;
	private int idUsuario;
	private String nomeUsuario;
	private Password senha;
	private String tipoAcesso;
	
	public Usuario() {
		super();
	}

	public Usuario(int idUsuario, String codigoTurma, String nomeUsuario, Password senha, String tipoAcesso) {
		super();
		this.idUsuario = idUsuario;
		this.codigoTurma = codigoTurma;
		this.nomeUsuario = nomeUsuario;
		this.senha = senha;
		this.tipoAcesso = tipoAcesso;
	}

	public int getId() {
		return idUsuario;
	}

	public void setId(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getCodigoTurma() {
		return codigoTurma;
	}

	public void setCodigoTurma(String codigoTurma) {
		this.codigoTurma = codigoTurma;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public Password getSenha() {
		return senha;
	}

	public void setSenha(Password senha) {
		this.senha = senha;
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
		result = prime * result + idUsuario;
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
		Usuario other = (Usuario) obj;
		if (idUsuario != other.idUsuario)
			return false;
		return true;
	}
	
	
	
	
	
	

}
