package br.com.edson.Model;

public enum ConceitoEnum {
	
	BAd ("Bad"), REGULAR ("Regular"), GOOD ("Good"), VERYGOOD ("Very Good"), EXCELLENT ("Excellent");
	
	private String descrição;

	private ConceitoEnum(String descrição) {
		this.descrição = descrição;
	}

	public String getDescrição() {
		return descrição;
	}
	
}
