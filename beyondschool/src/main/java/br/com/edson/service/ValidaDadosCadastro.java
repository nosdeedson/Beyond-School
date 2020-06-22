package br.com.edson.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.edson.repository.TurmasBD;

public class ValidaDadosCadastro implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private TurmasBD turmasBD;
	
	@Inject
	public ValidaDadosCadastro() {}
	
	/**
	 * verifica se o codigo informado pelo usuário existe
	 * @param codigo
	 * @throws NegocioException
	 */
	public boolean validarCodigo( String codigo) throws NegocioException {
				
		boolean flag = turmasBD.verificaCodigoGerado(codigo);
		if(!flag) {
			throw new NegocioException("Código inválido!! Digite novamente.");
		}
		return flag;
	}
	
	/**
	 * verifica se as senhas digitadas são iguais
	 * @param senha
	 * @param confirmeSenha
	 * @throws NegocioException
	 */
	public void verificaSenha(String senha, String confirmeSenha)throws NegocioException{
		
		if(!senha.equals(confirmeSenha)) {
			throw new NegocioException("Senhas não conferem!! Digite novamente.");
		}
	}
	
	/**
	 * cria o nome de usuário com o primeiro e o sobrenome unidos por ponto
	 * @param nome
	 * @return
	 */
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
