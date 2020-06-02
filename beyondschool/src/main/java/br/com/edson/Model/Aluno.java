package br.com.edson.Model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "aluno", uniqueConstraints = @UniqueConstraint( columnNames = { "matricula"}))
@PrimaryKeyJoinColumn(name = "id_pessoa")
public class Aluno extends Pessoa implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private List<Avaliacao> avaliacoes;
	private int matricula;
	private List<Responsavel> responsaveis;
	private Turma turma;
	private Usuario usuarioAluno;

	
	
}
