package br.com.edson.Model;

import java.io.Serializable;

public class Avaliacao implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private ConceitoEnum escrita;
	private ConceitoEnum escutar;
	private ConceitoEnum falar;
	private ConceitoEnum gramatica;
	private int idAvaliacao;
	private ConceitoEnum leitura;
	private String observacao;
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

	public ConceitoEnum getEscrita() {
		return escrita;
	}

	public void setEscrita(ConceitoEnum escrita) {
		this.escrita = escrita;
	}

	public ConceitoEnum getEscutar() {
		return escutar;
	}

	public void setEscutar(ConceitoEnum escutar) {
		this.escutar = escutar;
	}

	public ConceitoEnum getFalar() {
		return falar;
	}

	public void setFalar(ConceitoEnum falar) {
		this.falar = falar;
	}

	public ConceitoEnum getGramatica() {
		return gramatica;
	}

	public void setGramatica(ConceitoEnum gramatica) {
		this.gramatica = gramatica;
	}

	public int getIdAvaliacao() {
		return idAvaliacao;
	}

	public void setIdAvaliacao(int idAvaliacao) {
		this.idAvaliacao = idAvaliacao;
	}

	public ConceitoEnum getLeitura() {
		return leitura;
	}

	public void setLeitura(ConceitoEnum leitura) {
		this.leitura = leitura;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

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
