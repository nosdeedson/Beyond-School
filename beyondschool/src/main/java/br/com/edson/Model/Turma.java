package br.com.edson.Model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "turma")
public class Turma implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String codigoTurma;
	private String nomeLivro;
	private String nomeTurma;
	private Date horario;
	private Date horario2;
	private String primeiroDiaSemana;
	private String segundoDiaSemana;
	private Funcionario professor;
	
	public Turma() { }

	@Id
	@Column(name = "codigo_turma", nullable = false, length = 8)
	public String getCodigoTurma() {
		return codigoTurma;
	}

	public void setCodigoTurma(String codigoTurma) {
		this.codigoTurma = codigoTurma;
	}

	@Column(name = "nome_livro", length = 15, nullable = false)
	public String getNomeLivro() {
		return nomeLivro;
	}

	public void setNomeLivro(String nomeLivro) {
		this.nomeLivro = nomeLivro;
	}
	
	@Column(name = "nome_turma", length = 10, nullable = false)
	public String getNomeTurma() {
		return nomeTurma;
	}

	public void setNomeTurma(String nomeTurma) {
		this.nomeTurma = nomeTurma;
	}

	@Temporal(TemporalType.TIME)
	@Column(name = "horario",nullable = false)
	public java.util.Date getHorario() {
		return horario;
	}
	
	public void setHorario(java.util.Date horario) {
		this.horario = horario;
	}

	@Temporal(TemporalType.TIME)
	@Column(name = "horario2",nullable = false)
	public Date getHorario2() {
		return horario2;
	}

	public void setHorario2(Date horario2) {
		this.horario2 = horario2;
	}

	@Column(name = "primeiro_dia_semana", nullable = false)
	public String getPrimeiroDiaSemana() {
		return primeiroDiaSemana;
	}

	public void setPrimeiroDiaSemana(String primeiroDiaSemana) {
		this.primeiroDiaSemana = primeiroDiaSemana;
	}

	@Column(name = "segundo_dia_semana", nullable = false)
	public String getSegundoDiaSemana() {
		return segundoDiaSemana;
	}

	public void setSegundoDiaSemana(String segundoDiaSemana) {
		this.segundoDiaSemana = segundoDiaSemana;
	}

	@OneToOne (optional = false)
	@JoinColumn(name = "id_professor")
	public Funcionario getProfessor() {
		return professor;
	}

	public void setProfessor(Funcionario professor) {
		this.professor = professor;
	}
	
	
	
	
}
