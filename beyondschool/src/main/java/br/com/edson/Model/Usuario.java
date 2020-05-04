package br.com.edson.Model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.swing.JPasswordField;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.primefaces.component.password.Password;

@Entity
@Table(name = "usuario")
public class Usuario implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String codigoTurma;
	private int idUsuario;
	private String nomeUsuario;
	private JPasswordField senha;
	private String tipoAcesso;
	
	public Usuario() {
		super();
	}

	public Usuario(int idUsuario, String codigoTurma, String nomeUsuario, JPasswordField senha, String tipoAcesso) {
		super();
		this.idUsuario = idUsuario;
		this.codigoTurma = codigoTurma;
		this.nomeUsuario = nomeUsuario;
		this.senha = senha;
		this.tipoAcesso = tipoAcesso;
	}
	
	@NotEmpty
	@Column(name = "codigo_turma", length = 8, nullable = false)
	public String getCodigoTurma() {
		return codigoTurma;
	}

	public void setCodigoTurma(String codigoTurma) {
		this.codigoTurma = codigoTurma;
	}

	@Id
	@GeneratedValue
	@Column(name = "id_usuario")
	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	
	@NotEmpty
	@Column(name = "nome_usuario", length = 25, nullable = false)
	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}
	
	@NotEmpty
	@Size (min = 8, max = 20)
	@Column (name = "senha", length = 20, nullable = false)
	public JPasswordField getSenha() {
		return senha;
	}

	public void setSenha(JPasswordField senha) {
		this.senha = senha;
	}

	@NotEmpty
	@Column(name = "tipo_acesso", length = 15, nullable = false)
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
