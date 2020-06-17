package br.com.edson.Model;

public enum PapelEnum {
	
	ADMIN ("Admin"), PROFESSOR ("Professor");
	
	private String descricao;

	private PapelEnum(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}


}
