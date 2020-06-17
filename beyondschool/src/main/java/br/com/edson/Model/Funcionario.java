package br.com.edson.Model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "funcionario")
public class Funcionario extends Pessoa implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private PapelEnum tipoAcesso;

	@Enumerated(EnumType.STRING)
	@Column(name = "tipo_acesso", nullable = true)
	public PapelEnum getTipoAcesso() {
		return tipoAcesso;
	}

	public void setTipoAcesso(PapelEnum tipoAcesso) {
		this.tipoAcesso = tipoAcesso;
	}

}
