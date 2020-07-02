package br.com.edson.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import br.com.edson.Model.Funcionario;
import br.com.edson.repository.FuncionariosBD;

@FacesConverter(forClass = Funcionario.class)
public class FuncionarioConverter implements Converter {
	
	@Inject
	private FuncionariosBD funcionariosBD;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		
		Funcionario funcionario = null;
		
		if(value != null && !value.equals("")) {
			funcionario = funcionariosBD.porId( new Long(value));
		}
		
		return funcionario;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		
		if(value != null) {
			Funcionario funcionario = (Funcionario) value;
			
			return funcionario.getIdPessoa() == null ? null : funcionario.getIdPessoa().toString();
		}
		
		return null;
	}

}
