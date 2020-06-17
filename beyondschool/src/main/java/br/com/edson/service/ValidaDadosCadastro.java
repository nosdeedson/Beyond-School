package br.com.edson.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import br.com.edson.repository.TurmasBD;

public class ValidaDadosCadastro implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private TurmasBD turmasBD;
	
	@Inject
	public ValidaDadosCadastro() {}
	
	public void validarCodigo( String codigo) throws NegocioException {
		List<String> codigos = new ArrayList<String>();
		
		codigos = turmasBD.buscaCodigos();
		
		for (String codigoTurma : codigos) {
			if(codigoTurma.equals(codigo))
				throw new NegocioException("Código inválido");
		}
	}

}
