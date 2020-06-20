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
		boolean flag = false;
		for (String codigoTurma : codigos) {
			if(codigoTurma.equals(codigo)) {
				flag = true;
			}
		}
		if(!flag) {
			throw new NegocioException("Código inválido!! Digite novamente.");
		}		
	}
	
	public void verificaSenha(String senha, String confirmeSenha)throws NegocioException{
		
		if(!senha.equals(confirmeSenha)) {
			throw new NegocioException("Senhas não conferem!! Digite novamente.");
		}
	}
	
	public String criaNomeUsuario(String nome) {
		
		int primeiroEspaco = nome.indexOf(" ");
		
		int ultimoEspaco = nome.lastIndexOf(" ");
		
		int tm = nome.length();
		
		String nomeUser = nome.substring(0, primeiroEspaco);
		
		nomeUser += ".";
		
		nomeUser += nome.substring(ultimoEspaco+1, tm);
		
		return nomeUser;
	}

}
