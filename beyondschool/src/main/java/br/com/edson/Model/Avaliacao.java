package br.com.edson.Model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "avaliacao")
public class Avaliacao implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Aluno aluno;
	private ConceitoEnum escrita;
	private ConceitoEnum escutar;
	private ConceitoEnum falar;
	private ConceitoEnum gramatica;
	private int idAvaliacao;
	private ConceitoEnum leitura;
	private String observacao;
	private Professor professor;
	private ConceitoEnum vocabulario;
	
	public Avaliacao() {
		super();
	}

	public Avaliacao(ConceitoEnum escrita, ConceitoEnum escutar, ConceitoEnum falar, ConceitoEnum gramatica,
			int idAvaliacao, ConceitoEnum leitura, String observacao, ConceitoEnum vocabulario) {
		super();
		this.escrita = escrita;
		this.escutar = escutar;
		this.falar = falar;
		this.gramatica = gramatica;
		this.idAvaliacao = idAvaliacao;
		this.leitura = leitura;
		this.observacao = observacao;
		this.vocabulario = vocabulario;
	}
	
	@NotNull
	@OneToOne
	@JoinColumn(name = "matricula_aluno")
	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	public ConceitoEnum getEscrita() {
		return escrita;
	}

	public void setEscrita(ConceitoEnum escrita) {
		this.escrita = escrita;
	}
	
	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	public ConceitoEnum getEscutar() {
		return escutar;
	}

	public void setEscutar(ConceitoEnum escutar) {
		this.escutar = escutar;
	}

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	public ConceitoEnum getFalar() {
		return falar;
	}

	public void setFalar(ConceitoEnum falar) {
		this.falar = falar;
	}
	
	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	public ConceitoEnum getGramatica() {
		return gramatica;
	}

	public void setGramatica(ConceitoEnum gramatica) {
		this.gramatica = gramatica;
	}
	
	@Id
	@GeneratedValue
	@Column(name = "id_avaliacao")
	public int getIdAvaliacao() {
		return idAvaliacao;
	}

	public void setIdAvaliacao(int idAvaliacao) {
		this.idAvaliacao = idAvaliacao;
	}
	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	public ConceitoEnum getLeitura() {
		return leitura;
	}
	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	public void setLeitura(ConceitoEnum leitura) {
		this.leitura = leitura;
	}
	
	@NotEmpty
	@Column(name = "observacao", length = 400, nullable = false)
	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	
	@OneToOne
	@JoinColumn(name = "registro_professor")
	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	public ConceitoEnum getVocabulario() {
		return vocabulario;
	}

	public void setVocabulario(ConceitoEnum vocabulario) {
		this.vocabulario = vocabulario;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idAvaliacao;
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
		Avaliacao other = (Avaliacao) obj;
		if (idAvaliacao != other.idAvaliacao)
			return false;
		return true;
	}
	
	
	
	
}
