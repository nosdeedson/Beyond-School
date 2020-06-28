package br.com.edson.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import br.com.edson.Model.Turma;
import br.com.edson.repository.TurmasBD;

@FacesConverter(forClass = Turma.class)
public class TurmaConverter implements Converter {
	
	@Inject
	TurmasBD turmasBD;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		
		Turma turma = null;
		
		if( value != null && !value.equals("")) {
			turma = turmasBD.buscaTurma(value);
		}
		
		return turma;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Turma turma = (Turma) value;
			return turma.getCodigoTurma() == null ? null : turma.getCodigoTurma().toString();
		}
		return null;
	}

}
