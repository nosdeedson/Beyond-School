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
	
	
	private Long idUsuario;
	private String nomeUsuario;
	private JPasswordField senha;
	private String tipoAcesso;
	
	public Usuario() {
	}

	
	

}
