package br.com.edson.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import br.com.edson.Model.Aluno;
import br.com.edson.Model.Turma;
import br.com.edson.repository.AlunosBD;

@FacesConverter(forClass = Aluno.class)
public class AlunoConverter implements Converter {

	@Inject
	private AlunosBD alunosBD;
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Aluno aluno = null;
		
		if( value != null && !value.equals("")) {
			aluno = alunosBD.porId(new Long(value));
		}
		
		return aluno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Aluno aluno = (Aluno) value;
			return aluno.getIdPessoa() == null ? null : aluno.getIdPessoa().toString();
		}
		return null;
	}

}
