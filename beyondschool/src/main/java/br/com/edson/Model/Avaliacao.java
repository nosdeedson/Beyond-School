package br.com.edson.Model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "avaliacao")
public class Avaliacao implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long idAvaliacao;
	private Aluno aluno;
	private ConceitoEnum escrita;
	private ConceitoEnum escutar;
	private ConceitoEnum falar;
	private ConceitoEnum gramatica;
	private ConceitoEnum leitura;
	private ConceitoEnum vocabulario;
		
	
	
}
