package br.com.edson.Model;

public enum ConceitoEnum {
	
	BAd ("Bad"), REGULAR ("Regular"), GOOD ("Good"), VERYGOOD ("Very Good"), EXCELLENT ("Excellent");
	
	private String descricao;

	private ConceitoEnum(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
	
}
