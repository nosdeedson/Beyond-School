package br.com.edson.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "avaliacao")
public class Avaliacao implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long idAvaliacao;
	private Aluno aluno;
	private Bimestre bimestre;
	private List<Comentario> comentarios = new ArrayList<Comentario>();
	private ConceitoEnum escrevendo;
	private ConceitoEnum escutando;
	private ConceitoEnum falando;
	private ConceitoEnum gramatica;
	private ConceitoEnum homeWork;
	private ConceitoEnum leitura;
	private ConceitoEnum vocabulario;
	
	
	public Avaliacao() { }
	
	@Id
	@GeneratedValue
	@Column(name = "id_avaliacao")
	public Long getIdAvaliacao() {
		return idAvaliacao;
	}
	public void setIdAvaliacao(Long idAvaliacao) {
		this.idAvaliacao = idAvaliacao;
	}
	
	@ManyToOne
	@JoinColumn( name = "id_aluno")
	public Aluno getAluno() {
		return aluno;
	}
	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}
	
	@OneToOne
	@JoinColumn(name = "id_bimestre")
	public Bimestre getBimestre() {
		return bimestre;
	}
	public void setBimestre(Bimestre bimestre) {
		this.bimestre = bimestre;
	}
	
	@Enumerated(EnumType.STRING)
	@Column(name = "escrevendo", nullable = false)
	public ConceitoEnum getEscrevendo() {
		return escrevendo;
	}
	public void setEscrevendo(ConceitoEnum escrevendo) {
		this.escrevendo = escrevendo;
	}
	
	
	@Enumerated(EnumType.STRING)
	@Column(name = "escutando", nullable = false)
	public ConceitoEnum getEscutando() {
		return escutando;
	}
	public void setEscutando(ConceitoEnum escutando) {
		this.escutando = escutando;
	}
	

	@Enumerated(EnumType.STRING)
	@Column(name = "falando", nullable = false)
	public ConceitoEnum getFalando() {
		return falando;
	}
	public void setFalando(ConceitoEnum falando) {
		this.falando = falando;
	}
	
	@Enumerated(EnumType.STRING)
	@Column(name = "gramatica", nullable = false)
	public ConceitoEnum getGramatica() {
		return gramatica;
	}
	public void setGramatica(ConceitoEnum gramatica) {
		this.gramatica = gramatica;
	}
	
	@Enumerated(EnumType.STRING)
	@Column(name = "homework", nullable = false)
	public ConceitoEnum getHomeWork() {
		return homeWork;
	}
	public void setHomeWork(ConceitoEnum homeWork) {
		this.homeWork = homeWork;
	}
	
	
	@Enumerated(EnumType.STRING)
	@Column(name = "leitura", nullable = false)
	public ConceitoEnum getLeitura() {
		return leitura;
	}
	public void setLeitura(ConceitoEnum leitura) {
		this.leitura = leitura;
	}
	
	
	@Enumerated(EnumType.STRING)
	@Column(name = "vocabulario", nullable = false)
	public ConceitoEnum getVocabulario() {
		return vocabulario;
	}
	public void setVocabulario(ConceitoEnum vocabulario) {
		this.vocabulario = vocabulario;
	}

	@OneToMany (mappedBy = "avaliacao")
	public List<Comentario> getComentarios() {
		return comentarios;
	}

	public void setComentarios(List<Comentario> comentarios) {
		this.comentarios = comentarios;
	}	
	
	
	
}
